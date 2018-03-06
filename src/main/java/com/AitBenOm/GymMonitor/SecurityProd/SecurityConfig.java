package com.AitBenOm.GymMonitor.SecurityProd;

import com.AitBenOm.GymMonitor.Service.CORSFilter;
import com.AitBenOm.GymMonitor.Service.UserService;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        System.out.println("corsConfigurer");
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Configure");
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"Users/SingUp").permitAll()
                .antMatchers(HttpMethod.POST,"Users/saveFile").permitAll()
                .antMatchers("/Programs/**","/Exercises/**","/Loads/**","/Users/**").authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), userService))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),userService));
    }


}
