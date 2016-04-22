package TpAnual;

import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

public class ParadaColectivo implements Poi {

	private Point coordenada;
    private Domicilio domicilio;
    private Region region;
    List<String> palabrasClave = Arrays.asList("Colectivo", "Parada");
	private String linea;
    
    
	public ParadaColectivo(Domicilio unDomicilio,Region unaRegion,Point unaCoordenada, String unaLinea){
	  coordenada = unaCoordenada;
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  setLinea(unaLinea);
	  }
	public boolean esCerca(Point otraCoordenada) {
		
		return this.coordenada.distance(otraCoordenada) < 100;
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
	
	public void setLinea(String linea) {
		this.linea = linea;
	}

	public boolean estaDisponible(String dia, String hora) {
		return true;
	}	
	@Override
	public boolean mismoNombre(String nombreServicio) {
		return linea.equals(nombreServicio);
	}
	public boolean textoIncluido(String texto) {
		return linea.contains(texto) || palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto));
	}

}
