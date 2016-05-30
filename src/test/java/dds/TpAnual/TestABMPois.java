package dds.TpAnual;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

import Pois.ParadaColectivo;
import TpAnual.Domicilio;
import TpAnual.InfoFast;
import TpAnual.Poi;
import TpAnual.Region;

public class TestABMPois {

	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;
	private ParadaColectivo parada107;
	private InfoFast sistema;
	private InfoFast sistema2;

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

		
		sistema = new InfoFast();
		sistema2 = new InfoFast();
		
		sistema.getBase().getPois().clear();
	}
	
	@Test
	public void testAgregarPoi() {

		sistema.getBase().agregarUnPoi(parada114);

		Assert.assertTrue(sistema.getBase().getPois().contains(parada114));
	}

	@Test
	public void testBorrarPoi() {

		sistema.getBase().eliminarUnPoi(parada114);

		Assert.assertFalse(sistema.getBase().getPois().contains(parada114));
	}

	@Test
	public void testModificarPoi() {

		sistema.getBase().agregarUnPoi(parada114);
		parada114.setLineaColectivo("1114");
		sistema.getBase().modificarUnPoi(parada114);

		int posicionParada = sistema.getBase().getPois().indexOf(parada114);

		Assert.assertEquals("1114",
				((ParadaColectivo) sistema.getBase().getPois().get(posicionParada)).getLineaColectivo());

	}

	@Test 
	public void testDosSistemasAgreganPois(){
		sistema.getBase().agregarUnPoi(parada114);
		sistema2.getBase().agregarUnPoi(parada107);
		
		List<Poi> resultadoEsperado = Arrays.asList(parada114, parada107);
		
		Assert.assertTrue(sistema.getBase().getPois().equals(resultadoEsperado));
	}
	
}
