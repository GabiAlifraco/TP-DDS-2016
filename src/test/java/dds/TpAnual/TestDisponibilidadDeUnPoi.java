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
import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
import TpAnual.Region;
import TpAnual.ServicioCGP;
import TpAnual.InfoFast;

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

	private Domicilio domicilioCGP;
	private Point coordenadaCGP;
	private Region regionCGP;
	private Polygon zonaCGP;
	private CGP comuna3;

	private List<String> palabrasClaveRentas = Arrays.asList("Rentas", "CGP");
	private Disponibilidad disponibilidadCGPRentas;
	private List<String> diasDeAtencionCGPRentas = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes");
	private ServicioCGP rentas;

	private List<String> palabrasClaveTesoreria = Arrays.asList("Rentas", "CGP");
	private Disponibilidad disponibilidadTesoreria;
	private ServicioCGP tesoreria;
	private List<String> diasTesoreria = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes");

	private List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();

	private List<String> palabrasClaveBanco = new ArrayList<String>();

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
		tesoreria = new ServicioCGP("Tesoreria", diasTesoreria, disponibilidadTesoreria, palabrasClaveTesoreria);

		disponibilidadCGPRentas = new Disponibilidad("09:30", "15:30");
		rentas = new ServicioCGP("Rentas", diasDeAtencionCGPRentas, disponibilidadCGPRentas, palabrasClaveRentas);

		domicilioCGP = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionCGP = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaCGP = new Point(34.4353, 24.4856);
		zonaCGP = new Polygon();
		List<String> palabrasClaveCGP = Arrays.asList("Rentas");
		comuna3 = new CGP("CGP comuna3", domicilioCGP, regionCGP, coordenadaCGP, zonaCGP, serviciosCGP,
				palabrasClaveCGP);
		comuna3.serviciosCGP.add(tesoreria);
		comuna3.serviciosCGP.add(rentas);

	}
	
	
	@Test
	public void estaElCarrouselDisponible() {
		InfoFast sistema = new InfoFast();
		sistema.getBase().getPois().clear();
		sistema.getBase().getPois().add(parada114);
		sistema.getBase().getPois().add(bancoSantander);
		sistema.getBase().getPois().add(carrouselPlinPlin);
		sistema.getBase().getPois().add(comuna3);

		Assert.assertTrue(sistema.estaDisponiblePoi("Carrousel PlinPlin","Sabado","12:30"));

	}
	
	@Test
	public void estaElBancoDisponible() {
		InfoFast sistema = new InfoFast();
		sistema.getBase().getPois().clear();
		sistema.getBase().getPois().add(parada114);
		sistema.getBase().getPois().add(bancoSantander);
		sistema.getBase().getPois().add(carrouselPlinPlin);
		Assert.assertTrue(sistema.estaDisponiblePoi("Banco Santander", "Martes", "11:00"));
	}

	@Test
	public void estaElColectivoDisponible() {
		InfoFast sistema = new InfoFast();
		sistema.getBase().getPois().clear();
		sistema.getBase().getPois().add(parada114);
		sistema.getBase().getPois().add(bancoSantander);
		sistema.getBase().getPois().add(carrouselPlinPlin);
		Assert.assertTrue(sistema.estaDisponiblePoi("114", "Sabado", "12dsds:30"));
	}

	@Test
	public void estaRentasEstaDisponible() {
		InfoFast sistema = new InfoFast();
		sistema.getBase().getPois().clear();
		sistema.getBase().getPois().add(parada114);
		sistema.getBase().getPois().add(bancoSantander);
		sistema.getBase().getPois().add(carrouselPlinPlin);
		sistema.getBase().getPois().add(comuna3);
		Assert.assertFalse(sistema.estaDisponiblePoi("Tesoreria", "Domingo", "11:30"));
	}
}