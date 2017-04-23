package tr.com.sample.registration.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6375585203076524261L;

	private Integer studentNo;

	private String firstName;

	private String surName;

	private String phoneNumber;

	private StudentAddress studentAddress;

	public Integer getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(Integer studentNo) {
		this.studentNo = studentNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public StudentAddress getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(StudentAddress studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getFullName() {

		return firstName.concat(" ").concat(surName);
	}

	public Student(String firstName, String surName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.surName = surName;
		this.phoneNumber = phoneNumber;
	}

	public Student() {
		super();
	}

}
