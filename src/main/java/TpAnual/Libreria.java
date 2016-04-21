package TpAnual;

import org.uqbar.geodds.Point;

public class Libreria extends Comercio {

	public Libreria(Domicilio unDomicilio, Region unaRegion, Point unaCoordenada) {
		super(unDomicilio, unaRegion, unaCoordenada);
		
	}
	
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		return this.coordenada.distance(otraCoordenada) < 200;
	}

	@Override
	public boolean textoIncluido(String texto) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
