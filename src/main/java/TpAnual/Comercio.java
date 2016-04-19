package TpAnual;

import java.util.List;


public abstract class Comercio implements TipoPoi{

	private Poi unComercio;


	public Comercio(Poi unPoi){
		unComercio = unPoi;
	}

	
	public abstract boolean poiCercanoAOtro(Poi unPoi);
		
		

	
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}

}
