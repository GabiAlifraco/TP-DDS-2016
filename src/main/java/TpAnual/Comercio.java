package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public class Comercio implements TipoPoi{

	private Point coordenada;
	public Comercio(Point unaCoordenada){
		coordenada = unaCoordenada;
	}
	
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		
		return false;
	}

	@Override
	public boolean poiEstaDisponible() {
		
		return false;
	}

	@Override
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}

}
