package TpAnual;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;




public class InfoFast {

	private BaseDePois base;
	private Point coordenadaDispositivoMovil;
	
	/*public String obtenerInfoDe(Poi unPuntoDeInteres){
		
		return "";
	}
    HAY QUE TERMINAR ESTE METODO SI ES QUE EN ALGUN MOMENTO SE ESPECIFICA LA FUNCION*/
	
	public List<Poi> consultaDeCercania(){
		return base.getPois().stream().filter(poi -> poi.estaCercaDe(coordenadaDispositivoMovil)).collect(Collectors.toList());

	}
	
	public boolean estaDisponiblePoi(String nombreServicio, String dia, String hora) {
		return base.getPois().stream().filter(poi -> poi.mismoNombre(nombreServicio)).anyMatch(poi->poi.estaDisponible(nombreServicio,dia,hora));

	}
	

	public List<Poi> busquedaDePuntos(String unaBusqueda){
		return base.getPois().stream().filter(poi -> poi.textoIncluido(unaBusqueda)).collect(Collectors.toList());
				
	}
	
	public BaseDePois getBase(){
		return base;
	}

}
