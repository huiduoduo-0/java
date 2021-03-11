package com.hdd.toolkit.webmagic;

import com.alibaba.fastjson.JSONObject;
import com.hdd.toolkit.model.Goods;
import com.hdd.toolkit.utils.HttpClientUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDProcessor implements PageProcessor {

    private long sid;

    List<Goods> goodsList = new ArrayList<>();

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
            List<String> JdList = page.getHtml().$("#J_goodsList .gl-item").$(".p-img a").links().all();
            page.addTargetRequests(JdList);
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
            String intro = dom.select(".p-parameter-list li").text().trim();
            //商品小图片
            Elements imgsUrl = dom.select(".spec-items li img[src]");
            String s = "";
            List<String> imgsList = new ArrayList<>();//定义集合存储商品小图片
            for (Element e: imgsUrl){
                //将取到的标签转换string类型
                String imgs = String.valueOf(e);
                //截取字符串中的小图片路径
                s = imgs.substring(imgs.indexOf("src")+5,imgs.indexOf("jpg")+3);
                //截取后的小图片存入集合
                imgsList.add(s);
//                System.out.println("小图片   ====="+ s);
            }
            //颜色小图片
            Elements colorImgsUrl = dom.select("#choose-attr-1 a img[src]");
            String c = "";
            for (Element e : colorImgsUrl){
                //将取到的标签转换string类型
                String imgs = String.valueOf(e);
                //截取字符串中的小图片路径
                c = imgs.substring(imgs.indexOf("src")+5,imgs.indexOf("jpg")+3);
                //截取后的小图片存入集合
                imgsList.add(c);
//                System.out.println("颜色小图片  ==== "+ c);
            }
            System.out.println("  小图片集合---------"+imgsList);

            //商品颜色
            List<String> collorList = new ArrayList<>();//颜色集合
            Elements elements = dom.select("#choose-attr-1 a i");
            for (Element e : elements){
                String color = e.text();
                collorList.add(color);
//                System.out.println("  商品 颜色 +++++  "+color);
            }
            System.out.println("  颜色集合————————————"+collorList);

            //商品规格
            List<String> specList = new ArrayList<>();//规格集合
            Elements element1 = dom.select("#choose-attr-2 a");
            for (Element e : element1){
                String spec = e.text();
                specList.add(spec);
            }
            System.out.println("  规格集合 *********"+specList);

            //获取商品参数
            String parameter = dom.select(".Ptable").text().trim();
//            System.out.println("  参数   。。。。。"+parameter);
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
            goods.setSpec(parameter);
            goods.setUrl(url);
            goods.setSid(sid);
            page.putField("goods", goods);
//            System.out.println("goods  +++++  ----- " + goods);
            goodsList.add(goods);
            System.out.println(goodsList);

            //将京东商品对象存入session
//            session.setAttribute(jdgoods,goods);

        }
    }

    @Override
    public Site getSite() {
        return site;
    }

}


