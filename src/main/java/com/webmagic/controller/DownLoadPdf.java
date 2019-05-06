package com.webmagic.controller;

import com.webmagic.common.uitls.FileContext;
import java.util.List;

import static com.webmagic.common.uitls.DownLoadFile.downLoadByUrl;
import static java.lang.System.currentTimeMillis;

public class DownLoadPdf{


    public static void main(String[] args) throws Exception {
        long startTime, endTime;
        startTime = currentTimeMillis();
        FileContext fileContext = new FileContext();
        String path = "D:\\文档下载\\url.txt";
        int count = 0;
        System.out.println("开始爬取...");
        startTime = currentTimeMillis();
        List<String> urls = fileContext.getFileContext(path);
        for (int i = 0; i < urls.size(); i++) {
            downLoadByUrl(urls.get(i),i+".PDF","D:\\文档下载");
            count++;
            System.out.println("已下载"+count+"个文件");
        }
        endTime = currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+count+"条记录");




    }

}
