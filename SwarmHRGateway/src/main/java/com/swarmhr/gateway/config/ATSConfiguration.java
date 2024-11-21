package com.swarmhr.gateway.config;

import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.google.cloud.storage.Storage;
import com.swarmhr.gateway.util.GoogleStorageUtility;

//import com.google.cloud.storage.Storage;
//import com.swarmhr.user.util.GoogleStorageUtility;

@EnableAsync
@Configuration
@Import(value = ATSJpaConfiguration.class)
public class ATSConfiguration {

	private final int TIMEOUT = (int) TimeUnit.SECONDS.toMillis(10);

//	@Value("${spring.redis.host}")
//	String redisHost;
//	
//	@Value("${spring.redis.host}")
//	String redisPort;

	@Value("${spring.profile}")
	String currEnv;

	@Autowired
	Storage storage;

	@PersistenceContext
	EntityManager em;

//	@Value("${swarmhr.solr.url}")
//	String swarmhrSolrURL;
//	
//	@Value("${swarmhr.solr.username}")
//	String swarmhrSolrUserName;
//
//	@Value("${swarmhr.solr.secret}")
//	String swarmhrSolrUserSecret;

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH", "OPTIONS"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return (CorsConfigurationSource)source.;
//	}

	@Bean("Dataload-TP-TaskExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1000);
		executor.setMaxPoolSize(2000);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("Async-");
		return executor;
	}

	@Bean 
	public GoogleStorageUtility googleStorageUtility()throws Exception {
		return new GoogleStorageUtility(storage, currEnv );
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate(getRequestFactory());
	}

	private ClientHttpRequestFactory getRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

		factory.setReadTimeout(TIMEOUT);
		factory.setConnectTimeout(TIMEOUT);
		factory.setConnectionRequestTimeout(TIMEOUT);
		return factory;
	}

}
