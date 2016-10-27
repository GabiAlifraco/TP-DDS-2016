package TestPersistenciaMongo;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import CaracteristicaPoi.Disponibilidad;

public class testPersistenciaDisponibilidad {

	LocalTime horarioInicial;
	LocalTime horarioFinal;
	List<DayOfWeek> dias;
	Disponibilidad disponibilidad;
	
	MongoClient client;
	Morphia morphia = new Morphia();
	Datastore datastore;
	
	@Before
	public void initialize(){
		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "disponibilidad");
		
		dias = new ArrayList<DayOfWeek>();
				
		dias.add(DayOfWeek.MONDAY);
		dias.add(DayOfWeek.TUESDAY);
		horarioInicial = LocalTime.of(9, 30);
		horarioFinal = LocalTime.of(15, 00);
		disponibilidad = new Disponibilidad(dias,horarioInicial, horarioFinal);
	}
		
	@Test
	public void testPersistenciaHorarios(){
		Object id = datastore.save(disponibilidad).getId();
		Disponibilidad disponibilidadMongo = datastore.get(Disponibilidad.class, id);
		
		LocalTime horarioInicialPersistido = disponibilidadMongo.getHorarioInicial();
		LocalTime horarioFinalPersistido = disponibilidadMongo.getHorarioFinal();

		Assert.assertEquals(horarioInicial, horarioInicialPersistido);
		Assert.assertEquals(horarioFinal, horarioFinalPersistido);
		client.dropDatabase("disponibilidad");
	}

	@Test
	public void testPersistenciaDias(){
		Object id = datastore.save(disponibilidad).getId();
		Disponibilidad disponibilidadMongo = datastore.get(Disponibilidad.class, id);
		
		List<DayOfWeek> diasPersistidos = disponibilidadMongo.getDias();
		Assert.assertEquals(diasPersistidos, dias);
		client.dropDatabase("disponibilidad");

	}
}
