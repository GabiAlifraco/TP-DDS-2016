package cacheServicios;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import Pois.Poi;

public class CacheCGPs {
	Datastore datastore;
	
	public CacheCGPs(Datastore ds){
		this.datastore = ds;
	}
	
	public List<Poi> buscar(String palabraClave) {
		this.limpiarCache();
		List<RespuestaServicio> respuestas = datastore.find(RespuestaServicio.class, "servicioConsulta", palabraClave).asList();
		
		return respuestas.stream().map(respuesta -> respuesta.getPoisConsulta())
				.flatMap(cgps -> cgps.stream()).collect(Collectors.toList());
	}

	public Object guardar(List<Poi> listaCGPs, String palabraClave) {
		RespuestaServicio respuesta = new RespuestaServicio(LocalDate.now(), listaCGPs, palabraClave);
		return datastore.save(respuesta).getId();
	}

	public void limpiarCache() {
		
		Query<RespuestaServicio> filtroFecha = datastore.find(RespuestaServicio.class)
												.filter("fechaConsulta <=" ,LocalDate.now().minusDays(1));
		datastore.delete(filtroFecha);
	}
	
	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

}
