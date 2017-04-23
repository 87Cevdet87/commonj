package tr.com.sample.registration.model;

import java.io.Serializable;

public class StudentAddress implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1038718061199337912L;

	private Integer studentAddressNo;

	private String city;

	private String district;

	private String description;

	public Integer getStudentAddressNo() {
		return studentAddressNo;
	}

	public void setStudentAddressNo(Integer studentAddressNo) {
		this.studentAddressNo = studentAddressNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StudentAddress(String city, String district, String description) {
		super();
		this.city = city;
		this.district = district;
		this.description = description;
	}

	public StudentAddress() {
		super();
	}

}
