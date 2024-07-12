package com.njau.wens_detection_api.entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class FileInfo {
    private MultipartFile[] files;
    private String module;// 模块
    private String inf; //帧隔
    private String value;// 预警值
    private String parentPath; // python项目根目录
    private String pyPath; // 待检测文件目录（.../Wens_Broiler_Detection/Folder_Detection/drop/）
    private String[] filePath;// 待检测文件路径（.../Wens_Broiler_Detection/Folder_Detection/drop/drop.mp4）
    private String note;// 警示信息
    private String resultPath;// 检测结果路径

    public FileInfo(MultipartFile[] files, String module, String inf, String value) {
        this.files = files;
        this.module = module;
        this.inf = inf;
        this.value = value;
        this.parentPath = System.getProperty("user.dir").replace("Wens_Detection_Api", "Wens_Broiler_Detection") + "/";
        this.pyPath = this.parentPath + "Folder_Detection/" + module + "/";
        this.filePath = new String[files.length];
        this.note = null;
        this.resultPath = null;
    }

    public FileInfo() {
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPyPath() {
        return pyPath;
    }

    public void setPyPath(String pyPath) {
        this.pyPath = pyPath;
    }

    public String[] getFilePath() {
        return filePath;
    }

    public void setFilePath(String[] filePath) {
        this.filePath = filePath;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "files=" + Arrays.toString(files) +
                ", module='" + module + '\'' +
                ", inf='" + inf + '\'' +
                ", value='" + value + '\'' +
                ", parentPath='" + parentPath + '\'' +
                ", pyPath='" + pyPath + '\'' +
                ", filePath=" + Arrays.toString(filePath) +
                ", note='" + note + '\'' +
                ", resultPath='" + resultPath + '\'' +
                '}';
    }
}
