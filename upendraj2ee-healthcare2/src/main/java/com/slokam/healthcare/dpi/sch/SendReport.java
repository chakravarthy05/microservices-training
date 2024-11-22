package com.slokam.healthcare.dpi.sch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendReport {

	private Logger LOGGER = LoggerFactory.getLogger(SendReport.class);
	
	/*
	 *  
	 * https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions 
	 * 
	 */
	
	//@Scheduled(cron = "*/10 * * * * *" )
	public void test() {
		//LOGGER.info("This is test code from Send Report");
	}
	
}
