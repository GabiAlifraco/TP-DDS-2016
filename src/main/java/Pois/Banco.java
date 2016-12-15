package Pois;

import java.util.List;

import CaracteristicaPoi.Ubicacion;

@javax.persistence.Entity
@javax.persistence.DiscriminatorValue(value= "Banco")
public class Banco extends Poi {
	
	public Banco(String unNombre, List<String> serviciosBanco, Ubicacion ubicacion) {
		setNombre(unNombre);
		setPalabrasClave(serviciosBanco);
		setUbicacion(ubicacion);
	}
	public Banco(){
		
	}
	public int distanciaMinimaParaConsiderarmeCercano() {
		return 500;
	}

}
