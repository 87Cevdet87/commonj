package tr.com.sample.registration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public abstract class RowMapperTemplate<T> implements RowMapper<T>{

	
	protected  T mapRows(ResultSet rs, int rowNum) throws SQLException{
		
		if( rs.next() ){
			
			return mapRow(rs,rowNum);
		}
		
		return null;
	}
	
	public abstract T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
