package dds.TpAnual;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import Pois.ParadaColectivo;
import TpAnual.BaseDePois;
import TpAnual.Domicilio;
import TpAnual.OrigenDeDatos;
import TpAnual.Terminal;
import TpAnual.Poi;
import TpAnual.Region;

public class TestABMPois {

	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;
	private ParadaColectivo parada107;
	private Terminal terminalAbasto;
	private Terminal terminalFlorida;
	BaseDePois base = BaseDePois.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);

	@Before
	public void initialize() {
		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353, 25.4632);
		List<String> palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(domicilioParada, regionParada, coordenadaParada, "114", palabrasClave114);

		domicilioParada = new Domicilio("Arenales", 1261, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.955, 25.555);
		List<String> palabrasClave107 = Arrays.asList("Colectivo", "Parada");
		parada107 = new ParadaColectivo(domicilioParada, regionParada, coordenadaParada, "107", palabrasClave107);

		
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
	public void testDosSistemasAgreganPois(){
		terminalAbasto.getBase().agregarUnPoi(parada114);
		terminalFlorida.getBase().agregarUnPoi(parada107);
		
		List<Poi> resultadoEsperado = Arrays.asList(parada114, parada107);
		
		Assert.assertTrue(terminalAbasto.getBase().getPois().equals(resultadoEsperado));
	}
	
}
