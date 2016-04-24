package TpAnual;

import org.uqbar.geodds.Point;

public interface Poi {
	public boolean esCerca(Point otraCoordenada);
    public Point getCoordenada();
	
    public boolean estaDisponible(String dia,String hora);
    
    boolean textoIncluido(String texto);
	public boolean mismoNombre(String nombreServicio);

}
