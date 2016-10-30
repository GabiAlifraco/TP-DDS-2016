package TestPersistenciaMongo;

import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import Pois.ParadaColectivo;

public class testPersistenciaPoi {

	MongoClient client;
	Morphia morphia = new Morphia();
	Datastore datastore;

	private Domicilio domicilioParada;
	private Region regionParada;
	private Punto coordenadaParada;
	private Ubicacion ubicacionParada;
	private List<String> palabrasClave114;
	protected ParadaColectivo parada114;

	@Before
	public void initialize() {
		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "pois");

		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Punto(34.4353, 25.4632);
		ubicacionParada = new Ubicacion(domicilioParada, regionParada, coordenadaParada);
		palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(ubicacionParada, "114", palabrasClave114);

	}
	@After
	public void dropDB(){
		
		client.dropDatabase("pois");
	}

	@Test
	public void testPersistePalabrasClave() {
		Object id = datastore.save(parada114).getId();
		ParadaColectivo paradaMongo = datastore.get(ParadaColectivo.class, id);

		Assert.assertEquals(paradaMongo.getPalabrasClave(), palabrasClave114);
	}

	@Test
	public void testPersistenciaRegionUbicacion() {
		Object id = datastore.save(parada114).getId();
		ParadaColectivo paradaMongo = datastore.get(ParadaColectivo.class, id);
		Ubicacion ubicacionMongo = paradaMongo.getUbicacion();
		
		Assert.assertEquals(ubicacionMongo.getRegion().getLocalidad(), regionParada.getLocalidad());
		Assert.assertEquals(ubicacionMongo.getRegion().getBarrio(), regionParada.getBarrio());
		Assert.assertEquals(ubicacionMongo.getRegion().getProvincia(), regionParada.getProvincia());
		Assert.assertEquals(ubicacionMongo.getRegion().getPais(), regionParada.getPais());
	}

	@Test
	public void testPersistenciaCoordenadasUbicacion(){
		Object id = datastore.save(parada114).getId();
		ParadaColectivo paradaMongo = datastore.get(ParadaColectivo.class, id);
		Ubicacion ubicacionMongo = paradaMongo.getUbicacion();
		
		Assert.assertEquals(ubicacionMongo.getCoordenadas().getLatitud(), coordenadaParada.getLatitud(), 0.1);
		Assert.assertEquals(ubicacionMongo.getCoordenadas().getLongitud(), coordenadaParada.getLongitud(), 0.1);
	}

}