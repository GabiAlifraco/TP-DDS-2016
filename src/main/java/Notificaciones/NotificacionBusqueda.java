package Notificaciones;
import Resultado.Resultado;

public interface NotificacionBusqueda {
	
	public void notificarBusqueda(Resultado resultado);
	public void setTiempoMaximoBusqueda(long tiempoMaximoBusqueda);
	
}
