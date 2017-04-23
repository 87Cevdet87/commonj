package tr.com.sample.registration.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.sample.registration.dao.StudentRegisterBatchDao;
import tr.com.sample.registration.dao.StudentRegisterDao;
import tr.com.sample.registration.model.Student;
import tr.com.sample.registration.model.StudentAddress;
import tr.com.sample.registration.service.StudentRegistrationService;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

	@Autowired
	private StudentRegisterDao studentRegisterDao;

	@Autowired
	private StudentRegisterBatchDao studentRegisterBatchDao;

	@Transactional
	public void createRegistration(Student newStudent, StudentAddress newAddress) {

		newStudent.setStudentAddress(newAddress);
		Integer newStudentNo = studentRegisterDao.createRegistration(newStudent);

	}

	public Student getRegistrationInfo(Integer studentNo) {

		return studentRegisterDao.getRegistrationInfo(studentNo);
	}

	@Transactional
	public void updateStudentAddres(Student student, StudentAddress studentAddress) {

		student.setStudentAddress(studentAddress);
		studentRegisterDao.updateStudent(student);
	}

	public List<Student> getAllStudents() {
		return studentRegisterDao.getAllStudents();
	}

	@Transactional
	public void createRegistration(List<Map<String, Object>> batchValues) {

		List<Student> students = new ArrayList<Student>();
		Iterator<Map<String, Object>> batchValueIterator = batchValues.iterator();
		while (batchValueIterator.hasNext()) {

			Map<String, Object> nextBatchValue = batchValueIterator.next();
			Student student = new Student(
					nextBatchValue.get("FIRSTNAME") != null ? nextBatchValue.get("FIRSTNAME").toString() : null,
					nextBatchValue.get("SURNAME") != null ? nextBatchValue.get("SURNAME").toString() : null,
					nextBatchValue.get("PHONENUMBER") != null ? nextBatchValue.get("PHONENUMBER").toString() : null);

			StudentAddress studentAddress = new StudentAddress(
					nextBatchValue.get("CITY") != null ? nextBatchValue.get("CITY").toString() : null,
					nextBatchValue.get("DISTRICT") != null ? nextBatchValue.get("DISTRICT").toString() : null,
					nextBatchValue.get("DESC") != null ? nextBatchValue.get("DESC").toString() : null);

			student.setStudentAddress(studentAddress);

			students.add(student);
		}
		studentRegisterBatchDao.createRegistration(students);

	}

	@Transactional
	public void deleteStudent(Integer studentNo) {
		studentRegisterDao.deleteStudent(studentNo);

	}
}
