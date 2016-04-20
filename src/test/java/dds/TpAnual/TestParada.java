package dds.TpAnual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.*;

import TpAnual.Domicilio;
import TpAnual.ParadaColectivo;
import TpAnual.Poi;
import TpAnual.Region;
public class TestParada {

	private Domicilio unDomicilio;
	private Region unaRegion;
	private Point unaCoordenada;
	private Poi unaParada;
	private Domicilio domicilioJuan;
	private Region regionJuan;
	private Point coordenadaJuan;
	private Poi juan;
	private ParadaColectivo parada114;

	@Before
	public void initialize(){
		unDomicilio = new Domicilio("Arenales", 1100, "Riobamba", "Lima", 2000, 0, 0, 0, 1112);
		unaRegion = new Region("CABA","Villa Bosch","Bs As", "Argentina");
		unaCoordenada = new Point(32.4354, 35.1264);
		unaParada = new Poi(unDomicilio, unaRegion, unaCoordenada);
		parada114 = new ParadaColectivo(unaCoordenada);
		domicilioJuan = new Domicilio("Arenales", 1145,"Av.Las Heras","Riobamba", 2133, 3, 10, 2, 1123);
		regionJuan = new Region("CABA","Recoleta","Bs As","Argentina");
		coordenadaJuan = new Point(33.4264,32.6576);
		juan = new Poi(domicilioJuan, regionJuan, coordenadaJuan);
	}
	
	@Test
	public void juanEstaCercaDeLaParada114(){
		Assert.assertEquals(false,parada114.poiCercanoAOtro(coordenadaJuan));
	}
	
	@Test
	public void distanciaEntreJuanYParda114(){
		Assert.assertEquals(255.39433377124993,unaParada.getCoordenada().distance(coordenadaJuan),0);
	}
}
