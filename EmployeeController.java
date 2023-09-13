package com.springbackend.Springbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbackend.Springbackend.exception.ResourceNotFoundException;
import com.springbackend.Springbackend.model.Employee;
import com.springbackend.Springbackend.service.EmployeeService;

//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empService;

//http://localhost:8080/api/v1/getAllEmployees
	@GetMapping("/getAllEmployees")
	public List<Employee> getEmployees() {
		List<Employee> empList = empService.fetchEmployees();

		return empList;

	}

	// http://localhost:8080/api/v1/getEmployee/1
	@GetMapping("/getEmployee/{employeeId}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable("employeeId") int employeeId) //throws ResourceNotFoundException
		
	{
		Employee employee = empService.getEmployee(employeeId);
		if (employee==null)
		
		return new ResponseEntity<>("Invalid Employee Id",HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	// http://localhost:8080/api/v1/saveEmployee
	@PostMapping("/saveEmployee")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee emp) {

		Employee employee = empService.saveEmployee(emp);

		return new ResponseEntity<>("Employee added successsfully", HttpStatus.OK);
		//return employee;
	}

	// http://localhost:8080/api/v1/updateEmployee/2
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int employeeId,
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = empService.getEmployee(employeeId);

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = empService.saveEmployee(employee);
	//	return ResponseEntity.ok(updatedEmployee);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}

//http://localhost:8080/api/v1/deleteEmployee/1
	@DeleteMapping(value = "/deleteEmployee/{employeeId}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable("employeeId") int empId) {

		empService.deleteEmployee(empId);
		return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
	}
	/*
	 * @DeleteMapping("/deleteEmployee/{id}") public Map<String, Boolean>
	 * deleteEmployee(@PathVariable("id") int employeeId) throws
	 * ResourceNotFoundException { // Employee employee =
	 * empService.getEmployee(employeeId);
	 * 
	 * System.out.println("delete method called");
	 * empService.deleteEmployee(employeeId); Map<String, Boolean> response = new
	 * HashMap<>(); response.put("deleted", Boolean.TRUE); return response; }
	 */

}
