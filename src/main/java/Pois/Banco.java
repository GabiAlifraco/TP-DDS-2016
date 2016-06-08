package Pois;

import java.util.List;
import org.uqbar.geodds.Point;
import Pois.Poi;

public class Banco extends Poi {
	
	public Banco(String unNombre, Point unaCoordenada, List<String> serviciosBanco) {
		setCoordenada(unaCoordenada);
		setNombre(unNombre);
		setPalabrasClave(serviciosBanco);
	}

	public int distanciaMinimaParaConsiderarmeCercano() {
		return 500;
	}

	public boolean noTenesIdentificacion() {
		return (getNombre().equals(null));
	}

	public boolean mismoNombre(String nombreServicio) {
		return this.getNombre().equals(nombreServicio);
	}
}
