package com.prateek.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prateek.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.username = :username")
	User findByUsername(@Param("username") String username);

}
