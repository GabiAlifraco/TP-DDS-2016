package Pois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.bson.types.ObjectId;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;

@javax.persistence.Entity
@org.mongodb.morphia.annotations.Entity

@javax.persistence.Table(name="POIS")
@javax.persistence.Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@javax.persistence.DiscriminatorColumn(name= "Tipo_Poi")
public abstract class Poi{

	@javax.persistence.Id
	@javax.persistence.GeneratedValue
	@javax.persistence.Column(name="poiID")
	@org.mongodb.morphia.annotations.Transient
	private Long poiID;
	
	
	@javax.persistence.Transient
	@org.mongodb.morphia.annotations.Id
	private ObjectId id;
	
	@javax.persistence.OneToOne(cascade = CascadeType.ALL)
	@javax.persistence.JoinColumn(name="poiID")
	protected Ubicacion ubicacion;
	
	protected String nombre;
	
	@javax.persistence.ElementCollection
	@javax.persistence.CollectionTable(name="palabrasClaveDePoi",
		joinColumns=@JoinColumn(name="poiID"))
	@org.mongodb.morphia.annotations.Embedded
	private List<String> palabrasClave = new ArrayList<String>();
	
	@javax.persistence.OneToMany(cascade=CascadeType.ALL)
	@javax.persistence.JoinColumn(name="poiID")
	private List<Disponibilidad> horariosDeAtencion  = new ArrayList<Disponibilidad>();

	//Punto A de la entrega 0
	
	public boolean estaCercaDeOtroPoi(Poi unPoi) {
		return estaCercaDe(unPoi.getCoordenada());
	}

	//Habria que multiplicar esa distancia en Km por 1000 para que el metodo lo haga bien
	public boolean estaCercaDe(Punto otraCoordenada) {
		return this.getCoordenada().distance(otraCoordenada) < distanciaMinimaParaConsiderarmeCercano();
	}

	public abstract int distanciaMinimaParaConsiderarmeCercano();

	public boolean estaDisponible(String nombreServicio, DayOfWeek dia, LocalTime hora) {
		return horariosDeAtencion.stream().anyMatch(disponibilidad -> disponibilidad.disponibleEnDiayHora(dia, hora));
	}

	public boolean textoIncluido(String unNombre, String unaPalabraClave) {
		return getPalabrasClave().stream().anyMatch(palabra -> palabra.contains(unaPalabraClave))
				|| this.mismoNombre(unNombre);
	}

	public boolean mismoNombre(String nombreServicio) {
		return getNombre().equalsIgnoreCase(nombreServicio);
	}

	public Punto getCoordenada() {
		return this.ubicacion.getCoordenadas();
	}

	public void setCoordenada(Punto unaCoordenada) {
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

	public Long getPoiID() {
		return poiID;
	}

	public void setPoiID(Long poiID) {
		this.poiID = poiID;
	}

	public ObjectId getId() {
		return id;
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
