package TestPersistencia;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import CaracteristicaPoi.Disponibilidad;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersistenciaDisponibilidad extends AbstractPersistenceTest implements WithGlobalEntityManager{

	LocalTime horarioInicial;
	LocalTime horarioFinal;
	List<DayOfWeek> dias;
	Disponibilidad disponibilidad;
	@Before
	public void initialize(){
		dias = new ArrayList<DayOfWeek>();
				
		dias.add(DayOfWeek.MONDAY);
		horarioInicial = LocalTime.of(9, 30);
		horarioFinal = LocalTime.of(15, 00);
		disponibilidad = new Disponibilidad(dias,horarioInicial, horarioFinal);
		entityManager().persist(disponibilidad);
	}
	
	
	@Test
	public void testPersistenciaDisponibilidad(){
		Assert.assertEquals(entityManager().find(Disponibilidad.class, disponibilidad.getIdDisponibilidad())
				, disponibilidad);
	}
	
	@Test
	public void testPersistenciaHorarios(){
		LocalTime horarioInicialPersistido = entityManager()
				.find(Disponibilidad.class, disponibilidad.getIdDisponibilidad()).getHorarioInicial();
		LocalTime horarioFinalPersistido = entityManager()
				.find(Disponibilidad.class, disponibilidad.getIdDisponibilidad()).getHorarioFinal();
		Assert.assertEquals(horarioInicialPersistido, horarioInicial);
		Assert.assertEquals(horarioFinalPersistido, horarioFinal);
	}

	@Test
	public void testPersistenciaDias(){
		List<DayOfWeek> diasPersistidos = entityManager()
				.find(Disponibilidad.class, disponibilidad.getIdDisponibilidad()).getDias();
		Assert.assertEquals(diasPersistidos, dias);
	}
}
