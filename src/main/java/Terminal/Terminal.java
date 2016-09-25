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

	// Se agrega el getInstance del singleton de la base
	public Mapa base = Mapa.getInstance();
	private AlmacenadorBusquedas almacenador;
	private Point coordenadaDispositivoMovil;
	private String nombreTerminal;
	List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
	private List<Resultado> busquedas = new ArrayList<Resultado>();
	List<NotificacionBusqueda> losObserverBusqueda = new ArrayList<NotificacionBusqueda>();
	private String comunaTerminal;

	public Terminal(String nombre, List<OrigenDeDatos> servicios) {
		setNombreTerminal(nombre);
		setServicios(servicios);
	}

	public List<Poi> consultaDeCercania() {
		return base.getPois().stream().filter(poi -> poi.estaCercaDe(coordenadaDispositivoMovil))
				.collect(Collectors.toList());
	}

	public boolean estaDisponiblePoi(String nombreServicio, DayOfWeek dia, String unaHora) {
		return base.getPois().stream().filter(poi -> poi.getNombre().equals(nombreServicio))
				.anyMatch(poi -> poi.estaDisponible(nombreServicio, dia, LocalTime.parse(unaHora)));
	}

	public List<Poi> busquedaDePuntos(String unNombre, String unaPalabraClave) {

		LocalTime comienzo = LocalTime.now();
		List<Poi> listaResutados = obtenerResultadosServicios(unNombre, unaPalabraClave).stream()
				.collect(Collectors.toList());
		LocalTime finalizacion = LocalTime.now();

		Resultado resultado = new Resultado(LocalDate.now(), finalizacion, comienzo, unNombre + " " + unaPalabraClave,
				listaResutados.size(), this);

		agregarBusqueda(resultado);
		notificarBusqueda(resultado, this);

		return listaResutados;
	}

	public Set<Poi> obtenerResultadosServicios(String unNombre, String unaPalabraClave) {

		return servicios.stream().map(servicio -> servicio.buscarPois(unNombre, unaPalabraClave))
				.flatMap(pois -> pois.stream()).collect(Collectors.toSet());
	}

	/*public List<Integer> obtenerResultadosParciales() {
		List<Integer> resultadosParciales = getBusquedas().stream().map(resultado -> resultado.getCantidadResultados())
				.collect(Collectors.toList());
		return resultadosParciales;
	}*/

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

	private void agregarBusqueda(Resultado resultado) {
		busquedas.add(resultado);
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
		return base;
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

	public List<Resultado> getBusquedas() {
		return this.busquedas;
	}

	public boolean seRealizoBusqueda(LocalDate fecha) {
		return getBusquedas().stream().anyMatch(busqueda -> busqueda.getFecha().equals(fecha));
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