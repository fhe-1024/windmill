package com.mdc.schedule;

import java.lang.reflect.Method;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.mdc.util.SpringContextUtils;
import com.mdc.view.Laugh;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;


public class Spider extends BreadthCrawler {

	public Spider(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		// TODO Auto-generated constructor stub
		this.addSeed("http://www.qiushibaike.com/text/page/1");
		this.addRegex("^*/text/page/*");
		/* do not fetch jpg|png|gif */
		this.addRegex("-.*\\.(jpg|png|gif).*");
		/* do not fetch url contains # */
		this.addRegex("-.*#.*");
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		Elements elements = page.select("div#content-left>div");
		Object bean = SpringContextUtils.getBeanById("laughServiceImpl");
		Class<?> beanClass = bean.getClass();
		Method method = ReflectionUtils.findMethod(beanClass, "save", Laugh.class);
		for (int rank = 0; rank < elements.size(); rank++) {
			Element e = elements.get(rank);
			System.out.println("标签id:" + e.id().substring(11));
			System.out.println("头像图片:" + e.select("img").attr("src"));
			System.out.println("昵称:" + e.select("img").attr("alt"));
			System.out.println("用户id:" + e.select("a").eq(1).attr("href"));
			System.out.println("性别:" + (e.select("div.articleGender").attr("class").contains("womenIcon") ? "女" : "男"));
			System.out.println("糗事内容:" + e.select("div.content").text());
			System.out.println("笑脸:" + e.select("li.up").text());
			System.out.println("哭脸:" + e.select("li.down").text());
			Laugh laugh = new Laugh();
			laugh.setTagid(e.id().substring(11));
			laugh.setHeadimgurl(e.select("img").attr("src"));
			laugh.setNickname(e.select("img").attr("alt"));
			laugh.setUserid(e.select("a").eq(1).attr("href"));
			laugh.setSex((e.select("div.articleGender").attr("class").contains("womenIcon") ? "0" : "1"));
			laugh.setContent(e.select("div.content").text());
			laugh.setUp(e.select("li.up").text());
			laugh.setDown(e.select("li.down").text());
			ReflectionUtils.invokeMethod(method, bean, laugh);
		}

		// System.out.println("begin------" +
		// page.select("ul.pagination>li>a").last().attr("href") + "------end");
		next.add("http://www.qiushibaike.com" + page.select("ul.pagination>li>a").last().attr("href"));
	}

}
