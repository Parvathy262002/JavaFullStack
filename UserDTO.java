package com.springrest.SpringRestAPI.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import com.springrest.SpringRestAPI.model.Account;
import com.springrest.SpringRestAPI.model.Address;

public class UserDTO {
	private String username;

	private String password;

	private String email;

	private LocalDate dob;

	private Set<Address> address = new HashSet<>();
	private List<Account> account = new ArrayList<>();

	public LocalDate getDob() {
		return dob;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

}
