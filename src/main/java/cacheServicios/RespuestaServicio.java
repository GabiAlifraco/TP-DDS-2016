package cacheServicios;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Converters;

import Pois.Poi;
import converter.LocalDateConverter;

@org.mongodb.morphia.annotations.Entity
@Converters(LocalDateConverter.class)
public class RespuestaServicio {

	@org.mongodb.morphia.annotations.Id
	ObjectId id;
	
	LocalDate fechaConsulta;

	List<Poi> bancosConsulta;
	
	String servicioConsulta;
	
	String nombreBancoConsulta;
	
	public RespuestaServicio(LocalDate fecha, List<Poi> bancos, String servicio, String banco){
		this.fechaConsulta = fecha;
		this.bancosConsulta = bancos;
		this.servicioConsulta = servicio;
		this.nombreBancoConsulta = banco;
	}
	
	public RespuestaServicio(){
		
	}
	public LocalDate getFechaConsulta() {
		return fechaConsulta;
	}
	
	public void setFechaConsulta(LocalDate fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	
	public List<Poi> getBancosConsulta() {
		return bancosConsulta;
	}
	
	public void setBancosConsulta(List<Poi> bancosConsulta) {
		this.bancosConsulta = bancosConsulta;
	}
	
	public String getServicioConsulta() {
		return servicioConsulta;
	}
	
	public void setServicioConsulta(String servicioConsulta) {
		this.servicioConsulta = servicioConsulta;
		}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getNombreBancoConsulta() {
		return nombreBancoConsulta;
	}

	public void setNombreBancoConsulta(String nombreBancoConsulta) {
		this.nombreBancoConsulta = nombreBancoConsulta;
	}

}
