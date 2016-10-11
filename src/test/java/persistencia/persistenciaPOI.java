package persistencia;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import Pois.CGP;

public class persistenciaPOI extends AbstractPersistenceTest implements WithGlobalEntityManager{

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
		}
	
	@Test
	public void crearCGPTest(){
		entityManager().persist(cgpComuna3);
		assertEquals(cgpComuna3.getPoiID(), entityManager().find(CGP.class, cgpComuna3.getPoiID() ).getPoiID());
		
	}
}
