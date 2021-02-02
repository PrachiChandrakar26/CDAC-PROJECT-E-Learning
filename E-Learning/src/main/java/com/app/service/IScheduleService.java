package com.app.service;

import java.util.List;

import com.app.pojos.ScheduleClass;
import com.app.pojos.TeacherDetails;

public interface IScheduleService {
	// List all ScheduleClasses
	ScheduleClass addClassByTeacher(ScheduleClass transientPOJO);
	// get Scheduled Class details by subject and classname
	List<ScheduleClass> getClassesAvailableByClassAndSubject(String classname,String subject);                                                                                
	ScheduleClass updateScheduleDetails(int schedule_id,ScheduleClass s);
	boolean deleteScheduleDetails(int schedule_id);
	public List<ScheduleClass> viewClassesAvailableByUserId(TeacherDetails userId);                                                                               
	public ScheduleClass getScheduleDetailsById(int scheduleId);   
	public ScheduleClass saveSchedule(ScheduleClass schedule);
	public List<ScheduleClass> getAllScheduleDetails();                                                                              
}
