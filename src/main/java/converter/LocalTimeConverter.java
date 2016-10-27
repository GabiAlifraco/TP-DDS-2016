package converter;

import java.time.LocalTime;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public class LocalTimeConverter extends TypeConverter implements SimpleValueConverter {

	public LocalTimeConverter() {
		super(LocalTime.class);
	}

	@Override
	public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {

		if (fromDBObject instanceof String) {
			return LocalTime.parse((CharSequence) fromDBObject);
		} else {
			return null;
		}
	}

	@Override
	public Object encode(Object value, MappedField optionalExtraInfo) {

		if (value instanceof LocalTime) {
			return value.toString();

		} else {
			return null;
		}
	}
}
