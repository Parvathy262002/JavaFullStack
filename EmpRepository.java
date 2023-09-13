package com.springrest.SpringRestAPI.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springrest.SpringRestAPI.model.EmpCardModel;
import com.springrest.SpringRestAPI.model.Employee;
@Repository
public interface EmpRepository extends JpaRepository<Employee, String>{
 
	@Query("SELECT  distinct e FROM Employee e inner JOIN e.empCard c WHERE e.empid=?1")
	public List<Employee> findAllCard(String employeeid);
	
}
