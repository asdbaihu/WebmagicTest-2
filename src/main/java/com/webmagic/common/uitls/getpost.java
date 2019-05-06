package com.webmagic.common.uitls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webmagic.common.uitls.DownLoadFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.*;


public class getpost {

    public static Document getJsoupDocGet(String url) {
        //三次试错
        final int MAX = 10;
        int time = 0;
        Document doc = null;
        while (time < MAX) {
            try {
                doc = Jsoup
                        .connect(url)
                        .ignoreContentType(true)
                        .ignoreHttpErrors(true)
                        .timeout(1000 * 30)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                        .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                        .header("accept-encoding","gzip, deflate, br")
                        .header("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                        .get();
                return doc;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                time++;
            }
        }
        return doc;
    }




    public static Document getJsoupDocPost(String url, Map<String,String> paramMap) {
        //三次试错
        final int MAX = 10;
        int time = 0;
        Document doc = null;
        while (time < MAX) {
            try {
                doc = Jsoup
                        .connect(url)
                        .ignoreContentType(true)
                        .ignoreHttpErrors(true)
                        .timeout(1000 * 30)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                        .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                        .header("accept-encoding","gzip, deflate, br")
                        .header("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                        .data(paramMap)
                        .post();
                return doc;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                time++;
            }
        }
        return doc;
    }




    public static void main(String[] args){

        String savePath = "F:\\下载";
        String[] Listcode =new String[]{"AE0003","AE0005","AE0008","AE0009","AE0015","AE0018","AE0022","AE0020","S04081","S71707","AE4003","AE4006","S45466","S45982","S49393","S51370","S55046","S57225","S40531","SP0344","SP6464","S57230","SQ6206","SG1574","SQ7525","SU4432","SAA420","SAH970","SAS984","SZ1563","SEC330","SEC588","SGF091","SGJ156","SGF462","SEW269"};
        String postUrl = "https://www.firstcapital.com.cn/servlet/json";
        String pdfUrl = "https://www.firstcapital.com.cn";
        StringBuilder buf = new StringBuilder();
        int jsonCount= 0;
        int downloadCount= 0;
        for (String aListcode : Listcode) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("funcNo", "904318");
            map.put("catalogId", "10410");
            map.put("fundcode", aListcode);
            map.put("page", "1");
            map.put("numPage", "15");
            map.put("titleLength", "35");
            map.put("state", "3");
            String text = getJsoupDocPost(postUrl, map).text();
            JSONObject jsonObject = JSON.parseObject(text);
            String results = jsonObject.getString("results");
            System.out.println(results);
            int start = results.indexOf("a\":[");
            int end = results.indexOf("}],\"tot");
            String results2 = results.substring(start + 3, end + 2);
//            System.out.println(start);
//            System.out.println(end);
//            System.out.println(results2);
            buf.append(results2);
            jsonCount++;
        }

        String newJson = buf.toString().replaceAll("]\\[",",");
        System.out.println(newJson);

        System.out.println("抓取了"+jsonCount+"条");

        JSONArray jsonArray = JSON.parseArray(newJson);
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            if(jsonObject.getString("title").contains("2019")){
                if(jsonObject.getString("title").contains("管理报告")){
                    if(jsonObject.getString("title").contains("第一季")){
                        String PDFnane = jsonObject.getString("modified_date")+" "+jsonObject.getString("title");
                        String url = pdfUrl+jsonObject.getString("link_url");
                        try {
                            DownLoadFile.downLoadByUrl(url,PDFnane+".PDF",savePath);

                            downloadCount++;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    if(jsonObject.getString("title").contains("第1季")){
                        String PDFnane = jsonObject.getString("create_date")+" "+jsonObject.getString("title");
                        String url = pdfUrl+jsonObject.getString("link_url");
                        try {
                            DownLoadFile.downLoadByUrl(url,PDFnane+".PDF",savePath);
                            System.out.println(PDFnane);
                            System.out.println(url);
                            downloadCount++;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        System.out.println("下载了"+downloadCount+"个文件");
    }
}
