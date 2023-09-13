package com.springrest.SpringRestAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.SpringRestAPI.model.Loan;

public interface LoanRepository extends JpaRepository<Loan,String> {
 
	@Query("select loan.loanid from Loan loan where loan.loanType=?1")
	public String findByLoanType(String itemCategory);

}






