package com.app.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IStudentDao;
import com.app.pojos.StudentDetails;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {																					
	@Autowired
	private IStudentDao dao;
	@Override
	public List<StudentDetails> getAllStudentDetails()
	{
		return dao.findAll();
	}
	
	@Override
	public StudentDetails addStudentDetails(StudentDetails tPOJO)
	{
		return dao.save(tPOJO);
	}
	@Override
	public StudentDetails validateStudentDetails(String Email,String pass)
	{
		return dao.findByEmailAndPassword(Email,pass);
	}
	@Override
	public boolean deleteStudentDetails(int Student_id)
	{
		Optional<StudentDetails> sc = dao.findById(Student_id);
		if (sc.isPresent()) {
			dao.deleteById(Student_id);
			return true;
		}
		return false;
	}
	
	public void sendemail(String message ,String subject,String to,String from ) throws UnsupportedEncodingException
	{
		String host="smtp.gmail.com";
		  Properties props = System.getProperties();
		  props.setProperty("mail.transport.protocol", "smtp");     
		    props.setProperty("mail.host", "smtp.gmail.com");  
		    props.put("mail.smtp.auth", "true");  
		    props.put("mail.smtp.port", "465");  
		    props.put("mail.debug", "true");  
		    props.put("mail.smtp.socketFactory.port", "465");  
		    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
		    props.put("mail.smtp.socketFactory.fallback", "false");  
		  
		  /*
		  properties.put("mail.smtp.host", host);
		  properties.put("mail.smtp.port", "587");
		  properties.put("mail.smtp.auth", "true");
		  */

		  Session session = Session.getInstance(props, new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("prachichandrakar1998@gmail.com", "@gmail1998");
			}
			
			  
		});
		  session.setDebug(true);
		  
		  MimeMessage m=new MimeMessage(session);
		  
		  
		  try {
			m.setFrom(new InternetAddress(from));
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(message);
			Transport.send(m);
			System.out.println("Email Sent Successfully");
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		  
		
		  
		  
		  
		  
	}
	


}
