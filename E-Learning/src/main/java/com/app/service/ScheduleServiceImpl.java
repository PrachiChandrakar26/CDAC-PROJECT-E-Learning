package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IScheduleDao;
import com.app.pojos.ScheduleClass;
import com.app.pojos.TeacherDetails;


@Service
@Transactional
public class ScheduleServiceImpl implements IScheduleService {
	// dependency
	@Autowired
	private IScheduleDao dao;
	
	@Override
	public ScheduleClass addClassByTeacher(ScheduleClass scheduleClass) {
		System.out.println("in service "+scheduleClass+" "+scheduleClass.getTeacher());
		System.out.println("schedules "+scheduleClass.getTeacher().getSchedules());
		//add the link from Teacher  ---> schedule class :Madhura
		scheduleClass.getTeacher().getSchedules().add(scheduleClass);
		return dao.save(scheduleClass);
	//	return null;
	}
	
	@Override
	public ScheduleClass getScheduleDetailsById(int scheduleId)                                                                                
	{
        return dao.findByScheduleId(scheduleId);
	}
	
	@Override
	public List<ScheduleClass> viewClassesAvailableByUserId(TeacherDetails userId)                                                                                
	{
        return dao.findByTeacher(userId);
	}
	@Override
	public List<ScheduleClass> getAllScheduleDetails()                                                                                
	{
        return dao.findAll();
	}
	
	@Override
	public List<ScheduleClass> getClassesAvailableByClassAndSubject(String classname,String subject)                                                                                
	{
        return dao.findByClassnameAndSubject(classname,subject);
	}
	@Override
	public ScheduleClass updateScheduleDetails(int schedule_id,ScheduleClass s)
	{
		Optional<ScheduleClass> sc = dao.findById(schedule_id);
		if (sc.isPresent()) {
			ScheduleClass s_class = sc.get();
			s_class.setClassname(s.getClassname());
			s_class.setSubject(s.getSubject());
			s_class.setTopic(s.getTopic());
			s_class.setClass_date(s.getClass_date());
			s_class.setClass_Link(s.getClass_Link());
			
			return s_class;
		}
		return null;
	}
	@Override
	public boolean deleteScheduleDetails(int schedule_id)
	{
		Optional<ScheduleClass> sc = dao.findById(schedule_id);
		if (sc.isPresent()) {
			dao.deleteById(schedule_id);
			return true;
		}
		return false;
	}

	@Override
	public ScheduleClass saveSchedule(ScheduleClass sch) {
		
		return dao.save(sch);
	}
}














