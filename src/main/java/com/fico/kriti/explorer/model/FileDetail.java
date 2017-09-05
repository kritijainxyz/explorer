package com.fico.kriti.explorer.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KritiJain on 8/15/2017.
 */

@Entity
public class FileDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long fileId;
    private String fileName;
    private String fileType;
    private long fileSize;
    private String createdOn;
    private String modifiedOn;
    private boolean isTrashed;
    private long parentId;
    private boolean isFolder;
    private long userId;

    @OneToOne (fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Content content;

    //@JsonIgnore
    public Content getContent(){
        return content;
    }

    public void setContent(Content content){
        this.content = content;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<FileMetadata> metadata;

    public FileDetail(){

    }


    public List<FileMetadata> getMetadata(){
        return metadata;
    }

    public void setMetadata(FileMetadata metadata){
        if(this.metadata==null){
            this.metadata= new ArrayList<FileMetadata>();
        }
        this.metadata.add(metadata);
    }

    public FileDetail(long fileId, String fileName, String fileExtension, long fileSize, String createdOn, String modifiedOn, boolean isTrashed, long parentId, long userId, boolean isFolder, boolean isStarred, boolean isImportant, boolean isPersonal) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileExtension;
        this.fileSize = fileSize;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.isTrashed = isTrashed;
        this.parentId = parentId;
        this.isFolder = isFolder;
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "FileDetail{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                ", isTrashed=" + isTrashed +
                ", parentId=" + parentId +
                ", isFolder=" + isFolder +
                ", userId=" + userId +
                '}';
    }


    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public boolean isTrashed() {
        return isTrashed;
    }

    public void setTrashed(boolean trashed) {
        isTrashed = trashed;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
