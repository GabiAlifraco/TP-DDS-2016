package Pois;

import java.time.LocalTime;
import java.util.List;

import org.uqbar.geodds.Point;

import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
import TpAnual.Poi;
import TpAnual.Region;

public abstract class Comercio extends Poi{
	private List<String> palabrasClave;
    private String nombre;
    private List<String> diasDeAtencion;
	private Disponibilidad horarioDeAtencion;

	public Comercio(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada,List<String> diasDeAtencion,Disponibilidad horarioDeAtencion){
	  setCoordenada(unaCoordenada);
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  nombre = unNombre;
	  }
	
	public int distanciaMinimaParaConsiderarmeCercano(){
		return dameDistancia();
	}
	
	public boolean textoIncluido(String texto) {
		return palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto));
	}

	public boolean mismoNombre(String nombreServicio) {
		return nombre.equals(nombreServicio);
	}

	public abstract int dameDistancia();
	
	public boolean noTenesIdentificacion(){
		return(nombre.equals(null));
	}
	
	public void setDiasDeAtencion(List<String> dias){
		diasDeAtencion=dias;
	}
	
	public void setHorarioDeAtencion(Disponibilidad unHorario){
		horarioDeAtencion=unHorario;
	}
	
	public boolean estaDisponible(String nombreBuscado,String dia, String hora) {
		return (diasDeAtencion.contains(dia) && this.horaDentroDelRango(hora));
	}

	public boolean horaDentroDelRango(String hora) {
		return (horarioDeAtencion.getHorarioInicial().isBefore(LocalTime.parse(hora))
				&& horarioDeAtencion.getHorarioFinal().isAfter(LocalTime.parse(hora)));
	}
	
	public void setNombre(String unNombre){
		nombre=unNombre;
	}
	

}
