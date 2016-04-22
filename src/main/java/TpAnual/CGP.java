package TpAnual;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP implements Poi{
	
	private Point coordenada;
	private Domicilio domicilio;
	private Region region;
	List<String> palabrasClave = Arrays.asList("Rentas"); 
	private List<String> diasDeAtencion;
	private String nombre;
    private Disponibilidad horarioDeAtencion;
   
	private Polygon zona;
	
	public CGP (String unNombre, Domicilio unDomicilio,Region unaRegion,Point unaCoordenada,
			    Polygon unaZona,List<String> unosDiasDeAtencion, Disponibilidad unHorarioDeAtencion){
		setCoordenada(unaCoordenada);
		setZona(unaZona);
		setRegion(unaRegion);
		setDomicilio(unDomicilio);
		this.nombre = unNombre;
		this.diasDeAtencion= unosDiasDeAtencion;
		this.horarioDeAtencion= unHorarioDeAtencion;
	}
	
	
	public boolean esCerca(Point otraCoordenada) {
		return this.zona.isInside(coordenada);
		
	}

	public boolean textoIncluido(String texto) {
		return palabrasClave.stream().anyMatch(palabra -> palabra.contains(texto)); 
		
	}

	public boolean estaDisponible(String dia, String hora) {
		return diasDeAtencion.contains(dia)&& this.horarioDentroDelRango(hora);
	}
	public boolean horarioDentroDelRango(String hora){
		return (horarioDeAtencion.getHorarioInicial().isBefore(LocalTime.parse(hora)) && horarioDeAtencion.getHorarioFinal().isAfter(LocalTime.parse(hora)));
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


	public Polygon getZona() {
		return zona;
	}


	public void setZona(Polygon zona) {
		this.zona = zona;
	}
	
	public String getNombre() {
		return nombre;
	}
	


	

}
