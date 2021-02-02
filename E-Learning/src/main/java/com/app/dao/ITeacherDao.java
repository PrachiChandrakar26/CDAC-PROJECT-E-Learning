package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.TeacherDetails;

public interface ITeacherDao extends JpaRepository<TeacherDetails, Integer>{
	TeacherDetails findByEmailAndPassword(String email,String pass);
}
