package TpAnual;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

public class KioscoDiario extends Comercio {
	private List<String> diasDeAtencion;
    private Disponibilidad horarioDeAtencion;
    List<String> palabrasClave = Arrays.asList("Revistas", "Diarios", "Crucigrama");


	
	public KioscoDiario(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada,List<String> diasDeAtencion, Disponibilidad horarioDeAtencion) {
		super(unNombre,unDomicilio, unaRegion, unaCoordenada);
		this.diasDeAtencion= diasDeAtencion;
		this.horarioDeAtencion = horarioDeAtencion;
		
	}

	public int getDistancia() {
		return 500;
	}

	
	public boolean textoIncluido(String texto) {
		return palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto));
	}


	public boolean estaDisponible(String dia, String hora) {
		return diasDeAtencion.contains(dia) && this.horaDentroDelRango(hora);
	}

	public boolean horaDentroDelRango(String hora){
		return (horarioDeAtencion.getHorarioInicial().isBefore(LocalTime.parse(hora)) && horarioDeAtencion.getHorarioFinal().isAfter(LocalTime.parse(hora)));
	}

}
