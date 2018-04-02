package com.fico.kriti.explorer.services.serviceImpl;

import com.fico.kriti.explorer.model.Content;
import com.fico.kriti.explorer.model.FileDetail;
import com.fico.kriti.explorer.model.FileMetadata;
import com.fico.kriti.explorer.model.User;
import com.fico.kriti.explorer.repositories.ContentRepository;
import com.fico.kriti.explorer.repositories.FileRepository;
import com.fico.kriti.explorer.repositories.MetadataRepository;
import com.fico.kriti.explorer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Created by KritiJain on 8/17/2017.
 */
@Service
public class FileServiceImpl {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MetadataRepository metadataRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserServiceImpl userService;

    public User createFile(long userId, String fileName) {
        FileDetail obj = new FileDetail();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(new Date());
        obj.setCreatedOn(formattedDate);
        obj.setModifiedOn(formattedDate);
        obj.setFileName(fileName);
        obj.setFileType("application/text");
        obj.setUserId(userId);

        Content content = new Content();
        //content.setFileId(obj.getFileId());
        content.setFileId(0);
        content.setFileContent(null);
        obj.setContent(content);
        contentRepository.save(content);
        fileRepository.save(obj);

        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFileId(obj.getFileId());
        fileMetadata.setOwnerId(userId);
        fileMetadata.setSharedUserId(userId);
        fileMetadata.setPermission(3);
        obj.setMetadata(fileMetadata);
        metadataRepository.save(fileMetadata);
        fileRepository.save(obj);

        User user = userService.findUserById(userId);
        /*Content content = new Content();
        content.setFileContent(null);
//        obj.setFileContent(content);*/


        user.setFileDetail(obj);
        userRepository.save(user);

        return user;
    }

    public List<FileDetail> findAllOwnedFilesByUser(long userId) {
        User user = userService.findUserById(userId);
        List<FileDetail> files = user.getFileDetails();
        List<FileDetail> result = new ArrayList<FileDetail>();
        for (FileDetail file : files) {
            if (file.isTrashed() == false) {
                List<FileMetadata> metadata = file.getMetadata();
                for (FileMetadata meta : metadata) {
                    if (meta.getSharedUserId() == userId && meta.getOwnerId() == userId)
                        result.add(file);
                }
            }
        }
        return result;
    }

    public List<FileDetail> findAllFilesByUser(long userId) {
        User user = userService.findUserById(userId);
        List<FileDetail> files = user.getFileDetails();
        List<FileDetail> result = new ArrayList<FileDetail>();
        for (FileDetail file : files) {
            if (file.isTrashed() == false) {
                List<FileMetadata> metadata = file.getMetadata();
                for (FileMetadata meta : metadata) {
                    if (meta.getSharedUserId() == userId)
                        result.add(file);
                }
            }
        }
        return result;
    }

    public List<FileDetail> findSharedFilesWithUser(long userId) {
        User user = userService.findUserById(userId);
        List<FileDetail> files = user.getFileDetails();
        List<FileDetail> result = new ArrayList<FileDetail>();
        for (FileDetail file : files) {
            if (file.isTrashed() == false) {
                List<FileMetadata> metadata = file.getMetadata();
                for (FileMetadata meta : metadata) {
                    if (meta.getSharedUserId() == userId && meta.getOwnerId() != userId)
                        result.add(file);
                }
            }
        }
        return result;
    }

    public FileDetail findByFileIdAndUserId(long fileId, long userId) {
        User user = userService.findUserById(userId);

        return fileRepository.findByFileIdAndUserId(fileId, userId);
    }

    public void permanentDelete(long[] fileIds, long userId) {
        //System.out.println(fileId);
        User user = userService.findUserById(userId);

        for(int i=0;i<fileIds.length;i++) {
            FileDetail obj = fileRepository.findByFileIdAndUserId(fileIds[i], userId);
            fileRepository.deleteByFileIdAndUserId(fileIds[i], userId);

            user.deleteFileDetail(obj);
        }
        userRepository.save(user);
    }

    //to be done
    public void createCopy(long fileId, long userId) {
        FileDetail obj = fileRepository.findByFileIdAndUserId(fileId, userId);
        FileDetail newObj = new FileDetail();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(new Date());
        /*
        newObj.setFileName("Copy of " + obj.getFileName());
        newObj.setFileId(0);
        newObj.setCreatedOn(formattedDate);
        newObj.setModifiedOn(formattedDate);
        fileRepository.save(newObj);*/

        copyProperties(obj, newObj);
        newObj.setFileId(0);
        newObj.setCreatedOn(formattedDate);
        newObj.setModifiedOn(formattedDate);
        newObj.setFileName("Copy of " + obj.getFileName());

        Content content = new Content();
        content.setFileId(0);
        content.setFileContent(obj.getContent().getFileByteContent());
        newObj.setContent(content);
        contentRepository.save(content);
        fileRepository.save(newObj);


        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFileId(newObj.getFileId());
        fileMetadata.setOwnerId(userId);
        fileMetadata.setSharedUserId(userId);
        fileMetadata.setPermission(3);
        newObj.setMetadata(fileMetadata);
        metadataRepository.save(fileMetadata);
        fileRepository.save(newObj);

        User user = userService.findUserById(userId);
        user.setFileDetail(newObj);
        userRepository.save(user);
    }

