package TestMailAdministrador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ConsultasPoi.ResultadosConsultasPoi;
import Mocks.MockNotificadorAdministrador;
import OrigenesDeDatos.OrigenDeDatos;
import Requerimientos.NotificarAdministrador;
import Resultado.Resultado;
import Terminal.Terminal;
import Requerimientos.AlmacenarResultadosDeBusquedas;

public class TestMandarMailAlAdministrador {
	
	private ResultadosConsultasPoi sistema;
	private Resultado resultado;
	private LocalDate fecha;
	private MockNotificadorAdministrador notificadorAdministrador;
	private Terminal terminal;
	private List<OrigenDeDatos> servicios;
	@Before
	public void initialize(){
		sistema = new ResultadosConsultasPoi();
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