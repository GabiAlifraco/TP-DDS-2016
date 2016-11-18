package TestPersistencia;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import com.mongodb.MongoClient;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;
import OrigenesDeDatos.Mapa;
import Pois.CGP;
import Pois.ParadaColectivo;
import Pois.Poi;

public class persistenciaEnMapa extends AbstractPersistenceTest implements WithGlobalEntityManager{
	//CGP

	private Mapa base;
	private CGP cgpComuna3;
	private Ubicacion ubicacion;
	private List<String> palabras;
	private Punto coordenadas;
	private Domicilio domicilio;
	private Region region;
	private Disponibilidad disponibilidadTesoreria;
	private List<Disponibilidad> horariosTesoreria;
	private ServicioCGP tesoreria;
	private List<ServicioCGP> serviciosCGP;
	//ParadaColectivo
	private ParadaColectivo parada114;
	private Ubicacion ubicacion114;
	private Region region114;
	private Domicilio domicilio114;
	private Punto coordenada114;
	private List<String> palabras114;
	//Mapa
	
	
	@Before
	public void setUP(){
		

		base = Mapa.getInstance();
		
		cgpComuna3= new CGP();
		cgpComuna3.setNombre("cgp comuna 3");
		palabras = Arrays.asList("cgp","comuna 3","CABA","que salga colectivo del cgp");
		coordenadas = new Punto(34.4124, 24.4856);
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
		
		parada114 = new ParadaColectivo();
		parada114.setNombre("parada 114");
		palabras114 = Arrays.asList("parada","colectivo","114","linea 114");
		parada114.setPalabrasClave(palabras114);
		coordenada114 = new Punto(34.4124, 24.4856);
		domicilio114 = new Domicilio("Segurola", 1000, "Juan B Justo", "Belaustegui", 0, 0, 0, 0, 1111);
		region114= new Region("CABA", "Floresta", "Bs As", "Argentina");
		ubicacion114 = new Ubicacion();
		ubicacion114.setCoordenadas(coordenada114);
		ubicacion114.setDomicilio(domicilio114);
		ubicacion114.setRegion(region114);
		parada114.setUbicacion(ubicacion114);
		//persist(cgpComuna3);
		
		
		
		}
	@Test
	public void agregarPoisTest(){
		base.agregarUnPoi(cgpComuna3);
		base.agregarUnPoi(parada114);
		Assert.assertTrue(base.getPois().contains(cgpComuna3));
		
	}
	@Test
	public void agregarYEliminarPoisTest(){
		base.agregarUnPoi(cgpComuna3);
		base.agregarUnPoi(parada114);
		base.eliminarUnPoi(parada114);
		Assert.assertFalse(base.entityManager().contains(parada114));
		
}
	@Test
	public void metodoBuscarDeMapa(){
		base.agregarUnPoi(cgpComuna3);
		base.agregarUnPoi(parada114);
		base.eliminarUnPoi(parada114);
		List<Poi> encontrados =base.buscarPois("uncgp", "comuna 3");
		Assert.assertTrue((encontrados.contains(cgpComuna3))&(!(encontrados.contains(parada114))));
	}

}
