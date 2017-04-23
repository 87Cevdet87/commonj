package tr.com.sample.registration.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import tr.com.sample.registration.model.Student;

public class StudentRowMapper implements
		org.springframework.jdbc.core.RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

		Student student = new Student();
		student.setFirstName(rs.getString("FIRSTNAME"));
		student.setSurName(rs.getString("SURNAME"));
		student.setPhoneNumber(rs.getString("PHONE_NUMBER"));
		student.setStudentNo(rs.getInt("STUDENTNO"));
		
		return student;
	}

}
