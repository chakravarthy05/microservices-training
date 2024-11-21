package com.slokam.eurekaServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@GetMapping("getString")
	public String getString() {
		LOGGER.trace("entered the string method");
		LOGGER.debug(" IAM DEBUGING");
		LOGGER.info("I AM INFO");
		LOGGER.warn("TRACE ME IAM WARN");
		LOGGER.trace("LEAVING the sting method");
		return "this is chakry controller eureka server";
	}
}