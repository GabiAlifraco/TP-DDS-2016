package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public class Banco implements Poi {

	private Point coordenada;
    private Domicilio domicilio;
    private Region region;

	public Banco(Domicilio unDomicilio,Region unaRegion,Point unaCoordenada){
	  coordenada = unaCoordenada;
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  }
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		
		return this.coordenada.distance(otraCoordenada) < 500;
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

}
