package tr.com.sample.registration.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tr.com.sample.registration.model.StudentAddress;

public class StudentAddressRowMapper implements RowMapper<StudentAddress> {

	@Override
	public StudentAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		StudentAddress address = new StudentAddress();
		address.setAddressNo(rs.getInt("STUDENTADDRESSNO"));
		address.setCity(rs.getString("CITY"));
		address.setDescription(rs.getString("DESCRIPTION"));
		address.setDistrict(rs.getString("DISTRICT"));
		return address;
	}

}
