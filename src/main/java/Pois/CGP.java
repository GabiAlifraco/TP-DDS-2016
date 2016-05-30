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
	

	private Polygon zona;
	
	public CGP (String unNombre, Domicilio unDomicilio,Region unaRegion,Point unaCoordenada,
			    Polygon unaZona, List<ServicioCGP> servicios, List<String> palabrasClave){
		setCoordenada(unaCoordenada);
		setZona(unaZona);
		setRegion(unaRegion);
		setDomicilio(unDomicilio);
		setNombre(unNombre);
		serviciosCGP= servicios;
		setPalabrasClave(palabrasClave);
	}
	
	public int distanciaMinimaParaConsiderarmeCercano(){
		return 0;
	};
	
	@Override
	public boolean estaCercaDe(Point otraCoordenada) {
		return this.zona.isInside(getCoordenada());
		
	}
	
	public boolean noTenesIdentificacion(){
		return(getNombre().equals(null));
	}

	public boolean textoIncluido(String texto) {
		return serviciosCGP.stream().anyMatch(servicioCGP -> servicioCGP.getPalabrasClave().stream().anyMatch(palabra -> palabra.contains(texto)));
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
	


	

}
