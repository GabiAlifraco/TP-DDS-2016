package TestServiciosExternos;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.*;
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
import MocksServicios.MockedCGPService;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import OrigenesDeDatos.ProveedorCGPs;
import Pois.CGP;
import Pois.Poi;
import Terminal.Terminal;
import cacheServicios.CacheCGPs;
import cacheServicios.RespuestaServicio;

public class TestServicioCGP {
	MongoClient client;
	Morphia morphia;
	Datastore datastore;
	RespuestaServicio respuesta;
	
	ProveedorCGPs proveedorCGPs = new ProveedorCGPs();
	MockedCGPService servicioExternoCGP = new MockedCGPService();
	
	Mapa base;
	List<OrigenDeDatos> servicios;
	
	Terminal terminal;
	
	private List<ServicioCGP> serviciosCGP;
	private List<DayOfWeek> diasRentas;
	protected Disponibilidad disponibilidadCGPRentas;
	private List<Disponibilidad> horariosRentas;
	private ServicioCGP rentas;
	private Disponibilidad disponibilidadTesoreria;
	private List<Disponibilidad> horariosTesoreria;
	private List<DayOfWeek> diasTesoreria;
	private ServicioCGP tesoreria;
	protected CGP comuna3;
	private Domicilio domicilio;
	private Region region;
	private Ubicacion ubicacion;
	
	List<Poi> cgpsEncontrados;

	CacheCGPs cache;
	@Before
	public void initialize(){
		
	client = new MongoClient();
	morphia = new Morphia();
	datastore = morphia.createDatastore(client, "respuestasCGP");
	cache = new CacheCGPs(datastore);
	proveedorCGPs.setCache(cache);
	
	base = Mapa.getInstance();
	servicios = Arrays.asList(base, proveedorCGPs);
	terminal = new Terminal("Terminal Villa Crespo", servicios);
	
	serviciosCGP = new ArrayList<ServicioCGP>();

	diasRentas = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
			DayOfWeek.FRIDAY);
	disponibilidadCGPRentas = new Disponibilidad(diasRentas, LocalTime.of(9, 30), LocalTime.of(15, 30));
	horariosRentas = Arrays.asList(disponibilidadCGPRentas);

	diasTesoreria = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
			DayOfWeek.FRIDAY);
	disponibilidadTesoreria = new Disponibilidad(diasTesoreria, LocalTime.of(9, 0), LocalTime.of(15, 0));
	horariosTesoreria = Arrays.asList(disponibilidadTesoreria);

	tesoreria = new ServicioCGP("Tesoreria", horariosTesoreria);
	rentas = new ServicioCGP("Rentas", horariosRentas);
	serviciosCGP = Arrays.asList(rentas, tesoreria);
    
	domicilio= new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
	region = new Region("CABA", "Recoleta", "Bs As", "Argentina");
	Punto coordenadaCGP = new Punto(34.4124, 24.4856);
	
	ubicacion= new Ubicacion(domicilio,region,coordenadaCGP);
	comuna3 = new CGP("3", "Recoleta", serviciosCGP, ubicacion);
	
	Punto coordenadaCGP2 = new Punto(34.4124, 24.4852);
	Punto coordenadaCGP3 = new Punto(34.4120, 24.4851);
	List<Punto> zona = new ArrayList<Punto>();
	zona.add(coordenadaCGP);
	zona.add(coordenadaCGP2);
	zona.add(coordenadaCGP3);
	Zona zonaCGP = new Zona(zona);
	comuna3.setZona(zonaCGP);
	

	cgpsEncontrados = new ArrayList<Poi>();
	cgpsEncontrados.add(comuna3);
	}

	@Test
	public void buscarCGPAdapterCGP() {
		proveedorCGPs.setServiceCGP(servicioExternoCGP);
		List<Poi> resultado = proveedorCGPs.buscarPois("CGP comuna 15","Villa Crespo");
		Assert.assertTrue(resultado.get(0).getRegion().getBarrio().equals("Villa Crespo"));
		client.dropDatabase("respuestasCGP");
	}
	@Test
	public void buscarPOIxTerminal() {
		proveedorCGPs.setServiceCGP(servicioExternoCGP);
		List<Poi> resultado = terminal.obtenerResultadosServicios("Rentas", "Villa Crespo").stream().collect(Collectors.toList());
		
		Assert.assertTrue(resultado.get(0).getRegion().getBarrio().equals("Villa Crespo"));
		client.dropDatabase("respuestasCGP");
	}

	@Test
	public void busquedaPoisEnCache() {
		proveedorCGPs.setServiceCGP(servicioExternoCGP);

		cache.guardar(cgpsEncontrados, "Recoleta");
		
		List<Poi> cgps = proveedorCGPs.buscarPois("CGP comuna 3", "Recoleta");
		
		Assert.assertFalse(servicioExternoCGP.getConsultado());
		Assert.assertEquals("Recoleta", cgps.get(0).getRegion().getBarrio());
		client.dropDatabase("respuestasCGP");
	}
}
