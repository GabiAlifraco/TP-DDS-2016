package TpAnual;

import java.util.Arrays;
import java.util.List;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP implements Poi{
	
	private Point coordenada;
	private Domicilio domicilio;
	private Region region;
	List<String> palabrasClave = Arrays.asList("Rentas"); 
	private String nombre;
	public List<ServicioCGP> serviciosCGP;
	
    private Disponibilidad horarioDeAtencion;

	private Polygon zona;
	
	public CGP (String unNombre, Domicilio unDomicilio,Region unaRegion,Point unaCoordenada,
			    Polygon unaZona, List<ServicioCGP> servicios){
		setCoordenada(unaCoordenada);
		setZona(unaZona);
		setRegion(unaRegion);
		setDomicilio(unDomicilio);
		this.nombre = unNombre;
		serviciosCGP= servicios;
	}
	
	
	public boolean esCerca(Point otraCoordenada) {
		return this.zona.isInside(coordenada);
		
	}

	public boolean textoIncluido(String texto) {
		return serviciosCGP.stream().anyMatch(servicioCGP -> servicioCGP.getPalabrasClave().stream().anyMatch(palabra -> palabra.contains(texto)));
	}
	

	public boolean estaDisponible(String dia, String hora) {
		return serviciosCGP.stream().anyMatch(servicioCGP -> (servicioCGP.getDiasDeAtencion().contains(dia) && servicioCGP.horarioDentroDelRango(hora)));
	}
	
	public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
	}


	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}


	public Point getCoordenada() {
		return coordenada;
	}


	public void setCoordenada(Point coordenada) {
		this.coordenada = coordenada;
	}


	public Polygon getZona() {
		return zona;
	}


	public void setZona(Polygon zona) {
		this.zona = zona;
	}
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public boolean mismoNombre(String nombreServicio) {
		return serviciosCGP.stream().anyMatch(servicioCGP -> servicioCGP.igualNombre(nombreServicio));
	}
	


	

}
