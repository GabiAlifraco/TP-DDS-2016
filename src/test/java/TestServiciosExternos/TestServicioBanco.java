package TestServiciosExternos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import Inicializacion.CreadorDeObjetos;
import MocksServicios.MockBankService;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import OrigenesDeDatos.ProveedorBancos;
import Pois.Poi;
import Terminal.Terminal;
import cacheServicios.CacheBancos;

public class TestServicioBanco extends CreadorDeObjetos {

	ProveedorBancos proveedorBancos;
	MockBankService servicio;
	MongoClient client;
	Morphia morphia;
	Datastore datastore;
	CacheBancos cache;

	Mapa base;
	List<OrigenDeDatos> servicios;

	Terminal terminal;

	List<Poi> bancosEncontrados;

	@Before
	public void initialize() {
		this.crearBancoSantander();
		bancoSantander.setNombre("Banco de la Plaza");
		base = Mapa.getInstance();
		servicio = new MockBankService();

		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "respuestasServicioMock");

		cache = new CacheBancos(datastore);
		proveedorBancos = new ProveedorBancos();
		proveedorBancos.setCache(cache);
		servicios = Arrays.asList(base, proveedorBancos);
		terminal = new Terminal("Terminal abasto", servicios);

		bancosEncontrados = new ArrayList<Poi>();
		bancosEncontrados.add(bancoSantander);
	}

	@Test
	public void testJSONMapeadoABanco() {

		proveedorBancos.setBankService(servicio);
		List<Poi> bancos = new ArrayList<Poi>();
		bancos = proveedorBancos.buscarPois("Banco", "Servicio");
		Assert.assertTrue(bancos.get(0).getNombre().equals("Banco de la Plaza"));
		Assert.assertEquals(5, bancos.get(0).getPalabrasClave().size());
		Assert.assertTrue(bancos.get(0).getPalabrasClave().get(0).equals("cobro cheques"));
		client.dropDatabase("respuestasServicioMock");

	}

	@Test
	public void busquedaPoisDesdeTerminal() {

		proveedorBancos.setBankService(servicio);
		List<Poi> bancos = new ArrayList<Poi>();
		//bancos = terminal.busquedaDePuntos("Banco", "Servicio");
		bancos = proveedorBancos.buscarPois("Banco", "Servicio");
		
		Assert.assertTrue(bancos.get(0).getNombre().equals("Banco de la Plaza"));
		Assert.assertTrue(bancos.get(0).getPalabrasClave().contains("cobro cheques"));
		Assert.assertEquals(5, bancos.get(0).getPalabrasClave().size());
		client.dropDatabase("respuestasServicioMock");
	}

	@Test
	public void busquedaPoisEnCache() {
		proveedorBancos.setBankService(servicio);

		cache.guardar(bancosEncontrados, "Banco de la Plaza", "cobro cheques");
		
		List<Poi> bancos = proveedorBancos.buscarPois("Banco de la Plaza", "cobro cheques");
		
		Assert.assertFalse(servicio.getConsultado());
		Assert.assertEquals("Banco de la Plaza", bancos.get(0).getNombre());
		client.dropDatabase("respuestasServicioMock");
	}
}
