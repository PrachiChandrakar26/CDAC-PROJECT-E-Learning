package com.app.service;

import java.util.List;

import com.app.pojos.TeacherDetails;

public interface ITeacherService {
	// show all the details about teacher
	List<TeacherDetails> getAllTeacherDetails();
	// add new Teacher details
	TeacherDetails addTeacherDetails(TeacherDetails transientPOJO);																							
	TeacherDetails validateTeacherDetails(String Email,String pass);
	boolean deleteTeacherDetails(int Teacher_id);
}
