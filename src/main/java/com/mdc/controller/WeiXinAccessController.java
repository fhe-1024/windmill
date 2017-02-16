package com.mdc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mdc.mq.MessageReceiver;
import com.mdc.util.SHA1Util;

@Controller
@RequestMapping("/weixin")
public class WeiXinAccessController {

	private Log log = LogFactory.getLog(WeiXinAccessController.class);
	@Autowired
	private MessageReceiver messageReceiver;
	@Autowired
	private Destination queueDestination2;

	@RequestMapping(path = "/access")
	public String access(HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			try {
				String signature = request.getParameter("signature");
				String timestamp = request.getParameter("timestamp");
				String nonce = request.getParameter("nonce");
				String echostr = request.getParameter("echostr");
				String token = "kate";
				log.info(signature);
				List<String> list = new ArrayList<String>();
				list.add(nonce);
				list.add(token);
				list.add(timestamp);
				Collections.sort(list);
				String verify = "";
				for (int i = 0; i < list.size(); i++) {
					verify += list.get(i);
				}
				String verifySignature = SHA1Util.Sha1(verify);
				if (verifySignature.equals(signature)) {
					response.getWriter().write(echostr);
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		} else {
			try {
				InputStream is = request.getInputStream();
				int size = request.getContentLength();
				log.info("request长度:" + size);
				byte[] buffer = new byte[size];
				byte[] xmldataByte = new byte[size];
				int count = 0;
				int rbyte = 0;
				while (count < size) {
					rbyte = is.read(buffer);
					for (int i = 0; i < rbyte; i++) {
						xmldataByte[count + i] = buffer[i];
					}
					count += rbyte;
				}
				is.close();
				String requestStr = new String(xmldataByte, "UTF-8");
				log.info("xml:" + requestStr);
				Document doc = DocumentHelper.parseText(requestStr);
				Element rootElt = doc.getRootElement();
				String toUserName = rootElt.elementText("ToUserName");
				String FromUserName = rootElt.elementText("FromUserName");
				String CreateTime = rootElt.elementText("CreateTime");
				StringBuilder sb = new StringBuilder();
				sb.append("<xml>");
				sb.append("<ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>");
				sb.append("<FromUserName><![CDATA[" + toUserName + "]]></FromUserName>");
				sb.append("<CreateTime>" + CreateTime + "</CreateTime>");
				sb.append("<MsgType><![CDATA[text]]></MsgType>");

				String returnMsg = messageReceiver.receive(queueDestination2);

				log.info(new String(returnMsg.getBytes("utf-8"), "iso-8859-1"));
				sb.append(
						"<Content><![CDATA[" + new String(returnMsg.getBytes("utf-8"), "iso-8859-1") + "]]></Content>");
				sb.append("</xml>");
				log.info(sb.toString());
				response.getWriter().write(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public static void main(String[] args) {
		try {
			org.dom4j.Document doc = DocumentHelper.parseText(
					"<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>");
			org.dom4j.Element rootElt = doc.getRootElement();
			List<org.dom4j.Element> elementlist = rootElt.elements();
			for (org.dom4j.Element e : elementlist) {
				System.out.println("Element: " + e.getName() + "=" + e.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
