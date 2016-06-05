package TpAnual;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

import UbicacionPoi.Domicilio;
import UbicacionPoi.Region;

public abstract class Poi {

	// Declaramos los atributos principales del poi
	private Point coordenada;
	private Domicilio domicilio;
	private Region region;
	private String nombre;
	List<String> palabrasClave = new ArrayList<String>();

	// Setters y getters de los atributos
	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Point getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Point unaCoordenada) {
		this.coordenada = unaCoordenada;
	}

	// Esto es para la entrega 1: Calculo de Cercania
	public boolean estaCercaDeOtroPoi(Poi unPoi) {
		return estaCercaDe(unPoi.getCoordenada());
	}

	public boolean estaCercaDe(Point otraCoordenada) {
		return this.getCoordenada().distance(otraCoordenada) < distanciaMinimaParaConsiderarmeCercano();
	}

	public abstract int distanciaMinimaParaConsiderarmeCercano();

	// Esto es para la entrega 1: Calculo de la disponibilidad
	public abstract boolean estaDisponible(String nombreABuscar, String dia, String hora);

	// Esto es para la entrega 1: Busqueda de puntos
	public boolean textoIncluido(String unNombre, String unaPalabraClave) {
		return getPalabrasClave().stream().anyMatch(palabra -> palabra.contains(unaPalabraClave))
				|| this.mismoNombre(unNombre);
	}

	// Verifico que sea un poi valido
	public abstract boolean noTenesIdentificacion();

	public boolean sosValido() {
		return !((noTenesIdentificacion()) || (coordenada.equals(null)));
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPalabrasClave(List<String> palabras) {
		this.palabrasClave = palabras;
	}

	public List<String> getPalabrasClave() {
		return this.palabrasClave;
	}

	public void agregarPalabraClave(String unaPalabra) {
		this.palabrasClave.add(unaPalabra);
	}

	public boolean mismoNombre(String nombreServicio) {
		return getNombre().equals(nombreServicio);
	}

	public boolean mismaCoordenada(Point otraCoordenada) {
		return this.getCoordenada().equals(otraCoordenada);
	}

	public boolean noSeRepite(List<Poi> listaPois) {
		return !(listaPois.stream().anyMatch(poiLista -> poiLista.mismaCoordenada(this.getCoordenada())));

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordenada == null) ? 0 : coordenada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poi other = (Poi) obj;
		if (coordenada == null) {
			if (other.coordenada != null)
				return false;
		} else if (!coordenada.equals(other.coordenada))
			return false;
		return true;
	}

	
}