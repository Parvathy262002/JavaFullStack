package com.springrest.SpringRestAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.SpringRestAPI.dao.StudRepository;
import com.springrest.SpringRestAPI.model.Student;

@Service
public class StudentService {
@Autowired
StudRepository studRepo;
	public Student saveStudent(Student s) {
		//Student obj=studRepo.save(s);
		//return obj;

		return studRepo.save(s);
	}

	public List<Student> getAllStudents()
	{
		
		return studRepo.findAll();
	}
	
	public  Student getStudentById(int rno)
	{
		
		return studRepo.findById(rno).get();
	}
}
