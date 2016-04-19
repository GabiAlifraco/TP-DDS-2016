package TpAnual;

import java.util.List;


public abstract class Comercio extends Poi implements TipoPoi{

	public Comercio(Poi unPoi){
		super(unPoi.getCallePrincipal(),unPoi.getAlturaPrincipal(),unPoi.getEntreLaCalle(),unPoi.getHastaLaCalle(),
		      unPoi.getAlturaCalles(),unPoi.getPiso(),unPoi.getDepartamento(),unPoi.getUnidad(),
		      unPoi.getCodigoPostal(),unPoi.getLocalidad(),unPoi.getBarrio(),
		      unPoi.getProvincia(),unPoi.getPais(),unPoi.getLatitud(),unPoi.getLongitud());
	}

	
	public abstract boolean poiCercanoAOtro(Poi unPoi);
		
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}

}
