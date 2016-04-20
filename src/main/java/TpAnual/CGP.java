package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP implements TipoPoi{

    private Point coordenada;
	private Polygon zona;
	public CGP (Point unaCoordenada,Polygon unaZona){
		coordenada = unaCoordenada;
		zona = unaZona;
	}
	
	
	public boolean poiCercanoAOtro(Point otraCoordenada) {
		
		return this.zona.isInside(coordenada);
	}

	
	public boolean poiEstaDisponible() {
		
		return false;
	}

	
	public List<Poi> busquedaDePuntos(String unaBusqueda) {
		
		return null;
	}

}
