package dds.TpAnual;
import java.util.Arrays;

import java.util.List;
import TpAnual.Sistema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;

import TpAnual.Banco;
import TpAnual.CGP;
import TpAnual.Disponibilidad;
import TpAnual.Domicilio;

import TpAnual.Region;
public class TestCGP {
	
	private Domicilio domicilioCGP;
	private Region regionCGP;
	private Point coordenadaCGP;
	private CGP comuna3;
	private Polygon zonaCGP;
	
	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Point coordenadaBanco;
	private Banco bancoSantander;
	
	private Disponibilidad disponibilidadBancaria;
	private List<String> diasDeAtencionBancaria = Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes");
	private Disponibilidad disponibilidadCGPRentas;
	private List<String> diasDeAtencionCGPRentas = Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes");
	private Sistema sistema;

	
	@Before
	public void initialize(){
		domicilioCGP = new Domicilio("Arenales",1141 , "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionCGP = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaCGP = new Point(34.4353,24.4856);
		zonaCGP = new Polygon();
		disponibilidadCGPRentas = new Disponibilidad ("09:30","15:30");
		comuna3 = new CGP("Rentas",domicilioCGP, regionCGP, coordenadaCGP,zonaCGP,diasDeAtencionCGPRentas,disponibilidadCGPRentas);
		
		domicilioBanco = new Domicilio("Arenales",1245 , "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.3243,24.4657);
		disponibilidadBancaria = new Disponibilidad ("10:00","15:00");
		bancoSantander = new Banco("Banco Santander",domicilioBanco, regionBanco, coordenadaBanco,diasDeAtencionBancaria,disponibilidadBancaria);
	
		sistema = new Sistema();
	}
	
	@Test
	public void estaCercaLaComunaDelBanco(){
		Assert.assertEquals(false,sistema.poiCercanoAOtro(comuna3, bancoSantander));
	}
	
	@Test
	public void distanciaEntreLaComunaYElBanco(){
		Assert.assertEquals(12.477011440513905,comuna3.getCoordenada().distance(coordenadaBanco),0);
	}
	
	
}
