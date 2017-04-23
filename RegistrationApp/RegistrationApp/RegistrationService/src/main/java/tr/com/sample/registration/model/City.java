package tr.com.sample.registration.model;

import java.io.Serializable;
import java.util.List;

public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5799827908697685759L;

	private String cityNo;

	private String cityName;

	private List<District> districts;

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

}
