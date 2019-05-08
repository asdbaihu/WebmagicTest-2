
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.webmagic.common.uitls.DateTools;
import com.webmagic.common.uitls.FileName;
import com.webmagic.dao.FundHkDao;
import com.webmagic.model.FundHk;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testPDF {

    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void before(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        sqlSessionFactory = (SqlSessionFactory)applicationContext.getBean("sqlSessionFactory");

    }
    @Test
    public void testPdf() {
        long start = System.currentTimeMillis();
        DateTools dateTools = new DateTools();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FundHkDao fundHkDao = sqlSession.getMapper(FundHkDao.class);

        FileName fileName = new FileName();
        String path = "F:\\下载\\港交所\\";
        List<String> pdfList = fileName.getFileName(path);


        for (String pdfName : pdfList) {
            File file = new File(pdfName);
            InputStream is = null;
            PDDocument document = null;
            try {
                if (pdfName.endsWith(".PDF")) {
                    document = PDDocument.load(file);
                    int pageSize = document.getNumberOfPages();
                    // 一页一页读取
                    for (int i = 0; i < pageSize; i++) {
                        // 文本内容
                        PDFTextStripper stripper = new PDFTextStripper();
                        // 设置按顺序输出
                        stripper.setSortByPosition(true);
                        stripper.setStartPage(i + 1);
                        stripper.setEndPage(i + 1);
                        String text = stripper.getText(document);
                        String s = text.substring(0,50);
                        System.out.println(s+"0000000000000000000000000000000000000");
                        //用回车键来分隔几个元素
                        String[] ss = text.replaceAll(" ", "").replaceAll(",", "").split("\n");
                        FundHk fundHk = new FundHk();
                        if(s.contains("代號")){

                            for (int x = 0; x < 8; x++) {

                                System.out.println(ss[x]);
                                if (ss[x].contains("代號")) {
                                    String reg = "[0-9]{3,6}";
                                    Pattern pattern = Pattern.compile(reg);
                                    Matcher matcher = pattern.matcher(ss[x]);
                                    if (matcher.find()) {
                                        String JJJYDM = matcher.group();
                                        System.out.println(JJJYDM + "``````````````````````````````");
                                        fundHk.setJJJYDM(JJJYDM);
                                    }else {
                                        fundHk.setBZ(pdfName);
                                        System.out.println(pdfName);
                                    }


                                    continue;

                                }
                                if (ss[x].contains("日期")) {
                                    String reg = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日";
                                    Pattern pattern = Pattern.compile(reg);
                                    Matcher matcher = pattern.matcher(ss[x]);
                                    if (matcher.find()) {
                                        String GGRQ = matcher.group();
                                        System.out.println(GGRQ + "GGRQ``````````````````````````````");
                                        fundHk.setGGRQ(GGRQ);
                                    }
                                    continue;
                                }
                                if (ss[x].contains("淨值截")) {

                                    String reg1 = "(|-)[0-9]{1,9}\\.[0-9]{1,9}";
                                    Pattern pattern1 = Pattern.compile(reg1);
                                    Matcher matcher1 = pattern1.matcher(ss[x]);

                                    if (matcher1.find()) {
                                        String DWJZ = matcher1.group();
                                        System.out.println(DWJZ + "DWJZ``````````````````````````````");
                                        fundHk.setDWJZ(DWJZ);
                                    }
                                    if (ss[x].contains("截")) {
                                        String reg2 = "(|-)[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日";
                                        Pattern pattern2 = Pattern.compile(reg2);
                                        Matcher matcher2 = pattern2.matcher(ss[x]);
                                        if (matcher2.find()) {
                                            String JZRQ = matcher2.group();
                                            System.out.println(JZRQ + "JZRQ``````````````````````````````");
                                            fundHk.setJZRQ(JZRQ);
                                        }
                                    }

                                    continue;
                                }

                                if (ss[x].contains("購單位的資")) {
                                    String reg = "(|-)[0-9]{1,9}\\.[0-9]{1,9}";
                                    Pattern pattern = Pattern.compile(reg);
                                    Matcher matcher = pattern.matcher(ss[x]);
//id,GGRQ,JZRQ,DWJZ,DWJZBZ,MXZDWZCJZ,MXZDWZCJZBZ,MXZDWJZSJXJ,MXZDWJZSJXJBZ,FXFEZS
                                    if (matcher.find()) {
                                        String MXZDWZCJZ = matcher.group();
                                        System.out.println(MXZDWZCJZ + "MXZDWZCJZ``````````````````````````````");
                                        fundHk.setMXZDWZCJZ(MXZDWZCJZ);
                                    }
                                    continue;

                                }
                                if (ss[x].contains("實際現金")) {
                                    String reg = "(|-)[0-9]{1,9}\\.[0-9]{1,9}";
                                    Pattern pattern = Pattern.compile(reg);
                                    Matcher matcher = pattern.matcher(ss[x]);
                                    if (matcher.find()) {
                                        String MXZDWJZSJXJ = matcher.group();
                                        System.out.println(MXZDWJZSJXJ + "MXZDWJZSJXJ``````````````````````````````");
                                        fundHk.setMXZDWJZSJXJ(MXZDWJZSJXJ);
                                    }
                                    continue;
                                }
                                if (ss[x].contains("單位總數")) {
                                    String reg = "[0-9]{1,9}\\.[0-9]{1,9}";
                                    Pattern pattern = Pattern.compile(reg);
                                    Matcher matcher = pattern.matcher(ss[x]);
                                    if (matcher.find()) {
                                        String FXFEZS = matcher.group();
                                        System.out.println(FXFEZS + "FXFEZS``````````````````````````````");
                                        fundHk.setFXFEZS(FXFEZS);
                                    }
                                    break;
                                }


                            }
                        }else {
                            for (int x = 0; x < 8; x++) {
                                if (ss[x].contains("ETF(2810)")) {
                                    String reg = "[0-9]{4}";
                                    Pattern pattern = Pattern.compile(reg);
                                    Matcher matcher = pattern.matcher(ss[x]);
                                    if (matcher.find()) {
                                        String JJJYDM = matcher.group();
                                        System.out.println(JJJYDM + "JJJYDM``````````````````````````````");
                                        fundHk.setJJJYDM(JJJYDM);
                                    }else {
                                        fundHk.setBZ(pdfName);
                                        System.out.println(pdfName);
                                    }

                                    continue;

                                }
                                if (ss[x].matches("(20|21)[0-9]{6}\r")) {
                                    String GGRQ = ss[x].replaceAll("\r", "");
                                    fundHk.setGGRQ(GGRQ);
                                    System.out.println(GGRQ + "GGRQ``````````````````````````````");
                                    if (dateTools.dateToWeek(GGRQ).equals("星期一")) {

                                        int intGGRQ = Integer.valueOf(GGRQ) - 3;
                                        String stringGGRQ = intGGRQ + "";
                                        String BZ = "无截止日期，根据公告日期计算所得-----"+dateTools.dateToWeek(GGRQ);
                                        fundHk.setJZRQ(stringGGRQ);
                                        fundHk.setBZ(BZ);
                                        System.out.println(stringGGRQ);
                                        continue;

                                    }
                                    if (!dateTools.dateToWeek(GGRQ).equals("星期一")) {

                                        int intGGRQ = Integer.valueOf(GGRQ) - 1;
                                        String stringGGRQ = intGGRQ + "";
                                        String BZ = "无截止日期，根据公告日期计算所得"+dateTools.dateToWeek(GGRQ);
                                        fundHk.setBZ(BZ);
                                        fundHk.setJZRQ(stringGGRQ);
                                        System.out.println(intGGRQ);
                                        continue;

                                    }

                                }
                                if (ss[x].contains("(1)")) {
                                    String reg = "(|-)[0-9]{1,5}\\.[0-9]{1,9}";
                                    Pattern pattern = Pattern.compile(reg);
                                    Matcher matcher = pattern.matcher(ss[x]);
                                    if (matcher.find()) {
                                        String DWJZ = matcher.group();
                                        System.out.println(DWJZ + "JJJYDM``````````````````````````````");
                                        fundHk.setDWJZ(DWJZ);
                                    }
                                    continue;
                                }
                                if (ss[x].contains("(2)")) {
                                    String reg = "(|-)[0-9]{1,9}\\.[0-9]{1,9}";
                                    Pattern pattern = Pattern.compile(reg);
                                    Matcher matcher = pattern.matcher(ss[x]);
                                    if (matcher.find()) {
                                        String FXFEZS = matcher.group();
                                        System.out.println(FXFEZS + "FXFEZS``````````````````````````````");
                                        fundHk.setFXFEZS(FXFEZS);
                                    }

                                    break;
                                }
                            }
                        }




                        fundHkDao.insertFundHk(fundHk);

                        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-");


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

    @Test
    public void test2(){
        String s ="20071119\n";
        if (s.matches("(20|21)[0-9]{6}\n")){
            System.out.println(s.replaceAll("\n","")+"111");

        }
    }

}
