
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import com.webmagic.common.uitls.FileName;
import com.webmagic.dao.DealExcelPropertyDao;
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

    /**
     *
     * @param reg 正则表达式
     * @param text  文本
     * @return
     */
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
    private static String reg2String(String reg,String text){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(text);
        String qsname = "";
        while (matcher.find()) {
            qsname = matcher.group();
        }
        return qsname;
    }

    /**
     * 删除重复券商名称的元素
     * @param list
     * @return
     */
    private static List<String> removeDuplicate(List<String> list)  {
        List<String> qsname = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String aList = list.get(i);
            String reg = "[\\u4e00-\\u9fa5_()]{1,9}";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(aList);
            if (matcher.find()) {
                qsname.add(matcher.group());
            }
        }

        for (int i = 0; i < list.size()-1; i++) {
             for (int j=list.size()-1; j>i;j--){
                 if (list.get(j).contains(qsname.get(i))){
                     list.remove(j);
                 }
             }
        }
       return list;
    }

    /**
     * d读取全部PDF
     * @param
     * @return
     */

    private static String allText(PDDocument document){

        try {
            // 文本内容
            PDFTextStripper stripper = new PDFTextStripper();
            int pageSize = document.getNumberOfPages();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(1);
            stripper.setEndPage(pageSize);
            return stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean regJudgeContains(String text){

        String reg = "[a-zA-Z]{4,20}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
    @Test
    public void test23(){
        String a = "Citigroup Glob";
        boolean b = regJudgeContains(a);
        System.out.println(b);

}

    @Test
    public void testlist(){
        List<String> list = new ArrayList<>();
        list.add("长江证券 1 61932836.80 30.30% 57677.93 31.78%");
        list.add("国泰君安 2 41410345.41 20.26% 37845.81 20.85%");
        list.add("川财证券 1 37260557.84 18.23% 27248.87 15.01%");
        list.add("海通证券 2 33486806.41 16.39% 31136.27 17.15%");
        list.add("华创证券 1 15614170.17 7.64% 14229.25 7.84%");
        list.add("东方证券 2 14204960.18 6.95% 12945.11 7.13%");
        list.add("国元证券 2 463326.70 0.23% 431.47 0.24%");
        list.add("平安证券 1 - - - -");
        list.add("中信建投 1 - - - -");
        list.add("兴业证券 2 - - - -");
        list.add("光大证券 1 - - - -");
        list.add("招商证券 1 - - - -");
        list.add("中金公司 1 - - - -");
        list.add("国金证券 1 - - - -");
        list.add("华泰证券 1 - - - -");
        list.add("太平洋证券 2 - - - -");
        list.add("东方证券 - - - - -");
        list.add("中信建投 - - - - -");
        list.add("招商证券 - - - - -");
        list.add("国金证券 1 - - - -");
        list.add("太平洋证券 2 - - - -");
        list.add("太平洋证券 2 - - - -");
        for (String aList : list) {
            System.out.println(aList);
        }
        List<String> list2 = removeDuplicate(list);
        for (String s : list2) {
            System.out.println(s+"22222222222");
        }
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
        //已处理计数
        int countZS = 0;
        //未处理计数
        int countYCL = 0;
        //转型基金计数
        int countZX = 0;
        //QDII计数
        int countQDII = 0;
        //入库
        int countRK = 0;
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
                    //获取pdf页数
                    int pageSize = document.getNumberOfPages();
                    //System.out.println(pageSize);
                    DealExcelProperty dealExcelProperty = new DealExcelProperty();
                    String JJMC = null,JJJC = null,JJZDM = null,JZRQ="2018-12-31";

                    // 一页一页读取
                    for (int i = 0; i < pageSize; i++) {
                        String text = Objects.requireNonNull(loadPDF(i,1, document)).replaceAll(",","");
                        //System.out.println(text);
                        if (text.contains("转型前")||text.contains("转型后")){
                            System.out.println("转型基金----跳过");
                            countZX++;
                            break;
                        }
                        if(text !=null && text.contains("基金简介") && text.contains("简称") && text.contains("代码")){
                            String[] newText = text.split("\n");
                            for (String s : newText) {
                                if (s.contains("基金名称")) {
                                    JJMC = StringUtils.substringAfter(s, " ").replaceAll(" ","");
                                    //System.out.println(JJMC);

                                } else if (s.contains("基金简称")) {
                                    JJJC = StringUtils.substringAfter(s, " ").replaceAll(" ","");
                                    //System.out.println(JJJC);
                                } else if (s.contains("基金主代码")) {
                                    String reg = "[0-9]{6}";
                                    Pattern pattern = Pattern.compile(reg);
                                    Matcher matcher = pattern.matcher(s);
                                    if (matcher.find()) {
                                        JJZDM = matcher.group();
                                        //System.out.println(JJZDM);
                                    }
                                    break;
                                }
                            }
                            System.out.println("正在处理基金名称---------"+JJMC+"\n基金简称---------"+JJJC+"\n基金代码---------"+JJZDM);

                        }else if(text !=null && text.contains("券商名称") && text.contains("股票交易") && text.contains("应支付该券商的佣金")){
                            String newText = Objects.requireNonNull(loadPDF(i,3, document)).replaceAll(",","").replaceAll("\r","");
                            String reg = "[\\u4e00-\\u9fa5_()]{2,9}\\s([1-9]{0,2}|-)\\s((|-)[0-9]{1,12}\\.[0-9]{1,9}|-)\\s([0-9]{1,3}\\.[0-9]{1,2}%|-)\\s((|-)[0-9]{1,12}\\.[0-9]{1,9}|-)\\s([0-9]{1,3}\\.[0-9]{1,2}%|-)";
                            List<String> list = regString2List(reg,newText);
                            if (regJudgeContains(newText)){
                                countQDII++;
                                break;
                            }


                            String qsname = dealExcelPropertyDao.qsNameIsNull(reg2String("[\\u4e00-\\u9fa5_()]{2,9}",list.get(0)),JJZDM,JZRQ);
                            if (qsname!=null) {
                                //如果库里已有,跳过
                                countYCL++;
                                System.out.println("库里已有该年报");
                                break;
                            }

                            //去重
                            List<String> arrayList = removeDuplicate(list);
                            int count = 0;
                            int num = 1;
                            double GPcount = 0.00;
                            double GPZBcount = 0.00;
                            double QScount = 0.00;
                            double QSZBcount = 0.00;

                            for (String anArrayList : arrayList) {
                                //System.out.println(anArrayList+"111111111111111111111");
                                dealExcelProperty.setJJMC(JJMC);
                                dealExcelProperty.setJJJC(JJJC);
                                dealExcelProperty.setJJZDM(JJZDM);
                                dealExcelProperty.setJZRQ(JZRQ);
                                dealExcelProperty.setNUM(num);
                                num ++;
                                String[] result = anArrayList.replaceAll("%","").replaceAll("\n"," ").split(" ");
                                for (int i1 = 0; i1 < result.length; i1++) {
                                    switch (i1){
                                        case 0:
                                            dealExcelProperty.setQSname(result[i1]);
                                            break;
                                        case 1:
                                            if (result[i1].equals("-")){
                                                dealExcelProperty.setQSnum(null);
                                                break;
                                            }else {
                                                dealExcelProperty.setQSnum(Integer.parseInt(result[i1]));
                                                count+=Integer.parseInt(result[i1]);
                                            }

                                            break;
                                        case 2:
                                            if (result[i1].equals("-")){
                                                dealExcelProperty.setGPJE(null);
                                                break;
                                            }else{
                                                dealExcelProperty.setGPJE(Double.parseDouble(result[i1]));
                                                GPcount+=Double.parseDouble(result[i1]);
                                                break;
                                            }
                                        case 3:
                                            if (result[i1].equals("-")){
                                                dealExcelProperty.setGPJEZB(null);
                                                break;
                                            }else{
                                                dealExcelProperty.setGPJEZB(Double.parseDouble(result[i1]));
                                                GPZBcount+=Double.parseDouble(result[i1]);
                                                break;
                                            }
                                        case 4:
                                            if (result[i1].equals("-")){
                                                dealExcelProperty.setQSJE(null);
                                                break;
                                            }else{
                                                dealExcelProperty.setQSJE(Double.parseDouble(result[i1]));
                                                QScount+=Double.parseDouble(result[i1]);
                                                break;
                                            }
                                        case 5:
                                            if (result[i1].equals("-")){
                                                dealExcelProperty.setQSJEZB(null);
                                                break;
                                            }else{
                                                dealExcelProperty.setQSJEZB(Double.parseDouble(result[i1]));
                                                QSZBcount+=Double.parseDouble(result[i1]);
                                                break;
                                            }
                                         default:
                                             //System.out.println(result[i1]);
                                             break;
                                    }
                                }
                                dealExcelPropertyDao.insertFundJYXW(dealExcelProperty);


                            }
                            dealExcelProperty.setQSname("总计");
                            dealExcelProperty.setJJZDM(JJZDM);
                            dealExcelProperty.setNUM(num);
                            dealExcelProperty.setJZRQ(JZRQ);
                            dealExcelProperty.setQSnum(count);
                            dealExcelProperty.setGPJE(GPcount);
                            dealExcelProperty.setGPJEZB(GPZBcount);
                            dealExcelProperty.setQSJE(QScount);
                            dealExcelProperty.setQSJEZB(QSZBcount);
                            dealExcelPropertyDao.insertFundJYXW(dealExcelProperty);
                            countRK++;
                        }else if(text !=null && text.contains("券商名称") && text.contains("债券交易") && text.contains("回购交易") && text.contains("权证交易")){
                            String newText = Objects.requireNonNull(loadPDF(i,3, document)).replaceAll(",","").replaceAll("\r","");
                            String reg = "[\\u4e00-\\u9fa5_()]{2,9}\\s((|-)[0-9]{1,12}\\.[0-9]{1,9}|-)\\s([0-9]{1,3}\\.[0-9]{1,2}%|-)\\s((|-)[0-9]{1,12}\\.[0-9]{1,9}|-)\\s([0-9]{1,3}\\.[0-9]{1,2}%|-)\\s((|-)[0-9]{1,12}\\.[0-9]{1,9}|-)\\s([0-9]{1,3}\\.[0-9]{1,2}%|-)";
                            List<String> arrayList = regString2List(reg,newText);

                            double ZQcount = 0.00;
                            double ZQZBcount = 0.00;
                            double HGcount = 0.00;
                            double HGZBcount = 0.00;
                            double QZcount = 0.00;
                            double QZZBcount = 0.00;
                            double JJcount = 0.00;
                            double JJZBcount = 0.00;
                            double QHcount = 0.00;
                            double QHZBcount = 0.00;
                            for (String anArrayList : arrayList) {

                                String[] result = anArrayList.replaceAll("%","").replaceAll("\n"," ").split(" ");


                                for (int i1 = 0; i1 < result.length; i1++) {
                                    String qsname = dealExcelPropertyDao.qsNameIsNull(reg2String("[\\u4e00-\\u9fa5_()]{2,9}",anArrayList),JJZDM,JZRQ);
                                    if (qsname==null){
                                        //可以添加新增
                                        System.out.println("qsname is null");
                                    }else {
                                        switch (i1){
                                            case 0:
                                                dealExcelProperty.setQSname(qsname);
                                                break;
                                            case 1:
                                                if (result[i1].equals("-")){
                                                    dealExcelProperty.setZQJE(null);
                                                    break;
                                                }else {
                                                    dealExcelProperty.setZQJE(Double.parseDouble(result[i1]));
                                                    ZQcount+=Double.parseDouble(result[i1]);
                                                }

                                                break;
                                            case 2:
                                                if (result[i1].equals("-")){
                                                    dealExcelProperty.setZQJEZB(null);
                                                    break;
                                                }else{
                                                    dealExcelProperty.setZQJEZB(Double.parseDouble(result[i1]));
                                                    ZQZBcount+=Double.parseDouble(result[i1]);
                                                    break;
                                                }
                                            case 3:
                                                if (result[i1].equals("-")){
                                                    dealExcelProperty.setHGJE(null);
                                                    break;
                                                }else{
                                                    dealExcelProperty.setHGJE(Double.parseDouble(result[i1]));
                                                    HGcount+=Double.parseDouble(result[i1]);
                                                    break;
                                                }
                                            case 4:
                                                if (result[i1].equals("-")){
                                                    dealExcelProperty.setHGJEZB(null);
                                                    break;
                                                }else{
                                                    dealExcelProperty.setHGJEZB(Double.parseDouble(result[i1]));
                                                    HGZBcount+=Double.parseDouble(result[i1]);
                                                    break;
                                                }
                                            case 5:
                                                if (result[i1].equals("-")){
                                                    dealExcelProperty.setQZJE(null);
                                                    break;
                                                }else{
                                                    dealExcelProperty.setQZJE(Double.parseDouble(result[i1]));
                                                    QZcount+=Double.parseDouble(result[i1]);
                                                    break;
                                                }
                                            case 6:
                                                if (result[i1].equals("-")){
                                                    dealExcelProperty.setQZJEZB(null);
                                                    break;
                                                }else{
                                                    dealExcelProperty.setQZJEZB(Double.parseDouble(result[i1]));
                                                    QZZBcount+=Double.parseDouble(result[i1]);
                                                    break;
                                                }
                                            default:
                                                //System.out.println(result[i1]);
                                                break;
                                        }
                                    }
                                }
                                dealExcelProperty.setJJZDM(JJZDM);
                                dealExcelProperty.setJZRQ(JZRQ);
                                dealExcelPropertyDao.updateDealExcelPropertyByQSname(dealExcelProperty);
                            }
                            dealExcelProperty.setQSname("总计");
                            dealExcelProperty.setJJZDM(JJZDM);
                            dealExcelProperty.setJZRQ(JZRQ);
                            dealExcelProperty.setZQJE(ZQcount);
                            dealExcelProperty.setZQJEZB(ZQZBcount);
                            dealExcelProperty.setHGJE(HGcount);
                            dealExcelProperty.setHGJEZB(HGZBcount);
                            dealExcelProperty.setQZJE(QZcount);
                            dealExcelProperty.setQZJEZB(QZZBcount);
                            dealExcelProperty.setJJJE(JJcount);
                            dealExcelProperty.setJJJEZB(JJZBcount);
                            dealExcelProperty.setQHJE(QHcount);
                            dealExcelProperty.setQHJEZB(QHZBcount);
                            dealExcelPropertyDao.updateDealExcelPropertyByQSname(dealExcelProperty);
                        }
//                        else {
//                            String allText = allText(document);
//                            if (!allText.contains("基金租用")&&!allText.contains("券商名称")){
//                                countNO++;
//                                System.out.println("无交易席位");
//                                break;
//                            }
//                        }


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
            countZS++;
        }

        long end = System.currentTimeMillis();
        System.out.println("-------------------------------------------------------------");
        System.out.println("处理了"+countZS+"家,其中库里已存或者文件夹存在相同代码的文件"+countYCL+"家,转型基金"+countZX+"家,QDII:"+countQDII+"家,入库:"+countRK+"家,无基金租用或者无法识别:"+(countZS-countYCL-countZX-countQDII-countRK)+"家");
        System.out.println("---------------用时" + (start - end)/1000 + "秒---------------");



    }







}