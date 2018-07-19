package com.taowei.opinions.data.dbdata;

public class SQLInfo {

    private String userID;//用户
    private String taskID;//任务
    private String url; //下载链接
    private String filePath; //路径
    private String fileName; //
    private long fileSize;
    private long downloadSize;

    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getTaskID() {
        return taskID;
    }
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public long getFileSize() {
        return fileSize;
    }
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    public long getDownloadSize() {
        return downloadSize;
    }
    public void setDownloadSize(long downloadSize) {
        this.downloadSize = downloadSize;
    }

    @Override
    public String toString() {
        return "userID="+userID+";taskID="+taskID+";url="+url+";filePath="+filePath+";fileName="+fileName+";fileSize="+fileSize+";downloadSize="+downloadSize;
    }
}
