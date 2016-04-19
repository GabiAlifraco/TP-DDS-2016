package TpAnual;

import java.util.List;

public class ParadaColectivo implements TipoPoi  {

    

    private Poi parada;


	public ParadaColectivo(Poi unPoi){
		parada = unPoi;
		}
    
    
	public boolean poiCercanoAOtro(Poi unPoi) {
		
		return parada.calculoDistanciaEntrePois(unPoi) < 100 ;
	}

	
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}

}
