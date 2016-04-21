package dds.TpAnual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;

import TpAnual.Banco;
import TpAnual.Domicilio;
import TpAnual.ParadaColectivo;
import TpAnual.Poi;
import TpAnual.Region;
public class TestParada {

	private Region regionParada;
	private Domicilio domicilioParada;
	private Point coordenadaParada;
	private ParadaColectivo parada114;
	private Domicilio domicilioBanco;
	private Region regionBanco;
	private Point coordenadaBanco;
	private Banco bancoSantander;

	@Before
	public void initialize(){
		domicilioParada = new Domicilio("Arenales",1141 , "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353,25.4632);
		parada114 = new ParadaColectivo(domicilioParada, regionParada, coordenadaParada);
		
		domicilioBanco = new Domicilio("Arenales",1245 , "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.3243,24.4657);
		bancoSantander = new Banco(domicilioBanco, regionBanco, coordenadaBanco);
	}
	
	@Test
	public void estaCercaElBancoDeLaParada(){
		Assert.assertEquals(true,parada114.poiCercanoAOtro(coordenadaBanco));
	}
	
	
}
