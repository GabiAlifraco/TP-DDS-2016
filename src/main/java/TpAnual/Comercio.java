package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public abstract class Comercio implements Poi{

	protected Point coordenada;
    private Domicilio domicilio;
    private Region region;
    private String nombre;
    List<String> palabrasClave;
    

	public Comercio(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada){
	  setCoordenada(unaCoordenada);
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  nombre = unNombre;
	  }
	
	public abstract boolean esCerca(Point otraCoordenada);
	
	public boolean textoIncluido(String texto) {
		return palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto));
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
	@Override
	public boolean mismoNombre(String nombreServicio) {
		return nombre.equals(nombreServicio);
	}
}
