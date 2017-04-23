package tr.com.sample.registration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tr.com.sample.registration.constants.StudentRegistrationSql;
import tr.com.sample.registration.dao.StudentRegisterDao;
import tr.com.sample.registration.dao.mapper.StudentRowMapper;
import tr.com.sample.registration.model.Student;
import tr.com.sample.registration.model.StudentAddress;

@Repository
public class StudentRegisterDaoImpl implements StudentRegisterDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer createRegistration(final Student newStudent) {

		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(StudentRegistrationSql.SQL_NEW_STUDENT,
						Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, newStudent.getFirstName());
				ps.setString(2, newStudent.getSurName());
				ps.setString(3, newStudent.getPhoneNumber());

				StudentAddress studentAddress = newStudent.getStudentAddress();
				if (studentAddress != null) {

					ps.setString(4, studentAddress.getCity());
					ps.setString(5, studentAddress.getDistrict());
					ps.setString(6, studentAddress.getDescription());
				} else {

					ps.setString(4, null);
					ps.setString(5, null);
					ps.setString(6, null);
				}

				return ps;
			}
		}, holder);

		return holder.getKey().intValue();
	}

	public Student getRegistrationInfo(Integer studentNo) {

		return jdbcTemplate.queryForObject(StudentRegistrationSql.SQL_GET_STUDENT_BY_ID, new Object[] { studentNo },
				new StudentRowMapper());
	}

	public List<Student> getAllStudents() {
		return jdbcTemplate.query(StudentRegistrationSql.SQL_GET_STUDENTS, new Object[] {}, new StudentRowMapper());
	}

	public void deleteStudent(Integer studentNo) {

		jdbcTemplate.update(StudentRegistrationSql.SQL_DELETE_STUDENT, new Object[] { studentNo });
	}

	public void updateStudent(Student student) {

		StudentAddress studentAddress = student.getStudentAddress();
		jdbcTemplate.update(StudentRegistrationSql.SQL_UPDATE_STUDENT,
				new Object[] { student.getFirstName(), student.getSurName(), student.getPhoneNumber(),
						studentAddress.getCity(), studentAddress.getDistrict(), studentAddress.getDescription(),
						student.getStudentNo() });
	}

}
