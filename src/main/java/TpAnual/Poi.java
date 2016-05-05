package TpAnual;

import org.uqbar.geodds.Point;

public abstract class Poi {
	
	private Point coordenada;
	private Domicilio domicilio;
	private Region region;
	
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
	
    public abstract boolean estaDisponible(String nombreABuscar,String dia,String hora);
    
    public abstract boolean textoIncluido(String texto);
	
    public abstract boolean mismoNombre(String nombreServicio);
	
	public abstract boolean noTenesIdentificacion();
	
	public boolean sosValido(){
		return !((noTenesIdentificacion())||(coordenada.equals(null)));
	}

}
