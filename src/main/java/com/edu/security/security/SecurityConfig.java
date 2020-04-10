package com.edu.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.edu.security.auth.ApplicationUserService;
import com.edu.security.jwt.JwtConfig;
import com.edu.security.jwt.JwtTokenVerifier;
import com.edu.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;

import javax.crypto.SecretKey;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final PasswordEncoder passwordEncoder;
	private ApplicationUserService appUserService;
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	
	@Autowired
	public SecurityConfig(PasswordEncoder passwordEncoder,
			ApplicationUserService appUserService, 
			JwtConfig jwtConfig, SecretKey secretKey) {
		this.passwordEncoder = passwordEncoder;
		this.appUserService = appUserService;
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.csrf().disable()
		
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
		.addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
		
		.authorizeRequests()
		.antMatchers("/", "/css", "/js").permitAll()
				/*
				 * .antMatchers(HttpMethod.GET,
				 * "/api/person/get/**").hasAuthority(PERSON_READ.getPermission())
				 * .antMatchers(HttpMethod.POST,
				 * "/api/person/**").hasAuthority(PERSON_WRITE.getPermission())
				 * .antMatchers(HttpMethod.GET,
				 * "/api/intern/get/**").hasAuthority(INTERN_READ.getPermission())
				 * .antMatchers(HttpMethod.GET,
				 * "/api/doctor/get/**").hasAuthority(DOCTOR_READ.getPermission())
				 * .antMatchers(HttpMethod.POST,
				 * "/api/intern/**").hasAuthority(INTERN_WRITE.getPermission())
				 * .antMatchers(HttpMethod.POST,
				 * "/api/doctor/**").hasAuthority(DOCTOR_WRITE.getPermission())
				 * .antMatchers(HttpMethod.DELETE,
				 * "/api/person/**").hasAuthority(PERSON_WRITE.getPermission())
				 * .antMatchers(HttpMethod.DELETE,
				 * "/api/intern/**").hasAuthority(INTERN_WRITE.getPermission())
				 * .antMatchers(HttpMethod.DELETE,
				 * "api/doctor/**").hasAuthority(DOCTOR_WRITE.getPermission())
				 */
		.anyRequest()
		.authenticated();
		
//		.and()
//		 .formLogin() 
//		 .loginPage("/login").permitAll() 
//		 .defaultSuccessUrl("/welcome", true)
//		 .passwordParameter("password") 
//		 .usernameParameter("username") 
//		 .and()
//		 .rememberMe()
//		 .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(10))
//		 .key("john-lennon").rememberMeParameter("remember-me")
//		 .and()
//		 .logout()
//		 .logoutUrl("/logout")
//		 .clearAuthentication(true)
//		 .invalidateHttpSession(true)
//		 .deleteCookies("JSESSIONID", "remember-me")
//		 .logoutSuccessUrl("/login");
		
		
		//.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(daoAuthProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(appUserService);
		return provider;
	}
	
//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails doctor = User
//				.builder()
//				.username("doctor")
//				.password(passwordEncoder.encode("doctor"))
//				.authorities(DOCTOR.getGrantedAuthorities())
//				.build();
//		
//		UserDetails admin = User
//				.builder()
//				.username("admin")
//				.password(passwordEncoder.encode("admin"))
//				.authorities(ADMIN.getGrantedAuthorities())
//				.build();
//		
//		UserDetails person = User
//				.builder()
//				.username("person")
//				.password(this.passwordEncoder.encode("person"))
//				.authorities(PERSON.getGrantedAuthorities())
//				.build();
//		
//		UserDetails intern = User
//				.builder()
//				.username("intern")
//				.password(this.passwordEncoder.encode("intern"))
//				.authorities(INTERN.getGrantedAuthorities())
//				.build();
//		
//		return new InMemoryUserDetailsManager(
//				doctor,
//				admin,
//				person,
//				intern
//		);
//	}

}
