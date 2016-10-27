package TestPersistenciaMongo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import Pois.Banco;
import Pois.Poi;
import cacheServicios.RespuestaServicio;

public class testPersistenciaRespuestaServicio {

		MongoClient client;
		Morphia morphia;
		Datastore datastore;
		RespuestaServicio respuesta;
		
		private Punto coordenadaBanco;
		private List<String> palabrasClaveBanco;
		private Domicilio domicilioBanco;
		private Region regionBanco;
		private Ubicacion ubicacion;
		protected Banco bancoSantander;
		private Disponibilidad horarioBanco;
		private List<Disponibilidad> horariosBanco = new ArrayList<Disponibilidad>();
		private List<DayOfWeek> diasBanco = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
		private LocalDate fechaRespuesta;
		private List<Poi> bancosEncontrados = new ArrayList<Poi>();

		@Before
		public void initialize(){
		client = new MongoClient();
		morphia = new Morphia();
		datastore = morphia.createDatastore(client, "respuestas");
		
		domicilioBanco = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionBanco = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaBanco = new Punto(34.3243,21.4484);
		ubicacion = new Ubicacion(domicilioBanco,regionBanco,coordenadaBanco);
		palabrasClaveBanco = Arrays.asList("Cajero automatico", "Deposito");
		bancoSantander = new Banco("Banco Santander", palabrasClaveBanco,ubicacion);
		
		
		bancoSantander.setDomicilio(domicilioBanco);
		bancoSantander.setRegion(regionBanco);
		diasBanco = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY);
		horarioBanco = new Disponibilidad(diasBanco, LocalTime.of(9, 30), LocalTime.of(15, 00));
		horariosBanco.add(horarioBanco);
		bancoSantander.setHorariosDeAtencion(horariosBanco);
		
		bancosEncontrados.add(bancoSantander);
		
		fechaRespuesta = LocalDate.parse("2016-10-20");
		respuesta = new RespuestaServicio(fechaRespuesta, bancosEncontrados, "Deposito", "Banco Santander");
		
		}

		@Test
		public void testPersisteFechaRespuesta(){
			Object id = datastore.save(respuesta).getId();
			RespuestaServicio respuesta = datastore.get(RespuestaServicio.class, id);

			Assert.assertEquals(fechaRespuesta, respuesta.getFechaConsulta());
			client.dropDatabase("respuestas");
		}
		@Test
		public void testFiltraFecha(){
			Object id = datastore.save(respuesta).getId();
			List<RespuestaServicio> respuestas = 
					datastore.find(RespuestaServicio.class).filter("fechaConsulta <=" ,LocalDate.now()).asList();

			Assert.assertEquals(id, respuestas.get(0).getId());
			Assert.assertEquals(fechaRespuesta, respuestas.get(0).getFechaConsulta());
			client.dropDatabase("respuestas");
		}
		@Test
		public void testFiltroNombreServicio(){
			
		}
		}

