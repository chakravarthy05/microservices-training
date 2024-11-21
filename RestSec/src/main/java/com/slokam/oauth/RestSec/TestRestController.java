package com.slokam.oauth.RestSec;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

	 @GetMapping("test")
	 public String test() {
		   System.out.println("Test Test ...");
		   return "DATATAATT";
	 }
	 @GetMapping("/user/me")
	    public Principal user(Principal principal) {
	        return principal;
	    }
	 
	
}
