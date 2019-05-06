import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

public class test implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private static int count =0;
    public Site getSite() {
        return site;
    }
    public void process(Page page) {

        List<String> ipList = page.getHtml().xpath("//table[@id='ip_list']/tbody/tr").all();
        for (String s : ipList){
            System.out.println(s);
        }
        List<ProxyIp> result = new ArrayList<>();

        if(ipList != null && ipList.size() > 0){
            ipList.remove(0);  //移除表头
            for(String tmp : ipList){
                Html html = Html.create(tmp);

                ProxyIp proxyIp = new ProxyIp();
                String[] data = html.xpath("//body/text()").toString().trim().split("\\s+");
                proxyIp.setIp(data[0]);
                proxyIp.setPort(Integer.valueOf(data[1]));
                result.add(proxyIp);
                String ip = proxyIp.getIp();
                String port = proxyIp.getPort().toString();

                System.out.println("IP:"+ip+"....."+"端口:"+port);
            }
        }
        count ++;
        page.putField("result", result);
        page.addTargetRequest("http://www.xicidaili.com/nn/2");
        page.addTargetRequest("http://www.xicidaili.com/nt/");




    }
    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(new test()).addUrl("http://www.xicidaili.com/nn").thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+count+"条记录");
    }




}
