package TestPois;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Inicializacion.CreadorDeObjetos;



public class TestCGP extends CreadorDeObjetos{
		
	@Before
	public void initialize(){
	this.crearBancoSantander();
	this.crearCGPComuna3();
	}
	
	@Test
	public void estaCercaLaComunaDelBanco(){
		
		Assert.assertFalse(comuna3.estaCercaDe(bancoSantander.getCoordenada())); //Modificar
	}
	
	@Test
	public void distanciaEntreLaComunaYElBanco(){
		
		Assert.assertEquals(279,comuna3.getCoordenada().distance(bancoSantander.getCoordenada()),0.5); //Modificar
	}
	
	
}