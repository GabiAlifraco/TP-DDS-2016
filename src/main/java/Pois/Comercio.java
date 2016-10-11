package Pois;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Ubicacion;

@Entity
@DiscriminatorValue(value= "Comercio")
public class Comercio extends Poi {
	
	
	private int distancia;

	public Comercio(String unNombre, Ubicacion unaUbicacion, List<Disponibilidad> horariosDeAtencion,
			List<String> palabrasClave) {
		setNombre(unNombre);
		setUbicacion(unaUbicacion);
		setHorariosDeAtencion(horariosDeAtencion);
		setPalabrasClave(palabrasClave);
	}
    public Comercio(){
    	
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