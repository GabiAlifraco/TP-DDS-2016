package Notificaciones;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.jersey.api.client.TerminatingClientHandler;

import Resultado.Resultado;
import Terminal.Terminal;

public class AlmacenadorBusquedas implements NotificacionBusqueda {

	
	
	private static AlmacenadorBusquedas instance = null;
	private Map<Terminal,List<Resultado>> resultadosEncontrados = new HashMap<Terminal,List<Resultado>>();
	private List<Terminal> terminalesActivadas = new ArrayList<Terminal>();
	
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
		if(resultadosEncontrados.containsKey(terminal)){
			resultadosEncontrados.get(terminal).add(resultado);
		}else{
			List<Resultado> resultados = new ArrayList<Resultado>();
			resultados.add(resultado);
			
			resultadosEncontrados.put(terminal, resultados);
			terminalesActivadas.add(terminal);
			
		}
	}
	
	public void getReportePorFecha(Terminal terminal){
		if(terminalesActivadas.contains(terminal)){
			
		}
	}
	@Override
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda) {
		// TODO Auto-generated method stub
		
	}

	
}
