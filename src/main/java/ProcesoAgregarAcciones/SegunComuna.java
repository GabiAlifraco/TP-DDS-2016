package ProcesoAgregarAcciones;

import Terminal.Terminal;

public class SegunComuna extends Criterio {

	private String comuna;

	public SegunComuna(Accion accion, String comuna) {
		this.comuna = comuna;
	}

	protected boolean cumpleCriterio(Terminal terminal) {
		return terminal.getComunaTerminal().equals(comuna);
	}
}
