package tr.com.sample.registration.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tr.com.sample.registration.dao.StudentRegisterDao;
import tr.com.sample.registration.model.Student;
import tr.com.sample.registration.service.StudentRegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class StudentRegistrationTestDao {

	
	@Inject
	private StudentRegistrationService studentRegistrationService;
	
	@Inject
	private StudentRegisterDao studentRegisterDao;

	@Test
	public void test() {
		List<Student> allStudents = studentRegistrationService.getAllStudents();
		assertThat(4, equalTo(allStudents.size()));
	}

	
	@Test
	@Transactional
	@Rollback(true)
	public void test2(){
		
		Student student = new Student();
		student.setFirstName("TEST");
		student.setSurName("TEST_TEST");
		student.setPhoneNumber("1234567890");
		
		Integer newStudentNo = studentRegisterDao.createRegistration(student);
		assertThat(newStudentNo, is(notNullValue()));
		
	}
}
