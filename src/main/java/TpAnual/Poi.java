package TpAnual;

import org.uqbar.geodds.*;
public class Poi {

	private Domicilio domicilio;
	private Region region;
	private Point coordenada;
	
	public Poi(Domicilio unDomicilio,Region unaRegion,Point unaCoordenada){
		setDomicilio(unDomicilio);
		setRegion(unaRegion);
		setCoordenada(unaCoordenada);
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Point getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Point coordenada) {
		this.coordenada = coordenada;
	}
}
