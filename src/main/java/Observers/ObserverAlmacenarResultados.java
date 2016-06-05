package Observers;

import TpAnual.Resultado;


public class ObserverAlmacenarResultados implements ObserverBusqueda {

	@Override
	public void notificarBusqueda(Resultado resultado) {
		resultado.getTerminal().agregarBusqueda(resultado);
	}
}
