package TpAnual;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.uqbar.geodds.Point;

import Observers.ObserverBusqueda;
import OrigenesDeDatos.BaseDePois;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Poi;

public class Terminal {

	// Se agrega el getInstance del singleton de la base
	public BaseDePois base = BaseDePois.getInstance();
	private Point coordenadaDispositivoMovil;
	private String nombreTerminal;
	List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
	public List<Poi> resultadosConRepetidos = new ArrayList<Poi>();
	public List<Poi> resultadosSinRepetidos = new ArrayList<Poi>();
	private List<Resultado> busquedas = new ArrayList<Resultado>();

	public List<OrigenDeDatos> getServicios() {
		return this.servicios;
	}

	public void agregarNuevoServicio(OrigenDeDatos nuevoOrigen) {
		this.getServicios().add(nuevoOrigen);
	}

	// Getter de la base
	public BaseDePois getBase() {
		return base;
	}

	public Terminal(String nombre, List<OrigenDeDatos> servicios) {
		setNombreTerminal(nombre);
		setServicios(servicios);

	}

	private void setServicios(List<OrigenDeDatos> servicios) {
		this.servicios = servicios;
	}

	// Esto es para la entrega 1: Calculo de Cercania
	public List<Poi> consultaDeCercania() {

		return base.getPois().stream().filter(poi -> poi.estaCercaDe(coordenadaDispositivoMovil))
				.collect(Collectors.toList());
	}

	// Esto es para la entrega 1: Calculo de la disponibilidad
	public boolean estaDisponiblePoi(String nombreServicio, DayOfWeek dia, String unaHora) {
		return base.getPois().stream().filter(poi -> poi.getNombre().equals(nombreServicio))
				.anyMatch(poi -> poi.estaDisponible(nombreServicio, dia, LocalTime.parse(unaHora)));
	}

	// Esto es para la entrega 1: Busqueda de puntos
	public List<Poi> busquedaDePuntos(String unNombre, String unaPalabraClave) {

		long comienzo = segundoActual();
		List<Poi> listaResutados = obtenerResultadosServicios(unNombre, unaPalabraClave).stream().collect(Collectors.toList());
		long finalizacion = segundoActual();

		Resultado resultado = instanciarResultado(LocalDate.now(), (finalizacion - comienzo),
				unNombre + " " + unaPalabraClave, listaResutados.size(), this);

		notificarBusqueda(resultado);

		return listaResutados;
	}

	private Resultado instanciarResultado(LocalDate fechaActual, long segundosBusqueda, String frase,
			int cantidadResultados, Terminal terminal) {
		Resultado resultadoBusqueda = new Resultado(fechaActual, segundosBusqueda, frase, cantidadResultados, terminal);
		return resultadoBusqueda;
	}

	public Set<Poi> obtenerResultadosServicios(String unNombre, String unaPalabraClave) {

		return servicios.stream().map(servicio -> servicio.buscarPois(unNombre, unaPalabraClave))
				.flatMap(pois -> pois.stream()).collect(Collectors.toSet());
	}
	/*
	 * public List<Poi> obtenerResultadosServicios(String unNombre, String
	 * unaPalabraClave) { resultadosConRepetidos.clear();
	 * 
	 * servicios.stream().forEach(servicio ->
	 * agregarResultados(servicio.buscarPois(unNombre, unaPalabraClave)));
	 * resultadosConRepetidos.stream().forEach(resultado ->
	 * eliminarDuplicados(resultado, resultadosConRepetidos));
	 * 
	 * return resultadosSinRepetidos; }
	 

	public List<Poi> agregarResultados(List<Poi> lista) {

		if (lista != null) {
			resultadosConRepetidos = lista;
		}
		return resultadosConRepetidos;

	}

	private List<Poi> eliminarDuplicados(Poi poi, List<Poi> listaPois) {

		resultadosSinRepetidos = listaPois.stream().filter(poiLista -> poiLista.noSeRepite(listaPois))
				.collect(Collectors.toList());
		this.agregarPoi(resultadosSinRepetidos, poi);

		return resultadosSinRepetidos;

	}*/

	public void agregarPoi(List<Poi> lista, Poi unPoi) {
		lista.add(unPoi);
	}

	public String getNombreTerminal() {
		return nombreTerminal;
	}

	public void setNombreTerminal(String nombreTerminal) {
		this.nombreTerminal = nombreTerminal;
	}

	public long segundoActual() {
		return 1000 * System.currentTimeMillis();
	}

	public List<Resultado> getBusquedas() {
		return this.busquedas;
	}

	public List<Integer> obtenerResultadosParciales() {
		List<Integer> resultadosParciales = getBusquedas().stream().map(resultado -> resultado.getCantidadResultados())
				.collect(Collectors.toList());
		return resultadosParciales;
	}

	List<ObserverBusqueda> losObserverBusqueda = new ArrayList<ObserverBusqueda>();

	public void notificarBusqueda(Resultado resultado) {
		losObserverBusqueda.stream().forEach(observerBusqueda -> observerBusqueda.notificarBusqueda(resultado));
	}

	public void agregarObserver(ObserverBusqueda observer) {
		losObserverBusqueda.add(observer);
	}

	public void eliminarObserver(ObserverBusqueda observer) {
		losObserverBusqueda.remove(observer);
	}

	public List<ObserverBusqueda> getObserverBusquedas() {
		return this.losObserverBusqueda;
	}

	public void agregarBusqueda(Resultado resultado) {
		busquedas.add(resultado);
	}
}