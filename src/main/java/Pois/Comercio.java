package Pois;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import TpAnual.Disponibilidad;
import TpAnual.Poi;
import UbicacionPoi.Domicilio;
import UbicacionPoi.Region;

public abstract class Comercio extends Poi {
	private String nombre;
	private List<String> diasDeAtencion = new ArrayList<String>();
	private Disponibilidad horarioDeAtencion;

	public Comercio(String unNombre, Domicilio unDomicilio, Region unaRegion, Point unaCoordenada,
			List<String> diasDeAtencion, Disponibilidad horarioDeAtencion, List<String> palabrasClave) {
		setNombre(unNombre);
		setDomicilio(unDomicilio);
		setRegion(unaRegion);
		setCoordenada(unaCoordenada);
		setDiasDeAtencion(diasDeAtencion);
		setHorarioDeAtencion(horarioDeAtencion);
		setPalabrasClave(palabrasClave);
	}

	public int distanciaMinimaParaConsiderarmeCercano() {
		return dameDistancia();
	}

	public abstract int dameDistancia();

	public boolean noTenesIdentificacion() {
		return (nombre.equals(null));
	}

	public void setDiasDeAtencion(List<String> dias) {
		this.diasDeAtencion = dias;
	}

	public List<String> getDiasDeAtencion() {
		return this.diasDeAtencion;
	}

	public void setHorarioDeAtencion(Disponibilidad unHorario) {
		this.horarioDeAtencion = unHorario;
	}

	public Disponibilidad getHorarioDeAtencion(){
		return this.horarioDeAtencion;
	}
	
	public boolean estaDisponible(String nombreBuscado, String dia, String hora) {
		return (getDiasDeAtencion().contains(dia) && horaDentroDelRango(hora));
	}

	public boolean horaDentroDelRango(String hora) {
		return (getHorarioDeAtencion().getHorarioInicial().isBefore(LocalTime.parse(hora))
				&& getHorarioDeAtencion().getHorarioFinal().isAfter(LocalTime.parse(hora)));
	}

}
