package dds.TpAnual;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;

import TpAnual.Banco;
import TpAnual.Disponibilidad;
import TpAnual.Domicilio;
import TpAnual.ParadaColectivo;

import TpAnual.Region;
public class TestBanco {

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
	
		
	}
	
	
	@Test
	public void estaCercaElBancoDeLaParada(){
		//Assert.assertEquals(true,bancoSantander.esCerca(coordenadaParada));
		Assert.assertTrue(bancoSantander.esCerca(coordenadaParada));
	}
	
	@Test
	public void distanciaEntreElBancoyLaParada(){
		Assert.assertEquals(368.62302584457234,bancoSantander.getCoordenada().distance(parada114.getCoordenada()),0);
	}
	
	
}

