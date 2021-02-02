package com.app.service;

import java.util.List;

import com.app.pojos.StudentDetails;

public interface IStudentService {
	// add new Teacher details
	StudentDetails addStudentDetails(StudentDetails transientPOJO);																							
	StudentDetails validateStudentDetails(String Email,String pass);
	boolean deleteStudentDetails(int Student_id);
	public List<StudentDetails> getAllStudentDetails();
}
