package TpAnual;

import java.util.List;

public class Banco implements TipoPoi {

	private Poi unBanco;


	public Banco(Poi unPoi){
		unBanco = unPoi;
	}
	public boolean poiCercanoAOtro(Poi unPoi) {
		
		return unBanco.calculoDistanciaEntrePois(unPoi) < 500;
	}

	
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}

}
