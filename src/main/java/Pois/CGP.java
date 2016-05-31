package Pois;

import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import TpAnual.Domicilio;
import TpAnual.Poi;
import TpAnual.Region;
import TpAnual.ServicioCGP;

public class CGP extends Poi{
	
	
	public List<ServicioCGP> serviciosCGP;
	private List<String> lasPalabrasClave;
    
	private Polygon zona;
	public CGP (String unNombre,List<ServicioCGP> servicios){

		setNombre(unNombre);
		serviciosCGP= servicios;
		
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

	public boolean textoIncluido(String texto) {
		return getPalabrasClave().stream().anyMatch(palabra -> palabra.contains(texto));
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

	
	public boolean mismoNombre(String nombreServicio) {
		return getNombre().equals(nombreServicio) || serviciosCGP.stream().anyMatch(servicioCGP -> servicioCGP.igualNombre(nombreServicio));
	}

	public List<String> getLasPalabrasClave() {
		return lasPalabrasClave;
	}

	public void setLasPalabrasClave(List<String> lasPalabrasClave) {
		this.lasPalabrasClave = lasPalabrasClave;
	}

	


	

}
