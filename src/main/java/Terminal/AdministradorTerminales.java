package Terminal;

import java.util.List;


import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import CaracteristicaPoi.Punto;
import Notificaciones.NotificacionBusqueda;
import OrigenesDeDatos.*;

public class AdministradorTerminales implements WithGlobalEntityManager {
	
	public static AdministradorTerminales instancia= new AdministradorTerminales();

	public void agregarTerminal(Terminal terminal) {
		if (!entityManager().contains(terminal)) {
			entityManager().persist(terminal);
		}
	}

	public void eliminarTerminal(Terminal terminal) {
		if (entityManager().contains(terminal)) {
			entityManager().remove(terminal);
		}
	}

	public Terminal buscarTerminal(long idTerminal) {
		Terminal terminal = entityManager().find(Terminal.class, idTerminal);
		if (terminal != null) {
			Mapa mapa = Mapa.getInstance();
			ProveedorBancos proveedorBancos = new ProveedorBancos();
			ProveedorCGPs proveedorCGPs = new ProveedorCGPs();
			terminal.agregarNuevoServicio(mapa);
			terminal.agregarNuevoServicio(proveedorBancos);
			terminal.agregarNuevoServicio(proveedorCGPs);
		}
		return terminal;
	}

	public void modificarCoordenadaTerminal(long idTerminal, Punto coordenadaModificada) {
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
	
	public List<Terminal> listar() {
	    return entityManager()//
	        .createQuery("from Terminal", Terminal.class) //
	        .getResultList();
	  }
	
	public List<Terminal> buscarPorComuna(String nombre) {
	    return entityManager() //
	        .createQuery("from Terminal c where c.nombre like :nombre", Terminal.class) //
	        .setParameter("nombre", "%" + nombre + "%") //
	        .getResultList();
	  }
}
