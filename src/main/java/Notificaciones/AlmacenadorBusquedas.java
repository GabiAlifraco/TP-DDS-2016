package Notificaciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;

import Resultado.Resultado;
import ResultadosReportes.ResultadosReportes;
import Terminal.Terminal;

public class AlmacenadorBusquedas extends NotificacionBusqueda {

	@org.mongodb.morphia.annotations.Transient
	private static AlmacenadorBusquedas instance = null;
	@org.mongodb.morphia.annotations.Transient
	private Map<Terminal, List<Resultado>> resultadosEncontrados = new HashMap<Terminal, List<Resultado>>();
	@org.mongodb.morphia.annotations.Transient
	private List<Terminal> terminalesActivadas = new ArrayList<Terminal>();
	@org.mongodb.morphia.annotations.Transient
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
		if (resultadosEncontrados.containsKey(terminal)) {
			resultadosEncontrados.get(terminal).add(resultado);
		} else {
			terminalesActivadas.add(terminal);

			List<Resultado> resultados = new ArrayList<Resultado>();
			resultados.add(resultado);
			resultadosEncontrados.put(terminal, resultados);
		}
		
	}


    public List<Terminal> terminalesQueEjecutaronBusquedas(LocalDate fecha) {
		
		Set<Terminal> terminales = resultadosEncontrados.keySet();
		
		return terminales.stream().filter(terminal -> this.terminalEjecutoBusqueda(fecha, terminal))
		.collect(Collectors.toList());
		
	}
	private boolean terminalEjecutoBusqueda(LocalDate fecha, Terminal terminal) {

		List<Resultado>resultadosTerminal = resultadosEncontrados.get(terminal);
		return resultadosTerminal.stream().anyMatch(resultado -> resultado.getFecha().equals(fecha));
	}
	
	public Map<Terminal, List<Resultado>> getResultadosEncontrados() {
		return resultadosEncontrados;
	}
	
	public List<Terminal> getTerminalesActivadas() {
		return terminalesActivadas;
	}
	
	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	

	
	

	

}