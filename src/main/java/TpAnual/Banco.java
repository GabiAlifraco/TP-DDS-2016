package TpAnual;

import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

public class Banco implements Poi {

	private Point coordenada;
    private Domicilio domicilio;
    private Region region;
    List<String> palabrasClave = Arrays.asList("Cajero automatico", "Deposito");

	public Banco(Domicilio unDomicilio,Region unaRegion,Point unaCoordenada){
	  setCoordenada(unaCoordenada);
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  }
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		
		return this.coordenada.distance(otraCoordenada) < 500;
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
	public boolean textoIncluido(String texto){
		return palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto));
	}

}
