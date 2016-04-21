package dds.TpAnual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;

import TpAnual.Banco;
import TpAnual.CGP;
import TpAnual.Domicilio;

import TpAnual.Region;
public class TestCGP {
	
	private Domicilio domicilioCGP;
	private Region regionCGP;
	private Point coordenadaCGP;
	private CGP comuna3;
	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Point coordenadaBanco;
	private Banco bancoSantander;
	private Polygon zonaCGP;

	@Before
	public void initialize(){
		domicilioCGP = new Domicilio("Arenales",1141 , "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionCGP = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaCGP = new Point(34.4353,24.4856);
		zonaCGP = new Polygon();
		comuna3 = new CGP(domicilioCGP, regionCGP, coordenadaCGP,zonaCGP);
		
		domicilioBanco = new Domicilio("Arenales",1245 , "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.3243,24.4657);
		bancoSantander = new Banco(domicilioBanco, regionBanco, coordenadaBanco);
	}
	
	@Test
	public void estaCercaLaComunaDelBanco(){
		Assert.assertEquals(false,comuna3.poiCercanoAOtro(coordenadaBanco));
	}
	
	@Test
	public void distanciaEntreLaComunaYElBanco(){
		Assert.assertEquals(12.477011440513905,comuna3.getCoordenada().distance(coordenadaBanco),0);
	}
	
	
}
