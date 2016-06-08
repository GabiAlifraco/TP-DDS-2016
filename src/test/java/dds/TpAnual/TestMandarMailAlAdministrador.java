package dds.TpAnual;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Observers.ObserverAlmacenarResultados;
import Observers.ObserverDemoraBusqueda;
import OrigenesDeDatos.OrigenDeDatos;
import TpAnual.InfoFast;
import TpAnual.Resultado;
import TpAnual.Terminal;

public class TestMandarMailAlAdministrador {
	
	private InfoFast sistema;
	private Resultado resultado;
	private LocalDate fecha;
	private ObserverDemoraBusqueda observerDemora;
	private ObserverAlmacenarResultados observerAlmacenar;
	private Terminal terminal;
	private List<OrigenDeDatos> servicios;
	@Before
	public void initialize(){
		sistema = new InfoFast();
		fecha = LocalDate.parse("2016-10-16");
		observerDemora = new ObserverDemoraBusqueda();
		observerAlmacenar = new ObserverAlmacenarResultados();
		terminal = new Terminal("Terminal Abasto", servicios);
		terminal.getObserverBusquedas().add(observerDemora);
		terminal.getObserverBusquedas().add(observerAlmacenar);
		resultado = new Resultado(fecha, 10, "sarasa", 4, terminal);
		observerDemora.setTiempoMaximoBusqueda(5);
		observerDemora.setSistema(sistema);
	}
	@Test
	public void seLeNotificaraAlAdministrador(){
		terminal.notificarBusqueda(resultado);
	}

}
