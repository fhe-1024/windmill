package com.mdc.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {

	private Log log = LogFactory.getLog(getClass());

	@Scheduled(cron = "0 0/5 * * * ? ")
	public void exchange() {
		try {
			log.info("schedule job");
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
	}

}