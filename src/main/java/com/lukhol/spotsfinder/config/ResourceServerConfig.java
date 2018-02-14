package com.lukhol.spotsfinder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    
    private final String resourceIds = "resourcesId";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
        	.resourceId(resourceIds);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http
        	.requestMatchers()
        	.and()
        	.authorizeRequests()
        	//.antMatchers("/places").access("hasRole('USER') or hasRole('ADMIN')")
        	//.antMatchers("/places/**").access("hasRole('USER') or hasRole('ADMIN')")
        	//.antMatchers("/places/searches/**").authenticated()
        	.antMatchers("/**").permitAll();
    }
    
}