    public void move(long fileId, long userId, long parentId) {
        FileDetail obj = fileRepository.findByFileIdAndUserId(fileId, userId);
        obj.setParentId(parentId);
        fileRepository.save(obj);

    }

    public void moveToTrash(long fileId, long userId) {
        FileDetail obj = fileRepository.findByFileIdAndUserId(fileId, userId);
        obj.setTrashed(true);
        fileRepository.save(obj);

    }

    public void restore(long fileId, long userId) {
        FileDetail obj = fileRepository.findByFileIdAndUserId(fileId, userId);
        obj.setTrashed(false);
        fileRepository.save(obj);

    }

    public void rename(long fileId, long userId, String newName) {
        FileDetail obj = fileRepository.findByFileIdAndUserId(fileId, userId);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(new Date());
        obj.setModifiedOn(formattedDate);
        obj.setFileName(newName);
        fileRepository.save(obj);
    }

    public void tagStar(long fileId, long sharedUserId) {
        //FileDetail obj = fileRepository.findByFileIdAndUserId(fileId, sharedUserId);
        FileMetadata obj = metadataRepository.findByFileIdAndAndSharedUserId(fileId, sharedUserId);
        if (obj.isStarred() == false) {
            obj.setStarred(true);
        } else {
            obj.setStarred(false);
        }
        metadataRepository.save(obj);
    }

    public void tagImportant(long fileId, long sharedUserId) {
        FileMetadata obj = metadataRepository.findByFileIdAndAndSharedUserId(fileId, sharedUserId);
        if (obj.isImportant() == false) {
            obj.setImportant(true);
        } else {
            obj.setImportant(false);
        }
        metadataRepository.save(obj);
    }

    public void tagPersonal(long fileId, long sharedUserId) {
        FileMetadata obj = metadataRepository.findByFileIdAndAndSharedUserId(fileId, sharedUserId);
        if (obj.isPersonal() == false) {
            obj.setPersonal(true);
        } else {
            obj.setPersonal(false);
        }
        metadataRepository.save(obj);
    }

    public void share(long fileId, long userId, long sharedUserId, long permission) {
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFileId(fileId);
        fileMetadata.setOwnerId(userId);
        fileMetadata.setSharedUserId(sharedUserId);
        fileMetadata.setPermission(permission);
        FileDetail obj = findByFileIdAndUserId(fileId, userId);
        obj.setMetadata(fileMetadata);
        metadataRepository.save(fileMetadata);
        fileRepository.save(obj);

        User user = userService.findUserById(sharedUserId);
        user.setFileDetail(obj);
        userRepository.save(user);
    }

    public List<FileDetail> searchTrashed(long userId) {
        User user = userService.findUserById(userId);
        List<FileDetail> files = user.getFileDetails();
        List<FileDetail> result = new ArrayList<FileDetail>();
        for (FileDetail file : files) {
            if (file.isTrashed() == true)
                result.add(file);
        }
        return result;
    }


    public List<FileDetail> searchStarred(long userId) {
        User user = userService.findUserById(userId);
        List<FileDetail> files = user.getFileDetails();
        List<FileDetail> result = new ArrayList<FileDetail>();
        for (FileDetail file : files) {
            if (file.isTrashed() == false) {
                List<FileMetadata> metadata = file.getMetadata();
                for (FileMetadata meta : metadata) {
                    if (meta.getSharedUserId() == userId && meta.isStarred() == true)
                        result.add(file);
                }
            }
        }
        return result;
    }

    public List<FileDetail> searchImportant(long userId) {
        User user = userService.findUserById(userId);
        List<FileDetail> files = user.getFileDetails();
        List<FileDetail> result = new ArrayList<FileDetail>();
        for (FileDetail file : files) {
            if (file.isTrashed() == false) {
                List<FileMetadata> metadata = file.getMetadata();
                for (FileMetadata meta : metadata) {
                    if (meta.getSharedUserId() == userId && meta.isImportant() == true)
                        result.add(file);
                }
            }
        }
        return result;
    }

    public List<FileDetail> searchPersonal(long userId) {
        User user = userService.findUserById(userId);
        List<FileDetail> files = user.getFileDetails();
        List<FileDetail> result = new ArrayList<FileDetail>();
        for (FileDetail file : files) {
            if (file.isTrashed() == false) {
                List<FileMetadata> metadata = file.getMetadata();
                for (FileMetadata meta : metadata) {
                    if (meta.getSharedUserId() == userId && meta.isPersonal() == true)
                        result.add(file);
                }
            }
        }
        return result;
    }

