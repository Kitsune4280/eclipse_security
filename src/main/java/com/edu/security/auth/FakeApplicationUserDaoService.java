package com.edu.security.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import static com.edu.security.security.UserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements IApplicationUserDao {

	private final PasswordEncoder passwrodEncoder;
	
	public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
		this.passwrodEncoder = passwordEncoder;
	}
	
	@Override
	public Optional<ApplicationUser> selectAppUserByUsername(String username) {
		return this.getAppUsers().stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst();
	}
	
	private List<ApplicationUser> getAppUsers(){
		List<ApplicationUser> appUsers = Lists.newArrayList(
				new ApplicationUser(
						ADMIN.getGrantedAuthorities(),
						"admin",
						this.passwrodEncoder.encode("admin"),
						true,
						true,
						true,
						true),
				new ApplicationUser(
						DOCTOR.getGrantedAuthorities(),
						"doctor",
						this.passwrodEncoder.encode("doctor"),
						true,
						true,
						true,
						true),
				
				new ApplicationUser(
						INTERN.getGrantedAuthorities(),
						"intern",
						this.passwrodEncoder.encode("intern"),
						true,
						true,
						true,
						true),
				new ApplicationUser(
						PERSON.getGrantedAuthorities(),
						"person",
						this.passwrodEncoder.encode("person"),
						true,
						true,
						true,
						true)
				);
		return appUsers;
	}

	@Override
	public List<ApplicationUser> getAll() {
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

}
