package com.edu.security.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {
	
	private final IApplicationUserDao appUserDao;
	
	public ApplicationUserService(@Qualifier("mongodb") IApplicationUserDao appUserDao) {
		this.appUserDao = appUserDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.appUserDao.selectAppUserByUsername(username)
				.orElseThrow(() -> 
				new UsernameNotFoundException(String.format("User %s not found", username)));
	}

}
