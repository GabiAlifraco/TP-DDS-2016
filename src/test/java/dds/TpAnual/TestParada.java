package dds.TpAnual;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;

import Pois.Banco;
import Pois.ParadaColectivo;
import TpAnual.Domicilio;
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
   
	private List<String> palabrasClaveBanco = new ArrayList<String>();

	
    @Before
	public void initialize(){
		domicilioParada = new Domicilio("Arenales",1141 , "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Point(34.4353,25.4632);
		List<String> palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(domicilioParada, regionParada, coordenadaParada, "114", palabrasClave114);

		domicilioBanco = new Domicilio("Arenales",1245 , "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Point(34.3243,24.4657);
		palabrasClaveBanco.add("Cajero automatico");
		palabrasClaveBanco.add("Deposito");		
		bancoSantander = new Banco("Banco Santander", coordenadaBanco, palabrasClaveBanco);
		bancoSantander.setDomicilio(domicilioBanco);
		bancoSantander.setRegion(regionBanco);
	
	}
	
	@Test
	public void estaCercaElBancoDeLaParada(){
		Assert.assertTrue(parada114.estaCercaDe(bancoSantander.getCoordenada()));
	}
	
	@Test
	public void distanciaEntreElBancoyLaParada(){
		Assert.assertEquals(92.36909222476103,parada114.getCoordenada().distance(coordenadaBanco),0);
	}
	
	
}
