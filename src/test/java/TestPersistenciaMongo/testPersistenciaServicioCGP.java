package TestPersistenciaMongo;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.ServicioCGP;

public class testPersistenciaServicioCGP {

	MongoClient client;
	Morphia morphia = new Morphia();
	Datastore datastore;

	ServicioCGP rentas;
	LocalTime horarioInicial;
	LocalTime horarioFinal;
	List<DayOfWeek> dias;
	Disponibilidad disponibilidad;
	List<Disponibilidad> horariosDisponibilidad;

	@Before
	public void initialize() {
		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "servicios");
		
		dias = new ArrayList<DayOfWeek>();
		dias.add(DayOfWeek.MONDAY);
		horarioInicial = LocalTime.of(9, 30);
		horarioFinal = LocalTime.of(15, 00);
		disponibilidad = new Disponibilidad(dias, horarioInicial, horarioFinal);
		horariosDisponibilidad = Arrays.asList(disponibilidad);
		rentas = new ServicioCGP("Rentas", horariosDisponibilidad);
	}
	
	@After
	public void dropDB() {
		client.dropDatabase("servicios");

	}

	@Test
	public void testPersistenciaNombreServicio() {
		Object id = datastore.save(rentas).getId();
		ServicioCGP servicioMongo = datastore.get(ServicioCGP.class, id);
		Assert.assertEquals(servicioMongo.getNombre(), rentas.getNombre());
	}
	
	@Test
	public void testPersistenciaDisponibilidadServicioCGP(){
		Object id = datastore.save(rentas).getId();
		ServicioCGP servicioMongo = datastore.get(ServicioCGP.class, id);
		Disponibilidad disponibilidadPersistida = servicioMongo.getHorariosDeAtencion().get(0);
		
		Assert.assertEquals(disponibilidadPersistida.getDias(), dias);
		Assert.assertEquals(disponibilidadPersistida.getHorarioInicial(), horarioInicial);
		Assert.assertEquals(disponibilidadPersistida.getHorarioFinal(), horarioFinal);
	}

}
