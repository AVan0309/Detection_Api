package com.njau.wens_detection_api.entity;


import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseInfo {
    private String[] fileName;
    private String module;
    private String inf;
    private List<JsonNode> result;

    public ResponseInfo( String module, String inf) {
        this.module = module;
        this.result = new ArrayList<>();
        if("dead".equals(module)) {
            this.fileName = new String[2];
            this.inf = "The 'dead' module does not use the 'inf' parameter.";
        }else if("voice".equals(module)){
            this.fileName = new String[1];
            this.inf = "The 'voice' module does not use the 'inf' parameter.";
        }else {
            this.fileName = new String[1];
            this.inf = inf;
        }
    }

    public ResponseInfo() {
    }

    public String[] getFileName() {
        return fileName;
    }

    public void setFileName(String[] fileName) {
        this.fileName = fileName;
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

    public List<JsonNode> getResult() {
        return result;
    }

    public void setResult(List<JsonNode> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "fileName=" + Arrays.toString(fileName) +
                ", module='" + module + '\'' +
                ", inf='" + inf + '\'' +
                ", result=" + result +
                '}';
    }
}
