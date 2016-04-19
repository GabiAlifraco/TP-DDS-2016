package TpAnual;

public class Libreria extends Comercio{

	private Poi unaLibreria;
	
	public Libreria(Poi unPoi) {
		super(unPoi);
		unaLibreria = unPoi;
	}

	
	public boolean poiCercanoAOtro(Poi unPoi) {
		return unaLibreria.calculoDistanciaEntrePois(unPoi) < 500;
	}
}
