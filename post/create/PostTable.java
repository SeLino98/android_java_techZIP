package com.example.testorangapp.post;

import java.util.List;

public class PostTable {

    private String postUser;
    private int postIndex;

    private List<String> imageUrl;

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }


    //firstPage
    private String category;
    private String target;
    private String deadLine;
    private int flag;
    private String place;
    private String progress;
    private String applyLink;
    //flag default n = 0; 0은 상시 1은 기간 2는 기타
    //secondPage
    private String createDate;
    private String mainImageUrl; //정방형
    private String title;
    private String content;
    private String expireDate;

    /*
        private String walletColor;
        */
    public PostTable() {
        this.flag = 0;
        this.applyLink = "NullLink";
        this.expireDate = "NullDate";
        this.postUser = "postUser";
        this.category = "category";
        this.target = "target";
        this.deadLine = "deadLine";
        this.place = "place";
        this.progress = "progress";
        this.title = "title";
        this.content = "content";
    }
    @Override
    public String toString() {
        return "PostTable {" +
                "category = '" + category + '\'' +
                ", target = '" + target + '\'' +
                ", deadLine = '" + deadLine + '\'' +
                ", flag = '" + flag + '\'' +
                ", place = '" + place + '\'' +
                ", progress = '" + progress + '\'' +
                ", applyLink = '" + applyLink + '\'' +
                ", title = '" + title + '\'' +
                ", content = '" + content + '\'' +
                ", createDate = '" + createDate + '\'' +
                "}";
    }

    public int getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(int postIndex) {
        this.postIndex = postIndex;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getPostUser() {
        return postUser;
    }

    public void setPostUser(String postUser) {
        this.postUser = postUser;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getApplyLink() {
        return applyLink;
    }

    public void setApplyLink(String applyLink) {
        this.applyLink = applyLink;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
