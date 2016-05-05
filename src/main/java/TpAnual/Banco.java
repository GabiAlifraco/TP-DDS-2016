package TpAnual;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

public class Banco extends Poi {


    private String nombre;
    List<String> palabrasClave = Arrays.asList("Cajero automatico", "Deposito");
    private List<String> diasDeAtencion;
    private Disponibilidad horarioDeAtencion;
    

	public Banco(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada, List<String> diasDeAtencion,Disponibilidad unHorarioDeAtencion){
	  setCoordenada(unaCoordenada);
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  this.diasDeAtencion=diasDeAtencion;
	  horarioDeAtencion=unHorarioDeAtencion;
	  nombre = unNombre;
	  }
	public int distanciaMinimaParaConsiderarmeCercano(){
		return 500;
	}

	public boolean noTenesIdentificacion(){
		return(nombre.equals(null));
	}
	public boolean textoIncluido(String texto){
		return palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto));
	}
	public boolean estaDisponible(String nombreBuscado,String dia,String hora){
		return diasDeAtencion.contains(dia) && this.horaDentroDelRango(hora);
	}
	private boolean horaDentroDelRango(String hora) {
		return (horarioDeAtencion.getHorarioInicial().isBefore(LocalTime.parse(hora)) && horarioDeAtencion.getHorarioFinal().isAfter(LocalTime.parse(hora)));
	}
	
	public boolean mismoNombre(String nombreServicio) {
		return this.nombre.equals(nombreServicio);
	}
	

}
