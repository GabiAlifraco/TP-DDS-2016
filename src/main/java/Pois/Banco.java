package Pois;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

import TpAnual.Disponibilidad;

public class Banco extends Poi {

	private List<String> diasDeAtencion = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes");
	private Disponibilidad horarioDeAtencion = new Disponibilidad("10:00", "15:00");

	public Banco(String unNombre, Point unaCoordenada, List<String> serviciosBanco) {
		setCoordenada(unaCoordenada);
		setNombre(unNombre);
		setPalabrasClave(serviciosBanco);
	}

	public int distanciaMinimaParaConsiderarmeCercano() {
		return 500;
	}

	public boolean noTenesIdentificacion() {
		return (getNombre().equals(null));
	}

	public boolean estaDisponible(String nombreBuscado, String dia, String hora) {
		return diasDeAtencion.contains(dia) && this.horaDentroDelRango(hora);
	}

	private boolean horaDentroDelRango(String hora) {
		return (horarioDeAtencion.getHorarioInicial().isBefore(LocalTime.parse(hora))
				&& horarioDeAtencion.getHorarioFinal().isAfter(LocalTime.parse(hora)));
	}

	public boolean mismoNombre(String nombreServicio) {
		return this.getNombre().equals(nombreServicio);
	}


}
