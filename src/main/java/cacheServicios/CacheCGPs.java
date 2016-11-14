package cacheServicios;


import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;

import Pois.Poi;

public class CacheCGPs {
	Datastore datastore;
	
	public CacheCGPs(Datastore ds){
		this.datastore = ds;
	}
	
	public List<Poi> buscar(String calleOBarrio) {
		
		List<RespuestaServicio> q = datastore.find(RespuestaServicio.class).asList();
    	List<Poi> pois = q.stream().map(r -> r.getCgp()).filter(p -> p.getUbicacion().getRegion().getBarrio().equalsIgnoreCase(calleOBarrio)).collect(Collectors.toList());
    	List<Poi> aux = pois.stream().filter(p-> p.getUbicacion().getDomicilio().getCallePrincipal().contains(calleOBarrio)).collect(Collectors.toList());
      	pois.addAll(aux);
    	return pois;
	}

	public List<Object> guardarVarios(List<Poi> pois,String pclave){
		List<RespuestaServicio> resultados = pois.stream().map(p -> new RespuestaServicio(p,pclave)).collect(Collectors.toList());
		return this.guardarResultados(resultados);
	}
	private List<Object> guardarResultados(List<RespuestaServicio> resultados) {
		return resultados.stream().map(r -> datastore.save(r).getId()).collect(Collectors.toList());
		
	}
	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

}
