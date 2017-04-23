package tr.com.sample.registration.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tr.com.sample.registration.constants.Sql;
import tr.com.sample.registration.dao.mapper.StudentAddressRowMapper;
import tr.com.sample.registration.model.StudentAddress;

@Repository
public class StudentAddressRegistrationDaoImpl implements StudentAddressRegistrationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<StudentAddress> getStudentAddressList(Integer studentNo) {

		return jdbcTemplate.query(Sql.SQL_FETCH_STUDENT_ADDRESS, new Object[]{studentNo}, new StudentAddressRowMapper());
	}

	
	public void deleteStudentAddress(Integer studentAddressNo){
		
		jdbcTemplate.update(Sql.SQL_DELETE_STUDENT_ADDRESS, new Object[]{studentAddressNo});
	}
	
	public void updateStudentAddres(StudentAddress studentAddress){
		
		jdbcTemplate.update(Sql.SQL_UPDATE_STUDENT_ADDRESS, new Object[]{studentAddress.getCity(),studentAddress.getDistrict(),studentAddress.getDescription(),studentAddress.getAddressNo()});
	}
}
