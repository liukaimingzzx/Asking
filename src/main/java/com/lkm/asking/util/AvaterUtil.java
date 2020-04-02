package com.lkm.asking.util;

import com.lkm.asking.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class AvaterUtil {
    private String uploadFolder;

    //上传文件夹路径
    String path;

    public String avaterUpload(MultipartFile file,String username) {
        PathUtil(file,username);
        return path+file.getOriginalFilename();
    }

    public void PathUtil(MultipartFile file,String username) {
        uploadFolder = "D:\\intellijIDEA\\IDEAProjects\\Asking\\avaters\\";
        //uploadFolder = "/personal/files/asking/avaters/";
        path=uploadFolder+username+"\\";
        //path=uploadFolder+username+"/";
        String[] paths = path.split("\\\\");
        //String[] paths = path.split("/");
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
                    new FileOutputStream(path + file.getOriginalFilename()));
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
