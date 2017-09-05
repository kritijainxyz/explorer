package com.fico.kriti.explorer.model;

import javax.persistence.*;

/**
 * Created by KritiJain on 8/21/2017.*/


@Entity
public class FileMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long metadataId;
    private long sharedUserId;
    private long fileId;
    private long ownerId;
    private long permission;
    private boolean isImportant;
    private boolean isStarred;
    private boolean isPersonal;

    public FileMetadata() {
    }

    public FileMetadata(long sharedUserId, long fileId, long ownerId, long permission, boolean isImportant, boolean isStarred, boolean isPersonal) {
        this.sharedUserId = sharedUserId;
        this.fileId = fileId;
        this.ownerId = ownerId;
        this.permission = permission;
        this.isImportant = isImportant;
        this.isStarred = isStarred;
        this.isPersonal = isPersonal;
    }

    @Override
    public String toString() {
        return "FileMetadata{" +
                "metadataId=" + metadataId +
                ", sharedUserId=" + sharedUserId +
                ", fileId=" + fileId +
                ", ownerId=" + ownerId +
                ", permission=" + permission +
                ", isImportant=" + isImportant +
                ", isStarred=" + isStarred +
                ", isPersonal=" + isPersonal +
                '}';
    }

    public long getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(long metadataId) {
        this.metadataId = metadataId;
    }

    public long getSharedUserId() {
        return sharedUserId;
    }

    public void setSharedUserId(long sharedUserId) {
        this.sharedUserId = sharedUserId;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getPermission() {
        return permission;
    }

    public void setPermission(long permission) {
        this.permission = permission;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isStarred() {
        return isStarred;
    }

    public void setStarred(boolean starred) {
        isStarred = starred;
    }

    public boolean isPersonal() {
        return isPersonal;
    }

    public void setPersonal(boolean personal) {
        isPersonal = personal;
    }


}
