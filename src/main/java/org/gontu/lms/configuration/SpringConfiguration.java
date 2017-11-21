package org.gontu.lms.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// equivalent to spring-dispatcher-servlet.xml
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.gontu.lms")
public class SpringConfiguration {
	
// can be added below code to create a web application and for validating forms	
//	@Bean
//	public ViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//
//		return viewResolver;
//	}
//	
//	@Bean
//	public MessageSource messageSource() {
//	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//	    messageSource.setBasename("messages");
//	    return messageSource;
//	}
	
//	@Bean
//	 JedisConnectionFactory jedisConnectionFactory() {
//	  return new JedisConnectionFactory();
//	 }
//
//	 @Bean
//	 RedisTemplate< String, Object > redisTemplate() {
//	  final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
//	  template.setConnectionFactory( jedisConnectionFactory() );
//	  template.setKeySerializer( new StringRedisSerializer() );
//	  template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
//	  template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
//	  return template;
//	 }
	
}