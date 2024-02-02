package com.example.demo.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.model.Card;
import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.HomeRepository;

@Service
public class ServiceImpl implements ServiceIntf{

	@Autowired
	HomeRepository hr;
	
	@Autowired
	AccountRepository ar;
	
	@Override
	public void registerUser(User u) {
		hr.save(u);
	}
	
	public User loginUser(String username, String password) {
		return hr.findByUserNameAndPassword(username, password);	
	}

	@Override
	public void createUserAccount(Account a, int userId) {
		User u=hr.findByUserId(userId);
//		
//		a.setPinNo(otp);
//		
		u.setAccount(a);
		
		hr.save(u);
		
		
	}

	@Override
	public int depositMoney(int amt, long accNo) {
		
		//User u = hr.findByUserId(userId);
		
		Account a=ar.findByAccNo(accNo);
		System.out.println(a.getAccId());
		a.setBalance(a.getBalance()+amt);
		
		//u.setAccount(a);
		ar.save(a);
		
		//hr.save(u);
		
		return a.getBalance();
		
	}
	
	  @Override
	public int withdrawMoney(int amt, long accNo) {
		  Account a=ar.findByAccNo(accNo);
			System.out.println(a.getAccId());
			a.setBalance(a.getBalance()-amt);
			
			ar.save(a);
		  
		return a.getBalance();
	}
	  
	  @Override
	public double getbalance(long accNo) {
		
		Account a = ar.findByAccNo(accNo);   
		return a.getBalance();
	}

	@Override
	public void applyCard(long accNo) {
		
		Account a = ar.findByAccNo(accNo);   
		
		Card c = new Card();
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		System.out.println(number);
		
		c.setCardNo(number);
		
		int randomPin   =(int) (Math.random()*9000)+1000; 
		String otp  = String.valueOf(randomPin); 
		System.out.println(otp);
		
		c.setPin(otp);
		
		LocalDate currentDate = LocalDate.now();
        int randomYear = currentDate.getYear() + new Random().nextInt(5); // Expiry date in the next 5 years
        int randomMonth = new Random().nextInt(12) + 1; // 1 to 12
        LocalDate expiryDate = LocalDate.of(randomYear, randomMonth, 1).plusMonths(1).minusDays(1);

        // Format the expiry date as MM/YYYY
        String expDt = String.format("%02d/%04d", expiryDate.getMonthValue(), expiryDate.getYear());
        System.out.println(expDt);
        
        c.setExpDate(expDt);
        
        int cvvNo   =(int) (Math.random()*900)+100; 
		String cvv  = String.valueOf(cvvNo); 
		System.out.println(cvv);
		
		c.setCvv(cvv);
        
        a.setCard(c);
        
        ar.save(a);
        
        
	}

	@Override
	public void fundTransfer(long sourceAccNo, long targetAccNo,int amt) {
	
		Account sourceAcc = ar.findByAccNo(sourceAccNo);  
		
		Account targetAcc = ar.findByAccNo(targetAccNo);
		
		targetAcc.setBalance(targetAcc.getBalance()+amt);
		
		ar.save(targetAcc);
		
		sourceAcc.setBalance(sourceAcc.getBalance()-amt);
		
		ar.save(sourceAcc);
		
		System.out.println(targetAcc.getBalance()+" "+sourceAcc.getBalance());
		
		
	}

	
	
	
}
