package com.slokam.healthcare.dpi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OurCommandLandRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
			System.out.println("helloooo this is starting logic");
	}
}
