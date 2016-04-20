package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public class Banco implements TipoPoi {

	private Point coordenada;
	
	public Banco(Point unaCoordenada){
		coordenada = unaCoordenada;
	}
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		
		return this.coordenada.distance(otraCoordenada) < 500;
	}

	
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}

}
