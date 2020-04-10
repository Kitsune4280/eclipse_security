package com.edu.security.auth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {
	
}
