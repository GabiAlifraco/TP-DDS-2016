package persistencia;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;
import OrigenesDeDatos.Mapa;
import Pois.CGP;
import Pois.ParadaColectivo;

public class persistenciaEnMapa extends AbstractPersistenceTest implements WithGlobalEntityManager{
	//CGP
	private CGP cgpComuna3 = new CGP();
	private Ubicacion ubicacion;
	private List<String> palabras;
	private Point coordenadas;
	private Domicilio domicilio;
	private Region region;
	private Disponibilidad disponibilidadTesoreria;
	private List<Disponibilidad> horariosTesoreria;
	private ServicioCGP tesoreria;
	private List<ServicioCGP> serviciosCGP;
	//ParadaColectivo
	private ParadaColectivo parada114= new ParadaColectivo();
	private Ubicacion ubicacion114;
	private Region region114;
	private Domicilio domicilio114;
	private Point coordenada114;
	private List<String> palabras114;
	//Mapa
	Mapa base = Mapa.getInstance();
	
	@Before
	public void setUP(){
		cgpComuna3.setNombre("cgp comuna 3");
		palabras = Arrays.asList("cgp","comuna 3","CABA");
		coordenadas = new Point(34.4124, 24.4856);
		domicilio = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		region= new Region("CABA", "Recoleta", "Bs As", "Argentina");
		ubicacion= new Ubicacion();
		ubicacion.setCoordenadas(coordenadas);
		ubicacion.setDomicilio(domicilio);
		ubicacion.setRegion(region);
		cgpComuna3.setUbicacion(ubicacion);
		cgpComuna3.setPalabrasClave(palabras);
		disponibilidadTesoreria= new Disponibilidad();
		disponibilidadTesoreria.setHorarioFinal(LocalTime.of(15, 0));
		disponibilidadTesoreria.setHorarioInicial( LocalTime.of(9, 0));
		horariosTesoreria= Arrays.asList(disponibilidadTesoreria);
		tesoreria= new ServicioCGP();
		tesoreria.setNombre("Tesoreria");
		tesoreria.setHorariosDeAtencion(horariosTesoreria);
		serviciosCGP=new ArrayList<ServicioCGP>();
		serviciosCGP= Arrays.asList(tesoreria);
		cgpComuna3.setServiciosCGP(serviciosCGP);
		
		;
		parada114.setNombre("parada 114");
		palabras114 = Arrays.asList("parada","colectivo","114","linea 114");
		parada114.setPalabrasClave(palabras114);
		coordenada114 = new Point(34.4124, 24.4856);
		domicilio114 = new Domicilio("Segurola", 1000, "Juan B Justo", "Belaustegui", 0, 0, 0, 0, 1111);
		region114= new Region("CABA", "Floresta", "Bs As", "Argentina");
		ubicacion114 = new Ubicacion();
		ubicacion114.setCoordenadas(coordenada114);
		ubicacion114.setDomicilio(domicilio114);
		ubicacion114.setRegion(region114);
		parada114.setUbicacion(ubicacion114);
		
		
		
		}
	@Test
	public void agregarPoisTest(){
		base.getPois().clear();
		base.agregarUnPoi(cgpComuna3);
		base.agregarUnPoi(parada114);
		Assert.assertTrue(base.entityManager().contains(cgpComuna3));
		
	}
	@Test
	public void agregarYEliminarPoisTest(){
		base.getPois().clear();
		base.agregarUnPoi(cgpComuna3);
		base.agregarUnPoi(parada114);
		base.eliminarUnPoi(parada114);
		Assert.assertFalse(base.entityManager().contains(parada114));
		
}
}
