package com.example.gowheely.Model;

public class NotificationModel {
    private String notiType;
    private String notiDate;
    private String notiDesc;


    public NotificationModel(String notiType, String notiDate, String notiDesc) {
        this.notiType = notiType;
        this.notiDate = notiDate;
        this.notiDesc = notiDesc;
    }

    public String getNotiType() {
        return notiType;
    }

    public void setNotiType(String notiType) {
        this.notiType = notiType;
    }

    public String getNotiDate() {
        return notiDate;
    }

    public void setNotiDate(String notiDate) {
        this.notiDate = notiDate;
    }

    public String getNotiDesc() {
        return notiDesc;
    }

    public void setNotiDesc(String notiDesc) {
        this.notiDesc = notiDesc;
    }
}
