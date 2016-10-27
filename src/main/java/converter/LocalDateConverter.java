package converter;

import java.time.LocalDate;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public class LocalDateConverter extends TypeConverter implements SimpleValueConverter {

    public LocalDateConverter() {
        super(LocalDate.class);
    }

    @Override
    public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
        
        if (fromDBObject instanceof String) {
            return LocalDate.parse((CharSequence) fromDBObject);
        } else {
        	return null;
        }
        
    }

    @Override
    public Object encode(Object value, MappedField optionalExtraInfo) {
        
    	if (value instanceof LocalDate) {
            return value.toString();
        } else {
        	return null;
        }


    }
}