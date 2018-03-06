package com.AitBenOm.GymMonitor;

import com.AitBenOm.GymMonitor.Service.CORSFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GymmonitorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GymmonitorApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

	}
	@Bean
	public FilterRegistrationBean corsFilterRegistration(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CORSFilter());
		filterRegistrationBean.setName("CORS FILTER");
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setOrder(1);
		return  filterRegistrationBean;

	}
}