    public List<FileDetail> searchFilename(long userId, String fileName) {
        User user = userService.findUserById(userId);
        List<FileDetail> files = user.getFileDetails();
        List<FileDetail> result = new ArrayList<FileDetail>();
        for (FileDetail file : files) {
            if (file.isTrashed() == false) {
                if (file.getFileName().equalsIgnoreCase(fileName))
                    result.add(file);
            }
        }
        return result;
    }

    public void saveContent(long fileId, long userId, String content) {
        FileDetail file = fileRepository.findByFileIdAndUserId(fileId, userId);
        //Content con= contentRepository.findByFileId(fileId);
        Content con=file.getContent();
        byte[] byteContent = content.getBytes(Charset.forName("UTF-8"));
        con.setFileContent(byteContent);
        file.setFileSize(byteContent.length);
        contentRepository.save(con);
        fileRepository.save(file);
    }

    public void upload(long userId, MultipartFile file){
        FileDetail obj = new FileDetail();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(new Date());
        obj.setCreatedOn(formattedDate);
        obj.setModifiedOn(formattedDate);
        obj.setFileName(file.getOriginalFilename());
        obj.setFileType(file.getContentType());
        obj.setFileSize(file.getSize());
        obj.setUserId(userId);

        Content content = new Content();
        //content.setFileId(obj.getFileId());
        content.setFileId(0);
        try {
            content.setFileContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        obj.setContent(content);
        contentRepository.save(content);
        fileRepository.save(obj);

        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFileId(obj.getFileId());
        fileMetadata.setOwnerId(userId);
        fileMetadata.setSharedUserId(userId);
        fileMetadata.setPermission(3);
        obj.setMetadata(fileMetadata);
        metadataRepository.save(fileMetadata);
        fileRepository.save(obj);

        User user = userService.findUserById(userId);

        user.setFileDetail(obj);
        userRepository.save(user);
    }

    /*public void download(long userId, long fileId, final HttpServletResponse response) throws IOException {
        FileDetail file = fileRepository.findByFileIdAndUserId(fileId, userId);
        byte[] byteContent=file.getContent().getFileContent();

        File someFile = new File(file.getFileName());
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(byteContent);
        fos.flush();
        fos.close();

        response.setHeader("Pragma", "No-cache");
        // forces caches to submit the request to the origin server for
        // validation before releasing a cached copy, every time
        //response.setDateHeader("Expires", this.createCacheExpiryDate().getTime());
        response.setHeader("Cache-Control", "no-cache");
        //response.setContentType("image/jpeg");


        final InputStream inStream = new ByteArrayInputStream(byteContent);
        final ServletOutputStream outStream = response.getOutputStream();
        final byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, len);
        }
        outStream.flush();
    }*/

    /*public void download(long userId, long fileId, final HttpServletResponse response) throws IOException {
        FileDetail file = fileRepository.findByFileIdAndUserId(fileId, userId);
        byte[] byteContent=file.getContent().getFileContent();
        try {
            // get your file as InputStream
            InputStream is = new ByteArrayInputStream(byteContent);
            // copy it to response's OutputStream
            response.setHeader("Content-Disposition","attachment;filename =  " + file.getFileName());
            response.setContentType("application/pdf");
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            //log.info("Error writing file to output stream. Filename was '{}'", file.getFileName(), ex);
            throw new RuntimeException("IOError writing file to output stream");
        }
    }*/

    public ResponseEntity<Resource> download(long userId, long fileId, final HttpServletResponse response) throws IOException {
        FileDetail file = fileRepository.findByFileIdAndUserId(fileId, userId);
        byte[] byteContent=file.getContent().getFileByteContent();
        ByteArrayResource resource = new ByteArrayResource(byteContent);
        HttpHeaders header = new HttpHeaders();
        header.set("Content-Disposition",
                "attachment; filename=\"" + file.getFileName()+"\"");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .headers(header)
                .body(resource);
    }

    /*public User createFolder(long userId, long parentId, String fileName) {
        FileDetail obj = new FileDetail();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(new Date());
        obj.setCreatedOn(formattedDate);
        obj.setModifiedOn(formattedDate);
        obj.setFileName(fileName);
        obj.setFileType("application/text");
        obj.setUserId(userId);
        obj.setFolder(true);
        obj.setParentId(parentId);

        Content content = new Content();
        //content.setFileId(obj.getFileId());
        content.setFileId(0);
        content.setFileContent(null);
        obj.setContent(content);
        contentRepository.save(content);
        fileRepository.save(obj);

        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFileId(obj.getFileId());
        fileMetadata.setOwnerId(userId);
        fileMetadata.setSharedUserId(userId);
        fileMetadata.setPermission(3);
        obj.setMetadata(fileMetadata);
        metadataRepository.save(fileMetadata);
        fileRepository.save(obj);

        User user = userService.findUserById(userId);


        user.setFileDetail(obj);
        userRepository.save(user);

        return user;
    }*/

}

