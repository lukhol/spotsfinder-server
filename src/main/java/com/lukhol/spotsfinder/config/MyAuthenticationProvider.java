package com.lukhol.spotsfinder.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("======> MyAuthenticationProvider executed authentication for user {}", authentication.getName() + " <======");
		
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        if(name.equals("spotfinder") && password.equals("spotfinderSecret")){
        	List<GrantedAuthority> authorities = new ArrayList<>();
        	authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
            
        	return new UsernamePasswordAuthenticationToken("0", "user", authorities);
        }
        
		throw new BadCredentialsException("Not client request!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
