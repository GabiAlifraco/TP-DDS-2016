package TestPersistenciaMongo;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;
import CaracteristicaPoi.Zona;
import Inicializacion.CreadorDeObjetos;
import MocksServicios.MockedCGPService;
import OrigenesDeDatos.ProveedorCGPs;
import Pois.CGP;
import Pois.Poi;
import cacheServicios.CacheCGPs;
import seviciosExternos.CGPService;


public class testProveedorDeCGPs extends CreadorDeObjetos{
	MongoClient client;
	Morphia morphia;
	Datastore datastore;
	private CGP cgpComuna15 = new CGP();
	private Ubicacion ubicacion15;
	private List<String> palabras15;
	private Punto coordenadas15;
	private Domicilio domicilio15;
	private Region region15;
	private Disponibilidad disponibilidadTesoreria15;
	private List<Disponibilidad> horariosTesoreria15;
	private ServicioCGP tesoreria15;
	private List<ServicioCGP> serviciosCGP15;
	private CacheCGPs cache;
	private ProveedorCGPs proveedor;
	private CGPService servicio;
	@Before
	public void initialize(){
		this.crearCGPComuna3();
		cgpComuna15.setNombre("cgp comuna 15");
		palabras15 = Arrays.asList("cgp","comuna 15","CABA","que salga colectivo del cgp");
		coordenadas15 = new Punto(34.4124, 24.4856);
		domicilio15 = new Domicilio("Av.Cordoba", 5690, "Bonpland", "Fitz Roy", 0, 0, 0, 0, 1414);
		region15= new Region("CABA", "Villa crespo", "Bs As", "Argentina");
		ubicacion15= new Ubicacion();
		ubicacion15.setCoordenadas(coordenadas15);
		ubicacion15.setDomicilio(domicilio15);
		ubicacion15.setRegion(region15);
		cgpComuna15.setUbicacion(ubicacion15);
		cgpComuna15.setPalabrasClave(palabras15);
		disponibilidadTesoreria15= new Disponibilidad();
		disponibilidadTesoreria15.setHorarioFinal(LocalTime.of(15, 0));
		disponibilidadTesoreria15.setHorarioInicial( LocalTime.of(9, 0));
		horariosTesoreria15= Arrays.asList(disponibilidadTesoreria15);
		tesoreria15= new ServicioCGP();
		tesoreria15.setNombre("Tesoreria");
		tesoreria15.setHorariosDeAtencion(horariosTesoreria15);
		serviciosCGP15=new ArrayList<ServicioCGP>();
		serviciosCGP15= Arrays.asList(tesoreria15);
		cgpComuna15.setServiciosCGP(serviciosCGP15);
		Punto coordenadaCGP2 = new Punto(34.4124, 24.4852);
		Punto coordenadaCGP3 = new Punto(34.4120, 24.4851);
		List<Punto> zona = new ArrayList<Punto>();
		zona.add(coordenadas15);
		zona.add(coordenadaCGP2);
		zona.add(coordenadaCGP3);
		Zona zonaCGP = new Zona(zona);
		cgpComuna15.setZona(zonaCGP);
		cgpComuna15.setPalabrasClave(comuna3.getPalabrasClave());
		proveedor= new ProveedorCGPs();
		servicio = new MockedCGPService();
		proveedor.setServiceCGP(servicio);
	
		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "cgpsExternos");

		
	}
	
	@After
	public void dropDB() {

		client.dropDatabase("cgpsExternos");
	}
	@Test
	public void guarda(){
		cache= new CacheCGPs(datastore);	
		proveedor.setCache(cache);
		List<Poi> aGuardar = Arrays.asList(cgpComuna15);
		List<Poi> aGuardar2=Arrays.asList(comuna3);
		cache.guardarVarios(aGuardar,"Villa crespo");
		cache.guardarVarios(aGuardar2,"Arenales");
	
	Assert.assertEquals(1,cache.buscar("Villa crespo").size());
	
	}
	@Test 
	public void bucscaDesdeProveedor(){
	cache= new CacheCGPs(datastore);	
		proveedor.setCache(cache);
		List<Poi> aGuardar = Arrays.asList(cgpComuna15);
		List<Poi> aGuardar2=Arrays.asList(comuna3);
		cache.guardarVarios(aGuardar,"Villa crespo");
		cache.guardarVarios(aGuardar2,"Arenales");
		
		Assert.assertTrue(proveedor.buscarPois("CGP","Villa crespo").get(0).getNombre().equals("cgp comuna 15"));
	}
	@Test
	public void buscaNoEncuentraYLoAgregaALaBase(){
	cache= new CacheCGPs(datastore);	
		proveedor.setCache(cache);
		List<Poi> aGuardar = Arrays.asList(cgpComuna15);
		List<Poi> aGuardar2=Arrays.asList(comuna3);
		cache.guardarVarios(aGuardar,"Villa crespo");
		cache.guardarVarios(aGuardar2,"Arenales");
		proveedor.buscarPois("cgp algo","calle a buscar");
		Assert.assertTrue(cache.buscar("calle a buscar").get(0).getUbicacion().getRegion().getBarrio().equals("calle a buscar"));
	}

}
