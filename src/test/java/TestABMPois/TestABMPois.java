package TestABMPois;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Inicializacion.CreadorDeObjetos;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.ParadaColectivo;
import Pois.Poi;
import Terminal.Terminal;

public class TestABMPois extends CreadorDeObjetos {

	private Terminal terminalAbasto;
	private Terminal terminalFlorida;
	Mapa base = Mapa.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);

	@Before
	public void initialize() {
		this.crearParada114();
		this.crearBancoSantander();

		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalFlorida = new Terminal("Terminal Florida", servicios);

		terminalAbasto.getBase().getPois().clear();
	}

	@Test
	public void testAgregarPoi() {

		terminalAbasto.getBase().agregarUnPoi(parada114);

		Assert.assertTrue(terminalAbasto.getBase().getPois().contains(parada114));
	}

	@Test
	public void testBorrarPoi() {

		terminalAbasto.getBase().eliminarUnPoi(parada114);

		Assert.assertFalse(terminalAbasto.getBase().getPois().contains(parada114));
	}

	@Test
	public void testModificarPoi() {

		terminalAbasto.getBase().agregarUnPoi(parada114);
		parada114.setLineaColectivo("1114");
		terminalAbasto.getBase().modificarUnPoi(parada114);

		int posicionParada = terminalAbasto.getBase().getPois().indexOf(parada114);

		Assert.assertEquals("1114",
				((ParadaColectivo) terminalAbasto.getBase().getPois().get(posicionParada)).getLineaColectivo());

	}

	@Test
	public void testDosSistemasAgreganPois() {
		terminalAbasto.getBase().agregarUnPoi(parada114);
		terminalFlorida.getBase().agregarUnPoi(bancoSantander);

		List<Poi> resultadoEsperado = Arrays.asList(parada114, bancoSantander);

		Assert.assertTrue(terminalAbasto.getBase().getPois().equals(resultadoEsperado));
	}

}
