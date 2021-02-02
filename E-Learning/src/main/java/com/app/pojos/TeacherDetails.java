package com.app.pojos;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.app.pojos.ScheduleClass;
@Entity
@Table(name="TeacherDetails")
public class TeacherDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("teacher_id")
	private int teacherId;
	@Column(length = 30)
	private String fname;
	@Column(length = 30)
	private String lname;
	@Column(length = 20)
	private String address;
	@Column(length = 20)
	private String contact_number;
	@Column(length = 10)
	private int yearOfExp;
	@Column(length = 30)
	private String email;
	@Column(length = 20)
	private String password;
	@Column(length = 20)
	private String confirm_password;
//	@JsonIgnore
	@JsonIgnoreProperties("teacher")
	@OneToMany(mappedBy="teacher" ,cascade = CascadeType.ALL,orphanRemoval = false)
	private List<ScheduleClass> schedules=new ArrayList<>();
	public TeacherDetails() {
		super();
	}
	public TeacherDetails(int teacher_id, String fname, String lname, String address, String contact_number,
			int yearOfExp, String email, String password, String confirm_password, List<ScheduleClass> schedules) {
		super();
		teacherId = teacher_id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.contact_number = contact_number;
		this.yearOfExp = yearOfExp;
		this.email = email;
		this.password = password;
		this.confirm_password = confirm_password;
		this.schedules = schedules;
	}
	
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public int getYearOfExp() {
		return yearOfExp;
	}
	public void setYearOfExp(int yearOfExp) {
		this.yearOfExp = yearOfExp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	
	public List<ScheduleClass> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<ScheduleClass> schedules) {
		this.schedules = schedules;
	}
	@Override
	public String toString() {
		return "TeacherDetails [Teacher Id=" +teacherId + ", fname=" + fname + ", lname=" + lname + ", address="
				+ address + ", contact_number=" + contact_number + ", yearOfExp=" + yearOfExp + ", email=" + email
				+ ", password=" + password + ", confirm_password=" + confirm_password + "]";
	}
	
	
}
