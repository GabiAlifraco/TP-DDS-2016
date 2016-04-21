package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public abstract class Comercio implements Poi{

	protected Point coordenada;
    private Domicilio domicilio;
    private Region region;
    private String nombre;
    
    

	public Comercio(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada){
	  setCoordenada(unaCoordenada);
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  this.nombre = unNombre;
	  }
	
	public abstract boolean poiCercanoAOtro(Point otraCoordenada);
	
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
	public String getNombre() {
		return nombre;
	}


}
