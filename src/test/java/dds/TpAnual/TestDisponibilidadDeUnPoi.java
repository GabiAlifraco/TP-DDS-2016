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
import Pois.Carrousel;
import Pois.ParadaColectivo;
import TpAnual.BaseDePois;
import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
import TpAnual.OrigenDeDatos;
import TpAnual.Region;
import TpAnual.ServicioCGP;
import TpAnual.Terminal;

public class TestDisponibilidadDeUnPoi {
	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;

	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Point coordenadaBanco;
	private Banco bancoSantander;

	private Domicilio domicilioCarrousel;
	private Region regionCarrousel;
	private Point coordenadaCarrousel;
	private Carrousel carrouselPlinPlin;
	private Disponibilidad disponibilidadCarrousel;
	private Disponibilidad disponibilidadCarrousel2;
	private List<String> diasDeAtencionCarrousel = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes",
			"Sabado");

	private Domicilio domicilioCGP;    //Hay que sacarlo
	private CGP comuna3;

	private Disponibilidad disponibilidadCGPRentas;
	private List<String> diasDeAtencionCGPRentas = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes");
	private ServicioCGP rentas;

	private Disponibilidad disponibilidadTesoreria;
	private ServicioCGP tesoreria;
	private List<String> diasTesoreria = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes");

	private List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();

	private List<String> palabrasClaveBanco = new ArrayList<String>();
	
	BaseDePois base = BaseDePois.getInstance();
	List<OrigenDeDatos> servicios = Arrays.asList(base);
	

	@Before
	public void initialize() {
		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353, 25.4632);
		List<String> palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(domicilioParada, regionParada, coordenadaParada, "114", palabrasClave114);

		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.3243, 21.4484);
		palabrasClaveBanco.add("Cajero automatico");
		palabrasClaveBanco.add("Deposito");
		bancoSantander = new Banco("Banco Santander", coordenadaBanco, palabrasClaveBanco);
		bancoSantander.setDomicilio(domicilioBanco);
		bancoSantander.setRegion(regionBanco);

		domicilioCarrousel = new Domicilio("Av. Estado de Israel",4560, "Lambaré","Guardia Vieja",1200,0,0,0,1414);
		regionCarrousel = new Region ("CABA","Almagro","Bs.As","Argentina");
		coordenadaCarrousel = new Point (34.599434, 58.4296912);
		disponibilidadCarrousel = new Disponibilidad ("10:00","13:00");
		disponibilidadCarrousel2 = new Disponibilidad ("17:00","20:30");
		List<String> palabrasClaveCarrousel = Arrays.asList("Sortija", "Plaza", "Juegos");
		carrouselPlinPlin = new Carrousel ("Carrousel PlinPlin",domicilioCarrousel,regionCarrousel,coordenadaCarrousel,diasDeAtencionCarrousel,disponibilidadCarrousel, disponibilidadCarrousel2, palabrasClaveCarrousel);
		

		disponibilidadTesoreria = new Disponibilidad("09:00", "15:00");
		tesoreria = new ServicioCGP("Tesoreria", diasTesoreria, disponibilidadTesoreria);

		disponibilidadCGPRentas = new Disponibilidad("09:30", "15:30");
		rentas = new ServicioCGP("Rentas", diasDeAtencionCGPRentas, disponibilidadCGPRentas);

		domicilioCGP = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		new Point(34.4353, 24.4856);
		new Polygon();
		comuna3 = new CGP("CGP comuna3","Recoleta",serviciosCGP);
		comuna3.setDomicilio(domicilioCGP);
		comuna3.serviciosCGP.add(tesoreria);
		comuna3.serviciosCGP.add(rentas);

		
	}
	
	
	@Test
	public void estaElCarrouselDisponible() {
		Terminal terminalFlorida = new Terminal("Terminal Florida", servicios);
		terminalFlorida.getBase().getPois().clear();
		terminalFlorida.getBase().getPois().add(parada114);
		terminalFlorida.getBase().getPois().add(bancoSantander);
		terminalFlorida.getBase().getPois().add(carrouselPlinPlin);
		terminalFlorida.getBase().getPois().add(comuna3);

		Assert.assertTrue(terminalFlorida.estaDisponiblePoi("Carrousel PlinPlin","Sabado","12:30"));

	}
	
	@Test
	public void estaElBancoDisponible() {
		
		Terminal terminalAbasto = new Terminal("Terminal Abasto", servicios);

		terminalAbasto.getBase().getPois().clear();
		terminalAbasto.getBase().getPois().add(parada114);
		terminalAbasto.getBase().getPois().add(bancoSantander);
		terminalAbasto.getBase().getPois().add(carrouselPlinPlin);
		Assert.assertTrue(terminalAbasto.estaDisponiblePoi("Banco Santander", "Martes", "11:00"));
	}

	@Test
	public void estaElColectivoDisponible() {
		Terminal terminalCampus = new Terminal("Terminal Campus", servicios);
		terminalCampus.getBase().getPois().clear();
		terminalCampus.getBase().getPois().add(parada114);
		terminalCampus.getBase().getPois().add(bancoSantander);
		terminalCampus.getBase().getPois().add(carrouselPlinPlin);
		Assert.assertTrue(terminalCampus.estaDisponiblePoi("114", "Sabado", "12dsds:30"));
	}

	@Test
	public void estaRentasEstaDisponible() {
		Terminal terminalTeatroColon = new Terminal("Terminal Teatro Colon", servicios);
		terminalTeatroColon.getBase().getPois().clear();
		terminalTeatroColon.getBase().getPois().add(parada114);
		terminalTeatroColon.getBase().getPois().add(bancoSantander);
		terminalTeatroColon.getBase().getPois().add(carrouselPlinPlin);
		terminalTeatroColon.getBase().getPois().add(comuna3);
		Assert.assertFalse(terminalTeatroColon.estaDisponiblePoi("Tesoreria", "Domingo", "11:30"));
	}
}