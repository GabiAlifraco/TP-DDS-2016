package Pois;

import java.util.List;

import org.uqbar.geodds.Point;

import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
import TpAnual.Region;

public class Libreria extends Comercio {
	
	

	public Libreria(String unNombre, Domicilio unDomicilio, Region unaRegion, Point unaCoordenada,
			List<String> diasDeAtencion, Disponibilidad horarioDeAtencion, List<String> palabrasClave) {
		super(unNombre, unDomicilio, unaRegion, unaCoordenada,diasDeAtencion,horarioDeAtencion, palabrasClave);
	}

	public int dameDistancia(){
		return 200;
	}
	
	

	
	

}
