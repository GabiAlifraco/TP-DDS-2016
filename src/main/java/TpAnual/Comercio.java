package TpAnual;

import java.util.List;


public class Comercio implements TipoPoi{

	private Poi unComercio;


	public Comercio(Poi unPoi){
		unComercio = unPoi;
	}

	
	public boolean poiCercanoAOtro(Poi unPoi) {
		
		return false;
	}

	
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}

}
