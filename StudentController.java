package com.springrest.SpringRestAPI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.SpringRestAPI.model.Student;
import com.springrest.SpringRestAPI.service.StudentService;

//import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {
	@Autowired
	StudentService studService;
	@PostMapping("/saveStudent")
	public  Student  saveStudent(@Valid @RequestBody Student s)
	{
		return studService.saveStudent(s);
	}
	//http://localhost:9080/fetchStudents
	@GetMapping("/fetchStudents")
	public List<Student> getAllStudents()
	{
		return studService.getAllStudents();
	}
	
	@GetMapping("/fetchStudentById/{regno}")
	public Student getStudentById(@PathVariable("regno")  int rno)
	{
		return studService.getStudentById(rno);
	}
    @PutMapping("/updateStudent/{rno}")
    public ResponseEntity<Student> updateStudent(@PathVariable("rno") int regno, @Valid @RequestBody Student stud) {

       
        Student studObj = studService.getStudentById(regno);

        if (studObj != null) {
        
            studObj.setName(stud.getName());
            studObj.setDepartment(stud.getDepartment());
            return new ResponseEntity<>(studService.saveStudent(studObj), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
