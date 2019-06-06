
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.webmagic.common.uitls.FileName;
import com.webmagic.dao.DealExcelPropertyDao;
import com.webmagic.dao.FundHkDao;
import com.webmagic.model.DealExcelProperty;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class test2 {

    /**
    一页一页读取
     y:一次度几页
     */
    private static String loadPDF(int i, int y ,PDDocument document){
        try {
            // 文本内容
            PDFTextStripper stripper = new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(i+1);
            stripper.setEndPage(i+y);
            return stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> regString2List(String reg,String text){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(text);
        List<String> arrayList = new ArrayList<String>();
        while (matcher.find()) {
            String array = matcher.group();
            arrayList.add(array);
        }
        return arrayList;
    }

    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void before(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        sqlSessionFactory = (SqlSessionFactory)applicationContext.getBean("sqlSessionFactory");

    }
    @Test
    public void testPdf2() {
        long start = System.currentTimeMillis();
        FileName fileName = new FileName();
        String path = "F:\\下载\\基金年报\\";
        List<String> pdfList = fileName.getFileName(path);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DealExcelPropertyDao dealExcelPropertyDao = sqlSession.getMapper(DealExcelPropertyDao.class);

        for (String pdfName : pdfList) {
            File file = new File(pdfName);
            InputStream is = null;
            PDDocument document = null;
            try {
                if (pdfName.endsWith(".pdf")) {
                    document = PDDocument.load(file);
                    int pageSize = document.getNumberOfPages();
                    System.out.println(pageSize);
                    DealExcelProperty dealExcelProperty = new DealExcelProperty();
                    String JJMC = null,JJJC = null,JJZDM = null;
                    // 一页一页读取
                    for (int i = 0; i < pageSize; i++) {
                        String text = Objects.requireNonNull(loadPDF(i,1, document)).replaceAll(",","");
                        if(text !=null && text.contains("基金简介") && text.contains("简称") && text.contains("代码")){
                            String[] newText = text.split("\n");
                            for (String s : newText) {
                                if (s.contains("基金名称")) {
                                    JJMC = StringUtils.substringAfter(s, " ").replaceAll(" ","");
                                    System.out.println(JJMC);

                                } else if (s.contains("基金简称")) {
                                    JJJC = StringUtils.substringAfter(s, " ").replaceAll(" ","");
                                    System.out.println(JJJC);
                                } else if (s.contains("基金主代码")) {
                                    String reg = "[0-9]{6}";
                                    Pattern pattern = Pattern.compile(reg);
                                    Matcher matcher = pattern.matcher(s);
                                    if (matcher.find()) {
                                        JJZDM = matcher.group();
                                        System.out.println(JJZDM);
                                    }
                                    break;
                                }
                            }

                        }else if(text !=null && text.contains("券商名称") && text.contains("股票交易") && text.contains("应支付该券商的佣金")){
                            String newText = Objects.requireNonNull(loadPDF(i,3, document)).replaceAll(",","");
                            String reg = "[\\u4e00-\\u9fa5]{4,9}\\s[1-9]{0,2}\\s((|-)[0-9]{1,12}\\.[0-9]{1,9}|-)\\s([0-9]{1,3}\\.[0-9]{1,2}%|-)\\s((|-)[0-9]{1,12}\\.[0-9]{1,9}|-)\\s([0-9]{1,3}\\.[0-9]{1,2}%|-)";
                            List<String> arrayList = regString2List(reg,newText);

                            for (String anArrayList : arrayList) {
                                String[] result = anArrayList.split(" ");
                                for (int i2 = 0; i2 < result.length; i2++) {
                                    if (result[i2].equals("-")) {
                                        result[i2] = "";
                                    }
                                    System.out.println(arrayList);
                                }
                                for (String aResult : result) {
                                    dealExcelProperty.setJJMC(JJMC);
                                    dealExcelProperty.setJJJC(JJJC);
                                    dealExcelProperty.setJJZDM(Integer.parseInt(JJZDM));
                                    dealExcelProperty.setQSname(aResult);
                                    dealExcelProperty.setQSnum(Integer.parseInt(aResult));
                                    dealExcelProperty.setGPJE(Double.parseDouble(aResult));
                                    dealExcelProperty.setGPJEZB(Double.parseDouble(aResult));
                                    dealExcelProperty.setQSJE(Double.parseDouble(aResult));
                                    dealExcelProperty.setQSJEZB(Double.parseDouble(aResult));
                                    dealExcelPropertyDao.insertFundJYXW(dealExcelProperty);
                                }
                            }
                        }else if(text !=null && text.contains("券商名称") && text.contains("债券交易") && text.contains("回购交易") && text.contains("权证交易")){
                            String newText = Objects.requireNonNull(loadPDF(i,3, document)).replaceAll(",","");
                            String reg = "[\\u4e00-\\u9fa5]{4,9}\\s((|-)[0-9]{1,12}\\.[0-9]{1,9}|-)\\s([0-9]{1,3}\\.[0-9]{1,2}%|-)\\s((|-)[0-9]{1,12}\\.[0-9]{1,9}|-)\\s([0-9]{1,3}\\.[0-9]{1,2}%|-)\\s((|-)[0-9]{1,12}\\.[0-9]{1,9}|-)\\s([0-9]{1,3}\\.[0-9]{1,2}%|-)";
                            List<String> arrayList = regString2List(reg,newText);
                            for (Object o : arrayList) {
                                System.out.println(o+"22222222222222222222222222");
                            }
                        }



                    }





                }
            } catch (IOException ignored) {
            } finally {
                try {
                    if (document != null) {
                        document.close();
                    }

                } catch (IOException ignored) {
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("---------------" + (start - end) + "---------------");



    }







}