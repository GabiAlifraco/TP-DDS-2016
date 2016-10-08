package Terminal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.uqbar.geodds.Point;

import Notificaciones.AlmacenadorBusquedas;
import Notificaciones.NotificacionBusqueda;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Poi;
import Resultado.Resultado;

public class Terminal {

	public Mapa mapa = Mapa.getInstance();
	private AlmacenadorBusquedas almacenador;
	private Point coordenadaDispositivoMovil;
	private String nombreTerminal;
	List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
	List<NotificacionBusqueda> losObserverBusqueda = new ArrayList<NotificacionBusqueda>();
	private String comunaTerminal;

	public Terminal(String nombre, List<OrigenDeDatos> servicios) {
		setNombreTerminal(nombre);
		setServicios(servicios);
	}

	public List<Poi> consultaDeCercania() {
		return mapa.getPois().stream().filter(poi -> poi.estaCercaDe(coordenadaDispositivoMovil))
				.collect(Collectors.toList());
	}

	public boolean estaDisponiblePoi(String nombreServicio, DayOfWeek dia, String unaHora) {
		return mapa.getPois().stream().filter(poi -> poi.getNombre().equals(nombreServicio))
				.anyMatch(poi -> poi.estaDisponible(nombreServicio, dia, LocalTime.parse(unaHora)));
	}

	public List<Poi> busquedaDePuntos(String unNombre, String unaPalabraClave) {

		LocalTime comienzo = LocalTime.now();
		List<Poi> listaResultados = obtenerResultadosServicios(unNombre, unaPalabraClave).stream()
				.collect(Collectors.toList());
		mapa.agregarResultados(listaResultados);
		LocalTime finalizacion = LocalTime.now();

		Resultado resultado = new Resultado(LocalDate.now(), finalizacion, comienzo, unNombre + " " + unaPalabraClave,
				listaResultados.size(), this);

		//agregarBusqueda(resultado);
		notificarBusqueda(resultado, this);

		return listaResultados;
	}

	public Set<Poi> obtenerResultadosServicios(String unNombre, String unaPalabraClave) {
		
		return servicios.stream().map(servicio -> servicio.buscarPois(unNombre, unaPalabraClave))
				.flatMap(pois -> pois.stream()).collect(Collectors.toSet());
	}

	public void activarReportes() {
		this.almacenador.activarReportes(this);
	}

	public void desactivarReportes() {
		this.almacenador.desactivarReportes(this);
	}

	public void notificarBusqueda(Resultado resultado, Terminal terminal) {
		losObserverBusqueda.stream().forEach(observerBusqueda -> observerBusqueda.actualizar(resultado, terminal));
	}

	public void agregarObserver(NotificacionBusqueda observer) {
		losObserverBusqueda.add(observer);
	}

	public void eliminarObserver(NotificacionBusqueda observer) {
		losObserverBusqueda.remove(observer);
	}

	public void agregarNuevoServicio(OrigenDeDatos nuevoOrigen) {
		this.getServicios().add(nuevoOrigen);
	}

	public List<OrigenDeDatos> getServicios() {
		return this.servicios;
	}

	public List<NotificacionBusqueda> getObserverBusquedas() {
		return this.losObserverBusqueda;
	}

	public Mapa getBase() {
		return mapa;
	}

	private void setServicios(List<OrigenDeDatos> servicios) {
		this.servicios = servicios;
	}

	public String getNombreTerminal() {
		return nombreTerminal;
	}

	public void setNombreTerminal(String nombreTerminal) {
		this.nombreTerminal = nombreTerminal;
	}

	public String getComunaTerminal() {
		return comunaTerminal;
	}

	public void setComunaTerminal(String comunaTerminal) {
		this.comunaTerminal = comunaTerminal;
	}

	public void setAlmacenadorBusquedas(AlmacenadorBusquedas almacenador) {
		this.almacenador = almacenador;
	}
}