package tr.com.sample.registration.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import tr.com.sample.registration.model.Student;
import tr.com.sample.registration.model.StudentAddress;

public class StudentRowMapper implements org.springframework.jdbc.core.RowMapper<Student> {

	// @Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

		Student student = new Student();
		student.setFirstName(rs.getString("FIRSTNAME"));
		student.setSurName(rs.getString("SURNAME"));
		student.setPhoneNumber(rs.getString("PHONE_NUMBER"));
		student.setStudentNo(rs.getInt("STUDENTNO"));

		StudentAddress address = new StudentAddress();
		address.setCity(rs.getString("CITY"));
		address.setDescription(rs.getString("DESC"));
		address.setDistrict(rs.getString("DISTRICT"));
		student.setStudentAddress(address);

		return student;

		
	}

}
