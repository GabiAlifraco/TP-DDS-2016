package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public abstract class Comercio implements Poi{

	protected Point coordenada;
    private Domicilio domicilio;
    private Region region;
    private String nombre;
    List<String> palabrasClave;
    private int distancia;

	public Comercio(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada){
	  setCoordenada(unaCoordenada);
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  nombre = unNombre;
	  }
	
	public boolean esCerca(Point otraCoordenada){
		return this.coordenada.distance(otraCoordenada) < this.getDistancia();
	}
	
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
	
	public boolean mismoNombre(String nombreServicio) {
		return nombre.equals(nombreServicio);
	}

	public abstract int getDistancia();
		
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
}
