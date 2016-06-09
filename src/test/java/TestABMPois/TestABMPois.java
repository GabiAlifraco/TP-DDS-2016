package TestABMPois;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import OrigenesDeDatos.BaseDePois;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.ParadaColectivo;
import Pois.Poi;
import TpAnual.Terminal;
import UbicacionPoi.Domicilio;
import UbicacionPoi.Region;
import UbicacionPoi.Ubicacion;

public class TestABMPois {

	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private Region regionParada107;
	private Domicilio domicilioParada107;
	private Point coordenadaParada107;
	private ParadaColectivo parada114;
	private Ubicacion ubicacion114;
	private ParadaColectivo parada107;
	private Ubicacion ubicacion107;
	private Terminal terminalAbasto;
	private Terminal terminalFlorida;
	BaseDePois base = BaseDePois.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);

	@Before
	public void initialize() {
		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353, 25.4632);
		ubicacion114 = new Ubicacion(domicilioParada, regionParada, coordenadaParada);
		List<String> palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(ubicacion114, "114", palabrasClave114);

		domicilioParada107 = new Domicilio("Arenales", 1261, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada107 = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada107 = new Point(34.955, 25.555);
		ubicacion114 = new Ubicacion(domicilioParada107, regionParada107, coordenadaParada107);
		List<String> palabrasClave107 = Arrays.asList("Colectivo", "Parada");
		parada107 = new ParadaColectivo(ubicacion107, "107", palabrasClave107);

		
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
