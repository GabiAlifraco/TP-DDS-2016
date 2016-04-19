package TpAnual;

public class Libreria extends Comercio{

	
	
	public Libreria(Poi unPoi) {
		super(unPoi);
		
	}

	
	public boolean poiCercanoAOtro(Poi unPoi) {
		return this.calculoDistanciaEntrePois(unPoi) < 500;
	}
}
