package TpAnual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




public class Sistema {

	public List<Poi> pois = new ArrayList<Poi>();
    
	

	public boolean poiEstaDisponible(String nombreServicio, String dia, String hora) {
		return (pois.stream().filter(poi -> poi.getNombre()== nombreServicio)).anyMatch(poi -> poi.estaDisponible(dia,hora));

	}

	public List<Poi> busquedaDePuntos(String unaBusqueda){
		return pois.stream().filter(poi -> poi.textoIncluido(unaBusqueda)).collect(Collectors.toList());
				
	}

}
