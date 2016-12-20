package Terminal;

import java.time.DayOfWeek;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Accesos.Usuario;
import CaracteristicaPoi.Punto;
import Notificaciones.MailDemoraBusqueda;
import Notificaciones.NotificacionBusqueda;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import Pois.Poi;
import Resultado.Resultado;
import ResultadosReportes.ResultadosReportes;

@org.mongodb.morphia.annotations.Embedded
@Entity
@Table(name="Terminales")
public class Terminal implements WithGlobalEntityManager{

	@Id
	@GeneratedValue
	private long idTerminal;
	
	@Transient
	@org.mongodb.morphia.annotations.Transient
	private MailDemoraBusqueda mail;
	
	@Transient
	@org.mongodb.morphia.annotations.Transient
	public Mapa mapa = Mapa.getInstance();
	

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	@Embedded
	private Punto coordenadaDispositivoMovil;
	
	private String nombreTerminal;
	@Transient
	@org.mongodb.morphia.annotations.Transient
	List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
	@Transient
	@org.mongodb.morphia.annotations.Transient
	List<NotificacionBusqueda> notificadoresBusqueda = new ArrayList<NotificacionBusqueda>();
	private String comunaTerminal;
    @Transient
    @org.mongodb.morphia.annotations.Transient
	private ResultadosReportes resultReportes;
	
	public Terminal(String nombre, List<OrigenDeDatos> servicios) {
		setNombreTerminal(nombre);
		setServicios(servicios);
	}
	public Terminal(String nombre, List<OrigenDeDatos> servicios,Usuario usuario) {
		setNombreTerminal(nombre);
		setServicios(servicios);
		setUsuario(usuario);
	}

	public Terminal(){
		
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
				this, listaResultados);

		notificarBusqueda(resultado, this);

		return listaResultados;
	}

	public Set<Poi> obtenerResultadosServicios(String unNombre, String unaPalabraClave) {
		
		return servicios.stream().map(servicio -> servicio.buscarPois(unNombre, unaPalabraClave))
				.flatMap(pois -> pois.stream()).collect(Collectors.toSet());
	}

	public void activarReportes() {
		this.resultReportes.activarReportes(this);
	}

	public void desactivarReportes() {
		this.resultReportes.desactivarReportes(this);
	}

	public void notificarBusqueda(Resultado resultado, Terminal terminal) {
		notificadoresBusqueda.stream().forEach(observerBusqueda -> observerBusqueda.actualizar(resultado, terminal));
	}

	public void agregarObserver(NotificacionBusqueda observer) {
		notificadoresBusqueda.add(observer);
	}

	public void eliminarObserver(NotificacionBusqueda observer) {
		notificadoresBusqueda.remove(observer);
	}

	public void agregarNuevoServicio(OrigenDeDatos nuevoOrigen) {
		this.getServicios().add(nuevoOrigen);
	}

	public List<OrigenDeDatos> getServicios() {
		return this.servicios;
	}

	public List<NotificacionBusqueda> getNotificadoresBusqueda() {
		return this.notificadoresBusqueda;
	}
	public void setNotificadoresBusqueda(List<NotificacionBusqueda> notificadores){
		this.notificadoresBusqueda = notificadores;
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

	public long getIdTerminal() {
		return idTerminal;
	}
	
	public void setIdTerminal(long idTerminal) {
		this.idTerminal = idTerminal;
	}
	
	public Punto getCoordenadaDispositivoMovil() {
		return coordenadaDispositivoMovil;
	}
	
	public void setCoordenadaDispositivoMovil(Punto coordenadaDispositivoMovil) {
		this.coordenadaDispositivoMovil = coordenadaDispositivoMovil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreTerminal == null) ? 0 : nombreTerminal.hashCode());
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
		Terminal other = (Terminal) obj;
		if (nombreTerminal == null) {
			if (other.nombreTerminal != null)
				return false;
		} else if (!nombreTerminal.equals(other.nombreTerminal))
			return false;
		return true;
	}

	
}
