package com.springrest.SpringRestAPI.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.SpringRestAPI.dao.AccountRepository;
import com.springrest.SpringRestAPI.dao.EmpRepository;
import com.springrest.SpringRestAPI.dao.EmployeeCardRepository;
import com.springrest.SpringRestAPI.dao.LoanRepository;
import com.springrest.SpringRestAPI.dao.UserRepository;
import com.springrest.SpringRestAPI.dto.UserDTO;
import com.springrest.SpringRestAPI.exception.NoDataFoundException;
import com.springrest.SpringRestAPI.exception.ResourceNotFoundException;
import com.springrest.SpringRestAPI.model.Account;
import com.springrest.SpringRestAPI.model.Employee;
import com.springrest.SpringRestAPI.model.EmployeeCard;
import com.springrest.SpringRestAPI.model.EmployeeIssue;
import com.springrest.SpringRestAPI.model.Loan;
import com.springrest.SpringRestAPI.model.LoanModel;
import com.springrest.SpringRestAPI.model.LoginModel;
import com.springrest.SpringRestAPI.model.Transaction;
import com.springrest.SpringRestAPI.model.TransactionModel;
import com.springrest.SpringRestAPI.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	EmployeeCardRepository empCardRepo;
	@Autowired
	LoanRepository loanRepo;
	@Autowired
	EmpRepository empRepo;
public String validateUser(LoginModel u)
{
	String result="";
	User user=null;

	Optional<User> obj=userRepo.findById(u.getUsername());
	if(obj.isPresent())
	{
		user=obj.get();
	}
	if(user==null)
	{ 		result="Invalid user";
	}
		else
		{
			if(u.getPassword().equals(user.getPassword()))
			{				result="Login success"; 			}
			else
			{
				result="Login failed";
			} 		}
		return result;
	}
public User saveUser(User user) {
	
	return userRepo.save(user);
}
@Transactional
public String applyLoan(LoanModel loanModel) {
	String result="";
	
	EmployeeCard ecard=new EmployeeCard();
	Employee emp=empRepo.findById(loanModel.getEmployeeId()).get();
	String loanid=loanRepo.findByLoanType(loanModel.getItemCategory());
	Loan loan=loanRepo.findById(loanid).get();
	ecard.setCardId(3);
	ecard.setEmp(emp);
	ecard.setLoan(loan);
	ecard.setCardIssueDate(LocalDate.now());	
	EmployeeCard obj=empCardRepo.save(ecard);
	
	//inserting into issue table
	EmployeeIssue empIssue=new EmployeeIssue();
	
	if(obj!=null)
		result="Loan applied";
		return result;
}
public List<Integer> fetchAccounts(String uname) {
	return accountRepo.findByUsername(uname);
	
}
	
	public Employee findAllCard(String empid)
	{
//	List<EmpCardModel> cardList=new ArrayList<>();	
	//	List<Employee> empList= empRepo.findAll();
		return empRepo.findAllCard(empid);
	
	}
	@Transactional
	public String withdraw(TransactionModel transaction) throws Exception {
		String result="";
		Account account=accountRepo.findById(transaction.getAccountNumber()).get();
		double balance=account.getBalance();
		if(balance-transaction.getAmount() < 1000)
			result="Insufficient balance";
		else
		{
			//update the balance in the account table
			int rowsAffected=accountRepo.withdraw(transaction.getAmount(), transaction.getAccountNumber());
			if(rowsAffected>0)
			{
				Transaction trans=new Transaction();
				trans.setAmount(transaction.getAmount());
				trans.setTransType(transaction.getTransactionType());
				trans.setTransactionId(1000001);
				//transRepo.save(trans);
				result="Transaction success";
			
			}
			
		}
		
		return result;
	}
	public List<User> fetchAllUsers() throws NoDataFoundException 
	{
		List<User> userList=new ArrayList<>();
		//userList=userRepo.findAll();
	//return userList;
		if(userList.size()==0)
			throw new NoDataFoundException("No data to display");
		else
		return userList;
	}
	
	public User findUser(String username) throws ResourceNotFoundException
	{
		User u=userRepo.findById(username).orElse(null);
		//User u=userRepo.findById(username).get();
		if (u==null)
		throw new ResourceNotFoundException("User not found");
		else
		return u;
	}
	
	public String deleteEmployee(String empid) {
		empRepo.deleteById(empid);
		return "deleted";
	}
	
	public List<User> fetchAccountDetails(String username)
	{
		return userRepo.fetchAccountDetails(username);
	}
	}

