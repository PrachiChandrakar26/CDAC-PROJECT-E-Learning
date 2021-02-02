package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.ScheduleClass;
import com.app.pojos.TeacherDetails;

public interface IScheduleDao extends JpaRepository<ScheduleClass, Integer> {

	List<ScheduleClass> findByClassnameAndSubject(String classname,String subject);
	List<ScheduleClass> findByTeacher(TeacherDetails userId);
	ScheduleClass findByScheduleId(int id);
}
