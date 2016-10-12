package converter;
import javax.persistence.*;

import org.uqbar.geodds.Point;
public class PointConverter implements AttributeConverter<Point,String>{
	 private static final String SEPARATOR = ",";
	@Override
	public String convertToDatabaseColumn(Point point) {
		  StringBuilder sb = new StringBuilder();
		  sb.append(point.latitude()).append(SEPARATOR)
		     .append(point.longitude());
		  return sb.toString();
	}

	@Override
	public Point convertToEntityAttribute(String pointString) {
		String[] point = pointString.split(SEPARATOR);
		return new Point(Double.parseDouble(point[0]),Double.parseDouble(point[1]));
	}
}
