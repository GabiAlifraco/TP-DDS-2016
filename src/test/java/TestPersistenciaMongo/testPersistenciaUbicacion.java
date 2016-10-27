package TestPersistenciaMongo;

import org.junit.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;

public class testPersistenciaUbicacion {
	  
	MongoClient client;
	Morphia morphia = new Morphia();
	Datastore datastore;

	private Ubicacion ubicacion;
	private Region region;
	private Domicilio domicilio;
	private Punto coordenadas;
	
	@Before
	public void initialize(){
	client = new MongoClient();
	morphia = new Morphia();
	datastore = morphia.createDatastore(client, "ubicaciones");
	
	region = new Region("CABA", "Recoleta", "Bs As", "Argentina");
	domicilio = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
	coordenadas = new Punto(34.4353, 25.4632);
	ubicacion = new Ubicacion(domicilio, region, coordenadas);
	}
	
	@Test
	public void testPersistenciaRegion(){
		Object id = datastore.save(ubicacion).getId();
		Ubicacion ubicacionMongo = datastore.get(Ubicacion.class, id);
		
		Assert.assertEquals(ubicacionMongo.getRegion().getLocalidad(), region.getLocalidad());
		Assert.assertEquals(ubicacionMongo.getRegion().getBarrio(), region.getBarrio());
		Assert.assertEquals(ubicacionMongo.getRegion().getProvincia(), region.getProvincia());
		Assert.assertEquals(ubicacionMongo.getRegion().getPais(), region.getPais());
		client.dropDatabase("ubicaciones");
	}
	
	@Test
	public void testPersistenciaCoordenadas(){
		Object id = datastore.save(ubicacion).getId();
		Ubicacion ubicacionMongo = datastore.get(Ubicacion.class, id);
		
		Assert.assertEquals(ubicacionMongo.getCoordenadas().getLatitud(), coordenadas.getLatitud(), 0.1);
		Assert.assertEquals(ubicacionMongo.getCoordenadas().getLongitud(), coordenadas.getLongitud(), 0.1);
		client.dropDatabase("ubicaciones");
	}

	@Test
	public void testPersistenciaDomicilio(){
		Object id = datastore.save(ubicacion).getId();
		Ubicacion ubicacionMongo = datastore.get(Ubicacion.class, id);
		
		Assert.assertEquals(ubicacionMongo.getDomicilio().getCallePrincipal(), domicilio.getCallePrincipal());
		Assert.assertEquals(ubicacionMongo.getDomicilio().getAlturaPrincipal(), domicilio.getAlturaPrincipal());
		Assert.assertEquals(ubicacionMongo.getDomicilio().getEntreUnaCalle(), domicilio.getEntreUnaCalle());
		Assert.assertEquals(ubicacionMongo.getDomicilio().getHastaOtraCalle(), domicilio.getHastaOtraCalle());
		Assert.assertEquals(ubicacionMongo.getDomicilio().getAlturaCalles(), domicilio.getAlturaCalles());
		Assert.assertEquals(ubicacionMongo.getDomicilio().getPiso(), domicilio.getPiso());
		Assert.assertEquals(ubicacionMongo.getDomicilio().getDepartamento(), domicilio.getDepartamento());
		Assert.assertEquals(ubicacionMongo.getDomicilio().getUnidad(), domicilio.getUnidad());
		Assert.assertEquals(ubicacionMongo.getDomicilio().getCodigoPostal(), domicilio.getCodigoPostal());
		client.dropDatabase("ubicaciones");
	}
}

