package Pois;

import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
import TpAnual.Region;

public class Libreria extends Comercio {
	
	List<String> palabrasClave = Arrays.asList("Cuadernos", "Libros", "Lapiceras");

	public Libreria(String unNombre, Domicilio unDomicilio, Region unaRegion, Point unaCoordenada,
			List<String> diasDeAtencion, Disponibilidad horarioDeAtencion) {
		super(unNombre, unDomicilio, unaRegion, unaCoordenada,diasDeAtencion,horarioDeAtencion);
	}

	public int dameDistancia(){
		return 200;
	}
	
	

	
	

}
