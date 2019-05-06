import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 兴业证券公告抓取
 */
public class ixzzcgl implements PageProcessor {




    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private static int count =0;
    public Site getSite() {
        return site;
    }
    public void process(Page page) {

        String pdfURL = page.getHtml().xpath("//*[@id=\"xzzx_detail\"]/div[1]/div/div/div[1]/div[1]/div/a").links().get();
        System.out.println(pdfURL);
        String PDFnane = pdfURL.substring(pdfURL.indexOf("_2019")+1,pdfURL.indexOf(".pdf"));
        System.out.println(PDFnane);


        try {
            DownLoadFile.downLoadByUrl(pdfURL,PDFnane+".PDF","F:\\下载");
            count++;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void main(String[] args) throws IOException {

        System.out.println("开始爬取...");
        long startTime, endTime;
        startTime = System.currentTimeMillis();
        String url ="http://www.ixzzcgl.com";
        String ixzzcglJson = Jsoup.connect("http://www.ixzzcgl.com/servlet/json?funcNo=955622004&curPage=1&rowOfPage=300&length=80&keyword=2018%25e5%25b9%25b4%25e5%25b9%25b4%25e5%25ba%25a6%25e6%258a%25a5%25e5%2591%258a")
                .ignoreContentType(true)
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").get().text();

        JSONObject jsonObject = JSON.parseObject(ixzzcglJson);
        //JSONObject jsonObject1 = JSONObject.parseObject(COMPLEX_JSON_STR);//因为JSONObject继承了JSON，所以这样也是可以的
        JSONArray data = jsonObject.getJSONArray("data");
        List<String> newUrl = new ArrayList<>();
        for (Object obj : data) {
            jsonObject = (JSONObject) obj;
            newUrl.add(url+jsonObject.getString("weburl"));
        }

        for (String aNewUrl : newUrl) {

            Spider.create(new ixzzcgl()).addUrl(aNewUrl).thread(5).run();
        }

        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，下载了"+count+"条记录");


    }







}



