package Pois;

import java.util.List;
import org.uqbar.geodds.Point;
import TpAnual.Disponibilidad;
import Pois.Poi;
import UbicacionPoi.Domicilio;
import UbicacionPoi.Region;

public class Comercio extends Poi {
	
	private int distancia;

	public Comercio(String unNombre, Domicilio unDomicilio, Region unaRegion, Point unaCoordenada,List<Disponibilidad> horariosDeAtencion,
			List<String> palabrasClave) {
		setNombre(unNombre);
		setDomicilio(unDomicilio);
		setRegion(unaRegion);
		setCoordenada(unaCoordenada);
		setHorariosDeAtencion(horariosDeAtencion);
		setPalabrasClave(palabrasClave);
	}

	public int distanciaMinimaParaConsiderarmeCercano() {
		return this.getDistancia();
	}

	
    
	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia=distancia;
	}

}
