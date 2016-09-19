package MocksProcesos;

import Procesos.Almacenador;
import Procesos.Proceso;

public class MockProceso extends Proceso {

	boolean ejecutoProceso = false;
	
	public MockProceso(Almacenador almacenador){
		this.almacenadorResultados = almacenador;
	}

	@Override
	protected void ejecutarProceso() {
		this.cantidadElementosAfectados = 0;
		this.ejecutoProceso = true;
	}

	public boolean ejecutoProceso() {
		return ejecutoProceso;
	}

}
