package TpAnual;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;

public class Banco implements Poi {

	private Point coordenada;
    private Domicilio domicilio;
    private Region region;
    private String nombre;
    List<String> palabrasClave = Arrays.asList("Cajero automatico", "Deposito");
    private List<String> diasDeAtencion;
    private Disponibilidad horarioDeAtencion;
    

	public Banco(String unNombre,Domicilio unDomicilio,Region unaRegion,Point unaCoordenada, List<String> diasDeAtencion,Disponibilidad unHorarioDeAtencion){
	  setCoordenada(unaCoordenada);
	  setDomicilio(unDomicilio);
	  setRegion(unaRegion);
	  this.diasDeAtencion=diasDeAtencion;
	  this.horarioDeAtencion=unHorarioDeAtencion;
	  this.nombre = unNombre;
	  }
	public boolean esCerca(Point otraCoordenada) {
		
		return this.coordenada.distance(otraCoordenada) < 500;
	}

	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public Point getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Point coordenada) {
		this.coordenada = coordenada;

	}
	public boolean textoIncluido(String texto){
		return palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto));
	}
	public boolean estaDisponible(String dia,String hora){
		return diasDeAtencion.contains(dia) && this.horaDentroDelRango(hora);
	}
	private boolean horaDentroDelRango(String hora) {
		return (horarioDeAtencion.getHorarioInicial().isBefore(LocalTime.parse(hora)) && horarioDeAtencion.getHorarioFinal().isAfter(LocalTime.parse(hora)));
	}
	@Override
	public boolean mismoNombre(String nombreServicio) {
		return this.nombre.equals(nombreServicio);
	}
	

}
