package Pois;

import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

import TpAnual.Domicilio;
import TpAnual.Poi;
import TpAnual.Region;

public class ParadaColectivo extends Poi {

    List<String> palabrasClave = Arrays.asList("Colectivo", "Parada");
	private String lineaDeColectivo;
    
    
	public ParadaColectivo(Domicilio unDomicilio,Region unaRegion,Point unaCoordenada, String unaLinea){
	  setCoordenada(unaCoordenada);
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  lineaDeColectivo=unaLinea;
	  }
	
	
	public void setLineaColectivo(String linea) {
		this.lineaDeColectivo = linea;
	}

	public int distanciaMinimaParaConsiderarmeCercano(){
		return 100;
	}
	
	public boolean noTenesIdentificacion(){
		return ((lineaDeColectivo.equals(null)));
	}
	
	public boolean estaDisponible(String nombreBuscado,String dia, String hora) {
		return true;
	}	
	
	@Override
	public boolean mismoNombre(String nombreServicio) {
		return lineaDeColectivo.equals(nombreServicio);
	}
	public boolean textoIncluido(String texto) {
		return lineaDeColectivo.contains(texto) || palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto));
	}


}
