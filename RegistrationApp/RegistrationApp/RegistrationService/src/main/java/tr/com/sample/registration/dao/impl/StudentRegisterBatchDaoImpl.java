package tr.com.sample.registration.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tr.com.sample.registration.constants.StudentRegistrationSql;
import tr.com.sample.registration.dao.StudentRegisterBatchDao;
import tr.com.sample.registration.model.Student;
import tr.com.sample.registration.model.StudentAddress;

@Repository
public class StudentRegisterBatchDaoImpl implements StudentRegisterBatchDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createRegistration(final List<Student> newStudents) {

		String batchSql = StudentRegistrationSql.SQL_NEW_STUDENT;
		
		jdbcTemplate.batchUpdate(batchSql, new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Student newStudent = newStudents.get(i);
				StudentAddress newStudentAddress = newStudent.getStudentAddress();
				ps.setObject(1, newStudent.getFirstName());
				ps.setObject(2, newStudent.getSurName());
				ps.setObject(3, newStudent.getPhoneNumber());
				ps.setObject(4, newStudentAddress.getCity());
				ps.setObject(5, newStudentAddress.getDistrict());
				ps.setObject(6, newStudentAddress.getDescription());
			}

			public int getBatchSize() {
				return newStudents.size();
			}
		});
		
		
	}
}
