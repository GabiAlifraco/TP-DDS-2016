package Pois;

import java.util.List;

import org.uqbar.geodds.Point;

import TpAnual.Domicilio;
import TpAnual.Poi;
import TpAnual.Region;

public class ParadaColectivo extends Poi {

	private String lineaColectivo;

	public ParadaColectivo(Domicilio unDomicilio, Region unaRegion, Point unaCoordenada, String unNombre,
			List<String> palabrasClave) {
		setCoordenada(unaCoordenada);
		setDomicilio(unDomicilio);
		setRegion(unaRegion);
		setNombre(unNombre);
		setPalabrasClave(palabrasClave);
	}

	public int distanciaMinimaParaConsiderarmeCercano() {
		return 100;
	}

	public boolean noTenesIdentificacion() {
		return ((getNombre().equals(null)));
	}

	public boolean estaDisponible(String nombreBuscado, String dia, String hora) {
		return true;
	}

	public String getLineaColectivo() {
		return lineaColectivo;
	}

	public void setLineaColectivo(String lineaColectivo) {
		this.lineaColectivo = lineaColectivo;
	}
}
