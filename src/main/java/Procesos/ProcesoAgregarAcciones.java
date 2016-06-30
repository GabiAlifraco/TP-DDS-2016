package Procesos;

public class ProcesoAgregarAcciones extends Proceso {

	public ProcesoAgregarAcciones(Cronograma cronograma) {

		this.cronograma = cronograma;
	}

	public boolean ejecutar() {
		return finalizoOK;
	}

}
