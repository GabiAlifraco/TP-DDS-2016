package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public class ParadaColectivo implements Poi {

	
    private Domicilio domicilio;
    private Region region;
    private Point coordenada;
	public ParadaColectivo(Domicilio unDomicilio,Region unaRegion,Point unaCoordenada){
	  coordenada = unaCoordenada;
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  }
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		
		return this.coordenada.distance(otraCoordenada) < 100;
	}

	
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
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
