package TestPersistenciaRedis;

import java.util.Arrays;
import java.util.List;

import org.junit.*;

import DTOs.BancoDTO;
import cacheServicios.CacheBancos;
import json.JsonFactory;
import redis.clients.jedis.Jedis;

public class testPersistenciaCacheBancos {
	
	Jedis jedis;
	CacheBancos cacheBancos;
	List<BancoDTO> bancosEncontrados;
	BancoDTO[] bancosDTO;
	JsonFactory jsonFactory;
	
	@Before
	public void initialize(){
		
		jedis = new Jedis("localhost", 6379);
		cacheBancos = new CacheBancos(jedis);
		
		jsonFactory = new JsonFactory();
		bancosDTO = jsonFactory.fromJson(
				"[{" + "\"banco\": \"Banco de la Plaza\"," + "\"x\": -39.9338322," + "\"y\": 65.348353,"
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
	public void testGuardarEnCache(){
		cacheBancos.guardar(bancosEncontrados, "Banco de la Plaza", "cobro cheques");
		String key = "Banco de la Plaza" + "cobro cheques";
		
		List<BancoDTO> bancosRedis =  Arrays.asList(jsonFactory.fromJson(jedis.get(key), BancoDTO[].class));
		Assert.assertEquals(bancosEncontrados.get(0).getX(), bancosRedis.get(0).getX());
		Assert.assertEquals(bancosEncontrados.get(1).getX(), bancosRedis.get(1).getX());
	}
}
