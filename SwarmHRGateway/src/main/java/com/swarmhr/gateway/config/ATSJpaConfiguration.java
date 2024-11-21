package com.swarmhr.gateway.config;

//@EnableTransactionManagement
//@EntityScan(basePackages = {"com.swarmhr.ats.entity"})
//@EnableJpaRepositories(basePackages = {"com.swarmhr.ats.repository"})
//@Configuration
public class ATSJpaConfiguration { 
	
//	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
//    private static final String PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH = "spring.jpa.properties.hibernate.max_fetch_depth";
//    private static final String PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE = "spring.jpa.properties.hibernate.jdbc.fetch_size";
//    private static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "spring.jpa.properties.hibernate.jdbc.batch_size";
//    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "spring.jpa.properties.hibernate.show_sql";
//    private static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = {"com.swarmhr.ats.entity"};
//    
//    @Autowired
//	DataSource datasource;
//	
//	@Autowired
//    private Environment env;
//	
//	 @Bean
//     public JpaTransactionManager jpaTransactionManager() {
//         JpaTransactionManager transactionManager = new JpaTransactionManager();
//         transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
//         return transactionManager;
//     }
//
//    private HibernateJpaVendorAdapter vendorAdaptor() {
//         HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//         vendorAdapter.setShowSql(true);
//         return vendorAdapter;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
//
//         LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//         entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
//         entityManagerFactoryBean.setDataSource(datasource);
//         entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//         entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
//         entityManagerFactoryBean.set
//         entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
//
//         return entityManagerFactoryBean;
//     }
//    
//    
//     private Properties jpaHibernateProperties() {
//
//         Properties properties = new Properties();
//
//         properties.put(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH, env.getProperty(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH));
//         properties.put(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE, env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE));
//         properties.put(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE, env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE));
//         properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
//
////         properties.put(AvailableSettings.SCHEMA_GEN_DATABASE_ACTION, "none");
////         properties.put(AvailableSettings.USE_CLASS_ENHANCER, "false");      
//         return properties;       
//     }

}
