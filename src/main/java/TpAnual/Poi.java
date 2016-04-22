package TpAnual;

import org.uqbar.geodds.Point;

public interface Poi {
	public boolean esCerca(Point otraCoordenada);

	boolean textoIncluido(String texto);
	
	
	public boolean estaDisponible(String dia,String hora);

	public Point getCoordenada();

	public boolean mismoNombre(String nombreServicio);

}
