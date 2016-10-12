package Terminal;

import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Notificaciones.NotificacionBusqueda;

public class AdministradorUsuarios implements WithGlobalEntityManager {

	public void agregarTerminal(Terminal terminal) {
		if (!entityManager().contains(terminal)) {
			entityManager().persist(terminal);
		}
	}

	public void eliminarTerminal(Terminal terminal) {
		if (!entityManager().contains(terminal)) {
			entityManager().remove(terminal);
		}
	}
	
	public Terminal buscarTerminal(long idTerminal){
		return entityManager().find(Terminal.class, idTerminal);
	}

	public void modificarCoordenadaTerminal(long idTerminal, Point coordenadaModificada) {
		Terminal terminalPersistida = this.buscarTerminal(idTerminal);
		if (terminalPersistida != null) {
			eliminarTerminal(terminalPersistida);
			terminalPersistida.setCoordenadaDispositivoMovil(coordenadaModificada);
			agregarTerminal(terminalPersistida);

		}
	}

	public void modificarNombreTerminal(long idTerminal, String nombreModificado) {
		Terminal terminalPersistida = entityManager().find(Terminal.class, idTerminal);
		if (terminalPersistida != null) {
			eliminarTerminal(terminalPersistida);
			terminalPersistida.setNombreTerminal(nombreModificado);
			agregarTerminal(terminalPersistida);
		}
	}

	public void modificarNotificadoresTerminal(long idTerminal, List<NotificacionBusqueda> notificadoresModificados) {
		Terminal terminalPersistida = entityManager().find(Terminal.class, idTerminal);
		if (terminalPersistida != null) {
			eliminarTerminal(terminalPersistida);
			terminalPersistida.setNotificadoresBusqueda(notificadoresModificados);
			agregarTerminal(terminalPersistida);
		}
	}

	public void modificarComunaTerminal(long idTerminal, String comunaModificada) {
		Terminal terminalPersistida = entityManager().find(Terminal.class, idTerminal);
		if (terminalPersistida != null) {
			eliminarTerminal(terminalPersistida);
			terminalPersistida.setComunaTerminal(comunaModificada);
			agregarTerminal(terminalPersistida);
		}
	}
}
