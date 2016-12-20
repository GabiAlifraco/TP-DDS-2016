package Resultado;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Converters;

import Pois.Poi;
import Terminal.Terminal;
import converter.LocalDateConverter;

@org.mongodb.morphia.annotations.Entity("Resultados")
@Converters({LocalDateConverter.class})
public class Resultado {

	@org.mongodb.morphia.annotations.Id
	ObjectId id;
	
	
	LocalDate fecha;
	
	String fraseBuscada;
	
	int cantidadDeResultados;
	
	@org.mongodb.morphia.annotations.Embedded	
	Terminal terminal;
	
	@org.mongodb.morphia.annotations.Embedded
	List<Poi> poisEncontrados;
	

	public Resultado(LocalDate localDate, String unaFraseBuscada, Terminal terminal, List<Poi> poisEncontrados) {

		this.setFecha(localDate);
		this.setFraseBuscada(unaFraseBuscada);
		this.setCantidadResultados(poisEncontrados.size());
		this.setTerminal(terminal);
		this.setPoisEncontrados(poisEncontrados);

	}
	
	public Resultado(){
		
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

	public void setFecha(LocalDate localDate) {
		this.fecha = localDate;

	}

	public int getCantidadResultados() {
		return cantidadDeResultados;
	}

	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public Date getFecha() {
		String fechaString=fecha.toString();
		
		return java.sql.Date.valueOf(fechaString); 
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
