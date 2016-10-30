package TestServiciosExternos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.*;

import DTOs.BancoDTO;
import Inicializacion.CreadorDeObjetos;
import MocksServicios.MockBankService;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import OrigenesDeDatos.ProveedorBancos;
import Pois.Poi;
import Terminal.Terminal;
import cacheServicios.CacheBancos;
import json.JsonFactory;
import redis.clients.jedis.Jedis;

public class TestServicioBanco extends CreadorDeObjetos {

	ProveedorBancos proveedorBancos;
	MockBankService servicio;

	Jedis jedis;
	CacheBancos cache;
	JsonFactory jsonFactory;
	
	Mapa base;
	List<OrigenDeDatos> servicios;

	Terminal terminal;

	List<BancoDTO> bancosEncontrados;
	BancoDTO[] bancosDTO;

	@Before
	public void initialize() {
		this.crearBancoSantander();
		bancoSantander.setNombre("Banco de la Plaza");
		base = Mapa.getInstance();
		servicio = new MockBankService();

		jedis = new Jedis("localhost", 6379);
		
		cache = new CacheBancos(jedis);

		proveedorBancos = new ProveedorBancos();
		proveedorBancos.setCache(cache);

		servicios = Arrays.asList(base, proveedorBancos);
		terminal = new Terminal("Terminal abasto", servicios);

		jsonFactory = new JsonFactory();
		bancosDTO = jsonFactory.fromJson(
				"[{" + "\"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
						+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
						+ "\"servicios\": [ \"cobro cheques\", \"depositos\", \"extracciones\", \"transferencias\", \"creditos\" ] "
						+ "}," + "{" + "\"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9345681," + "\"y\": 72.344546,"
						+ "\"sucursal\": \"Caballito\"," + "\"gerente\": \"Fabian Fantaguzzi\","
						+ "\"servicios\": [ \"depositos\", \"extracciones\", \"transferencias\", \"seguros\" ]" + "}]",
				BancoDTO[].class);
		
		bancosEncontrados = Arrays.asList(bancosDTO);
	}
	
	@After
	public void cerrarConexion(){
		jedis.flushDB();
		jedis.quit();
	}

	@Test
	public void testJSONMapeadoABanco() {

		proveedorBancos.setBankService(servicio);
		List<Poi> bancos = new ArrayList<Poi>();
		bancos = proveedorBancos.buscarPois("Banco", "Servicio");
		Assert.assertTrue(bancos.get(0).getNombre().equals("Banco de la Plaza"));
		Assert.assertEquals(5, bancos.get(0).getPalabrasClave().size());
		Assert.assertTrue(bancos.get(0).getPalabrasClave().get(0).equals("cobro cheques"));

	}

	@Test
	public void busquedaPoisDesdeTerminal() {

		proveedorBancos.setBankService(servicio);
		List<Poi> bancos = new ArrayList<Poi>();
		bancos = proveedorBancos.buscarPois("Banco", "Servicio");
		
		Assert.assertTrue(bancos.get(0).getNombre().equals("Banco de la Plaza"));
		Assert.assertTrue(bancos.get(0).getPalabrasClave().contains("cobro cheques"));
		Assert.assertEquals(5, bancos.get(0).getPalabrasClave().size());
	}

	@Test
	public void busquedaPoisEnCache() {
		proveedorBancos.setBankService(servicio);

		cache.guardar(bancosEncontrados, "Banco de la Plaza", "cobro cheques");
		List<Poi> bancos = proveedorBancos.buscarPois("Banco de la Plaza", "cobro cheques");
		
		Assert.assertFalse(servicio.getConsultado());
		Assert.assertEquals("Banco de la Plaza", bancos.get(0).getNombre());
	}
}
