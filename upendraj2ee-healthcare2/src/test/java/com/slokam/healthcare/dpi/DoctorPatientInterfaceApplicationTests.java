package com.slokam.healthcare.dpi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorPatientInterfaceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void test() {
		System.out.println("Hello::");
	    
	    try {
			 mockMvc.perform(get("/treatment/5"))
			 .andExpect(status().isOk())
			 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
			 .andExpect(MockMvcResultMatchers.jsonPath("$.disease.description").value("fever"))
			 .andExpect(MockMvcResultMatchers.jsonPath("$.appointment.location").value("bhimavaram"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}

}
