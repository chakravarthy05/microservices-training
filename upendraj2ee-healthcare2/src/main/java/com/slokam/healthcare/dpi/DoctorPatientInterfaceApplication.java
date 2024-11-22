package com.slokam.healthcare.dpi;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.slokam.healthcare.dpi.service.impl.TreatmentServiceDBImpl;


//@configuration , @componentScan , @EnableAutoConfiguration
// @entityScan
@SpringBootApplication 
@EnableScheduling
@EnableAspectJAutoProxy
@EnableTransactionManagement
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DoctorPatientInterfaceApplication {
//extends SpringBootServletInitializer{

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(DoctorPatientInterfaceApplication.class); }
	 */
	public static void main(String[] args) {
		SpringApplication.run(DoctorPatientInterfaceApplication.class, args);
	}

	@Bean(name="treatmentServiceDBImpl2")
	public TreatmentServiceDBImpl treatmentServiceDBImpl() {
		return new TreatmentServiceDBImpl();
	}
}
