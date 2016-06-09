package Requerimientos;

import Resultado.Resultado;

public class AlmacenarResultadosDeBusquedas implements NotificacionBusqueda {

	@Override
	public void notificarBusqueda(Resultado resultado) {
		resultado.getTerminal().agregarBusqueda(resultado);
	}
}
