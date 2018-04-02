package com.fico.kriti.explorer.controllers;

import com.fico.kriti.explorer.model.FileDetail;
import com.fico.kriti.explorer.model.User;
import com.fico.kriti.explorer.services.serviceImpl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

/**
 * Created by KritiJain on 8/16/2017.
 */
@RestController
public class FileController {

    @Autowired
    private FileServiceImpl fileService;

    //pass fileName in the json
    //(@PathVariable("userId") long userId, @RequestBody FileDetail obj)
    @ResponseBody
    @RequestMapping(value = "/files/{userId}/filename/{fileName}", consumes = {"application/json"}, produces = {"application/json"}, method = {RequestMethod.POST})
    public User createFile(@PathVariable("userId") long userId, @PathVariable("fileName") String fileName) {
        return fileService.createFile(userId, fileName);
    }

    @ResponseBody
    @RequestMapping(value = "/files/user/ownedfiles/{userId}", produces = {"application/json"}, method = {RequestMethod.GET})
    public List<FileDetail> findAllOwnedFilesByUser(@PathVariable("userId") long userId) {
        return fileService.findAllOwnedFilesByUser(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/user/allfiles/{userId}", produces = {"application/json"}, method = {RequestMethod.GET})
    public List<FileDetail> findAllFilesByUser(@PathVariable("userId") long userId) {
        return fileService.findAllFilesByUser(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/user/sharedfiles/{userId}", produces = {"application/json"}, method = {RequestMethod.GET})
    public List<FileDetail> findSharedFilesWithUser(@PathVariable("userId") long userId) {
        return fileService.findSharedFilesWithUser(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}", produces = {"application/json"}, method = {RequestMethod.GET})
    public FileDetail findByFileIdAndUserId(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId) {
        return fileService.findByFileIdAndUserId(fileId, userId);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/files/user/{userId}/delete", method = {RequestMethod.PATCH})
    public void permanentDelete(@RequestBody long[] fileIds, @PathVariable("userId") long userId) {
        fileService.permanentDelete(fileIds, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/copy", method = {RequestMethod.POST})
    public void createCopy(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId) {
        fileService.createCopy(fileId, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/move/{parentId}", consumes = {"application/json"}, method = {RequestMethod.PATCH})
    public void move(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId, @PathVariable("userId") long parentId) {
        fileService.move(fileId, userId, parentId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/trash", method = {RequestMethod.PATCH})
    public void moveToTrash(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId) {
        fileService.moveToTrash(fileId, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/restore", method = {RequestMethod.PATCH})
    public void restore(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId) {
        fileService.restore(fileId, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/rename/{newName}", method = {RequestMethod.PATCH})
    public void rename(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId, @PathVariable("newName") String newName) {
        fileService.rename(fileId, userId, newName);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/star", method = {RequestMethod.PATCH})
    public void tagStar(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId) {
        fileService.tagStar(fileId, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/important", method = {RequestMethod.PATCH})
    public void tagImportant(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId) {
        fileService.tagImportant(fileId, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/personal", method = {RequestMethod.PATCH})
    public void tagPersonal(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId) {
        fileService.tagPersonal(fileId, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/share/{sharedUserId}/permission/{permission}", method = {RequestMethod.PATCH})
    public void share(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId, @PathVariable("sharedUserId") long sharedUserId, @PathVariable("permission") long permission) {
        fileService.share(fileId, userId, sharedUserId, permission);
    }

    @ResponseBody
    @RequestMapping(value = "/files/user/searchTrashed/{userId}", produces = {"application/json"}, method = {RequestMethod.GET})
    public List<FileDetail> searchTrashed(@PathVariable("userId") long userId) {
        return fileService.searchTrashed(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/user/searchStarred/{userId}", produces = {"application/json"}, method = {RequestMethod.GET})
    public List<FileDetail> searchStarred(@PathVariable("userId") long userId) {
        return fileService.searchStarred(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/user/searchImportant/{userId}", produces = {"application/json"}, method = {RequestMethod.GET})
    public List<FileDetail> searchImportant(@PathVariable("userId") long userId) {
        return fileService.searchImportant(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/user/searchPersonal/{userId}", produces = {"application/json"}, method = {RequestMethod.GET})
    public List<FileDetail> searchPersonal(@PathVariable("userId") long userId) {
        return fileService.searchPersonal(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/files/user/{userId}/searchFilename/{fileName}", produces = {"application/json"}, method = {RequestMethod.GET})
    public List<FileDetail> searchFilename(@PathVariable("userId") long userId, @PathVariable("fileName") String fileName) {
        return fileService.searchFilename(userId, fileName);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/saveContent", method = {RequestMethod.PATCH})
    public void saveContent(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId, @RequestBody String content) {
        fileService.saveContent(fileId, userId, content);
    }

    @ResponseBody
    @RequestMapping(value = "/files/user/{userId}/upload", method = {RequestMethod.POST})
    public void upload(@PathVariable("userId") long userId, @RequestParam MultipartFile file) {
        fileService.upload(userId, file);
    }

    @ResponseBody
    @RequestMapping(value = "/files/{fileId}/user/{userId}/download", method= {RequestMethod.GET})
    public ResponseEntity<Resource> download(@PathVariable("userId") long userId, @PathVariable("fileId") long fileId, final HttpServletResponse response) {
        try {
            return fileService.download(userId, fileId, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*@ResponseBody
    @RequestMapping(value = "/files/{userId}/parent/{parentId}/filename/{fileName}", consumes = {"application/json"}, produces = {"application/json"}, method = {RequestMethod.POST})
    public User createFolder(@PathVariable("userId") long userId, @PathVariable("parentId") long parentId, @PathVariable("fileName") String fileName) {
        return fileService.createFolder(userId, parentId, fileName);
    }
    */

}
