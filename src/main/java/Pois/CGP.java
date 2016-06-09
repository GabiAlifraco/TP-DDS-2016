package Pois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import UbicacionPoi.Region;

public class CGP extends Poi{
	
	public List<ServicioCGP> serviciosCGP;
    
	private String nombre;
	private Polygon zona;
	public CGP (String unaComuna,String barrio,List<ServicioCGP> servicios){
		Region regionCGP = new Region("CABA", barrio, "Bs As", "Argentina");
		super.setRegion(regionCGP);
		setNombre("CGP Comuna "+ unaComuna);
		serviciosCGP = servicios;
		
	}
	@Override
	public boolean estaDisponible(String nombreServicio, DayOfWeek dia, LocalTime hora) {
		if (this.contieneServicio(nombreServicio)){
			return serviciosCGP.stream().filter(servicio -> servicio.getNombre().contains(nombreServicio)).anyMatch(servicio -> servicio.horarioDisponible(dia,hora));
		}
		else{
			return false;
		}
	}
	
	private boolean contieneServicio(String nombreABuscar) {
		return serviciosCGP.stream().anyMatch(servicio -> servicio.getNombre().equals(nombreABuscar));
	}


	public int distanciaMinimaParaConsiderarmeCercano(){
		return 0;
	};
	
	@Override
	public boolean estaCercaDe(Point otraCoordenada) {
		return this.getZona().isInside(otraCoordenada);
		
	}
	
	
	@Override
	public boolean textoIncluido(String unNombre, String unaPalabraClave) {
		return this.mismoNombre(unNombre) || this.contieneServicio(unaPalabraClave);
	}
	

	public Polygon getZona() {
		return zona;
	}


	public void setZona(Polygon zona) {
		this.zona = zona;
	}

	public boolean noSeRepite(List<Poi> listaPois) {
		return !(listaPois.stream().anyMatch(poiLista -> poiLista.mismoNombre(this.getNombre())));
	}

	public List<ServicioCGP> getServicios(){
		return this.serviciosCGP;
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
		if (!super.equals(obj))
			return false;
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