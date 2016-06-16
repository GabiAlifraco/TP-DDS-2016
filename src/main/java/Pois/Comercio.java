package Pois;

import java.util.List;

import org.uqbar.geodds.Point;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Ubicacion;

public class Comercio extends Poi {

	private int distancia;

	public Comercio(String unNombre, Ubicacion unaUbicacion, List<Disponibilidad> horariosDeAtencion,
			List<String> palabrasClave) {
		setNombre(unNombre);
		setUbicacion(unaUbicacion);
		setHorariosDeAtencion(horariosDeAtencion);
		setPalabrasClave(palabrasClave);
	}
    
	public int distanciaMinimaParaConsiderarmeCercano() {
		return this.getDistancia();
	}
	
	@Override
	public Point getCoordenada() {
		return this.ubicacion.getCoordenadas();
	}
	
	public int getDistancia() {
		return distancia;
	}
    public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
    
    public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
    private void setUbicacion(Ubicacion unaUbicacion) {
		this.ubicacion = unaUbicacion;
    }
}