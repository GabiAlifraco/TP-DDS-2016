package Notificaciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import Resultado.Resultado;
import Terminal.Terminal;

public class AlmacenadorBusquedas extends NotificacionBusqueda {

	private static AlmacenadorBusquedas instance = null;
	private List<Terminal> terminalesActivadas = new ArrayList<Terminal>();
	private Datastore datastore;
	
	
	protected AlmacenadorBusquedas() {
	}

	public static AlmacenadorBusquedas getInstance() {
		if (instance == null) {
			instance = new AlmacenadorBusquedas();
		}
		return instance;
	}

	@Override
	public void actualizar(Resultado resultado, Terminal terminal) {
		datastore.save(resultado);
		terminalesActivadas.add(terminal);
	}


    public List<Terminal> terminalesQueEjecutaronBusquedas(LocalDate fecha) {
		
		Set<Terminal> terminales = getResultadosEncontrados().keySet();
		
		return terminales.stream().filter(terminal -> this.terminalEjecutoBusqueda(fecha, terminal))
		.collect(Collectors.toList());
		
	}
	private boolean terminalEjecutoBusqueda(LocalDate fecha, Terminal terminal) {

		List<Resultado>resultadosTerminal = getResultadosEncontrados().get(terminal);
		return resultadosTerminal.stream().anyMatch(resultado -> resultado.getFecha().equals(fecha));
	}
	
	public Map<Terminal, List<Resultado>> getResultadosEncontrados() {
		List<Resultado> resultados = datastore.find(Resultado.class).asList();
		Map<Terminal, List<Resultado>> resultadosEncontrados = new HashMap<Terminal, List<Resultado>>();
		resultados.stream().forEach(resultado -> this.insertarResultado(resultado, resultadosEncontrados));
				
		return resultadosEncontrados;
	}
	
	private void insertarResultado(Resultado resultado, Map<Terminal, List<Resultado>> resultadosEncontrados){
		if (resultadosEncontrados.containsKey(resultado.getTerminal())) {
			resultadosEncontrados.get(resultado.getTerminal()).add(resultado);
		} else {

			List<Resultado> resultados = new ArrayList<Resultado>();
			resultados.add(resultado);
			resultadosEncontrados.put(resultado.getTerminal(), resultados);
		}

	}
	public List<Terminal> getTerminalesActivadas() {
		return terminalesActivadas;
	}
	public void setTerminalesActivadas(Terminal terminal){
		terminalesActivadas.add(terminal);
	}
	
	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	public List<Resultado> buscarSegunFiltro(Query<Resultado> filtro){
		return filtro.asList();
	}
}