package tr.com.sample.registration.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tr.com.sample.registration.model.City;
import tr.com.sample.registration.service.AddressProviderService;
import tr.com.sample.registration.util.ObjectMapperUtil;

@Service
public class AddressProviderServiceImpl implements AddressProviderService {

//	@Cacheable("cities")
	public List<City> getCities() {

		InputStream in = this.getClass().getClassLoader().getResourceAsStream("city.json");

		List<City> cities = ObjectMapperUtil.genericReadValueAsList(in, City.class);

		return cities;
	}

}
