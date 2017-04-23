package tr.com.sample.registration.model;

import java.io.Serializable;

public class City implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5799827908697685759L;

	private String cityNo;
	
	private String cityName;

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
	
	
}
