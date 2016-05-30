package TpAnual;
import org.uqbar.geodds.Point;
public abstract class Poi {
	
	//Declaramos los atributos principales del poi
	private Point coordenada;
	private Domicilio domicilio;
	private Region region;
	
	//Setters y getters de los atributos
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
	
	//Esto es para la entrega 1: Calculo de Cercania
	public boolean estaCercaDeOtroPoi(Poi unPoi){
		return estaCercaDe(unPoi.getCoordenada());
	}
	public boolean estaCercaDe(Point otraCoordenada) {
		return this.coordenada.distance(otraCoordenada) < distanciaMinimaParaConsiderarmeCercano() ;
	}
	public abstract int distanciaMinimaParaConsiderarmeCercano();
	
	//Esto es para la entrega 1: Calculo de la disponibilidad 
    public abstract boolean estaDisponible(String nombreABuscar,String dia,String hora);
    
    //Esto es para la entrega 1: Busqueda de puntos
    public abstract boolean textoIncluido(String texto);
    public abstract boolean mismoNombre(String nombreServicio);
	
    //Verifico que sea un poi valido
    public abstract boolean noTenesIdentificacion();
	public boolean sosValido(){
		return !((noTenesIdentificacion())||(coordenada.equals(null)));
	}

}
