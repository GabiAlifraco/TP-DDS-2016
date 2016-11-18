package Resultado;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Converters;
import Pois.Poi;
import Terminal.Terminal;
import converter.LocalDateConverter;
import converter.LocalTimeConverter;

@org.mongodb.morphia.annotations.Entity
@Converters({LocalDateConverter.class, LocalTimeConverter.class})
public class Resultado {

	@org.mongodb.morphia.annotations.Id
	ObjectId id;
	
	
	LocalDate fecha;
	
	@org.mongodb.morphia.annotations.Transient
	long segundosBusqueda;
	
	String fraseBuscada;
	int cantidadDeResultados;
	
	LocalTime unTiempoInicio;
	LocalTime unTiempoFinalizacion;
	
	Terminal terminal;
	
	@org.mongodb.morphia.annotations.Embedded
	List<Poi> poisEncontrados;
	

	public Resultado(LocalDate unaFecha, LocalTime tiempoInicio, LocalTime tiempoFinalizacion, String unaFraseBuscada,
			Terminal terminal, List<Poi> poisEncontrados) {

		this.setFecha(unaFecha);
		this.setTiempoBusqueda(tiempoInicio, tiempoFinalizacion);
		this.setFraseBuscada(unaFraseBuscada);
		this.setCantidadResultados(poisEncontrados.size());
		this.setTerminal(terminal);
		this.setPoisEncontrados(poisEncontrados);
		this.setUnTiempoInicio(tiempoInicio);
		this.setUnTiempoFinalizacion(tiempoFinalizacion);

	}
	
	public Resultado(){
		
	}

	public LocalTime getUnTiempoInicio() {
		return unTiempoInicio;
	}

	public void setUnTiempoInicio(LocalTime unTiempoInicio) {
		this.unTiempoInicio = unTiempoInicio;
	}

	public LocalTime getUnTiempoFinalizacion() {
		return unTiempoFinalizacion;
	}

	public void setUnTiempoFinalizacion(LocalTime unTiempoFinalizacion) {
		this.unTiempoFinalizacion = unTiempoFinalizacion;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;

	}

	public Terminal getTerminal(){
		return terminal;
	}

	public void setCantidadResultados(int totalResultados) {
		this.cantidadDeResultados = totalResultados;
	}

	private void setFraseBuscada(String unaFraseBuscada) {
		this.fraseBuscada = unaFraseBuscada;
	}

	private void setTiempoBusqueda(LocalTime comienzo, LocalTime finalizacion) {
		this.segundosBusqueda = ChronoUnit.SECONDS.between(comienzo, finalizacion);
	}

	public void setFecha(LocalDate unaFecha) {
		this.fecha = unaFecha;

	}

	public int getCantidadResultados() {
		return cantidadDeResultados;
	}

	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public long getSegundosBusqueda() {
		return segundosBusqueda;
	}
	
	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}
	
	public void setCantidadDeResultados(int cantidadDeResultados) {
		this.cantidadDeResultados = cantidadDeResultados;
	}
	public List<Poi> getPoisEncontrados() {
		return poisEncontrados;
	}
	
	public void setPoisEncontrados(List<Poi> poisEncontrados) {
		this.poisEncontrados = poisEncontrados;
	}
}
