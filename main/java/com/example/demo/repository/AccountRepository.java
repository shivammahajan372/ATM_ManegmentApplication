package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

	public Account findByAccNo(long accNo);
}
