package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public class ParadaColectivo implements TipoPoi {

	private Point coordenada;


	public ParadaColectivo(Point unaCoordenada){
	  coordenada = unaCoordenada;
	}
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		
		return this.coordenada.distance(otraCoordenada) < 100;
	}

	
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}
	
	

}
