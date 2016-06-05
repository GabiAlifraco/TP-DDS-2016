package Pois;

import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import TpAnual.Poi;
import TpAnual.ServicioCGP;
import UbicacionPoi.Region;

public class CGP extends Poi{
	
	
	public List<ServicioCGP> serviciosCGP;
    
	private Polygon zona;
	public CGP (String unaComuna,String barrio,List<ServicioCGP> servicios){
		Region regionCGP = new Region("CABA", barrio, "Bs As", "Argentina");
		super.setRegion(regionCGP);
		setNombre("CGP Comuna "+ unaComuna);
		serviciosCGP = servicios;
		
	}
	
	public int distanciaMinimaParaConsiderarmeCercano(){
		return 0;
	};
	
	@Override
	public boolean estaCercaDe(Point otraCoordenada) {
		return this.getZona().isInside(otraCoordenada);
		
	}
	
	public boolean noTenesIdentificacion(){
		return(getNombre().equals(null));
	}
	
	@Override
	public boolean textoIncluido(String unNombre, String unaPalabraClave) {
		return this.mismoNombre(unNombre) || this.mismoNombreServicio(unaPalabraClave);
	}
	

	public boolean estaDisponible(String nombreBuscado,String dia, String hora) {
		if (getNombre().equals(nombreBuscado)){
			return serviciosCGP.stream().anyMatch(servicioCGP -> (servicioCGP.getDiasDeAtencion().contains(dia) && servicioCGP.horarioDentroDelRango(hora)));
		}
		return estaDisponibleEnHorario(nombreBuscado,dia,hora);
	}
	
	public boolean estaDisponibleEnHorario(String servicio, String dia, String hora){
		return  serviciosCGP.stream().anyMatch(servicioCGP->(servicioCGP.getNombre().equals(servicio)) && (servicioCGP.getDiasDeAtencion().contains(dia) && servicioCGP.horarioDentroDelRango(hora)));
	}

	public Polygon getZona() {
		return zona;
	}


	public void setZona(Polygon zona) {
		this.zona = zona;
	}

	
	public boolean mismoNombreServicio(String nombreServicio) {
		return serviciosCGP.stream().anyMatch(servicioCGP -> servicioCGP.igualNombre(nombreServicio));
	}

	public boolean noSeRepite(List<Poi> listaPois) {
		return !(listaPois.stream().anyMatch(poiLista -> poiLista.mismoNombre(this.getNombre())));
	}

	public List<ServicioCGP> getServicios(){
		return this.serviciosCGP;
	}

	

}
