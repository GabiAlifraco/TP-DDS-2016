package Pois;

import java.util.List;

import org.uqbar.geodds.Point;

import TpAnual.Disponibilidad;
import UbicacionPoi.Domicilio;
import UbicacionPoi.Region;

public class KioscoDiario extends Comercio {
		
	public KioscoDiario(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada,List<String> diasDeAtencion, Disponibilidad horarioDeAtencion, List<String> palabrasClave) {
		super(unNombre,unDomicilio, unaRegion, unaCoordenada,diasDeAtencion,horarioDeAtencion, palabrasClave);
		
	}

	public int dameDistancia() {
		return 500;
	}

	

	

}
