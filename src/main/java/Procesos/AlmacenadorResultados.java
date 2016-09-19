package Procesos;

import java.util.ArrayList;
import java.util.List;

public class AlmacenadorResultados implements Almacenador
{
	
	
	private static AlmacenadorResultados instance = null;
	private List<ResultadoEjecucion> resultadosProcesos = new ArrayList<ResultadoEjecucion>();
	
	
	public void guardarResultado(ResultadoEjecucion resultado){
		resultadosProcesos.add(resultado);
		
	}
	
	protected AlmacenadorResultados(){
		
	}

	public static AlmacenadorResultados getInstance() {
		if (instance == null) {
			instance = new AlmacenadorResultados();
		}
		return instance;
	}

	@Override
	public List<ResultadoEjecucion> getResultadosProcesos() {
		return resultadosProcesos;
	}


}
