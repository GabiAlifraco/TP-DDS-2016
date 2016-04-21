package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP implements Poi{
	
	private Point coordenada;
	private Domicilio domicilio;
	private Region region;
    
	private Polygon zona;
	public CGP (Domicilio unDomicilio,Region unaRegion,Point unaCoordenada,Polygon unaZona){
		setCoordenada(unaCoordenada);
		setZona(unaZona);
		setRegion(unaRegion);
		setDomicilio(unDomicilio);
	}
	
	
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		return this.zona.isInside(coordenada);
		
	}

	
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}


	public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
	}


	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}


	public Point getCoordenada() {
		return coordenada;
	}


	public void setCoordenada(Point coordenada) {
		this.coordenada = coordenada;
	}


	public Polygon getZona() {
		return zona;
	}


	public void setZona(Polygon zona) {
		this.zona = zona;
	}

}
