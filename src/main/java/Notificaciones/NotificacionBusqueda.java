package Notificaciones;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import Resultado.Resultado;
import Terminal.Terminal;

@Table(name="NotificacionesBusqueda")  
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) 
public abstract class NotificacionBusqueda {
	
	public abstract void actualizar(Resultado resultado, Terminal terminal);
	
}
