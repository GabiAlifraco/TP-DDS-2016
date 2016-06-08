package Pois;

import java.util.List;

import org.uqbar.geodds.Point;

import TpAnual.Disponibilidad;
import Pois.Poi;
import UbicacionPoi.Ubicacion;

public class Comercio extends Poi {

	private int distancia;

	public Comercio(String unNombre, Ubicacion unaUbicacion, List<Disponibilidad> horariosDeAtencion,
			List<String> palabrasClave) {
		setNombre(unNombre);
		setUbicacion(unaUbicacion);
		setHorariosDeAtencion(horariosDeAtencion);
		setPalabrasClave(palabrasClave);
	}

	private void setUbicacion(Ubicacion unaUbicacion) {
		this.ubicacion = unaUbicacion;

	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;
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

	@Override
	public Point getCoordenada() {
		return this.ubicacion.getCoordenadas();
	}

}