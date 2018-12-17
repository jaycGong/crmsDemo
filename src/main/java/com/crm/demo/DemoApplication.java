package com.crm.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.DelegatingFilterProxy;
/***
 * 
 * @author jacy
 *
 */
@SpringBootApplication
public class DemoApplication
{
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	//过滤器
	@Bean
    public FilterRegistrationBean loginFilter() 
	{
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		DelegatingFilterProxy httpBasicFilter = new DelegatingFilterProxy();
		registrationBean.setFilter(httpBasicFilter);
		Map<String,String> m = new HashMap<String,String>();
		m.put("targetBeanName","userFilter");
		registrationBean.setInitParameters(m);
		return registrationBean;
    }
}
