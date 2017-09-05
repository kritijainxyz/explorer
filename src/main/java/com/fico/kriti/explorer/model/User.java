package com.fico.kriti.explorer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KritiJain on 8/15/2017.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String userName;
    private String password;
    private long contactNumber;
    private String emailId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<FileDetail> fileDetail;


    public User(){

    }

    public List<FileDetail> getFileDetails(){
        return fileDetail;
    }

    public void setFileDetail(FileDetail fileDetail){
        if(this.fileDetail==null){
            this.fileDetail= new ArrayList<FileDetail>();
        }
        this.fileDetail.add(fileDetail);
    }

    public void deleteFileDetail(FileDetail fileDetail){
        if(this.fileDetail!=null && this.fileDetail.contains(fileDetail)){
            this.fileDetail.remove(fileDetail);
        }
    }


    public User(long userId, String userName, String password, long contactNumber, String emailId) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.contactNumber = contactNumber;
        this.emailId = emailId;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", contactNumber=" + contactNumber +
                ", emailId='" + emailId + '\'' +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
