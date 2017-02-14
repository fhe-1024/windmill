package com.mdc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.mdc.service.ILaughService;
import com.mdc.util.PageUtil;
import com.mdc.view.Laugh;

@Controller
@RequestMapping("/laugh")
public class LaughController {
	private Log log = LogFactory.getLog(LaughController.class);
	@Autowired
	private ILaughService laughService;

	@RequestMapping(path = "/getLaughList")
	public void getConsultList(HttpServletRequest request, HttpServletResponse response) {
		String pagesize = request.getParameter("page");
		String rows = request.getParameter("rows");
		PageUtil<Laugh> page = new PageUtil<Laugh>(Integer.parseInt(rows));
		page.setPageNo(Integer.parseInt(pagesize));
		List<Laugh> resultList = new ArrayList<Laugh>();
		try {
			resultList = laughService.getList(page, new HashMap<String, Object>());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", page.getTotalCount());
			map.put("rows", resultList);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(new Gson().toJson(map));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
	}
}
