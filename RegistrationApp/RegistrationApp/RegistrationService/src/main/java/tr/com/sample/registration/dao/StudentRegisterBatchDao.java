package tr.com.sample.registration.dao;

import java.util.List;

import tr.com.sample.registration.model.Student;

public interface StudentRegisterBatchDao {

	void createRegistration(final List<Student> newStudents);
}
