package CaracteristicaPoi;

import org.uqbar.geodds.Point;

@javax.persistence.Embeddable
public class Punto {

	private double latitud;
	private double longitud;
	
	@javax.persistence.Transient
	@org.mongodb.morphia.annotations.Transient
	private Point point;
	
	public Punto(double x, double y){
		this.latitud = x;
		this.longitud = y;
		this.point = new Point(x, y);
	}
	
	public Punto(){
		
	}
	
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double distance(Punto otraCoordenada) {
		Point otraCoordenadaPoint = new Point(otraCoordenada.latitud, otraCoordenada.longitud);
		return point.distance(otraCoordenadaPoint);
	}
	
}
