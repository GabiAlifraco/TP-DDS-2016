package Pois;

import java.util.List;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Ubicacion;

@javax.persistence.Entity
@javax.persistence.DiscriminatorValue(value= "Comercio")
public class Comercio extends Poi {
	
	
	private int distancia;

	public Comercio(String unNombre, Ubicacion unaUbicacion, List<Disponibilidad> horariosDeAtencion,
			List<String> palabrasClave) {
		setNombre(unNombre);
		setUbicacion(unaUbicacion);
		setHorariosDeAtencion(horariosDeAtencion);
		setPalabrasClave(palabrasClave);
		setTipo_Poi("Comercio");
	}
    public Comercio(){
		setTipo_Poi("Comercio");
    	    }
	public int distanciaMinimaParaConsiderarmeCercano() {
		return this.getDistancia();
	}
	public int getDistancia() {
		return distancia;
	}
    public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
}