package com.fico.kriti.explorer.model;

import javax.persistence.*;

/**
 * Created by KritiJain on 8/15/2017.
 */

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long fileId;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] fileContent;

    public Content() {
    }

    public Content(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getFileContent() {
        if (fileContent == null) {
            return null;
        }
        return new String(fileContent);
    }

    public byte[] getFileByteContent() {
        if (fileContent == null) {
            return null;
        }
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
