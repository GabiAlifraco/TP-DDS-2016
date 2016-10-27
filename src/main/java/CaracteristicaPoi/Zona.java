package CaracteristicaPoi;

import java.util.List;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

@javax.persistence.Embeddable
public class Zona {

	@javax.persistence.Embedded
	private List<Punto> zona;

	@org.mongodb.morphia.annotations.Transient
	@javax.persistence.Transient
	private Polygon polygon;
	
	public Zona(List<Punto> puntos){
		this.zona = puntos;
		this.polygon = this.mapperZonaAPolygon(puntos);
	}
	
	
	public boolean isInside(Punto otraCoordenada) {
		Point pointCoordenada = new Point(otraCoordenada.getLatitud(), otraCoordenada.getLongitud());
		return polygon.isInside(pointCoordenada);
	}
	
	private Polygon mapperZonaAPolygon(List<Punto> puntos){
		Polygon polygon = new Polygon();
		Point point;
		for (Punto punto: puntos){
			point = new Point(punto.getLatitud(), punto.getLongitud());
			polygon.add(point);
		}
		return polygon;
	}
	

	public List<Punto> getZona() {
		return zona;
	}

	public void setZona(List<Punto> zona) {
		this.zona = zona;
	}

}
