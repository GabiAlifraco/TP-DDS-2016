package TpAnual;

import org.uqbar.geodds.Point;

public class KioscoDiario extends Comercio {

	
	public KioscoDiario(Domicilio unDomicilio, Region unaRegion, Point unaCoordenada) {
		super(unDomicilio, unaRegion, unaCoordenada);
	}

	public boolean poiCercanoAOtro(Point otraCoordenada) {
		
		return this.coordenada.distance(otraCoordenada) < 500;
	}

}
