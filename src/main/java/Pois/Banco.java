package Pois;

import java.util.List;
import org.uqbar.geodds.Point;

import CaracteristicaPoi.Poi;


public class Banco extends Poi {
	
	public Banco(String unNombre, Point unaCoordenada, List<String> serviciosBanco) {
		setCoordenada(unaCoordenada);
		setNombre(unNombre);
		setPalabrasClave(serviciosBanco);
	}

	public int distanciaMinimaParaConsiderarmeCercano() {
		return 500;
	}
	
	public boolean mismoNombre(String nombreServicio) {
		return this.getNombre().equals(nombreServicio);
	}
}
