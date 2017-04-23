package tr.com.sample.registration.parser;

import java.util.List;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class ObjectMapperUtil {

	public static <T> List<T> genericReadValueAsList(String data,
			Class<T> targetClass) {
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);

		final CollectionType javaType = mapper.getTypeFactory()
				.constructCollectionType(List.class, targetClass);
		List<T> values = null;
		try {
			values = mapper.readValue(data, javaType);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return values;

	}

}
