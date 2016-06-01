package dds.TpAnual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import Pois.Banco;
import Pois.CGP;
import Pois.ParadaColectivo;
import TpAnual.Terminal;
import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
import TpAnual.Poi;
import TpAnual.Region;
import TpAnual.ServicioCGP;


public class TestSistema {
	private Terminal sistema;
	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;
	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Point coordenadaBanco;
	private Banco bancoSantander;
	private Domicilio domicilioCGP;
	private Region regionCGP;
	private Point coordenadaCGP;   //Hay que sacarlo
	private CGP comuna3;
	private Polygon zonaCGP;    //Hay que sacarlo
	
	private Disponibilidad disponibilidadCGPRentas;
	private List<String> palabrasClaveRentas = Arrays.asList("Rentas","CGP"); //Hay que sacarlo
	private List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();
	private ServicioCGP rentas;
	private List<String> diasDeAtencionCGPRentas = Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes");

	private List<String> palabrasClaveBanco = new ArrayList<String>();


	@Before
	public void initialize() {
		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353, 25.4632);
		List<String> palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(domicilioParada, regionParada, coordenadaParada, "114", palabrasClave114);

		parada114.setLineaColectivo("Parada 114");
		

		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.3243, 24.4657);
		palabrasClaveBanco.add("Cajero automatico");
		palabrasClaveBanco.add("Deposito");		
		bancoSantander = new Banco("Banco Santander", coordenadaBanco, palabrasClaveBanco);
		bancoSantander.setDomicilio(domicilioBanco);
		bancoSantander.setRegion(regionBanco);
		
		domicilioCGP = new Domicilio("Arenales",1141 , "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionCGP = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaCGP = new Point(34.4353,24.4856);
		zonaCGP = new Polygon();
		List<String> palabrasClaveCGP = Arrays.asList("Rentas");
		comuna3 = new CGP("CGP comuna3",serviciosCGP);
		comuna3.setDomicilio(domicilioCGP);
		comuna3.setRegion(regionCGP);
		disponibilidadCGPRentas = new Disponibilidad ("09:30","15:30");
		rentas = new ServicioCGP("Rentas",diasDeAtencionCGPRentas,disponibilidadCGPRentas);
		
		sistema = new Terminal();
		sistema.getBase().getPois().clear();
		sistema.getBase().agregarUnPoi(parada114);
		sistema.getBase().agregarUnPoi(bancoSantander);
		sistema.getBase().agregarUnPoi(comuna3);
		comuna3.serviciosCGP.add(rentas);
		comuna3.agregarPalabraClave("Rentas");
	}

	
	@Test
	public void busquedaDePoisPorClave() {
		
		List<Poi> resultadoEsperado = Arrays.asList(bancoSantander);

		Assert.assertTrue(sistema.busquedaDePuntos("Cajero").equals(resultadoEsperado));

	}

	@Test
	public void busquedaSinResultados() {
		
		Assert.assertTrue(sistema.busquedaDePuntos("Plaza").isEmpty());

	}
	@Test
	public void busquedaDeServicioCGPPorClave() {

		List<Poi> resultadoEsperado = Arrays.asList(comuna3);
		
		Assert.assertTrue(sistema.busquedaDePuntos("Rentas").equals(resultadoEsperado));

	}

}