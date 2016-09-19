package Procesos;

import java.util.List;

public interface Almacenador {
	
	public void guardarResultado(ResultadoEjecucion resultado);

	public List<ResultadoEjecucion> getResultadosProcesos();
}
