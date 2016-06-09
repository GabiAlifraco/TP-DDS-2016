package TestPois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;

import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Region;
import Pois.Banco;
import Pois.CGP;
import Pois.ServicioCGP;
import TpAnual.Disponibilidad;

public class TestCGP {
	
	private Domicilio domicilioCGP;
	private Point coordenadaCGP;
	private CGP comuna3;
	private Polygon zonaCGP;
	
	private Disponibilidad disponibilidad1;
	private List<Disponibilidad> horariosCGPRentas = new ArrayList<Disponibilidad>();
	private List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();
	private ServicioCGP rentas;
	private List<DayOfWeek> diasDeAtencionCGPRentas = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY);
	
	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Point coordenadaBanco;
	private Banco bancoSantander;	
	private List<String> palabrasClaveBanco = new ArrayList<String>();
	
	private Point coordenadaCGP2;
	private Point coordenadaCGP3;


	

	
	@Before
	public void initialize(){
		domicilioCGP = new Domicilio("Arenales",1141 , "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		
		coordenadaCGP = new Point(34.4124,24.4856);
		coordenadaCGP2 = new Point(34.4124,24.4852);
		coordenadaCGP3 = new Point(34.4120,24.4851);
		zonaCGP = new Polygon();
		comuna3 = new CGP("CGP comuna3","Recoleta",serviciosCGP);
		
		comuna3.setDomicilio(domicilioCGP);
	    comuna3.setCoordenada(coordenadaCGP);
		comuna3.setZona(zonaCGP);
		
		zonaCGP.add(coordenadaCGP);
		zonaCGP.add(coordenadaCGP2);
		zonaCGP.add(coordenadaCGP3);
		
		disponibilidad1 = new Disponibilidad (diasDeAtencionCGPRentas,LocalTime.of(9,30),LocalTime.of(15,30));
		horariosCGPRentas.add(disponibilidad1);
		rentas = new ServicioCGP("Rentas",horariosCGPRentas);
		
		
		domicilioBanco = new Domicilio("Arenales",1245 , "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.4120,24.4854);
		palabrasClaveBanco.add("Cajero automatico");
		palabrasClaveBanco.add("Deposito");		
		bancoSantander = new Banco("Banco Santander", coordenadaBanco, palabrasClaveBanco);
		bancoSantander.setDomicilio(domicilioBanco);
		bancoSantander.setRegion(regionBanco);
		
		comuna3.serviciosCGP.add(rentas);

	}
	
	@Test
	public void estaCercaLaComunaDelBanco(){
		
		Assert.assertFalse(comuna3.estaCercaDe(bancoSantander.getCoordenada())); //Modificar
	}
	
	@Test
	public void distanciaEntreLaComunaYElBanco(){
		
		Assert.assertEquals(0.02,coordenadaCGP.distance(coordenadaBanco),0.5); //Modificar
	}
	
	
}