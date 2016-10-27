package Pois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import CaracteristicaPoi.Ubicacion;

@javax.persistence.Entity
@javax.persistence.DiscriminatorValue(value= "ParadaColectivo")
public class ParadaColectivo extends Poi {

	

	public ParadaColectivo(Ubicacion unaUbicacion, String unNombre, List<String> palabrasClave) {
		setUbicacion(unaUbicacion);
		setNombre(unNombre);
		setPalabrasClave(palabrasClave);
	}
	public ParadaColectivo(){
		
	}
	public int distanciaMinimaParaConsiderarmeCercano() {
		return 100;
	}
	
	@Override
	public boolean estaDisponible(String nombreABuscar, DayOfWeek dia, LocalTime hora) {
		return true;
	}


	
}