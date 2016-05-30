package TpAnual;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public abstract class Poi {
	
	private Point coordenada;
	private Domicilio domicilio;
	private Region region;
	private String nombre;
	private List<String> palabrasClave = new ArrayList<String>();

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
	
	public boolean estaCercaDeOtroPoi(Poi unPoi){
		return estaCercaDe(unPoi.getCoordenada());
	}
	
	public boolean estaCercaDe(Point otraCoordenada) {
		
		return this.coordenada.distance(otraCoordenada) < distanciaMinimaParaConsiderarmeCercano() ;
	}
	
	public abstract int distanciaMinimaParaConsiderarmeCercano();
  
	public Point getCoordenada() {
		return coordenada;
	}
	
	public void setCoordenada(Point unaCoordenada) {
		this.coordenada = unaCoordenada;
	}
	
	public void setNombre(String unNombre){
		this.nombre = unNombre;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setPalabrasClave(List<String> palabras){
		this.palabrasClave = palabras;
	}

	public List<String> getPalabrasClave(){
		return this.palabrasClave;
	}
	
	public void agregarPalabraClave(String unaPalabra){
		this.palabrasClave.add(unaPalabra);
	}
	
    public abstract boolean estaDisponible(String nombreABuscar,String dia,String hora);
    
    public abstract boolean textoIncluido(String texto);
	
    public boolean mismoNombre(String nombreServicio) {
		return getNombre().equals(nombreServicio);
	}
	
	public abstract boolean noTenesIdentificacion();
	
	public boolean sosValido(){
		return !((noTenesIdentificacion())||(coordenada.equals(null)));
	}

}
