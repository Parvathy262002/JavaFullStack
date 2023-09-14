package com.springrest.SpringRestAPI.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springrest.SpringRestAPI.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	@Query("select account.accountno from  Account account where account.user.username=?1")
	 public List<Integer> findByUsername(String username);
	
	@Query("select account.balance from  Account account where account.accountno=?1")
	 public double findByAccountNumber(int accountno);
	
	@Modifying
	//update account set balance=balance-10000 where accountno=10001
	@Query("update Account account set account.balance=account.balance-?1 where account.accountno=?2" )
	public int withdraw(double amount,int accountno);
	
	@Modifying
	@Query("update Account account set account.balance=account.balance+?1 where account.accountno=?2" )
	public int deposit(double amount,int accountno);
}
