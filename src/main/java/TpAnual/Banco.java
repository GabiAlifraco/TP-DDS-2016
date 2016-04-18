package TpAnual;

import java.util.List;

public class Banco implements TipoPoi {

	private Poi unBanco;


	public Banco(Poi unPoi){
		unBanco = unPoi;
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
