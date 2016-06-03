package dds.TpAnual;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Observers.ObserverBusqueda;
import Observers.ObserverDemoraBusqueda;
import TpAnual.InfoFast;
import TpAnual.OrigenDeDatos;
import TpAnual.Resultado;
import TpAnual.Terminal;

public class TestMandarMailAlAdministrador {
	
	private InfoFast sistema;
	private Resultado resultado;
	private LocalDate fecha;
	private List<ObserverBusqueda> observers = new ArrayList<ObserverBusqueda>();
	private ObserverDemoraBusqueda observerDemora;
	private Terminal terminal;
	private List<OrigenDeDatos> servicios;
	@Before
	public void initialize(){
		sistema = new InfoFast();
		fecha = LocalDate.parse("2016-10-16");
		resultado = new Resultado(fecha, 10, "sarasa", 4);
		observerDemora = new ObserverDemoraBusqueda();
		terminal = new Terminal("Terminal Abasto", servicios);
		terminal.getObserverBusquedas().add(observerDemora);
		observerDemora.setTiempoMaximoBusqueda(5);
		observerDemora.setSistema(sistema);
	}
	@Test
	public void seLeNotificaraAlAdministrador(){
		terminal.notificarBusqueda(resultado);
	}

}
