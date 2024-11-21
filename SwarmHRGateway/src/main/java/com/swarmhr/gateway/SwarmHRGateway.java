package com.swarmhr.gateway;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.swarmhr.gateway.zuulfilter.SwarmHRErrorFilter;
import com.swarmhr.gateway.zuulfilter.SwarmHRPostFilter;
import com.swarmhr.gateway.zuulfilter.SwarmHRPreFilter;
import com.swarmhr.gateway.zuulfilter.SwarmHRRouteFilter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// https://github.com/arjunbalussery/gateway/tree/master/src/main/java/com/arjun/gateway

//@EnableAsync
//@EnableCaching
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class SwarmHRGateway 
{
    public static void main( String[] args ){
        SpringApplication.run(SwarmHRGateway.class, args);
    }
    
    @Bean
    public SwarmHRPreFilter swarmHRPreFilter() {
        return new SwarmHRPreFilter();
    }
    @Bean
    public SwarmHRPostFilter swarmHRPostFilter() {
        return new SwarmHRPostFilter();
    }
    @Bean
    public SwarmHRErrorFilter swarmHRErrorFilter() {
        return new SwarmHRErrorFilter();
    }
    @Bean
    public SwarmHRRouteFilter swarmHRRouteFilter() {
        return new SwarmHRRouteFilter();
    }
    
    @RestController
	static class SwarmHRRestHandler{
		
		@RequestMapping(method = RequestMethod.OPTIONS, path = "/ats-option")
		public String callDefaultOption(HttpServletResponse response) {
			response.setHeader("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
     			   "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, SWARMHRAPP, SWARMHRUSERNAME, ATSTOKEN, SWARMRH_ATS_TOKEN");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			return "SwarmHR Request";
		} 
	}
    
 // http://localhost:8081/ats/v2/api-docs
 	@Configuration
// 	@EnableWebMvc
 	@EnableSwagger2
 	static class SwaggerConfig extends WebMvcConfigurationSupport{
 	    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()                 .apis(RequestHandlerSelectors.basePackage("com.swarmhr.gateway.controller"))
                //.paths(regex(any))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }
    
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API for SwarmHR Service - Swarm HR\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Ramesh Thummu", "https://swarmhr.com/about/", "rthummu@swarmhr.com"))
                .build();
    }
    
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		super.addResourceHandlers(registry);
		registry.addResourceHandler("swagger-ui.html")
         .addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
         .addResourceLocations("classpath:/META-INF/resources/webjars/");

	}

	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		super.addCorsMappings(registry);
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("POST, PUT, GET, OPTIONS, DELETE, PATCH")
				.allowedHeaders("Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, SWARMHRAPP, SWARMHRUSERNAME, ATSTOKEN, SWARMRH_ATS_TOKEN")
				.exposedHeaders("Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, SWARMHRAPP, SWARMHRUSERNAME, ATSTOKEN, SWARMRH_ATS_TOKEN")
				.allowCredentials(false).maxAge(3600);
		}
	
	@Bean
    public ClassLoaderTemplateResolver templateResolver() {

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");

        return templateResolver;
    }
 	}
    
    @RestController
    public class Test{
    	
    	@GetMapping("/api/auth/val")
    	public String getAuthAPI() {
    		return "Subasis";
    	}
    }
}
