package dds.TpAnual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import TpAnual.Banco;
import TpAnual.CGP;
import TpAnual.Carrousel;
import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
import TpAnual.ParadaColectivo;
import TpAnual.Region;
import TpAnual.ServicioCGP;
import TpAnual.Sistema;

public class TestDisponibilidadDeUnPoi {
	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;
	
	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Point coordenadaBanco;
	private Banco bancoSantander;
	private Disponibilidad disponibilidadBancaria;
	private List<String> diasDeAtencionBancaria = Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes");
	
	private Domicilio domicilioCarrousel;
	private Region regionCarrousel;
	private Point coordenadaCarrousel;
	private Carrousel carrouselPlinPlin;
	private Disponibilidad disponibilidadCarrousel;
	private Disponibilidad disponibilidadCarrousel2;
	private List<String> diasDeAtencionCarrousel = Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado");
	
	private Domicilio domicilioCGP;
	private Point coordenadaCGP;
	private Region regionCGP;
	private Polygon zonaCGP;
	private CGP comuna3;
	
	private List<String> palabrasClaveRentas = Arrays.asList("Rentas","CGP");
	private Disponibilidad disponibilidadCGPRentas;
	private List<String> diasDeAtencionCGPRentas = Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes");
	private ServicioCGP rentas;
	
	private List<String> palabrasClaveTesoreria = Arrays.asList("Rentas","CGP");
	private Disponibilidad disponibilidadTesoreria;
	private ServicioCGP tesoreria;
	private List<String> diasTesoreria= Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes");
	
	private List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();
	
	
	@Before
	public void initialize(){
		domicilioParada = new Domicilio("Arenales",1141 , "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353,25.4632);
		parada114 = new ParadaColectivo(domicilioParada, regionParada, coordenadaParada,"114");
		
		domicilioBanco = new Domicilio("Arenales",1245 , "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.3243,21.4484);
		disponibilidadBancaria = new Disponibilidad ("10:00","15:00");
		bancoSantander = new Banco("Banco Santander",domicilioBanco, regionBanco, coordenadaBanco,diasDeAtencionBancaria,disponibilidadBancaria);
		
		domicilioCarrousel = new Domicilio("Av. Estado de Israel",4560, "Lambaré","Guardia Vieja",1200,0,0,0,1414);
		regionCarrousel = new Region ("CABA","Almagro","Bs.As","Argentina");
		coordenadaCarrousel = new Point (34.599434, 58.4296912);
		disponibilidadCarrousel = new Disponibilidad ("10:00","13:00");
		disponibilidadCarrousel2 = new Disponibilidad ("17:00","20:30");
		carrouselPlinPlin = new Carrousel ("Carrousel PlinPlin",domicilioCarrousel,regionCarrousel,coordenadaCarrousel,diasDeAtencionCarrousel,disponibilidadCarrousel, disponibilidadCarrousel2);
		
		disponibilidadTesoreria = new Disponibilidad("09:00","15:00");
		tesoreria = new ServicioCGP("Tesoreria",diasTesoreria,disponibilidadTesoreria,palabrasClaveTesoreria);
		
		disponibilidadCGPRentas = new Disponibilidad ("09:30","15:30");
		rentas = new ServicioCGP("Rentas",diasDeAtencionCGPRentas,disponibilidadCGPRentas,palabrasClaveRentas);
				
		domicilioCGP = new Domicilio("Arenales",1141 , "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionCGP = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaCGP = new Point(34.4353,24.4856);
		zonaCGP = new Polygon();
		comuna3 = new CGP("CGP comuna3",domicilioCGP, regionCGP, coordenadaCGP,zonaCGP, serviciosCGP);
		comuna3.serviciosCGP.add(tesoreria);
		comuna3.serviciosCGP.add(rentas);
		
	}
	
	@Test
	public void estaElCarrouselDisponible() {
		Sistema sistema = new Sistema();
		sistema.pois.add(parada114);
		sistema.pois.add(bancoSantander);
		sistema.pois.add(carrouselPlinPlin);
		sistema.pois.add(comuna3);
		
		Assert.assertEquals(true, sistema.poiEstaDisponible("Carrousel PlinPlin","Sabado","12:30"));

	}
	@Test
	public void estaElBancoDisponible() {
		Sistema sistema = new Sistema();
		sistema.pois.add(parada114);
	    sistema.pois.add(bancoSantander);
		sistema.pois.add(carrouselPlinPlin);
		Assert.assertEquals(true, sistema.poiEstaDisponible("Banco Santander","Martes","11:00"));
	}
	@Test
	public void estaElColectivoDisponible() {
		Sistema sistema = new Sistema();
		sistema.pois.add(parada114);
		sistema.pois.add(bancoSantander);
		sistema.pois.add(carrouselPlinPlin);
		Assert.assertEquals(true, sistema.poiEstaDisponible("114","Sabado","12dsds:30"));
	}
	@Test
	public void estaRentasEstaDisponible() {
		Sistema sistema = new Sistema();
		sistema.pois.add(parada114);
		sistema.pois.add(bancoSantander);
		sistema.pois.add(carrouselPlinPlin);
		sistema.pois.add(comuna3);
		Assert.assertEquals(false, sistema.poiEstaDisponible("Tesoreria","Domingo","11:30"));
}
}