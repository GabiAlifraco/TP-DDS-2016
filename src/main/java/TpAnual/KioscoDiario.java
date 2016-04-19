package TpAnual;

public class KioscoDiario extends Comercio{

	private Poi unKioscoDiario;
	public KioscoDiario(Poi unPoi) {
		super(unPoi);
		unKioscoDiario = unPoi;
	}

	
	public boolean poiCercanoAOtro(Poi unPoi) {
		
		return unKioscoDiario.calculoDistanciaEntrePois(unPoi) < 200;
	}
	
}
