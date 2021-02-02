package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.StudentDetails;

public interface IStudentDao extends JpaRepository<StudentDetails, Integer> {
	StudentDetails findByEmailAndPassword(String email,String pass);
	
}
