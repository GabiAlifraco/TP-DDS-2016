package TpAnual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.uqbar.geodds.Point;

public class Sistema {

	public List<Poi> pois = new ArrayList<Poi>();

	public boolean poiCercanoAOtro(Point otraCoordenada) {

		return false;
	}

	public boolean poiEstaDisponible() {

		return false;
	}

	public List<Poi> busquedaDePuntos(String unaBusqueda){
		return pois.stream().filter(poi -> poi.textoIncluido(unaBusqueda)).collect(Collectors.toList());
				
	}

}
