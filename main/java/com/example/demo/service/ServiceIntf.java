package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.User;

public interface ServiceIntf {
	
	public void registerUser(User u);
	
	public User loginUser(String username, String password);
	
	public void createUserAccount(Account a, int userId);
	
	public int depositMoney(int amt,long accNo);
	
	public int withdrawMoney(int amt, long accNo);
	
	public double getbalance(long accNo);
	
	public void applyCard(long accNo);
	
	public void fundTransfer(long sourceAccNo,long targetAccNo,int amt);

}
