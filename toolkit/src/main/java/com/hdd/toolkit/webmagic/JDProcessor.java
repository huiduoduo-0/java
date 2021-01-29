package com.hdd.toolkit.webmagic;

import com.alibaba.fastjson.JSONObject;
import com.hdd.toolkit.model.Goods;
import com.hdd.toolkit.utils.HttpClientUtil;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import java.util.List;
import java.util.Map;

public class JDProcessor implements PageProcessor {

    private long sid;

    private Site site = Site.me()
            .setTimeOut(5000)//超时时间 5秒
            .setCycleRetryTimes(3)//重置次数 3次
            .setCharset("UTF-8")//编码格式
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");//用户代理

    public void setSid(long sid) {
        this.sid = sid;
    }

    @Override
    public void process(Page page) {
        //每次爬取的间隔时间1——3秒
        this.site.setSleepTime((int) (Math.random() * 2000) + 1000);

        if (page.getHtml().$("#J_goodsList").get() != null) {
            //列表页
            List<String> all = page.getHtml().$("#J_goodsList .gl-item").$(".p-img a").links().all();
            page.addTargetRequests(all);
        } else {
            //详情页
            Document dom = page.getHtml().getDocument();
            //获取商品标题
            String title = dom.select(".sku-name").get(0).text();
            //获取商品图片
            String imgUrl = dom.select("#spec-img").get(0).attr("data-origin");
            //获取商品路径
            String url = page.getUrl().get();
            //获取商品介绍
            String intro = dom.select(".p-parameter-list li").text();
            System.out.println("intro = " + intro);
            //获取规格值
            String spec = dom.select(".Ptable").text();
            System.out.println("spec--------- = " + spec);
            //获取商品的价格
            String skuId = url.substring(url.lastIndexOf("/") + 1, url.indexOf(".html"));
            //调用工具类 doget方法获取json数据
            String priceJson = HttpClientUtil.doGet("https://p.3.cn/prices/mgets?skuIds=J_" + skuId);
            //将json格式转为list集合
            List<Map<String, String>> priceList = (List<Map<String, String>>) JSONObject.parse(priceJson);
            //取出第一个元素的 价格
            Map<String, String> priceMap = priceList.get(0);
            String p = priceMap.get("p");
            double price = Double.parseDouble(p);//商品价格

            Goods goods = new Goods();
            goods.setTittle(title);
            goods.setImgUrl(imgUrl);
            goods.setPrice((long) price);
            goods.setIntroduce(intro);
            goods.setSpec(spec);
            goods.setUrl(url);
            goods.setSid(sid);
            page.putField("goods", goods);
            System.out.println("goods  +++++  ----- " + goods);
        }
    }

       @Override
    public Site getSite() {
        return site;
    }

}
