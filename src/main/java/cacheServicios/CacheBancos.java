package cacheServicios;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import Pois.Poi;

public class CacheBancos {

	Datastore datastore;
	
	public CacheBancos(Datastore ds){
		this.datastore = ds;
	}
	
	public List<Poi> buscar(String nombreBanco, String servicio) {
		this.limpiarCache();
		List<RespuestaServicio> respuestas = datastore.find(RespuestaServicio.class, "servicioConsulta", servicio).asList();
		
		return respuestas.stream().filter(respuesta -> respuesta.getNombreBancoConsulta().equals(nombreBanco)).map(respuesta -> respuesta.getBancosConsulta())
			.flatMap(bancos -> bancos.stream()).collect(Collectors.toList());		
	}

	public void limpiarCache() {
	
		Query<RespuestaServicio> filtroFecha = datastore.find(RespuestaServicio.class)
												.filter("fechaConsulta <=" ,LocalDate.now().minusDays(1));
		datastore.delete(filtroFecha);
	}

	public Object guardar(List<Poi> bancosServicio, String banco, String servicio) {
		
		RespuestaServicio respuesta = new RespuestaServicio(LocalDate.now(), bancosServicio, servicio, banco);
		
		return datastore.save(respuesta).getId();
	}
	
	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}


}
