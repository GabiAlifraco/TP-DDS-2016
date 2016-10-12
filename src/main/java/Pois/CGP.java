package Pois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import CaracteristicaPoi.Region;
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;
import javax.persistence.*;
public class CGP extends Poi{
	//@OneToMany(cascade= CascadeType.ALL)
	private List<ServicioCGP> serviciosCGP;
    private String nombre;
	//@Column(name="zona")
	private Polygon zona;
	
	public CGP(String unaComuna,String barrio,List<ServicioCGP> servicios, Ubicacion ubicacion){
		setUbicacion(ubicacion);
		Region regionCGP = new Region("CABA", barrio, "Bs As", "Argentina");
		setRegion(regionCGP);
		setNombre("CGP Comuna "+ unaComuna);
		serviciosCGP = servicios;
	}
	public CGP(){
		
	}
	
	@Override
	public boolean estaCercaDe(Point otraCoordenada) {
		return this.getZona().isInside(otraCoordenada);
	}
	@Override
	public boolean estaDisponible(String nombreServicio, DayOfWeek dia, LocalTime hora) {
			return serviciosCGP.stream().filter(servicio -> servicio.getNombre().contains(nombreServicio)).anyMatch(servicio -> servicio.horarioDisponible(dia,hora));
	}
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
	public List<ServicioCGP> getServicios(){
		return this.serviciosCGP;
	}
	
	public Polygon getZona() {
		return zona;
	}
    public void setZona(Polygon zona) {
		this.zona = zona;
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

}
