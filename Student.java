package com.springrest.SpringRestAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="student_details")
public class Student {
	@Id
	@Column(name="rollno")
	@Min(value = 1000, message = "Roll number must be greater or equal to 1000")
   @Max(value = 99999, message = "Roll number must be less than or equal to 99999")
	private int regno;
	
	@Column(name="stud_name",length=30,nullable=false)
	@NotBlank(message = "Student name cannot be blank")
	@Length(min = 5, max = 30, message = "Student name must be between 5-30 characters")
	private String name;
	
	@Column(name="dept")
	private String department;
	
	@Email(message="Email is invalid")
	private String email;
	
	public Student()
	{}
	public int getRegno() {
		return regno;
	}
	public void setRegno(int regno) {
		this.regno = regno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return String.format("Student [regno=%s, name=%s, department=%s]", regno, name, department);
	}
	
	
	

}
