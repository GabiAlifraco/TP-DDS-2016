package TpAnual;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

public class Carrousel extends Comercio {
	private List<String> diasDeAtencion;
    private Disponibilidad horarioDeAtencion;
    private Disponibilidad horarioDeAtencion2;
    List<String> palabrasClave = Arrays.asList("Sortija", "Plaza", "Juegos");
   
    public Carrousel(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada,List<String> diasDeAtencion, Disponibilidad intervaloDeAtencion,Disponibilidad otroIntervaloDeAtencion) {
		super(unNombre,unDomicilio, unaRegion, unaCoordenada);
		this.diasDeAtencion= diasDeAtencion;
		this.horarioDeAtencion = intervaloDeAtencion;
		this.horarioDeAtencion2 = otroIntervaloDeAtencion;
	}

	public boolean textoIncluido(String texto) {
		return palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto));
	}
	
	public boolean estaDisponible(String dia, String hora) {
		return diasDeAtencion.contains(dia) && this.horaDentroDelRango(hora);
	}

	private boolean horaDentroDelRango(String hora) {
		boolean rango1 = horarioDeAtencion.getHorarioInicial().isBefore(LocalTime.parse(hora)) && horarioDeAtencion.getHorarioFinal().isAfter(LocalTime.parse(hora));
		boolean rango2 = horarioDeAtencion2.getHorarioInicial().isBefore(LocalTime.parse(hora)) && horarioDeAtencion2.getHorarioFinal().isAfter(LocalTime.parse(hora));
		return (rango1 || rango2);
	}

	public boolean esCerca(Point otraCoordenada) {
		return this.coordenada.distance(otraCoordenada) < 200;
	}
	

}
