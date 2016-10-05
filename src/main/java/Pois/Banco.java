package Pois;

import java.util.List;
import org.uqbar.geodds.Point;

import CaracteristicaPoi.Ubicacion;


public class Banco extends Poi {
	
	public Banco(String unNombre, Point unaCoordenada, List<String> serviciosBanco,Ubicacion ubicacion) {
		setCoordenada(unaCoordenada);
		setNombre(unNombre);
		setPalabrasClave(serviciosBanco);
		setUbicacion(ubicacion);
	}

	public int distanciaMinimaParaConsiderarmeCercano() {
		return 500;
	}

}
