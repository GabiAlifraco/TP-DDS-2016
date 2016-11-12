package main;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Accesos.Usuario;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	  public static void main(String[] args) {
	    new Bootstrap().run();
	  }

	  public void run() {
		beginTransaction();
	    withTransaction(() -> {
	    	persist(new Usuario("terminalAbasto", "Abasto123", "TERMINAL"));
	    	persist(new Usuario("administrador", "contraseña", "ADMINISTRADOR"));
	    });
	    commitTransaction();
	  }

	}