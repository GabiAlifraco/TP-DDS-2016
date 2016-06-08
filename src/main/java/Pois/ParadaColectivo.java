package Pois;

import java.util.List;

import org.uqbar.geodds.Point;

import UbicacionPoi.Domicilio;
import UbicacionPoi.Region;
import UbicacionPoi.Ubicacion;

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
