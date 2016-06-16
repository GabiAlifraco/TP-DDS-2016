package Pois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;

public abstract class Poi {

	// Declaramos los atributos principales del poi
	private Point coordenada;
	private Domicilio domicilio;
	private Region region;
	protected Ubicacion ubicacion;
	protected String nombre;
	private List<String> palabrasClave = new ArrayList<String>();
	private List<Disponibilidad> horariosDeAtencion  = new ArrayList<Disponibilidad>();

	
	// Esto es para la entrega 1: Calculo de Cercania
	
	public boolean estaCercaDeOtroPoi(Poi unPoi) {
		return estaCercaDe(unPoi.getCoordenada());
	}

	public boolean estaCercaDe(Point otraCoordenada) {
		return this.getCoordenada().distance(otraCoordenada) < distanciaMinimaParaConsiderarmeCercano();
	}

	public abstract int distanciaMinimaParaConsiderarmeCercano();

	// Esto es para la entrega 1: Calculo de la disponibilidad
	
	public boolean estaDisponible(String nombreServicio, DayOfWeek dia, LocalTime hora){ 
		return horariosDeAtencion.stream().anyMatch(disponibilidad -> disponibilidad.disponibleEnDiayHora(dia,hora));
	}

	// Esto es para la entrega 1: Busqueda de puntos
	public boolean textoIncluido(String unNombre, String unaPalabraClave) {
		return getPalabrasClave().stream().anyMatch(palabra -> palabra.contains(unaPalabraClave))
				|| this.mismoNombre(unNombre);
	}
	
	public boolean mismoNombre(String nombreServicio) {
		return getNombre().equals(nombreServicio);
	}
	//Estos metodos no se estan utilizando
	public void agregarPalabraClave(String unaPalabra) {
		this.palabrasClave.add(unaPalabra);
	}
	
	// Setters y getters de los atributos
	public Point getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Point unaCoordenada) {
		this.coordenada = unaCoordenada;
	}
	
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<String> getPalabrasClave() {
		return this.palabrasClave;
	}
	public void setPalabrasClave(List<String> palabras) {
		this.palabrasClave = palabras;
	}
	
	public List<Disponibilidad> getHorariosDeAtencion() {
		return horariosDeAtencion;
	}
	public void setHorariosDeAtencion(List<Disponibilidad> horariosDeAtencion) {
		this.horariosDeAtencion = horariosDeAtencion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordenada == null) ? 0 : coordenada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poi other = (Poi) obj;
		if (coordenada == null) {
			if (other.coordenada != null)
				return false;
		} else if (!coordenada.equals(other.coordenada))
			return false;
		return true;
	}
}
