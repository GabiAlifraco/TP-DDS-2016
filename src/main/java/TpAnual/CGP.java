package TpAnual;

import java.util.List;

public class CGP implements TipoPoi {

    


	private Poi unCGP;


	public CGP(Poi unPoi){
		unCGP = unPoi;
	}
	
    public boolean poiCercanoAOtro(Poi unPoi) {
		
    	return unCGP.getLatitud() == unPoi.getLatitud() && unCGP.getLongitud() == unPoi.getLongitud();
	}

	
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}
	


}
