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

import com.app.pojos.TeacherDetails;
import com.app.service.TeacherServiceImpl;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherController {
	@Autowired
	public TeacherServiceImpl service;
	
	@GetMapping
	public ResponseEntity<?> listAllTeacherDetails() {
		System.out.println("in list all Teacher's details");
		// invoke service layer's method : controller --> service impl (p) --->JPA
		// repo's impl class(SC)
		List<TeacherDetails> teachers = service.getAllTeacherDetails();
		if (teachers.isEmpty())
			// empty teacher detail list : set sts code : HTTP 204 (no contents)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		// in case of non empty list : OK, send the list
		return new ResponseEntity<>(teachers, HttpStatus.OK);
	}
	
	// request handling method to create a new Teacher details : POST																													
	@PostMapping
	public ResponseEntity<?> addTeacherDetails(@RequestBody TeacherDetails t)throws UnsupportedEncodingException
	{
		System.out.println("in addTeacherDetails"+ t );
		try {
			String message="This Message from E learning You Registered Successfully So Smile :) and Learn";
			String subject="E-Learning";
			String from="prachichandrakar1998@gmail.com";
			String to=t.getEmail();
			service.sendemail(message, subject, to, from);
			
			TeacherDetails savedTeacherDetails= service.addTeacherDetails(t);
			return new ResponseEntity<>(savedTeacherDetails, HttpStatus.OK);
		
		}catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}
	@PostMapping("/login")
	public TeacherDetails validateTeacherDetails(@RequestBody TeacherDetails t) {
		System.out.println("in validateTeacherDetails");
		String em=t.getEmail();
		String pass=t.getPassword();
		TeacherDetails teachers = service.validateTeacherDetails(em,pass);
	
		return teachers;
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteTeacherDetails(@PathVariable int id) {
		System.out.println("in delete " + id );
		try {
			boolean deleteTeacherDetails = service.deleteTeacherDetails(id);
			return new ResponseEntity<>(deleteTeacherDetails, HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
