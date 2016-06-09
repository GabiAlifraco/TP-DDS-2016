package TestSistema;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import OrigenesDeDatos.BaseDePois;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Banco;
import Pois.CGP;
import Pois.ParadaColectivo;
import Pois.Poi;
import Pois.ServicioCGP;
import TpAnual.Terminal;
import UbicacionPoi.Domicilio;
import UbicacionPoi.Region;
import UbicacionPoi.Ubicacion;
import TpAnual.Disponibilidad;


public class TestSistema {

	private Terminal terminalAbasto;
	
	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;
	private Ubicacion ubicacionParada;
	
	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Point coordenadaBanco;
	private Banco bancoSantander;
	private List<String> palabrasClaveBanco = new ArrayList<String>();
	
	private Domicilio domicilioCGP;
	private Region regionCGP;
	private CGP comuna3;
	
	private List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();
	private Disponibilidad disponibilidadCGPRentas;
	private List<DayOfWeek> diasRentas = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY);
	private List<Disponibilidad> horariosRentas = new ArrayList<Disponibilidad>();
	private ServicioCGP rentas;

	


	@Before
	public void initialize() {
		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353, 25.4632);
		ubicacionParada = new Ubicacion(domicilioParada, regionParada, coordenadaParada);
		List<String> palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(ubicacionParada, "114", palabrasClave114);
		parada114.setLineaColectivo("Parada 114");
		

		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.3243, 24.4657);
		palabrasClaveBanco.add("Cajero automatico");
		palabrasClaveBanco.add("Deposito");		
		bancoSantander = new Banco("Banco Santander", coordenadaBanco, palabrasClaveBanco);
		bancoSantander.setDomicilio(domicilioBanco);
		bancoSantander.setRegion(regionBanco);
		
		disponibilidadCGPRentas = new Disponibilidad (diasRentas,LocalTime.of(9,30),LocalTime.of(15,30));
		horariosRentas.add(disponibilidadCGPRentas);
		rentas = new ServicioCGP("Rentas",horariosRentas);

		new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		new Point(34.4353, 24.4856);
		new Polygon();
		comuna3 = new CGP("CGP comuna3","Recoleta",serviciosCGP);
		comuna3.serviciosCGP.add(rentas);
		
		
		BaseDePois base = BaseDePois.getInstance();
		List<OrigenDeDatos> servicios = Arrays.asList(base);
		terminalAbasto = new Terminal("Terminal Abasto", servicios);
		terminalAbasto.getBase().getPois().clear();
		terminalAbasto.getBase().agregarUnPoi(parada114);
		terminalAbasto.getBase().agregarUnPoi(bancoSantander);
		terminalAbasto.getBase().agregarUnPoi(comuna3);
		comuna3.serviciosCGP.add(rentas);
		comuna3.agregarPalabraClave("Rentas");
	}

	

	@Test
	public void busquedaDePoisPorClave() {
		
		terminalAbasto.getBase().agregarUnPoi(bancoSantander);
		List<Poi> resultadoEsperado = Arrays.asList(bancoSantander);
		Assert.assertTrue(terminalAbasto.busquedaDePuntos("Cajero", "Cajero").equals(resultadoEsperado));

	}

	@Test
	public void busquedaSinResultados() {
		
		Assert.assertTrue(terminalAbasto.busquedaDePuntos("Plaza", "Plaza").isEmpty());

	}
	@Test
	public void busquedaDeServicioCGPPorClave() {

		List<Poi> resultadoEsperado = Arrays.asList(comuna3);
		
		Assert.assertTrue(terminalAbasto.busquedaDePuntos("Rentas", "Rentas").equals(resultadoEsperado));

	}

}