package com.xiuluo.model.aboutUs;

public class AboutUs {
    private Integer aboutId;

    private String aboutContent;

    private Integer aboutStatus;

    private String aboutBackup;

    public Integer getAboutId() {
        return aboutId;
    }

    public void setAboutId(Integer aboutId) {
        this.aboutId = aboutId;
    }

    public String getAboutContent() {
        return aboutContent;
    }

    public void setAboutContent(String aboutContent) {
        this.aboutContent = aboutContent == null ? null : aboutContent.trim();
    }

    public Integer getAboutStatus() {
        return aboutStatus;
    }

    public void setAboutStatus(Integer aboutStatus) {
        this.aboutStatus = aboutStatus;
    }

    public String getAboutBackup() {
        return aboutBackup;
    }

    public void setAboutBackup(String aboutBackup) {
        this.aboutBackup = aboutBackup == null ? null : aboutBackup.trim();
    }
}