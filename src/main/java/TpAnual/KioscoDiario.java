package TpAnual;

import java.time.LocalTime;
import java.util.List;

import org.uqbar.geodds.Point;

public class KioscoDiario extends Comercio {
	private List<String> diasDeAtencion;
    private Disponibilidad horarioDeAtencion;

	
	public KioscoDiario(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada,List<String> diasDeAtencion, Disponibilidad horarioDeAtencion) {
		super(unNombre,unDomicilio, unaRegion, unaCoordenada);
		this.diasDeAtencion= diasDeAtencion;
		this.horarioDeAtencion = horarioDeAtencion;
		
	}

	public boolean poiCercanoAOtro(Point otraCoordenada) {
		
		return this.coordenada.distance(otraCoordenada) < 500;
	}

	@Override
	public boolean textoIncluido(String texto) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean estaDisponible(String dia, String hora) {
		return diasDeAtencion.contains(dia) && this.horaDentroDelRango(hora);
	}

	public boolean horaDentroDelRango(String hora){
		return (horarioDeAtencion.getHorarioInicial().isBefore(LocalTime.parse(hora)) && horarioDeAtencion.getHorarioFinal().isAfter(LocalTime.parse(hora)));
	}
}
