package TestSistema;

import Inicializacion.CreadorDeObjetos;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Poi;
import Terminal.Terminal;

public class TestSistema extends CreadorDeObjetos {

	private Terminal terminalAbasto;

	@Before
	public void initialize() {
		this.crearParada114();
		this.crearBancoSantander();
		this.crearCGPComuna3();		
		
		Mapa base = Mapa.getInstance();
		List<OrigenDeDatos> servicios = Arrays.asList(base);
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalAbasto.getBase().getPois().clear();
		terminalAbasto.getBase().entityManager().getTransaction().begin();
		terminalAbasto.getBase().agregarUnPoi(parada114);		
		terminalAbasto.getBase().agregarUnPoi(comuna3);
		terminalAbasto.getBase().agregarUnPoi(bancoSantander);
		terminalAbasto.getBase().entityManager().getTransaction().commit();
		

	}


	@Test
	public void busquedaSinResultados() {
		Assert.assertTrue(terminalAbasto.busquedaDePuntos("Plaza", "Plaza").isEmpty());

	}

	@Test
	public void busquedaDeServicioCGPPorClave() {
		List<Poi> resultadoEsperado = Arrays.asList(comuna3);

		Assert.assertEquals(resultadoEsperado,terminalAbasto.busquedaDePuntos("Rentas", "Rentas"));

	}
	@Test
	public void busquedaDePoisPorClave() {
		
		List<Poi> resultadoEsperado = Arrays.asList(bancoSantander);
		Assert.assertEquals(resultadoEsperado,terminalAbasto.busquedaDePuntos("Cajero", "Cajero"));

	}
}