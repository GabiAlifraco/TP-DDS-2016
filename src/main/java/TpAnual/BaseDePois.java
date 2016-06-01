package TpAnual;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class BaseDePois implements OrigenDeDatos{

	private static BaseDePois instance = null;
	public List<Poi> pois = new ArrayList<Poi>();

	public List<Poi> getPois() {
		return this.pois;
	}
	protected BaseDePois() {
	}

	public static BaseDePois getInstance() {
		if (instance == null){
			instance = new BaseDePois();
		}
		return instance;
	}

	//Alta, Baja y Modificaci�n de un Poi
	public void agregarUnPoi(Poi unPoi) {
		pois.add(unPoi);
	}

	public void eliminarUnPoi(Poi unPoi) {
		pois.remove(unPoi);
	}

	public void modificarUnPoi(Poi unPoi) {
		pois.remove(unPoi);
		pois.add(unPoi);
	}

	public List<Poi> buscarPois(String unNombre, String unaPalabraClave) {
		return getPois().stream().filter(poi ->
	poi.textoIncluido(unNombre, unaPalabraClave)).collect(Collectors.toList());
	}
}
