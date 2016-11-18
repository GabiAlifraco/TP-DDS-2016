package Pois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;

import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;
import CaracteristicaPoi.Zona;

@javax.persistence.Entity
@javax.persistence.DiscriminatorValue(value="CGP")
public class CGP extends Poi{
	
	@javax.persistence.OneToMany(cascade=CascadeType.ALL)
	@javax.persistence.JoinColumn(name="poiID")
	private List<ServicioCGP> serviciosCGP;
	
	@javax.persistence.Embedded
	private Zona zona;
	
	public CGP(String unaComuna,String barrio,List<ServicioCGP> servicios, Ubicacion ubicacion){
		setUbicacion(ubicacion);
		Region regionCGP = new Region("CABA", barrio, "Bs As", "Argentina");
		setRegion(regionCGP);
		setNombre("CGP Comuna "+ unaComuna);
		serviciosCGP = servicios;
		setPalabrasClave(this.getPalabrasClave());
		
	}
	public CGP(){
		dValue = "CGP";
	}
	
	@Override
	public boolean estaCercaDe(Punto otraCoordenada) {
		return this.getZona().isInside(otraCoordenada);
	}
	
	
	@Override
	public boolean estaDisponible(String nombreServicio, DayOfWeek dia, LocalTime hora) {
			return serviciosCGP.stream().filter(servicio -> servicio.getNombre().contains(nombreServicio)).anyMatch(servicio -> servicio.horarioDisponible(dia,hora));
	}
	
	//Se supone que lo contiene, no que es exactamente igual al nombre del servicio.
	@Override
	public boolean textoIncluido(String unNombre, String unaPalabraClave) {
		return this.mismoNombre(unNombre) || this.contieneServicio(unaPalabraClave);
	}
	private boolean contieneServicio(String nombreABuscar) {
		return serviciosCGP.stream().anyMatch(servicio -> servicio.getNombre().equals(nombreABuscar));
	}

	public int distanciaMinimaParaConsiderarmeCercano(){
		return 0;
	}
	//Getters y Setters
	
	public Zona getZona() {
		return zona;
	}

    public void setZona(Zona zona) {
		this.zona = zona;
	}
    
	public List<ServicioCGP> getServiciosCGP() {
		return serviciosCGP;
	}
	public void setServiciosCGP(List<ServicioCGP> serviciosCGP) {
		this.serviciosCGP = serviciosCGP;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (getClass() != obj.getClass())
			return false;
		CGP other = (CGP) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public List<String> getPalabrasClave(){
		return serviciosCGP.stream().map(servicio -> servicio.getNombre()).collect(Collectors.toList());
	}

}
