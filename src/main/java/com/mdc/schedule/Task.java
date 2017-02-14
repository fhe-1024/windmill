package com.mdc.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mdc.service.ILaughService;

@Component
public class Task {

	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private ILaughService laughService;
	
	@Scheduled(cron = "0 0 10 * * ? ")
	public void exchange() throws Exception {
		Spider crawler = new Spider("crawl", true);
		crawler.setThreads(50);
		crawler.setTopN(50);
		crawler.start(35);
	}
	
	

}