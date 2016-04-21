package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public class Comercio implements Poi{

	private Point coordenada;
    private Domicilio domicilio;
    private Region region;

	public Comercio(Domicilio unDomicilio,Region unaRegion,Point unaCoordenada){
	  setCoordenada(unaCoordenada);
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  }
	
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		
		return false;
	}

	@Override
	public boolean poiEstaDisponible() {
		
		return false;
	}

	@Override
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}

	public Point getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Point coordenada) {
		this.coordenada = coordenada;
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

}
