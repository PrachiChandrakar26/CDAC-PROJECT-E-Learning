package com.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.StudentDetails;
import com.app.service.StudentServiceImpl;

@RestController 
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")

public class StudentController {
	@Autowired
	public StudentServiceImpl service;

	
	@GetMapping
	public ResponseEntity<?> listAllTeacherDetails() {
		System.out.println("in list all Teacher's details");
		// invoke service layer's method : controller --> service impl (p) --->JPA
		// repo's impl class(SC)
		List<StudentDetails> teachers = service.getAllStudentDetails();
		if (teachers.isEmpty())
			// empty teacher detail list : set sts code : HTTP 204 (no contents)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		// in case of non empty list : OK, send the list
		return new ResponseEntity<>(teachers, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<?> addStudentDetails(@RequestBody StudentDetails s) throws UnsupportedEncodingException
	{
		System.out.println("in addStudentDetails"+ s );
		try {
			StudentDetails savedStudentDetails= service.addStudentDetails(s);
			String message="This Message from E learning You Registered Successfully So Smile :) and Learn";
			String subject="E-Learning";
			String from="prachichandrakar1998@gmail.com";
			String to=s.getEmail();
			service.sendemail(message, subject, to, from);
			return new ResponseEntity<>(savedStudentDetails, HttpStatus.OK);
		
		}catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}
	@PostMapping("/login")
	public StudentDetails validateStudentDetails(@RequestBody StudentDetails s) {
		System.out.println("in validateStudentDetails");
		String em=s.getEmail();
		String pass=s.getPassword();
		StudentDetails Students = service.validateStudentDetails(em, pass);
		if(Students!=null)
			return Students;
		return null;
	}
	@DeleteMapping("delete/{Student_id}")
	public ResponseEntity<?> deleteStudentDetails(@PathVariable int Student_id) {
		System.out.println("in delete " + Student_id );
		try {
			boolean deleteStudentDetails = service.deleteStudentDetails(Student_id);
			return new ResponseEntity<>(deleteStudentDetails, HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
