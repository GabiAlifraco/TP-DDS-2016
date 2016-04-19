package TpAnual;

import java.util.List;

public class Banco extends Poi implements TipoPoi  {

	public Banco(Poi unPoi){
		super(unPoi.getCallePrincipal(),unPoi.getAlturaPrincipal(),unPoi.getEntreLaCalle(),unPoi.getHastaLaCalle(),
		      unPoi.getAlturaCalles(),unPoi.getPiso(),unPoi.getDepartamento(),unPoi.getUnidad(),
		      unPoi.getCodigoPostal(),unPoi.getLocalidad(),unPoi.getBarrio(),
		      unPoi.getProvincia(),unPoi.getPais(),unPoi.getLatitud(),unPoi.getLongitud());
	}


	public boolean poiCercanoAOtro(Poi unPoi) {
		
		return this.calculoDistanciaEntrePois(unPoi) < 500;
	}

	
	public boolean poiEstaDisponible() {
	
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}

}
