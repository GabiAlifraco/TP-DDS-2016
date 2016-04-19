package TpAnual;

import java.util.List;

public interface TipoPoi {
	
		public boolean poiCercanoAOtro(Poi unPoi);
		public boolean poiEstaDisponible();
		public List<Poi> busquedaDePuntos(String unaBusqueda);
}
