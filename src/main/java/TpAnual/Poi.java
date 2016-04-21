package TpAnual;

import java.util.List;

import org.uqbar.geodds.Point;

public interface Poi {
	public boolean poiCercanoAOtro(Point otraCoordenada);

	public boolean poiEstaDisponible();

	boolean textoIncluido(String texto);
}
