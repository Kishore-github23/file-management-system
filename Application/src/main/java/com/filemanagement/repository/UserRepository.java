package com.filemanagement.repository;

import com.filemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//For login
	Optional<User>findByUsername(String username);
	
	//For registration validation
	Optional<User>findByEmail(String email);
	
	//Check if username already exists
	boolean existsByUsername(String username);
	
	//check if already email exists
	boolean existsByEmail(String email);
	
	
}
