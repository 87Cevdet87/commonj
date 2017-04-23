package tr.com.sample.registration.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

import com.opencsv.CSVReader;

import tr.com.sample.registration.constants.ViewMode;
import tr.com.sample.registration.model.City;
import tr.com.sample.registration.model.District;
import tr.com.sample.registration.model.Student;
import tr.com.sample.registration.model.StudentAddress;
import tr.com.sample.registration.service.AddressProviderService;
import tr.com.sample.registration.service.StudentRegistrationService;

@Named("studentRegisterBean")
@ViewScoped
public class StudentRegistrationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -946673399137753881L;

	@Inject
	private StudentRegistrationService studentRegistrationService;

	@Inject
	private AddressProviderService addressProviderService;

	private List<Student> students;

	private List<StudentAddress> studentAddressList;

	private ViewMode viewMode;

	private List<Student> filteredStudents;

	private Student selectedStudent;

	private StudentAddress selectedStudentAddress;

	private List<City> cities;

	private List<District> districts;

	@PostConstruct
	public void init() {

		loadStudents();
	}

	public void createNewStudent() {

		viewMode = ViewMode.CREATE;

		selectedStudent = new Student();
		selectedStudentAddress = new StudentAddress();

		cities = addressProviderService.getCities();
	}

	public void loadStudents() {
		students = studentRegistrationService.getAllStudents();
	}

	public void removeStudent(Integer studentNo) {

		studentRegistrationService.deleteStudent(studentNo);
		loadStudents();
	}

	public void updateStudent(Integer studentNo) {

		selectedStudent = studentRegistrationService.getRegistrationInfo(studentNo);

		viewMode = ViewMode.UPDATE;

		selectedStudentAddress = selectedStudent.getStudentAddress();
		cities = addressProviderService.getCities();
		findDistricts(selectedStudentAddress.getCity());

	}

	private void findDistricts(String cityNo) {

		Optional<List<District>> findAny = cities.stream().filter(c -> c.getCityNo().equals(cityNo)).map(c -> c.getDistricts()).findAny();
		if (findAny.isPresent()) {

			districts = findAny.get();
		}
	}

	public void onCityChange() {

		findDistricts(selectedStudentAddress.getCity());
	}

	public void showStudentAddressList(Integer studentNo) {

		studentAddressList = new ArrayList<StudentAddress>();
		Student student = studentRegistrationService.getRegistrationInfo(studentNo);
		studentAddressList.add(student.getStudentAddress());
	}

	@SuppressWarnings("resource")
	public void handleFileUpload(FileUploadEvent event) {

		UploadedFile file = event.getFile();
		try {

			InputStream in = file.getInputstream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			CSVReader csvReader = new CSVReader(reader);

			List<Map<String, Object>> listOfMap = new ArrayList<Map<String, Object>>();
			final List<String[]> dataList = csvReader.readAll();
			if (!dataList.isEmpty()) {

				final String[] columnData = dataList.get(0);

				for (int i = 1; i < dataList.size(); i++) {

					int index = 0;
					String rowData = dataList.get(i)[0];

					String attributeNameList = columnData[index].toString();
					String[] columnList = attributeNameList.split(";");

					Map<String, Object> map = new HashMap<String, Object>();
					String[] rowDataList = rowData.split(";");
					for (int idx = 0; idx < columnList.length; idx++) {

						System.out.println(columnList[idx].toUpperCase() + "=" + rowDataList[idx]);

						map.put(columnList[idx].toUpperCase(), rowDataList[idx]);
					}

					listOfMap.add(map);
				}

				studentRegistrationService.createRegistration(listOfMap);

				loadStudents();
			}
		} catch (IOException e) {

			FacesMessage message = new FacesMessage("Error", event.getFile().getFileName() + " is not uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void createNewStudentAction() {

		if (ViewMode.CREATE == viewMode) {
			studentRegistrationService.createRegistration(selectedStudent, selectedStudentAddress);
		} else {

			studentRegistrationService.updateStudentAddres(selectedStudent, selectedStudentAddress);
		}
		loadStudents();
		selectedStudent = null;
		selectedStudentAddress = null;
	}

	public String onFlowProcess(FlowEvent event) {

		return event.getNewStep();

	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Student> getFilteredStudents() {
		return filteredStudents;
	}

	public void setFilteredStudents(List<Student> filteredStudents) {
		this.filteredStudents = filteredStudents;
	}

	public Student getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public StudentRegistrationService getStudentRegistrationService() {
		return studentRegistrationService;
	}

	public void setStudentRegistrationService(StudentRegistrationService studentRegistrationService) {
		this.studentRegistrationService = studentRegistrationService;
	}

	public StudentAddress getSelectedStudentAddress() {
		return selectedStudentAddress;
	}

	public void setSelectedStudentAddress(StudentAddress selectedStudentAddress) {
		this.selectedStudentAddress = selectedStudentAddress;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public List<StudentAddress> getStudentAddressList() {
		return studentAddressList;
	}

	public void setStudentAddressList(List<StudentAddress> studentAddressList) {
		this.studentAddressList = studentAddressList;
	}

}
