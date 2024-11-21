package com.swarmhr.gateway.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ATSCrosConfiguration {

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*"); // 1
		corsConfiguration.addAllowedHeader("*"); // 2
		corsConfiguration.addAllowedMethod("*"); // 3
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		// UrlBasedCorsConfigurationSource source = new
		// UrlBasedCorsConfigurationSource();
		// CorsConfiguration ccSource = buildConfig()
		// source.registerCorsConfiguration("/**", ccSource); // 4
		CorsConfigurationSource ccs = new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub
				CorsConfiguration corsConfiguration = new CorsConfiguration();
				corsConfiguration.addAllowedOrigin("*"); // 1
				corsConfiguration.addAllowedHeader("*"); // 2
				corsConfiguration.addAllowedMethod("*"); // 3
				return corsConfiguration;
			}
		};

		/// CorsConfigurationSource ccs = new UrlBasedCorsConfigurationSource();
		// CorsFilter cf = new CorsFilter();
		return new CorsFilter(ccs);
	}

}
