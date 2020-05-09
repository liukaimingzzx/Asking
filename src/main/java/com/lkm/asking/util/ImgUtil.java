package com.lkm.asking.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class ImgUtil {
    private String uploadFolder;

    //上传文件夹路径
    private String path;
    private String suffix;

    public void imgUpload(MultipartFile file, String timestamp) {
        PathUtil(file,timestamp);
    }

    public void PathUtil(MultipartFile file,String timestamp) {
        //uploadFolder = "D:\\intellijIDEA\\IDEAProjects\\Asking\\avaters\\";
        String fileName = file.getOriginalFilename();
        suffix = fileName.substring(fileName.lastIndexOf("."));
        uploadFolder = "/personal/files/asking/img/";
        //path=uploadFolder+username+"\\";
        path=uploadFolder+timestamp+"/";
        //String[] paths = path.split("\\\\");
        String[] paths = path.split("/");
        StringBuffer fullPath = new StringBuffer();
        for (int i = 0; i < paths.length; i++) {
            //fullPath.append(paths[i]).append("\\");
            fullPath.append(paths[i]).append("/");
            File tempDir = new File(fullPath.toString());
            if (!tempDir.exists()) {
                tempDir.mkdir();
            }
        }
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(path + timestamp+suffix));
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
