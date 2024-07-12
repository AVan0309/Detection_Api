package com.njau.wens_detection_api.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njau.wens_detection_api.entity.FileInfo;
import com.njau.wens_detection_api.entity.ResponseInfo;
import com.njau.wens_detection_api.service.FunctionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {

    @Override
    public ResponseInfo detection(FileInfo fileInfo, ResponseInfo responseInfo) {
        String module = fileInfo.getModule();
        MultipartFile[] files = fileInfo.getFiles();
        String[] filePath = fileInfo.getFilePath();
        if(!"dead".equals(module)) {
            MultipartFile file = files[0];
            responseInfo.setFileName(new String[]{files[0].getOriginalFilename()});
            filePath[0] = fileInfo.getPyPath() + file.getOriginalFilename();
        }else {
            String[] fileNames = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getOriginalFilename();
                String imageName = fileName.split("\\.")[0];
                fileNames[i] = fileName;
                if(!"infrared".equals(imageName) && !"rgb".equals(imageName)) {
                    fileInfo.setNote("图片命名有误，请以【infrared】和【rgb】命名热红外图像与彩色图像！");
                    break;
                }
                filePath[i] = fileInfo.getPyPath() + imageName + "/" + fileName;
            }
            responseInfo.setFileName(fileNames);
        }
        fileInfo.setFilePath(filePath);
        setDir(fileInfo);
        downloadFile(fileInfo);
        run(fileInfo);
        return response(fileInfo, responseInfo);
    }

    public ResponseInfo response(FileInfo fileInfo,ResponseInfo responseInfo) {
        File file = new File(fileInfo.getResultPath());
        List<JsonNode> jsonList = new ArrayList<>();
        //判断文件是否存在
        if (file.exists()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    if(tempString.contains("{")) {
                        // 显示行号
                        System.out.println("line " + line + ": " + tempString);
                        JsonNode jsonNode = StringToJson(tempString);
                        jsonList.add(jsonNode);
                    }
                    line++;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }
        responseInfo.setResult(jsonList);
        return responseInfo;
    }

    private void downloadFile(FileInfo fileInfo){
        MultipartFile[] files = fileInfo.getFiles();
        String[] filePath = fileInfo.getFilePath();
        for (int i = 0; i < files.length; i++) {
            try {
                files[i].transferTo(new File(filePath[i]));
                fileInfo.setNote("传输成功，检测成功！");
            } catch (IOException e) {
                fileInfo.setNote("传输失败，请重试！");
                e.printStackTrace();
            }
        }
    }

    private void run(FileInfo fileInfo) {
//         python脚本文件路径：/home/yc/Projects/Wens_Detection_System/Wens_Broiler_Detection/run.py
        String pyFilePath = "/home/yc/Projects/Wens_Detection_System/Wens_Broiler_Detection/run.py";

//         python执行需要的环境路径 ：/home/yc/SOFTWARE/ANACONDA/envs/Wens_Detection_Envs/bin/python
        String envPath = "/home/yc/SOFTWARE/ANACONDA/envs/Wens_Detection_Envs/bin/python";//
//        String envPath = "/root/miniconda3/envs/detection/bin/python";
//        String envPath = "/home/lxg/anaconda3/envs/deaction/bin/python";         // String envPath = "/home/yc/SOFTWARE/ANACONDA/envs/Wens_Detection_Envs/bin/python";
        // python执行需要的参数
        String module = fileInfo.getModule();
        String inf = fileInfo.getInf();
        String filePath = fileInfo.getPyPath();
        String value = fileInfo.getValue();
        String parentPath = fileInfo.getParentPath();
        //调用python
        try {
            //环境和main
            String[] args=new String[]{envPath, pyFilePath, "--module", module, "--filePath", filePath, "--value", value, "--inf", inf, "--parentPath", parentPath};
//            for (int i = 0; i < args.length; i++) {
//                System.out.println(args[i]);
//            }
            ProcessBuilder processBuilder = new ProcessBuilder(args);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains("txt")) {
                    fileInfo.setResultPath(line);
                }
            }
            in.close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDir(FileInfo fileInfo) {
        // 待检测文件路径：/home/yc/Projects/Wens_Detection_System/Wens_Broiler_Detection/Folder_Detection/dead/
        String[] folderPath = null;
        String module = fileInfo.getModule();
        if("dead".equals(module)){
            folderPath = new String[]{fileInfo.getPyPath()+ "infrared/", fileInfo.getPyPath()+ "rgb/"};
        }else {
            folderPath = new String[]{fileInfo.getPyPath()};
        }
        for (int i = 0; i < folderPath.length; i++) {
            File folder = new File(folderPath[i]);
            clearFolder(folder);
        }
    }

    private void clearFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                    } else if (file.isDirectory()) {
                        clearFolder(file);
                    }
                }
            }
        }
    }

    private JsonNode StringToJson (String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

}
