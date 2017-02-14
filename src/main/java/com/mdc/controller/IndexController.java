package com.mdc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private Log log = LogFactory.getLog(getClass());

	@RequestMapping("/index")
	public String index() {
		log.info("访问首页");
		return "index";
	}

	@RequestMapping("/")
	public String mlink() {
		log.info("访问首页");
		return "redirect:/index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}