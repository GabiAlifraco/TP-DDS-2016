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
import Pois.Banco;
import Pois.CGP;
import Pois.Comercio;
import Pois.ParadaColectivo;
import Pois.Poi;
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
		
			CGP cgpComuna3=new CGP();
			cgpComuna3.setNombre("Cgp SantaFe");
			Punto coordenadas3 = new Punto(87.9045, 77.4765);
			Domicilio domicilio3 = new Domicilio("Sevoria", 334, "Berno", "Falcao", 370, 0, 0, 0, 1993);
			Region region3 = new Region("Castelli", "Rosario", "Santa Fe", "Argentina");
			Ubicacion ubicacion3 = new Ubicacion();
			ubicacion3.setCoordenadas(coordenadas3);
			ubicacion3.setDomicilio(domicilio3);
			ubicacion3.setRegion(region3);
			cgpComuna3.setUbicacion(ubicacion3);
			cgpComuna3.setPalabrasClave(palabrasClaveCgp);
			Disponibilidad disponibilidadRRHH = new Disponibilidad();
			disponibilidadRRHH.setHorarioFinal(LocalTime.of(14, 0));
			disponibilidadRRHH.setHorarioInicial( LocalTime.of(8, 0));
			List<Disponibilidad> horariosRRHH = Arrays.asList(disponibilidadRRHH);
			ServicioCGP rrhh = new ServicioCGP();
			rrhh.setNombre("Recursos Humanos");
			rrhh.setHorariosDeAtencion(horariosRRHH);
			List<ServicioCGP> serviciosCGP3 = new ArrayList<ServicioCGP>();
			serviciosCGP3= Arrays.asList(rrhh);
			cgpComuna3.setServiciosCGP(serviciosCGP3);
			Punto coordenadaCGP7 = new Punto(34.4124, 24.4856);
			Punto coordenadaCGP8 = new Punto(34.4124, 24.4852);
			Punto coordenadaCGP9 = new Punto(34.4120, 24.4851);
			ArrayList<Punto> zona3 = new ArrayList<Punto>();
			zona2.add(coordenadaCGP7);
			zona2.add(coordenadaCGP8);
			zona2.add(coordenadaCGP9);
			Zona zonaCGP3 = new Zona(zona3);
			cgpComuna3.setZona(zonaCGP3);
			
			//Creo paradas
			//Palabras y disponibilidad comun a todas las paradas
			List<String> palabrasClaveParada = Arrays.asList("Colectivo", "Parada");
			/*List<DayOfWeek> diasParada = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
					DayOfWeek.FRIDAY);*/
			//horarioParada.setDias(diasParada);
		
			
			ParadaColectivo parada114=new ParadaColectivo();
			parada114.setNombre("Parada 114");
			Punto coordenadaParada = new Punto(37.3598, 98.2843);
			Domicilio domicilioParada = new Domicilio("Av.Segurola", 1141, "Av.Gaona", "Av. J.B.Justo", 6100, 0, 0, 0, 1111);
			Region regionParada = new Region("CABA", "Floresta", "Bs As", "Argentina");
			Ubicacion ubicacionParada = new Ubicacion();
			ubicacionParada.setCoordenadas(coordenadaParada);
			ubicacionParada.setDomicilio(domicilioParada);
			ubicacionParada.setRegion(regionParada);
			parada114.setUbicacion(ubicacionParada);
			Disponibilidad horarioParada =new Disponibilidad();
			horarioParada.setHorarioInicial(LocalTime.of(00,00));
			horarioParada.setHorarioFinal(LocalTime.of(23,59));
			List<Disponibilidad> horariosParada=Arrays.asList(horarioParada);
			parada114.setPalabrasClave(palabrasClaveParada);
			parada114.setHorariosDeAtencion(horariosParada);
			
			ParadaColectivo parada146=new ParadaColectivo();
			parada146.setNombre("Parada 146");
			Domicilio domicilioParada2 = new Domicilio("Av San Martin", 1454, "Galicia", "Tres Arroyos", 944, 0, 0, 0, 1416);
			Region regionParada2 = new Region("CABA", "Villa Crespo", "Bs As", "Argentina");
			Punto coordenadaParada2 = new Punto(47.3206, 38.5587);
			Ubicacion ubicacionParada2 = new Ubicacion();
			ubicacionParada2.setCoordenadas(coordenadaParada2);
			ubicacionParada2.setDomicilio(domicilioParada2);
			ubicacionParada2.setRegion(regionParada2);
			parada146.setUbicacion(ubicacionParada2);
			Disponibilidad horarioParada2 =new Disponibilidad();
			horarioParada2.setHorarioInicial(LocalTime.of(00,00));
			horarioParada2.setHorarioFinal(LocalTime.of(23,59));
			List<Disponibilidad> horariosParada2=Arrays.asList(horarioParada2);
			parada146.setPalabrasClave(palabrasClaveParada);
			parada146.setHorariosDeAtencion(horariosParada2);
			
			ParadaColectivo parada37=new ParadaColectivo();
			parada37.setNombre("Parada 37");
			Domicilio domicilioParada3 = new Domicilio("Hipolito Yrigoyen", 947, "SantiagoEstero", "Robles", 900, 0, 0, 0, 1234);
			Region regionParada3 = new Region("AreaMetropolitana", "Avellaneda", "Bs As", "Argentina");
			Punto coordenadaParada3 = new Punto(114.5675, 15.4276);
			Ubicacion ubicacionParada3 = new Ubicacion();
			ubicacionParada3.setCoordenadas(coordenadaParada3);
			ubicacionParada3.setDomicilio(domicilioParada3);
			ubicacionParada3.setRegion(regionParada3);
			parada37.setUbicacion(ubicacionParada3);
			Disponibilidad horarioParada3 =new Disponibilidad();
			horarioParada3.setHorarioInicial(LocalTime.of(00,00));
			horarioParada3.setHorarioFinal(LocalTime.of(23,59));
			List<Disponibilidad> horariosParada3=Arrays.asList(horarioParada3);
			parada37.setPalabrasClave(palabrasClaveParada);
			parada37.setHorariosDeAtencion(horariosParada3);

			//Creo Bancos
			
			List<String> palabrasClaveBanco = Arrays.asList("banco","tarjeta");

			
			Banco banco1= new Banco();
			banco1.setNombre("Banco HSBC");
			Punto coordenadaBanco1 = new Punto(22.9577, 34.0056);
			Domicilio domicilioBanco1 = new Domicilio("Av.Gaona", 2168, "Franklin", "Av. Boyaca", 1350, 0, 0, 0, 1861);
			Region regionBanco1 = new Region("CABA", "Flores", "Bs As", "Argentina");
			Ubicacion ubicacionBanco1 = new Ubicacion();
			ubicacionBanco1.setCoordenadas(coordenadaBanco1);
			ubicacionBanco1.setDomicilio(domicilioBanco1);
			ubicacionBanco1.setRegion(regionBanco1);
			banco1.setUbicacion(ubicacionBanco1);
			Disponibilidad disponibilidadBanco = new Disponibilidad();
			disponibilidadBanco.setHorarioFinal(LocalTime.of(15, 0));
			disponibilidadBanco.setHorarioInicial( LocalTime.of(10, 0));
			List<Disponibilidad> horariosBanco = Arrays.asList(disponibilidadBanco);
			banco1.setPalabrasClave(palabrasClaveBanco);
			banco1.setHorariosDeAtencion(horariosBanco);
			
			Banco banco2= new Banco();
			banco2.setNombre("Banco Frances");
			Punto coordenadaBanco2 = new Punto(32.1507, 43.0563);
			Domicilio domicilioBanco2 = new Domicilio("Sunchales", 1668, "Herrera", "Av.Las Tres", 445, 0, 0, 0, 1201);
			Region regionBanco2 = new Region("Grutas", "Puerto Madryn", "Rio Negro", "Argentina");
			Ubicacion ubicacionBanco2 = new Ubicacion();
			ubicacionBanco2.setCoordenadas(coordenadaBanco2);
			ubicacionBanco2.setDomicilio(domicilioBanco2);
			ubicacionBanco2.setRegion(regionBanco2);
			banco2.setUbicacion(ubicacionBanco2);
			Disponibilidad disponibilidadBanco2 = new Disponibilidad();
			disponibilidadBanco2.setHorarioFinal(LocalTime.of(15, 0));
			disponibilidadBanco2.setHorarioInicial( LocalTime.of(10, 0));
			List<Disponibilidad> horariosBanco2 = Arrays.asList(disponibilidadBanco2);
			banco2.setPalabrasClave(palabrasClaveBanco);
			banco2.setHorariosDeAtencion(horariosBanco2);

			
			Banco banco3= new Banco();
			banco3.setNombre("Banco Comafi");
			Punto coordenadaBanco3 = new Punto(132.6577, 37.8845);
			Domicilio domicilioBanco3 = new Domicilio("Belgrano", 4168, "Lloyt", "Bolivar", 496, 0, 0, 0, 1771);
			Region regionBanco3 = new Region("Las Ñecas", "San Rafael", "Mendoza", "Argentina");
			Ubicacion ubicacionBanco3 = new Ubicacion();
			ubicacionBanco3.setCoordenadas(coordenadaBanco3);
			ubicacionBanco3.setDomicilio(domicilioBanco3);
			ubicacionBanco3.setRegion(regionBanco3);
			banco3.setUbicacion(ubicacionBanco3);
			Disponibilidad disponibilidadBanco3 = new Disponibilidad();
			disponibilidadBanco3.setHorarioFinal(LocalTime.of(15, 0));
			disponibilidadBanco3.setHorarioInicial( LocalTime.of(10, 0));
			List<Disponibilidad> horariosBanco3 = Arrays.asList(disponibilidadBanco3);
			banco3.setPalabrasClave(palabrasClaveBanco);
			banco3.setHorariosDeAtencion(horariosBanco3);
			
			//Creo comercios
			
			List<String> palabrasClaveComercio = Arrays.asList("ropa","cliente","juguetes","comida");
			
			Comercio comercio1= new Comercio();
			comercio1.setNombre("Mimo & Co");
			Punto coordenadaComercio1 = new Punto(234.6747, 55.8845);
			Domicilio domicilioComercio1 = new Domicilio("Valnia", 3324, "Birios", "Papuka", 1866, 0, 0, 0, 1313);
			Region regionComercio1 = new Region("Lagunita", "Santo Tome", "Corrientes", "Argentina");
			Ubicacion ubicacionComercio1 = new Ubicacion();
			ubicacionComercio1.setCoordenadas(coordenadaComercio1);
			ubicacionComercio1.setDomicilio(domicilioComercio1);
			ubicacionComercio1.setRegion(regionComercio1);
			comercio1.setUbicacion(ubicacionComercio1);
			Disponibilidad disponibilidadComercio1 = new Disponibilidad();
			disponibilidadComercio1.setHorarioFinal(LocalTime.of(18, 0));
			disponibilidadComercio1.setHorarioInicial( LocalTime.of(8, 0));
			List<Disponibilidad> horariosComercio1 = Arrays.asList(disponibilidadComercio1);
			comercio1.setPalabrasClave(palabrasClaveComercio);
			comercio1.setHorariosDeAtencion(horariosComercio1);
		  

			Comercio comercio2= new Comercio();
			comercio2.setNombre("Vans S.R.L");
			Punto coordenadaComercio2 = new Punto(6.9856, 137.0324);
			Domicilio domicilioComercio2 = new Domicilio("Chacras", 68, "Lloyters", "Variase", 2496, 0, 0, 0, 1631);
			Region regionComercio2 = new Region("Las Viboras", "Tilcara", "Jujuy", "Argentina");
			Ubicacion ubicacionComercio2 = new Ubicacion();
			ubicacionComercio2.setCoordenadas(coordenadaComercio2);
			ubicacionComercio2.setDomicilio(domicilioComercio2);
			ubicacionComercio2.setRegion(regionComercio2);
			comercio2.setUbicacion(ubicacionComercio2);
			Disponibilidad disponibilidadComercio2 = new Disponibilidad();
			disponibilidadComercio1.setHorarioFinal(LocalTime.of(16, 0));
			disponibilidadComercio1.setHorarioInicial( LocalTime.of(10, 0));
			List<Disponibilidad> horariosComercio2 = Arrays.asList(disponibilidadComercio2);
			comercio2.setPalabrasClave(palabrasClaveComercio);
			comercio2.setHorariosDeAtencion(horariosComercio2);
	
			
			Comercio comercio3= new Comercio();
			comercio3.setNombre("Solo Deportes S.A");
			Punto coordenadaComercio3 = new Punto(63.9788,83.2264);
			Domicilio domicilioComercio3 = new Domicilio("Av Rivadavia", 6577, "Puan", "Curapaligue", 1654, 0, 0, 0, 1465);
			Region regionComercio3 = new Region("CABA", "Caballito", "Buenos Aires", "Argentina");
			Ubicacion ubicacionComercio3 = new Ubicacion();
			ubicacionComercio3.setCoordenadas(coordenadaComercio3);
			ubicacionComercio3.setDomicilio(domicilioComercio3);
			ubicacionComercio3.setRegion(regionComercio3);
			comercio3.setUbicacion(ubicacionComercio3);
			Disponibilidad disponibilidadComercio3 = new Disponibilidad();
			disponibilidadComercio3.setHorarioFinal(LocalTime.of(21, 0));
			disponibilidadComercio3.setHorarioInicial( LocalTime.of(10, 0));
			List<Disponibilidad> horariosComercio3 = Arrays.asList(disponibilidadComercio3);
			comercio3.setPalabrasClave(palabrasClaveComercio);
			comercio3.setHorariosDeAtencion(horariosComercio3);
	
			Usuario admin=new Usuario("admin", "admin", "ADMINISTRADOR");
			
			Terminal abasto = new Terminal("Terminal Abasto", servicios,new Usuario("terminalAbasto", "Abasto123", "TERMINAL"));
			Punto coordenadasa = new Punto(34.6030, -58.4107);
			abasto.setCoordenadaDispositivoMovil(coordenadasa);
			abasto.setComunaTerminal("3");
			Terminal villacrespo = new Terminal("Terminal Villa Crespo", servicios,new Usuario("terminalVC","elmejorbarrio123","terminal"));
			Punto coordenadasVC = new Punto(-34.597208, 58.441588);
			villacrespo.setCoordenadaDispositivoMovil(coordenadasVC);
			villacrespo.setComunaTerminal("15");
			beginTransaction();
		    withTransaction(() -> {
		    /*persist(cgpComuna1);
		    persist(parada37);
		    persist(cgpComuna3);
		    persist(banco1);
		    persist(comercio1);
		    persist(banco2);
		    persist(cgpComuna2);
		    persist(banco3);
		    persist(parada114);
		    persist(comercio2);
		    persist(parada146);
		    persist(comercio3);*/
		    persist(abasto);
			persist(villacrespo);
			persist(admin);
		    });
		    commitTransaction();
	  }

			
	
	  }
