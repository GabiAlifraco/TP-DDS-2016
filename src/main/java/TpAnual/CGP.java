package TpAnual;

import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP implements Poi{
	
	private Point coordenada;
	private Domicilio domicilio;
	private Region region;
	List<String> palabrasClave = Arrays.asList("Rentas"); // Hay que cambiarlo

    
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
	
	public boolean textoIncluido(String texto) {
		return palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto));
	}
	

}
