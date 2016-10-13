package Resultado;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.convert.LambdaConverter;

import Pois.Poi;

import javax.persistence.*;

import Terminal.Terminal;

@Entity
@Table(name="ResultadosBusqueda")
public class Resultado implements WithGlobalEntityManager{

	@Id
	@GeneratedValue
	private long idResultado;
	@Column(name="FechaBusqueda")
	LocalDate fecha;
	@Transient
	long segundosBusqueda;
	String fraseBuscada;
	int cantidadDeResultados;
	@OneToOne
	Terminal terminal;
	@Convert(converter=LambdaConverter.class)
	LocalTime unTiempoInicio;
	@Convert(converter=LambdaConverter.class)
	LocalTime unTiempoFinalizacion;
	@ManyToMany
	List<Poi> poisEncontrados;
	

	public Resultado(LocalDate unaFecha, LocalTime tiempoInicio, LocalTime tiempoFinalizacion, String unaFraseBuscada,
			Terminal terminal, List<Poi> poisEncontrados) {

		this.setFecha(unaFecha);
		this.setTiempoBusqueda(tiempoInicio, tiempoFinalizacion);
		this.setFraseBuscada(unaFraseBuscada);
		this.setCantidadResultados(poisEncontrados.size());
		this.setTerminal(terminal);
		this.setPoisEncontrados(poisEncontrados);

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

	private void setTerminal(Terminal terminal) {
		this.terminal = terminal;

	}

	public Terminal getTerminal(){
		return terminal;
	}

	private void setCantidadResultados(int totalResultados) {
		this.cantidadDeResultados = totalResultados;
	}

	private void setFraseBuscada(String unaFraseBuscada) {
		this.fraseBuscada = unaFraseBuscada;
	}

	private void setTiempoBusqueda(LocalTime comienzo, LocalTime finalizacion) {
		this.segundosBusqueda = ChronoUnit.SECONDS.between(finalizacion, comienzo);
	}

	private void setFecha(LocalDate unaFecha) {
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

	public long getIdResultado() {
		return idResultado;
	}
	
	public void setIdResultado(long idResultado) {
		this.idResultado = idResultado;
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
