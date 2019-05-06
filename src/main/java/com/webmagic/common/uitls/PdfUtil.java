package com.webmagic.common.uitls;

import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PdfUtil {
    /**
     * 通过PDFbox获取文章总页数
     *
     * @param filePath:文件路径
     * @return
     * @throws IOException
     */
    public static int getNumberOfPages(String filePath) throws IOException, InterruptedException {
        File file = new File(filePath);
        PDDocument pdDocument = PDDocument.load(new File(filePath));
        int pages = pdDocument.getNumberOfPages();
        pdDocument.close();
        return pages;
    }

    /**
     * 通过PDFbox获取文章内容
     *
     * @param filePath
     * @return
     */
    public static String getContent(String filePath) throws IOException {
        PDFParser pdfParser = new PDFParser(new org.apache.pdfbox.io.RandomAccessFile(new File(filePath), "rw"));
        pdfParser.parse();
        PDDocument pdDocument = pdfParser.getPDDocument();
        String text = new PDFTextStripper().getText(pdDocument);
        pdDocument.close();

        return text;
    }


    public void readFdf(File pdfFile) throws Exception {
        // 是否排序
        boolean sort = false;
        // 输入文本文件名称
        String textFileName = null;
        // 编码方式
        String encoding = "UTF-8";
        // 开始提取页数
        int startPage = 1;
        // 结束提取页数
        int endPage = 1;
        // 文件输入流，生成文本文件
        Writer output = null;
        // 内存中存储的PDF Document
        PDDocument document = null;

        File outputFile = null;
        try {

            // 从本地装载文件
            //注意参数已不是以前版本中的URL.而是File。
            System.out.println("开始装载文件"+pdfFile.getName());
            document = PDDocument.load(pdfFile);
            if (pdfFile.getName().length() > 4) {
                textFileName = pdfFile.getName().substring(0, pdfFile.getName().length() - 4) + ".txt";
                outputFile = new File(pdfFile.getParent(),textFileName);
                System.out.println("新文件绝对路径为："+outputFile.getAbsolutePath());


            }
            System.out.println("装载文件结束");


            System.out.println("开始写到txt文件中");
            // 文件输入流，写入文件倒textFile
            output = new OutputStreamWriter(new FileOutputStream(outputFile),encoding);
            System.out.println("写入txt文件结束");
            // PDFTextStripper来提取文本
            PDFTextStripper stripper = null;
            stripper = new PDFTextStripper();
            // 设置是否排序
            stripper.setSortByPosition(sort);
            // 设置起始页
            stripper.setStartPage(startPage);
            // 设置结束页
            stripper.setEndPage(endPage);
            // 调用PDFTextStripper的writeText提取并输出文本
            System.out.println("开始调用writeText方法");
            stripper.writeText(document, output);
            System.out.println("调用writeText方法结束");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (output != null) {
                // 关闭输出流
                output.close();
            }
            if (document != null) {
                // 关闭PDF Document
                document.close();
            }
        }


    }

    private static Map<String,String> fileNames = new HashMap<String, String>();

    /**
     * 获取文件夹所有文件名
     * @param path
     */
    private void getFile(String path){
        File file = new File(path);
        File[] array = file.listFiles();
        for(int i=0;i<array.length;i++){
            if(array[i].isFile()){
                String hz = array[i].getName().substring(array[i].getName().lastIndexOf("."),array[i].getName().length());
                if(hz.equals(".PDF")){
                    String mz = array[i].getName().substring(0,array[i].getName().lastIndexOf("."));
                    fileNames.put(mz+".txt", array[i].getName());
                }
            }else if(array[i].isDirectory()){
                getFile(array[i].getPath());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "D:\\文档下载\\";
        PdfUtil pdfUtil = new PdfUtil();
        pdfUtil.getFile(path);
        FileInputStream fis = null;
        BufferedWriter writer = null;
        for(Map.Entry<String, String> entry: fileNames.entrySet()) {
            fis = new FileInputStream(path + entry.getValue());
            writer = new BufferedWriter(new FileWriter(path + entry.getKey()));
            PDFParser p = new PDFParser((RandomAccessRead) fis);
            p.parse();
            PDFTextStripper ts = new PDFTextStripper();
            String ss = ts.getText(p.getPDDocument());
            writer.write(ss);
            fis.close();
            writer.close();
        }
    }



}


