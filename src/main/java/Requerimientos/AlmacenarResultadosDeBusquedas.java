package Requerimientos;

import TpAnual.Resultado;


public class AlmacenarResultadosDeBusquedas implements ObserverBusqueda {

	@Override
	public void notificarBusqueda(Resultado resultado) {
		resultado.getTerminal().agregarBusqueda(resultado);
	}
}
