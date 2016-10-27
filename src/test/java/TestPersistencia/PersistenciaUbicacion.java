
package TestPersistencia;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;

public class PersistenciaUbicacion extends AbstractPersistenceTest implements WithGlobalEntityManager{

	private Ubicacion ubicacion;
	private Region region;
	private Domicilio domicilio;
	private Punto coordenadas;
	@Before
	public void initialize(){
		region = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		domicilio = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		coordenadas = new Punto(34.4353, 25.4632);
		ubicacion = new Ubicacion(domicilio, region, coordenadas);
		entityManager().persist(ubicacion);
	}
	
	@Test 
	public void testPersistenciaUbicacion(){
		Assert.assertEquals(entityManager().find(Ubicacion.class, ubicacion.getUbicacionID()), ubicacion);
	}

	@Test 
	public void testPersistenciaRegion(){
		Region regionPersistida = entityManager().find(Ubicacion.class, ubicacion.getUbicacionID()).getRegion();
		Assert.assertEquals(regionPersistida.getBarrio(), region.getBarrio());
		Assert.assertEquals(regionPersistida.getLocalidad(), region.getLocalidad());
		Assert.assertEquals(regionPersistida.getProvincia(), region.getProvincia());
		Assert.assertEquals(regionPersistida.getPais(), region.getPais());
	}
	@Test 
	public void testPersistenciaDomicilio(){

		Domicilio domicilioPersistido = entityManager().find(Ubicacion.class, ubicacion.getUbicacionID()).getDomicilio();
		Assert.assertEquals(domicilioPersistido.getCallePrincipal(), domicilio.getCallePrincipal());
		Assert.assertEquals(domicilioPersistido.getAlturaPrincipal(), domicilio.getAlturaPrincipal());
		Assert.assertEquals(domicilioPersistido.getEntreUnaCalle(), domicilio.getEntreUnaCalle());
		Assert.assertEquals(domicilioPersistido.getHastaOtraCalle(), domicilio.getHastaOtraCalle());
		Assert.assertEquals(domicilioPersistido.getAlturaCalles(), domicilio.getAlturaCalles());
		Assert.assertEquals(domicilioPersistido.getPiso(), domicilio.getPiso());
		Assert.assertEquals(domicilioPersistido.getDepartamento(), domicilio.getDepartamento());
		Assert.assertEquals(domicilioPersistido.getUnidad(), domicilio.getUnidad());
		Assert.assertEquals(domicilioPersistido.getCodigoPostal(), domicilio.getCodigoPostal());
	}
	
	@Test
	public void testPersistenciaCoordenadas(){
		Punto coordenadasPersistidas = entityManager().find(Ubicacion.class, ubicacion.getUbicacionID()).getCoordenadas();
		Assert.assertEquals(coordenadasPersistidas, coordenadas);
	}

}
