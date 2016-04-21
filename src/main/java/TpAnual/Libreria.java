package TpAnual;

import org.uqbar.geodds.Point;

public class Libreria extends Comercio {

	public Libreria(Domicilio unDomicilio, Region unaRegion, Point unaCoordenada) {
		super(unDomicilio, unaRegion, unaCoordenada);
		
	}
	
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		return this.coordenada.distance(otraCoordenada) < 200;
	}
	
	

}
