package com.lkm.asking.util;


import java.io.*;

public class DefaultAvater {
    private String uploadFolder;

    //上传文件夹路径
    private String path;
    private String suffix;

    public void avaterUpload(File file, String username) {
        PathU(file,username);
    }

    public void PathU(File file,String username) {
        uploadFolder = "D:\\intellijIDEA\\IDEAProjects\\Asking\\avaters\\";
        String fileName = file.getName();
        suffix = fileName.substring(fileName.lastIndexOf("."));
        //uploadFolder = "/personal/files/asking/avaters/";
        path = uploadFolder + username + "\\";
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
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            int bytesRead = 0;
            while (-1 != (bytesRead = bufferedInputStream.read(buff, 0, buff.length))) {
                bao.write(buff, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                bufferedInputStream.close();
                buff = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(path + username+suffix));
            out.write(bao.toByteArray());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
