package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public interface TipoPoi  {
	
	   public boolean poiCercanoAOtro(Point otraCoordenada);
		public boolean poiEstaDisponible();
		public List<Poi> busquedaDePuntos(String unaBusqueda);
		
}
