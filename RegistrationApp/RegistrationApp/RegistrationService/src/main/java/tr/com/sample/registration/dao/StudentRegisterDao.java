package tr.com.sample.registration.dao;

import java.util.List;

import tr.com.sample.registration.model.Student;

public interface StudentRegisterDao {

	Integer createRegistration(Student newStudent);

	Student getRegistrationInfo(Integer studentNo);

	List<Student> getAllStudents();

	void deleteStudent(Integer studentNo);

	void updateStudent(Student student);

}
