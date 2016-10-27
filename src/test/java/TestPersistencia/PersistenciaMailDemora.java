package TestPersistencia;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.Ubicacion;
import Notificaciones.MailDemoraBusqueda;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.ParadaColectivo;
import Pois.Poi;
import Resultado.Resultado;
import Terminal.Terminal;

public class PersistenciaMailDemora extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private Resultado resultado;
	private LocalDate fecha;
	protected MailDemoraBusqueda notificadorAdministrador;
	private Terminal terminal;
	private List<OrigenDeDatos> servicios;
	private Domicilio domicilioParada;
	private Region regionParada;
	private Punto coordenadaParada;
	private Ubicacion ubicacionParada;
	private List<String> palabrasClave114;
	protected ParadaColectivo parada114;
	
	@Before
	public void initialize(){
		domicilioParada = new Domicilio("Arenales", 1141, "Junin", "Santa Fe", 2100, 0, 0, 0, 1111);
		regionParada = new Region("CABA", "Recoleta", "Bs As", "Argentina");
		coordenadaParada = new Punto(34.4353, 25.4632);
		ubicacionParada = new Ubicacion(domicilioParada, regionParada, coordenadaParada);
		palabrasClave114 = Arrays.asList("Colectivo", "Parada");
		parada114 = new ParadaColectivo(ubicacionParada, "114", palabrasClave114);
		fecha = LocalDate.parse("2016-10-16");
		notificadorAdministrador = new MailDemoraBusqueda();
		terminal = new Terminal("Terminal Abasto", servicios);
		terminal.agregarObserver(notificadorAdministrador);
		notificadorAdministrador.setTiempoMaximoBusqueda(5);
		List<Poi> poisEncontrados = Arrays.asList(parada114);
		resultado = new Resultado(fecha, LocalTime.of(10, 40, 02), LocalTime.of(10, 40, 10), "sarasa", terminal, poisEncontrados);
		
		terminal.notificarBusqueda(resultado,terminal);
	}
	
	@Test
	public void persistirMail(){
		entityManager().persist(notificadorAdministrador);
		assertEquals(notificadorAdministrador.getId_MailDemora(), entityManager().find(MailDemoraBusqueda.class, notificadorAdministrador.getId_MailDemora()).getId_MailDemora());
	}
	
	@Test 
	public void persistirTiempoMaxBusqueda(){
		entityManager().persist(notificadorAdministrador);
		assertEquals(notificadorAdministrador.getTiempoMaximoBusqueda(),entityManager().find(MailDemoraBusqueda.class, notificadorAdministrador.getId_MailDemora()).getTiempoMaximoBusqueda());
	}
}
