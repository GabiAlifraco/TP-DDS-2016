package TestMailAdministrador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Mocks.MockNotificadorAdministrador;
import Observers.ObserverAlmacenarResultados;
import Observers.NotificadorAdministrador;
import OrigenesDeDatos.OrigenDeDatos;
import TpAnual.InfoFast;
import TpAnual.Resultado;
import TpAnual.Terminal;

public class TestMandarMailAlAdministrador {
	
	private InfoFast sistema;
	private Resultado resultado;
	private LocalDate fecha;
	private MockNotificadorAdministrador notificadorAdministrador;
	private Terminal terminal;
	private List<OrigenDeDatos> servicios;
	@Before
	public void initialize(){
		sistema = new InfoFast();
		fecha = LocalDate.parse("2016-10-16");
		notificadorAdministrador = new MockNotificadorAdministrador();
		terminal = new Terminal("Terminal Abasto", servicios);
		terminal.getObserverBusquedas().add(notificadorAdministrador);
		resultado = new Resultado(fecha, LocalTime.of(10, 40, 02), LocalTime.of(10, 40, 10), "sarasa", 4, terminal);
		notificadorAdministrador.setTiempoMaximoBusqueda(5);
		notificadorAdministrador.setSistema(sistema);
	}
	@Test
	public void seLeNotificaraAlAdministrador(){
		terminal.notificarBusqueda(resultado);
		Assert.assertTrue(notificadorAdministrador.administradorNotificado);
	}

}