package tr.com.sample.registration.service;

import java.util.List;
import java.util.Map;

import tr.com.sample.registration.model.Student;
import tr.com.sample.registration.model.StudentAddress;

public interface StudentRegistrationService {

	void createRegistration(Student newStudent, StudentAddress newAddress);

	Student getRegistrationInfo(Integer studentNo);

	void updateStudentAddres(Student selectedStudent, StudentAddress studentAddress);

	List<Student> getAllStudents();
	
	void createRegistration(List<Map<String, Object>> batchValues);

	void deleteStudent(Integer studentNo);
}
