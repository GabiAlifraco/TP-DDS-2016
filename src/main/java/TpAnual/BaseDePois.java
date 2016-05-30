package TpAnual;
import java.util.ArrayList;
import java.util.List;
public class BaseDePois {

	private static BaseDePois instance = null;
	public List<Poi> pois = new ArrayList<Poi>();

	public List<Poi> getPois() {
		return pois;
	}
	protected BaseDePois() {
	}

	public static BaseDePois getInstance() {
		if (instance == null){
			instance = new BaseDePois();
		}
		return instance;
	}

	//Alta, Baja y Modificación de un Poi
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
}
