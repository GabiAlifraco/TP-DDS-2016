package MocksProcesos;

import java.util.ArrayList;
import java.util.List;

import Procesos.Almacenador;
import Procesos.ResultadoEjecucion;

public class MockAlmacenadorResultados implements Almacenador{

	private List<ResultadoEjecucion> resultadosProcesos = new ArrayList<ResultadoEjecucion>();
	
	public List<ResultadoEjecucion> getResultadosProcesos() {
		return resultadosProcesos;
	}

	@Override
	public void guardarResultado(ResultadoEjecucion resultado) {
		resultadosProcesos.add(resultado);
		
	}
	
	

}
