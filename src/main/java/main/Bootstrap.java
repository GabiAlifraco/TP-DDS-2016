package main;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Accesos.AdministradorUsuarios;
import Accesos.Usuario;
import CaracteristicaPoi.Disponibilidad;
import CaracteristicaPoi.Domicilio;
import CaracteristicaPoi.Punto;
import CaracteristicaPoi.Region;
import CaracteristicaPoi.ServicioCGP;
import CaracteristicaPoi.Ubicacion;
import CaracteristicaPoi.Zona;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import OrigenesDeDatos.ProveedorBancos;
import OrigenesDeDatos.ProveedorCGPs;
import Pois.CGP;
import Terminal.AdministradorTerminales;
import Terminal.Terminal;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	 public static void main(String[] args) {

	    new Bootstrap().run();
	  }

	  public void run() {
			Mapa mapa = Mapa.getInstance();
			ProveedorBancos bancos = new ProveedorBancos();
			ProveedorCGPs cgps = new ProveedorCGPs();
			List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
			servicios.add(mapa);
			servicios.add(bancos);
			servicios.add(cgps);
			
			CGP cgpComuna3=new CGP();
			cgpComuna3.setNombre("cgp comuna 3");
			List<String> palabras = Arrays.asList("cgp","comuna 3","CABA");
			Punto coordenadas = new Punto(34.4124, 24.4856);
			Domicilio domicilio = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
			Region region = new Region("CABA", "Recoleta", "Bs As", "Argentina");
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.setCoordenadas(coordenadas);
			ubicacion.setDomicilio(domicilio);
			ubicacion.setRegion(region);
			cgpComuna3.setUbicacion(ubicacion);
			cgpComuna3.setPalabrasClave(palabras);
			Disponibilidad disponibilidadTesoreria = new Disponibilidad();
			disponibilidadTesoreria.setHorarioFinal(LocalTime.of(15, 0));
			disponibilidadTesoreria.setHorarioInicial( LocalTime.of(9, 0));
			List<Disponibilidad> horariosTesoreria = Arrays.asList(disponibilidadTesoreria);
			ServicioCGP tesoreria = new ServicioCGP();
			tesoreria.setNombre("Tesoreria");
			tesoreria.setHorariosDeAtencion(horariosTesoreria);
			List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();
			serviciosCGP= Arrays.asList(tesoreria);
			cgpComuna3.setServiciosCGP(serviciosCGP);
			Punto coordenadaCGP = new Punto(34.4124, 24.4856);
			Punto coordenadaCGP2 = new Punto(34.4124, 24.4852);
			Punto coordenadaCGP3 = new Punto(34.4120, 24.4851);
			ArrayList<Punto> zona = new ArrayList<Punto>();
			zona.add(coordenadaCGP);
			zona.add(coordenadaCGP2);
			zona.add(coordenadaCGP3);
			Zona zonaCGP = new Zona(zona);
			cgpComuna3.setZona(zonaCGP);
			

		  
	
			
			Terminal abasto = new Terminal("Terminal Abasto", servicios,new Usuario("terminalAbasto", "Abasto123", "TERMINAL"));
			Punto coordenadasa = new Punto(-34.6030, -58.4107);
			abasto.setCoordenadaDispositivoMovil(coordenadasa);
			abasto.setComunaTerminal("3");
	    	Usuario admin=new Usuario("admin", "admin", "ADMINISTRADOR");
			Terminal villacrespo = new Terminal("Terminal Villa Crespo", servicios,new Usuario("terminalVC","elmejorbarrio123","terminal"));
			Punto coordenadasVC = new Punto(-34.597208, -58.441588);
			villacrespo.setCoordenadaDispositivoMovil(coordenadasVC);
			villacrespo.setComunaTerminal("15");
			beginTransaction();
		    withTransaction(() -> {
		    persist(cgpComuna3);
			persist(abasto);
			persist(villacrespo);
			persist(admin);
			
		    });
		    commitTransaction();
	  }

			
	
	  }
