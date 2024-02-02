package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.model.User;
import com.example.demo.service.ServiceIntf;

@RestController
public class HomeController {
	
	@Autowired
	ServiceIntf si;
	
	@GetMapping("/loginUser")
	public User login(@RequestParam (value = "userName") String username,@RequestParam (value = "password") String password)
	{
		return si.loginUser(username, password);
	}
	
	@PostMapping("/registerUser")
	public String registerUser(@RequestBody User u)
	{
		si.registerUser(u);
		return "You are successfully registered..!!";
	}
	
	@PostMapping("/createUserAccount/{userId}")
	public String createUserAccount(@RequestBody Account a,@PathVariable("userId") int userId)
	{
		si.createUserAccount(a, userId);
		return "Your account created successfully..!!";
	}
	
	@PostMapping("depositMoney")
	public String depositMoney(@RequestParam("amt") int amt, @RequestParam("accNo") long accNo)
	{
		System.out.println("****");
		int bal = si.depositMoney(amt, accNo);
		return "Money deposited successfully and your account balance is "+bal;
	}
	
	@PostMapping("withdrawMoney")
	public String withdrawMoney(@RequestParam("amt") int amt, @RequestParam("accNo") long accNo)
	{
		int bal = si.withdrawMoney(amt, accNo);
		return "Money withdraw successfully and your account balance is "+bal;
	}
	
	@GetMapping("/getbalance")
	public String getbalance(@RequestParam long accNo)
	{
		return "Your balance is " + si.getbalance(accNo);
		
	}
	
	@GetMapping("/applyCard")
	public String applyCard(@RequestParam long accNo)
	{
		si.applyCard(accNo);
		return "card applied";
	}
	
	@PostMapping("/fundTransfer")
	public String fundTransfer(@RequestParam long sourceAccNo,@RequestParam long targetAccNo,@RequestParam int amt)
	{
		si.fundTransfer(sourceAccNo, targetAccNo, amt);
		return "Fund Transfered";
	}

}
