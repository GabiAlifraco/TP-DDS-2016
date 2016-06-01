package TpAnual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.geodds.Point;

public class Terminal {

	// Se agrega el getInstance del singleton de la base
	private BaseDePois base = BaseDePois.getInstance();
	private Point coordenadaDispositivoMovil;
	private String nombreTerminal;
	List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
	List<Poi> resultadosConRepetidos = new ArrayList<Poi>();
	List<Poi> resultadosSinRepetidos = new ArrayList<Poi>();

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

	/*
	 * public String obtenerInfoDe(Poi unPuntoDeInteres){
	 * 
	 * return ""; } HAY QUE TERMINAR ESTE METODO SI ES QUE EN ALGUN MOMENTO SE
	 * ESPECIFICA LA FUNCION
	 */

	// Esto es para la entrega 1: Calculo de Cercania
	public List<Poi> consultaDeCercania() {
		return base.getPois().stream().filter(poi -> poi.estaCercaDe(coordenadaDispositivoMovil))
				.collect(Collectors.toList());
	}

	// Esto es para la entrega 1: Calculo de la disponibilidad
	public boolean estaDisponiblePoi(String nombreServicio, String dia, String hora) {
		return base.getPois().stream().filter(poi -> poi.mismoNombre(nombreServicio))
				.anyMatch(poi -> poi.estaDisponible(nombreServicio, dia, hora));
	}

	// Esto es para la entrega 1: Busqueda de puntos
	public List<Poi> busquedaDePuntos(String palabraClave, String otraPalabraClave) {

		return obtenerResultadosServicios(palabraClave, otraPalabraClave);
	}

	public List<Poi> obtenerResultadosServicios(String palabraClave, String otraPalabraClave) {
		resultadosConRepetidos.clear();

		servicios.stream().forEach(servicio -> agregarResultados(servicio.buscarPois(palabraClave, otraPalabraClave)));
		resultadosConRepetidos.stream().forEach(resultado -> eliminarDuplicados(resultado, resultadosConRepetidos));

		return resultadosSinRepetidos;
	}

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

	}

	public void agregarPoi(List<Poi> lista, Poi unPoi) {
		lista.add(unPoi);
	}

	public String getNombreTerminal() {
		return nombreTerminal;
	}

	public void setNombreTerminal(String nombreTerminal) {
		this.nombreTerminal = nombreTerminal;
	}

}
