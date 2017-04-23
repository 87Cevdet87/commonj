package tr.com.sample.registration.model;

import java.io.Serializable;

public class District implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1692370299802831130L;

	private String districtNo;

	private String districtName;

	public String getDistrictNo() {
		return districtNo;
	}

	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

}
