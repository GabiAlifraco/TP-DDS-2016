package OrigenesDeDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Pois.Poi;

public class Mapa implements OrigenDeDatos {

	private static Mapa instance = null;
	private List<Poi> pois = new ArrayList<Poi>();

	// Alta, Baja y Modificaci�n de un Poi
	public void agregarUnPoi(Poi unPoi) {
		pois.add(unPoi);
	}

	public void eliminarUnPoi(Poi unPoi) {
		if (pois.contains(unPoi)) {
			pois.remove(unPoi);
		}
	}

	public void modificarUnPoi(Poi unPoi) {
		this.eliminarUnPoi(unPoi);
		this.agregarUnPoi(unPoi);
	}

	public List<Poi> buscarPois(String unNombre, String unaPalabraClave) {
		return getPois().stream().filter(poi -> poi.textoIncluido(unNombre, unaPalabraClave))
				.collect(Collectors.toList());
	}

	public List<Poi> getPois() {
		return this.pois;
	}

	protected Mapa() {
	}

	public static Mapa getInstance() {
		if (instance == null) {
			instance = new Mapa();
		}
		return instance;
	}
}
