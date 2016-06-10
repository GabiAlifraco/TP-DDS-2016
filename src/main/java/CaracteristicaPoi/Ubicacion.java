package CaracteristicaPoi;

import org.uqbar.geodds.Point;



public class Ubicacion {

	private Point coordenadas;
	private Domicilio domicilio;
	private Region region;
	
	public Ubicacion(Domicilio domicilio, Region region, Point coordenadas){
		setDomicilio(domicilio);
		setRegion(region);
		setCoordenadas(coordenadas);
	}
	
	public Point getCoordenadas() {
		return coordenadas;
	}

	private void setCoordenadas(Point coordenadas) {
		this.coordenadas = coordenadas;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	private void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Region getRegion() {
		return region;
	}

	private void setRegion(Region region) {
		this.region = region;
	}
}