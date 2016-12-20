package Pois;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

import CaracteristicaPoi.Ubicacion;

@Embedded
@javax.persistence.Entity
@javax.persistence.DiscriminatorValue(value= "Banco")
public class Banco extends Poi {
	
	public Banco(String unNombre, List<String> serviciosBanco, Ubicacion ubicacion) {
		setNombre(unNombre);
		setPalabrasClave(serviciosBanco);
		setUbicacion(ubicacion);
		setTipo_Poi("Banco");
	}
	public Banco(){
		setTipo_Poi("Banco");		
	}
	public int distanciaMinimaParaConsiderarmeCercano() {
		return 500;
	}

}
