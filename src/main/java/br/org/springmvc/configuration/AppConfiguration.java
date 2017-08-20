package br.org.springmvc.configuration;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.mchange.v2.c3p0.DriverManagerDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "br.org.springmvc")
public class AppConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	
	@Value("${STATIC_ROOT:/opt/static/}")
	private String staticRoot;
	
	private ApplicationContext applicationContext;
	
	@Bean
	public ITemplateResolver templateResolver(ServletContext servletContext) {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		return templateResolver;
	}
	
	@Bean
	public TemplateEngine templateEngine(ITemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(templateResolver);
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver viewResolver(TemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		viewResolver.setApplicationContext(applicationContext);
		viewResolver.setOrder(1);
		return viewResolver;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		ViewResolver viewResolver = applicationContext.getBean(ThymeleafViewResolver.class);
		registry.viewResolver(viewResolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("file:"+staticRoot,"/static/");
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(true);
		dataSource.setDriverClass("org.sqlite.JDBC");
		dataSource.setJdbcUrl("jdbc:sqlite:banco.db");
		dataSource.setUser("");
		dataSource.setPassword("");		
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("br.org.springmvc");
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto","update");
		
		properties.setProperty("hibernate.connection.characterEncoding","ISO-8859-1");
		
		properties.setProperty("hibernate.c3p0.min_size","50");
		properties.setProperty("hibernate.c3p0.max_size","200");
		properties.setProperty("hibernate.c3p0.timeout","300");
		properties.setProperty("hibernate.c3p0.max_statements","50");
		properties.setProperty("hibernate.c3p0.idle_test_period","3000");
		

		sessionFactory.setHibernateProperties(properties);
		return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
		HibernateTransactionManager manager = new HibernateTransactionManager();
		manager.setSessionFactory(sessionFactory.getObject());
		return manager;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}