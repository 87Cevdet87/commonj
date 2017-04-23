package tr.com.sample.registration.dao;

import java.util.List;

import tr.com.sample.registration.model.StudentAddress;

public interface StudentAddressRegistrationDao {

	List<StudentAddress> getStudentAddressList(Integer studentNo);
}
