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
import TpAnual.Sistema;
import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
import TpAnual.ParadaColectivo;
import TpAnual.Poi;
import TpAnual.Region;
import TpAnual.ServicioCGP;


public class TestSistema {
	private Sistema sistema;
	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;
	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Point coordenadaBanco;
	private Banco bancoSantander;
	private Disponibilidad disponibilidadBancaria;
	private List<String> diasDeAtencionBancaria = Arrays.asList("Lunes", "Martes", "Miercoles", "Jueves", "Viernes");
	private Domicilio domicilioCGP;
	private Region regionCGP;
	private Point coordenadaCGP;
	private CGP comuna3;
	private Polygon zonaCGP;
	
	private Disponibilidad disponibilidadCGPRentas;
	private List<String> palabrasClaveRentas = Arrays.asList("Rentas","CGP");
	private List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();
	private ServicioCGP rentas;
	private List<String> diasDeAtencionCGPRentas = Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes");


	@Before
	public void initialize() {
		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353, 25.4632);
		parada114 = new ParadaColectivo(domicilioParada, regionParada, coordenadaParada, "114");
		parada114.setLinea("Parada 114");
		

		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.3243, 24.4657);
		disponibilidadBancaria = new Disponibilidad("10:00", "15:00");
		bancoSantander = new Banco("Banco Santander", domicilioBanco, regionBanco, coordenadaBanco,
				                   diasDeAtencionBancaria, disponibilidadBancaria);
		domicilioCGP = new Domicilio("Arenales",1141 , "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionCGP = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaCGP = new Point(34.4353,24.4856);
		zonaCGP = new Polygon();
		comuna3 = new CGP("CGP comuna3",domicilioCGP, regionCGP, coordenadaCGP,zonaCGP,serviciosCGP);
		
		disponibilidadCGPRentas = new Disponibilidad ("09:30","15:30");
		rentas = new ServicioCGP("Rentas",diasDeAtencionCGPRentas,disponibilidadCGPRentas,palabrasClaveRentas);
		

		sistema = new Sistema();
		sistema.pois.add(parada114);
		sistema.pois.add(bancoSantander);
		sistema.pois.add(comuna3);
		comuna3.serviciosCGP.add(rentas);
	}

	@Test
	public void estaCercaElBancoDeLaParada(){
		Assert.assertEquals(true,sistema.poiCercanoAOtro(parada114,bancoSantander));
	}
	
	
	@Test
	public void busquedaDePoisPorClave() {

		List<Poi> resultadoEsperado = Arrays.asList(bancoSantander);

		Assert.assertEquals(true, sistema.busquedaDePuntos("Cajero").equals(resultadoEsperado));

	}

	@Test
	public void busquedaSinResultados() {
		
		Assert.assertEquals(true, sistema.busquedaDePuntos("Plaza").isEmpty());

	}
	@Test
	public void busquedaDeServicioCGPPorClave() {

		List<Poi> resultadoEsperado = Arrays.asList(comuna3);

		Assert.assertEquals(true, sistema.busquedaDePuntos("CGP").equals(resultadoEsperado));

	}

}