package TpAnual;

public class KioscoDiario extends Comercio{

	
	public KioscoDiario(Poi unPoi) {
		super(unPoi);
		
	}

	
	public boolean poiCercanoAOtro(Poi unPoi) {
		
		return this.calculoDistanciaEntrePois(unPoi) < 200;
	}
	
}
