package Accesos;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class AdministradorUsuarios implements WithGlobalEntityManager {

	public void crearUsuario(Usuario usuario) {
		entityManager().persist(usuario);
	}

	public Usuario buscarUsuario(String nombreUsuario) {
		
		List<Usuario> encontrados = entityManager()
				.createQuery("from Usuario as u where u.username = :nombre", Usuario.class)
				.setParameter("nombre", nombreUsuario).getResultList();
		if(encontrados.isEmpty()){
			return null;
		} else {
			return encontrados.get(0);
		}
	}

}

