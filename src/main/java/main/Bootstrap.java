package main;

import java.time.DayOfWeek;
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
import Pois.ParadaColectivo;
import Terminal.AdministradorTerminales;
import Terminal.Terminal;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	 /*public static void main(String[] args) {

	    new Bootstrap().run();
	  }*/

	  public void run() {
		  
		  	//Comun a los CGP
			Mapa mapa = Mapa.getInstance();
			ProveedorBancos bancos = new ProveedorBancos();
			ProveedorCGPs cgps = new ProveedorCGPs();
			List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
			servicios.add(mapa);
			servicios.add(bancos);
			servicios.add(cgps);
			List<String> palabrasClaveCgp = Arrays.asList("cgp","comuna");
			
			
			CGP cgpComuna1=new CGP();
			cgpComuna1.setNombre("Cgp Alvear");
			Punto coordenadas = new Punto(34.4124, 24.4856);
			Domicilio domicilio = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa Fe", 2100, 0, 0, 0, 1111);
			Region region = new Region("CABA", "Recoleta", "Bs As", "Argentina");
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.setCoordenadas(coordenadas);
			ubicacion.setDomicilio(domicilio);
			ubicacion.setRegion(region);
			cgpComuna1.setUbicacion(ubicacion);
			cgpComuna1.setPalabrasClave(palabrasClaveCgp);
			Disponibilidad disponibilidadTesoreria = new Disponibilidad();
			disponibilidadTesoreria.setHorarioFinal(LocalTime.of(15, 0));
			disponibilidadTesoreria.setHorarioInicial( LocalTime.of(9, 0));
			List<Disponibilidad> horariosTesoreria = Arrays.asList(disponibilidadTesoreria);
			ServicioCGP tesoreria = new ServicioCGP();
			tesoreria.setNombre("Tesoreria");
			tesoreria.setHorariosDeAtencion(horariosTesoreria);
			List<ServicioCGP> serviciosCGP = new ArrayList<ServicioCGP>();
			serviciosCGP= Arrays.asList(tesoreria);
			cgpComuna1.setServiciosCGP(serviciosCGP);
			Punto coordenadaCGP1 = new Punto(34.4124, 24.4856);
			Punto coordenadaCGP2 = new Punto(34.4124, 24.4852);
			Punto coordenadaCGP3 = new Punto(34.4120, 24.4851);
			ArrayList<Punto> zona = new ArrayList<Punto>();
			zona.add(coordenadaCGP1);
			zona.add(coordenadaCGP2);
			zona.add(coordenadaCGP3);
			Zona zonaCGP = new Zona(zona);
			cgpComuna1.setZona(zonaCGP);
			
			CGP cgpComuna2=new CGP();
			cgpComuna2.setNombre("Cgp Piñeyro");
			Punto coordenadas2 = new Punto(65.6785, 12.4461);
			Domicilio domicilio2 = new Domicilio("Saavedra", 45, "Alcala", "SantaRosa", 500, 0, 0, 0, 1234);
			Region region2 = new Region("ZonaSur", "Lanus", "Bs As", "Argentina");
			Ubicacion ubicacion2 = new Ubicacion();
			ubicacion2.setCoordenadas(coordenadas2);
			ubicacion2.setDomicilio(domicilio2);
			ubicacion2.setRegion(region2);
			cgpComuna2.setUbicacion(ubicacion2);
			cgpComuna2.setPalabrasClave(palabrasClaveCgp);
			Disponibilidad disponibilidadAdministracion = new Disponibilidad();
			disponibilidadAdministracion.setHorarioFinal(LocalTime.of(14, 0));
			disponibilidadAdministracion.setHorarioInicial( LocalTime.of(8, 0));
			List<Disponibilidad> horariosAdministracion = Arrays.asList(disponibilidadAdministracion);
			ServicioCGP administracion = new ServicioCGP();
			administracion.setNombre("Administracion");
			administracion.setHorariosDeAtencion(horariosAdministracion);
			List<ServicioCGP> serviciosCGP2 = new ArrayList<ServicioCGP>();
			serviciosCGP2= Arrays.asList(administracion);
			cgpComuna2.setServiciosCGP(serviciosCGP2);
			Punto coordenadaCGP4 = new Punto(34.4124, 24.4856);
			Punto coordenadaCGP5 = new Punto(34.4124, 24.4852);
			Punto coordenadaCGP6 = new Punto(34.4120, 24.4851);
			ArrayList<Punto> zona2 = new ArrayList<Punto>();
			zona2.add(coordenadaCGP4);
			zona2.add(coordenadaCGP5);
			zona2.add(coordenadaCGP6);
			Zona zonaCGP2 = new Zona(zona2);
			cgpComuna2.setZona(zonaCGP2);
			
			//Creo paradas
			//Palabras y disponibilidad comun a todas las paradas
			List<String> palabrasClaveParada = Arrays.asList("Colectivo", "Parada");
			List<DayOfWeek> diasParada = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
					DayOfWeek.FRIDAY);
			Disponibilidad horarioParada =new Disponibilidad();
			horarioParada.setDias(diasParada);
			horarioParada.setHorarioInicial(LocalTime.of(00,00));
			horarioParada.setHorarioFinal(LocalTime.of(23,59));
			List<Disponibilidad> horariosParada=Arrays.asList(horarioParada);
			
			ParadaColectivo parada114=new ParadaColectivo();
			parada114.setNombre("114");
			Punto coordenadaParada = new Punto(37.3598, 98.2843);
			Domicilio domicilioParada = new Domicilio("Av.Segurola", 1141, "Av.Gaona", "Av. J.B.Justo", 6100, 0, 0, 0, 1111);
			Region regionParada = new Region("CABA", "Floresta", "Bs As", "Argentina");
			Ubicacion ubicacionParada = new Ubicacion();
			ubicacionParada.setCoordenadas(coordenadaParada);
			ubicacionParada.setDomicilio(domicilioParada);
			ubicacionParada.setRegion(regionParada);
			parada114.setUbicacion(ubicacionParada);
			parada114.setPalabrasClave(palabrasClaveParada);
			parada114.setHorariosDeAtencion(horariosParada);
			
			ParadaColectivo parada146=new ParadaColectivo();
			Domicilio domicilioParada2 = new Domicilio("Av Santa Fe", 3354, "J.Salguero", "Videl", 1244, 0, 0, 0, 1435);
			Region regionParada2 = new Region("CABA", "Palermo", "Bs As", "Argentina");
			Punto coordenadaParada2 = new Punto(47.3206, 38.5587);
			Ubicacion ubicacionParada2 = new Ubicacion();
			ubicacionParada.setCoordenadas(coordenadaParada2);
			ubicacionParada.setDomicilio(domicilioParada2);
			ubicacionParada.setRegion(regionParada2);
			parada114.setUbicacion(ubicacionParada2);
			parada114.setPalabrasClave(palabrasClaveParada);
			parada114.setNombre("146");
			parada114.setHorariosDeAtencion(horariosParada);
			
			ParadaColectivo parada37=new ParadaColectivo();
			Domicilio domicilioParada3 = new Domicilio("Hipolito Yrigoyen", 947, "SantiagoEstero", "Robles", 900, 0, 0, 0, 1234);
			Region regionParada3 = new Region("AreaMetropolitana", "Avellaneda", "Bs As", "Argentina");
			Punto coordenadaParada3 = new Punto(114.5675, 15.4276);
			Ubicacion ubicacionParada3 = new Ubicacion();
			ubicacionParada.setCoordenadas(coordenadaParada3);
			ubicacionParada.setDomicilio(domicilioParada3);
			ubicacionParada.setRegion(regionParada3);
			parada114.setUbicacion(ubicacionParada3);
			parada114.setPalabrasClave(palabrasClaveParada);
			parada114.setNombre("37");
			parada114.setHorariosDeAtencion(horariosParada);

	
			

		  
	
			
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
		    persist(cgpComuna1);
		    persist(cgpComuna2);
		    //persist(parada114);
			persist(abasto);
			persist(villacrespo);
			persist(admin);
			/*persist(parada37);
		    persist(parada146);*/
		    });
		    commitTransaction();
	  }

			
	
	  }
