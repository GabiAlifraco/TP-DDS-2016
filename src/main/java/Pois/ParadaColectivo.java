package Pois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.uqbar.geodds.Point;

import CaracteristicaPoi.Ubicacion;



public class ParadaColectivo extends Poi {

	private String lineaColectivo;
	private Ubicacion ubicacion;

	public ParadaColectivo(Ubicacion unaUbicacion, String unNombre, List<String> palabrasClave) {
		setUbicacion(unaUbicacion);
		setNombre(unNombre);
		setPalabrasClave(palabrasClave);
	}

	public void setUbicacion(Ubicacion unaUbicacion) {
		this.ubicacion = unaUbicacion;		
	}
	public Ubicacion getUbicacion() {
		return this.ubicacion;		
	}
	
	@Override
	public Point getCoordenada(){
		return this.ubicacion.getCoordenadas();
	}

	
	public int distanciaMinimaParaConsiderarmeCercano() {
		return 100;
	}

	@Override
	public boolean estaDisponible(String nombreABuscar, DayOfWeek dia, LocalTime hora) {
		return true;
	}

	public String getLineaColectivo() {
		return lineaColectivo;
	}

	public void setLineaColectivo(String lineaColectivo) {
		this.lineaColectivo = lineaColectivo;
	}
}