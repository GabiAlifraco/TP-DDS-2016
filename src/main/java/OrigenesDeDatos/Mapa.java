package OrigenesDeDatos;

import java.util.List;


import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Pois.Poi;

public class Mapa implements OrigenDeDatos, WithGlobalEntityManager{

	private static Mapa instance = null;
	
	public void agregarUnPoi(Poi unPoi) {
	List<Poi> encontrados =entityManager().createQuery("from Poi",Poi.class).getResultList();
		if (!encontrados.contains(unPoi)) {
	 		entityManager().persist(unPoi);
		}
	}

	public void agregarResultados(List<Poi> listaResultados) {
		
		listaResultados.stream().forEach(resultado -> this.agregarUnPoi(resultado));
	}

	public void eliminarUnPoi(Poi unPoi) {
		List<Poi> encontrados =entityManager().createQuery("from Poi",Poi.class).getResultList();
		if (encontrados.contains(unPoi)) {
			entityManager().remove(unPoi);
		}
	}

	public void modificarUnPoi(Poi unPoi) {
		entityManager().persist(unPoi);
	}

	public List<Poi> buscarPois(String unNombre, String unaPalabraClave) {
		return entityManager().createQuery("from Poi p join p.palabrasClave as e where e like :pclave or p.nombre=:nombreBuscado",Poi.class).setParameter("pclave","%"+unaPalabraClave+"%").setParameter("nombreBuscado",unNombre).getResultList();
	}

	public List<Poi> getPois() {
		return entityManager().createQuery("from Poi",Poi.class).getResultList();
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