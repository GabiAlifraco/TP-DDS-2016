package TpAnual;

import java.util.ArrayList;
import java.util.List;

public class BaseDePois {
	
	private static BaseDePois instance= new BaseDePois();
	public List<Poi> pois = new ArrayList<Poi>();
	
	private BaseDePois(){
		
	}
	
	public static BaseDePois getInstance(){
		return instance;
	}
	
	public List<Poi> getPois(){
		return pois;
	}
	
	public void agregarUnPoi(Poi unPoi){
		
		pois.add(unPoi);
	}
	
	public void eliminarUnPoi(Poi unPoi){
		pois.remove(unPoi);
	}
	

}
