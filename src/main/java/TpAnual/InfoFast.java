package TpAnual;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.geodds.Point;
public class InfoFast {
	
	// Se agrega el getInstance del singleton de la base
	private BaseDePois base = BaseDePois.getInstance();
	private Point coordenadaDispositivoMovil;
	
	
	//Getter de la base
	public BaseDePois getBase(){
		return base;
	}
	/*public String obtenerInfoDe(Poi unPuntoDeInteres){
		
		return "";
	}
    HAY QUE TERMINAR ESTE METODO SI ES QUE EN ALGUN MOMENTO SE ESPECIFICA LA FUNCION*/
	
	//Esto es para la entrega 1: Calculo de Cercania
	public List<Poi> consultaDeCercania(){
		return base.getPois().stream().filter(poi -> poi.estaCercaDe(coordenadaDispositivoMovil))
				.collect(Collectors.toList());
	}
	//Esto es para la entrega 1: Calculo de la disponibilidad 
	public boolean estaDisponiblePoi(String nombreServicio, String dia, String hora) {
		return base.getPois().stream().filter(poi -> poi.mismoNombre(nombreServicio))
				.anyMatch(poi->poi.estaDisponible(nombreServicio,dia,hora));
	}
	//Esto es para la entrega 1: Busqueda de puntos
	public List<Poi> busquedaDePuntos(String unaBusqueda){
		return base.getPois().stream().filter(poi -> poi.textoIncluido(unaBusqueda))
				.collect(Collectors.toList());		
	}

}
