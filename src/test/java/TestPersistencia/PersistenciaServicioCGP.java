package TestPersistencia;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.ServicioCGP;

public class PersistenciaServicioCGP extends AbstractPersistenceTest implements WithGlobalEntityManager {


	ServicioCGP rentas;
	LocalTime horarioInicial;
	LocalTime horarioFinal;
	List<DayOfWeek> dias;
	Disponibilidad disponibilidad;
	List<Disponibilidad> horariosDisponibilidad;
	
	@Before
	public void initialize(){
		dias = new ArrayList<DayOfWeek>();
		dias.add(DayOfWeek.MONDAY);
		horarioInicial = LocalTime.of(9, 30);
		horarioFinal = LocalTime.of(15, 00);
		disponibilidad = new Disponibilidad(dias,horarioInicial, horarioFinal);
		horariosDisponibilidad = Arrays.asList(disponibilidad);
		rentas = new ServicioCGP("Rentas", horariosDisponibilidad);
		entityManager().persist(rentas);
	}
	
	
	@Test
	public void testPersistenciaServicioCGP(){
		Assert.assertEquals(entityManager().find(ServicioCGP.class, rentas.getIdServicioCGP())
				, rentas);
	}
	
	@Test
	public void testPersistenciaNombreServicio(){
		Assert.assertEquals(entityManager().find(ServicioCGP.class, rentas.getIdServicioCGP()).getNombre()
				, rentas.getNombre());
	}
	@Test
	public void testPersistenciaDisponibilidadServicioCGP(){
		Assert.assertEquals(entityManager().find(ServicioCGP.class, rentas.getIdServicioCGP()).getHorariosDeAtencion()
				, horariosDisponibilidad);
	}
	
}
