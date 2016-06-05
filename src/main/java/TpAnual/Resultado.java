package TpAnual;

import java.time.LocalDate;

public class Resultado {

	LocalDate fecha;
	long segundosBusqueda;
	String fraseBuscada;
	int cantidadDeResultados;
	Terminal terminal;
	
	public Resultado(LocalDate unaFecha, long unTiempoBusqueda, String unaFraseBuscada, int totalResultados,
			Terminal terminal) {

		this.setFecha(unaFecha);
		this.setTiempoBusqueda(unTiempoBusqueda);
		this.setFraseBuscada(unaFraseBuscada);
		this.setCantidadResultados(totalResultados);
		this.setTerminal(terminal);

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

	private void setTiempoBusqueda(long unTiempoBusqueda) {
		this.segundosBusqueda = unTiempoBusqueda;
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

}
