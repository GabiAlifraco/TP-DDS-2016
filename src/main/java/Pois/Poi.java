package Pois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.*;
import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="POIS")
public abstract class Poi implements WithGlobalEntityManager {

	// Declaramos los atributos principales del poi
	@Id
	@GeneratedValue
	@Column(name = "poiID")
	private long poiID;
	@OneToOne
	protected Ubicacion ubicacion;
	protected String nombre;
	@ElementCollection
	@CollectionTable(name = "PalabrasClave")
	private List<String> palabrasClave = new ArrayList<String>();
	@OneToMany
	private List<Disponibilidad> horariosDeAtencion = new ArrayList<Disponibilidad>();

	// Esto es para la entrega 1: Calculo de Cercania

	public boolean estaCercaDeOtroPoi(Poi unPoi) {
		return estaCercaDe(unPoi.getCoordenada());
	}

	public boolean estaCercaDe(Point otraCoordenada) {
		return this.getCoordenada().distance(otraCoordenada) < distanciaMinimaParaConsiderarmeCercano();
	}

	public abstract int distanciaMinimaParaConsiderarmeCercano();

	// Esto es para la entrega 1: Calculo de la disponibilidad

	public boolean estaDisponible(String nombreServicio, DayOfWeek dia, LocalTime hora) {
		return horariosDeAtencion.stream().anyMatch(disponibilidad -> disponibilidad.disponibleEnDiayHora(dia, hora));
	}

	// Esto es para la entrega 1: Busqueda de puntos
	public boolean textoIncluido(String unNombre, String unaPalabraClave) {
		return getPalabrasClave().stream().anyMatch(palabra -> palabra.contains(unaPalabraClave))
				|| this.mismoNombre(unNombre);
	}

	public boolean mismoNombre(String nombreServicio) {
		return getNombre().equals(nombreServicio);
	}

	// Setters y getters de los atributos
	public Point getCoordenada() {
		return this.ubicacion.getCoordenadas();
	}

	public void setCoordenada(Point unaCoordenada) {
		this.ubicacion.setCoordenadas(unaCoordenada);
	}

	public Domicilio getDomicilio() {
		return ubicacion.getDomicilio();
	}

	public void setDomicilio(Domicilio domicilio) {
		ubicacion.setDomicilio(domicilio);
	}

	public Region getRegion() {
		return ubicacion.getRegion();
	}

	public void setRegion(Region region) {
		ubicacion.setRegion(region);
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

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ubicacion.getCoordenadas() == null) ? 0 : ubicacion.getCoordenadas().hashCode());
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
		if (this.getCoordenada() == null) {
			if (other.getCoordenada() != null)
				return false;
		} else if (!(this.getCoordenada().distance(other.getCoordenada()) == 0))
			return false;
		return true;
	}
}
