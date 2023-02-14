package com.masai.authrepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.authmodels.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUserName(String userName);

}
