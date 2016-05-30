package Pois;

import java.time.LocalTime;
import java.util.List;

import org.uqbar.geodds.Point;

import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
import TpAnual.Region;

public class Carrousel extends Comercio {

	private Disponibilidad horarioDeAtencion2;
	
	public Carrousel(String unNombre, Domicilio unDomicilio, Region unaRegion, Point unaCoordenada,
			List<String> diasDeAtencion, Disponibilidad intervaloDeAtencion, Disponibilidad otroIntervaloDeAtencion,
			List<String> palabrasClave) {
		
		super(unNombre, unDomicilio, unaRegion, unaCoordenada, diasDeAtencion, intervaloDeAtencion, palabrasClave);
		horarioDeAtencion2 = otroIntervaloDeAtencion;
	}

	public int dameDistancia() {
		return 0; // Agregar alguna distancia a priori para el calculo de
					// cercania
	}

	public boolean textoIncluido(String texto) {
		return getPalabrasClave().stream().anyMatch(palabra -> palabra.contains(texto));
	}

	public boolean estaDisponible(String nombreBuscado, String dia, String hora) {
		return getDiasDeAtencion().contains(dia) && this.horaDentroDelRango(hora);
	}

	public boolean horaDentroDelRango(String hora) {
		boolean rango1 = getHorarioDeAtencion().getHorarioInicial().isBefore(LocalTime.parse(hora))
				&& getHorarioDeAtencion().getHorarioFinal().isAfter(LocalTime.parse(hora));
		boolean rango2 = horarioDeAtencion2.getHorarioInicial().isBefore(LocalTime.parse(hora))
				&& horarioDeAtencion2.getHorarioFinal().isAfter(LocalTime.parse(hora));
		return (rango1 || rango2);
	}

}
