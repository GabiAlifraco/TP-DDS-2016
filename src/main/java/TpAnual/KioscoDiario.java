package TpAnual;

import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

public class KioscoDiario extends Comercio {
	
    List<String> palabrasClave = Arrays.asList("Revistas", "Diarios", "Crucigrama");


	
	public KioscoDiario(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada,List<String> diasDeAtencion, Disponibilidad horarioDeAtencion) {
		super(unNombre,unDomicilio, unaRegion, unaCoordenada,diasDeAtencion,horarioDeAtencion);
		
	}

	public int dameDistancia() {
		return 500;
	}

	

	

}
