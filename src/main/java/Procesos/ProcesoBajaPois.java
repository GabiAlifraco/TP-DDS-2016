package Procesos;

import java.util.List;

import OrigenesDeDatos.Mapa;
import Pois.Poi;
import seviciosExternos.ServicioBajas;

public class ProcesoBajaPois {

	private ServicioBajas servicioRESTBajas;
	Mapa mapa = Mapa.getInstance();
	
	public ProcesoBajaPois(ServicioBajas servicioBajas){
		this.servicioRESTBajas = servicioBajas;
	}
	
	public void ejecutar() {
		List<Poi> poisADarDeBaja = servicioRESTBajas.consultarBajas();
		poisADarDeBaja.stream().forEach(poiDTO -> mapa.eliminarUnPoi(poiDTO));
	}

}
