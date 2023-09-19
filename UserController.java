package com.springrest.SpringRestAPI.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.SpringRestAPI.dto.UserDTO;
import com.springrest.SpringRestAPI.exception.NoDataFoundException;
import com.springrest.SpringRestAPI.exception.ResourceNotFoundException;
import com.springrest.SpringRestAPI.model.Employee;
import com.springrest.SpringRestAPI.model.LoanModel;
import com.springrest.SpringRestAPI.model.LoginModel;
import com.springrest.SpringRestAPI.model.TransactionModel;
import com.springrest.SpringRestAPI.model.User;
import com.springrest.SpringRestAPI.service.UserService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	UserService usrService;

	// http://localhost:9080/api/users/checkLogin
	@PostMapping("/checkLogin")
	public String validateUser(@Valid @RequestBody LoginModel u) {

		return usrService.validateUser(u);
	}

	// http://localhost:9080/api/users/saveUser
	@PostMapping("/saveUser")
	public String saveUser(@RequestBody UserDTO user) {
		String result = "";
		
		/* if (user.getDob() == null) result = "Date is mandatory"; else {
		
		User u = usrService.saveUser(user);
		if (u != null)
			result = "User inserted";*/

		// }
		
return result;
	
	}

	@GetMapping("/fetchAccounts/{username}")
	public List<Integer> fetchAccounts(@PathVariable("username") String uname) {

		List<Integer> accountList = usrService.fetchAccounts(uname);
		return accountList;
	}

	
	@GetMapping("/fetchAccountDetails/{username}")
	public List<User> fetchAccountDetails(@PathVariable("username") String username) {

		List<User> userList = usrService.fetchAccountDetails(username);
		return userList;
	}
	
	
	@PostMapping("/applyLoan")
	public String applyLoan(@RequestBody LoanModel loan) {
		String result = "";
		result = usrService.applyLoan(loan);
		return result;
	}

	@GetMapping("/findAllCard/{empid}")
	public Employee findAllCard(@PathVariable("empid") String empid) {
		return usrService.findAllCard(empid);
	}

	@PutMapping("/withdraw")
	public String withdraw(@RequestBody TransactionModel transaction) throws Exception {

		return usrService.withdraw(transaction);
	}

	@GetMapping("/fetchAllUsers")
	public List<UserDTO> fetchAllUsers() throws NoDataFoundException {
	return usrService.fetchAllUsers().stream().map(u -> modelMapper.
			map(u, UserDTO.class))
				.collect(Collectors.toList());
/*		List<User> list=usrService.fetchAllUsers();
		List<UserDTO> ulist=new ArrayList<>();
		for(User u:list)
		{
			ulist.add(modelMapper.map(u,UserDTO.class));
			}*/
		//return ulist;
	}

	@DeleteMapping("/deleteEmployee/{empid}")
	public String deleteEmployee(@PathVariable("empid") String empid) {
		String result = usrService.deleteEmployee(empid);
		return result;
	}

	@GetMapping("/findUser/{username}")
	public UserDTO findUser(@PathVariable("username") String username) throws ResourceNotFoundException 
	{
		User user=usrService.findUser(username);
		// convert entity to DTO
		UserDTO u = modelMapper.map(user, UserDTO.class);
        return u;
		//return ResponseEntity.ok().body(u);
		
	}


}