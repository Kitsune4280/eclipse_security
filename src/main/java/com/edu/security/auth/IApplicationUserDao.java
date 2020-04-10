package com.edu.security.auth;

import java.util.List;
import java.util.Optional;

public interface IApplicationUserDao {
	Optional<ApplicationUser> selectAppUserByUsername(String username);
	
	List<ApplicationUser> getAll();
	ApplicationUser create(ApplicationUser usr);
	ApplicationUser update(ApplicationUser user);
	ApplicationUser get(String id);
	ApplicationUser delete(String id);

}
