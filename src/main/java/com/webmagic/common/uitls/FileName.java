package com.webmagic.common.uitls;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取文件名
 * path 文件路径
 *
 *
 */
public class FileName {
    public List<String> getFileName(String path) {

        List<String> pdfPathList = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return null;
        }
        File fa[] = f.listFiles();
        int count = 0;
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                System.out.println(fs.getName() + " [目录]");
            } else {
                pdfPathList.add(path+fs.getName());
                count++;
            }
        }
        System.out.println(count);
        return pdfPathList;
    }


    public static void main(String[] args) {
        FileName fileName = new FileName();
        String path = "F:\\下载\\港交所\\";
        List<String> list = fileName.getFileName(path);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
