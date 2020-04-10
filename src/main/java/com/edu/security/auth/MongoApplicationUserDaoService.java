package com.edu.security.auth;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import static com.edu.security.security.UserRole.*;

@Repository("mongodb")
public class MongoApplicationUserDaoService implements IApplicationUserDao{
	
	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserRepository repository;
	
	public MongoApplicationUserDaoService(PasswordEncoder passwordEncoder,
			ApplicationUserRepository repository) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
	}
	
	@PostConstruct
	void init() {
		this.repository.deleteAll();
		this.repository.saveAll(this.getAppUsers());
	}

	@Override
	public Optional<ApplicationUser> selectAppUserByUsername(String username) {
		System.out.println("-----------------MONGODB DATA WAS CALLED------------------");
		
		return this.repository.findAll().stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst();
	}

	@Override
	public List<ApplicationUser> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationUser create(ApplicationUser usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationUser update(ApplicationUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationUser get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationUser delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<ApplicationUser> getAppUsers(){
		List<ApplicationUser> appUsers = Lists.newArrayList(
				new ApplicationUser(
						ADMIN.getGrantedAuthorities(),
						"admin",
						this.passwordEncoder.encode("admin"),
						true,
						true,
						true,
						true),
				new ApplicationUser(
						DOCTOR.getGrantedAuthorities(),
						"doctor",
						this.passwordEncoder.encode("doctor"),
						true,
						true,
						true,
						true),
				
				new ApplicationUser(
						INTERN.getGrantedAuthorities(),
						"intern",
						this.passwordEncoder.encode("intern"),
						true,
						true,
						true,
						true),
				new ApplicationUser(
						PERSON.getGrantedAuthorities(),
						"person",
						this.passwordEncoder.encode("person"),
						true,
						true,
						true,
						true)
				);
		return appUsers;
	}
	

}
