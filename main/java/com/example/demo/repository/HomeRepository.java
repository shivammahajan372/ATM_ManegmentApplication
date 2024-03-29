package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;
import com.example.demo.model.User;

@Repository
public interface HomeRepository extends CrudRepository<User, Integer>{
	
	public User findByUserNameAndPassword(String username,String password);
	
	public User findByUserId(int userId);
	
	

}
