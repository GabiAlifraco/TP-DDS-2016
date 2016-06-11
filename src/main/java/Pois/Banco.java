package Pois;

import java.util.List;
import org.uqbar.geodds.Point;


public class Banco extends Poi {
	
	public Banco(String unNombre, Point unaCoordenada, List<String> serviciosBanco) {
		setCoordenada(unaCoordenada);
		setNombre(unNombre);
		setPalabrasClave(serviciosBanco);
	}

	public int distanciaMinimaParaConsiderarmeCercano() {
		return 500;
	}

}
