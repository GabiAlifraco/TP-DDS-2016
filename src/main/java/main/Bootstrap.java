package main;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.mongodb.MongoClient;

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
import Resultado.Resultado;
import Terminal.AdministradorTerminales;
import Terminal.Terminal;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	 /*public static void main(String[] args) {

	    new Bootstrap().run();
	  }*/

	  public void run() {
		  
		  	//Vamos a cargar unos resultados de busquedas anteriores desde las distintas terminales
		  	final Morphia morphia=new Morphia();
		  	morphia.mapPackage("Resultado");
		  	morphia.mapPackage("Pois");
		  	final Datastore datastore = morphia.createDatastore(new MongoClient(), "tpAnual");
		  	datastore.ensureIndexes();
		  
		  
		  
		  	//Comun a los CGP
			Mapa mapa = Mapa.getInstance();
			ProveedorBancos bancos = new ProveedorBancos();
			ProveedorCGPs cgps = new ProveedorCGPs();
			List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
			servicios.add(mapa);
			servicios.add(bancos);
			servicios.add(cgps);
			List<String> palabrasClaveCgp = Arrays.asList("cgp","comuna");
			
			
			//Creo CGPs
			CGP cgpComuna1=new CGP();
			cgpComuna1.setNombre("Cgp_Alvear");
			Punto coordenadas = new Punto(34.4124, 24.4856);
			Domicilio domicilio = new Domicilio("Arenales", 1245, "M.T.De.Alvear", "Santa_Fe", 2100, 0, 0, 0, 1111);
			Region region = new Region("CABA", "Recoleta", "Buenos_Aires", "Argentina");
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
			cgpComuna2.setNombre("Cgp_Piñeyro");
			Punto coordenadas2 = new Punto(65.6785, 12.4461);
			Domicilio domicilio2 = new Domicilio("Saavedra", 45, "Alcala", "Santa_Rosa", 500, 0, 0, 0, 1234);
			Region region2 = new Region("ZonaSur", "Lanus", "Buenos_Aires", "Argentina");
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
			cgpComuna3.setNombre("Cgp_SantaFe");
			Punto coordenadas3 = new Punto(87.9045, 77.4765);
			Domicilio domicilio3 = new Domicilio("Sevoria", 334, "Berno", "Falcao", 370, 0, 0, 0, 1993);
			Region region3 = new Region("Castelli", "Rosario", "Santa_Fe", "Argentina");
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
			rrhh.setNombre("Recursos_Humanos");
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
		
			ParadaColectivo parada114=new ParadaColectivo();
			parada114.setNombre("Parada_114");
			Punto coordenadaParada = new Punto(37.3598, 98.2843);
			Domicilio domicilioParada = new Domicilio("Av.Segurola", 1141, "Av.Gaona", "Av.J.B.Justo", 6100, 0, 0, 0, 1111);
			Region regionParada = new Region("CABA", "Floresta", "Buenos_Aires", "Argentina");
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
			parada146.setNombre("Parada_146");
			Domicilio domicilioParada2 = new Domicilio("Av.San_Martin", 1454, "Galicia", "Tres_Arroyos", 944, 0, 0, 0, 1416);
			Region regionParada2 = new Region("CABA", "Villa_Crespo", "Buenos_Aires", "Argentina");
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
			parada37.setNombre("Parada_37");
			Domicilio domicilioParada3 = new Domicilio("Hipolito_Yrigoyen", 947, "Santiago_Estero", "Robles", 900, 0, 0, 0, 1234);
			Region regionParada3 = new Region("Area_Metropolitana", "Avellaneda", "Buenos_Aires", "Argentina");
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

			ParadaColectivo parada19=new ParadaColectivo();
			parada19.setNombre("Parada_19");
			Domicilio domicilioParada4 = new Domicilio("Avenida_Corrientes",6700, " Avenida_Federico_Lacroze", "Maure", 4200, 0, 0, 0, 1420);
			Region regionParada4 = new Region("Once", "Plaza_Miserere", "Buenos_Aires", "Argentina");
			Punto coordenadaParada4 = new Punto(119.5124, 18.7652);
			Ubicacion ubicacionParada4 = new Ubicacion();
			ubicacionParada4.setCoordenadas(coordenadaParada4);
			ubicacionParada4.setDomicilio(domicilioParada4);
			ubicacionParada4.setRegion(regionParada4);
			parada19.setUbicacion(ubicacionParada4);
			Disponibilidad horarioParada4 =new Disponibilidad();
			horarioParada4.setHorarioInicial(LocalTime.of(00,00));
			horarioParada4.setHorarioFinal(LocalTime.of(23,59));
			List<Disponibilidad> horariosParada4=Arrays.asList(horarioParada4);
			parada19.setPalabrasClave(palabrasClaveParada);
			parada19.setHorariosDeAtencion(horariosParada4);
			
			ParadaColectivo parada70=new ParadaColectivo();
			parada70.setNombre("Parada_70");
			Domicilio domicilioParada5 = new Domicilio("Iriarte",3400, " Vieytes ", "Luna", 1500, 0, 0, 0, 1218);
			Region regionParada5 = new Region("Nueva_Pompeya", "Omnibus_Retiro", "Buenos_Aires", "Argentina");
			Punto coordenadaParada5 = new Punto(113.4567, 15.3754);
			Ubicacion ubicacionParada5 = new Ubicacion();
			ubicacionParada5.setCoordenadas(coordenadaParada5);
			ubicacionParada5.setDomicilio(domicilioParada5);
			ubicacionParada5.setRegion(regionParada5);
			parada70.setUbicacion(ubicacionParada5);
			Disponibilidad horarioParada5 =new Disponibilidad();
			horarioParada5.setHorarioInicial(LocalTime.of(00,00));
			horarioParada5.setHorarioFinal(LocalTime.of(23,59));
			List<Disponibilidad> horariosParada5=Arrays.asList(horarioParada5);
			parada70.setPalabrasClave(palabrasClaveParada);
			parada70.setHorariosDeAtencion(horariosParada5);
			
			ParadaColectivo parada47=new ParadaColectivo();
			parada47.setNombre("Parada_47");
			Domicilio domicilioParada6 = new Domicilio("Avenida_Juan_Bautista_Justo",9700, " General_Paz ", "Nogoya", 6200, 0, 0, 0, 1325);
			Region regionParada6 = new Region("Liniers", "Federico_Lacroze", "Buenos_Aires", "Argentina");
			Punto coordenadaParada6 = new Punto(152.3456, 21.7891);
			Ubicacion ubicacionParada6 = new Ubicacion();
			ubicacionParada6.setCoordenadas(coordenadaParada6);
			ubicacionParada6.setDomicilio(domicilioParada6);
			ubicacionParada6.setRegion(regionParada6);
			parada47.setUbicacion(ubicacionParada6);
			Disponibilidad horarioParada6 =new Disponibilidad();
			horarioParada6.setHorarioInicial(LocalTime.of(00,00));
			horarioParada6.setHorarioFinal(LocalTime.of(23,59));
			List<Disponibilidad> horariosParada6=Arrays.asList(horarioParada6);
			parada47.setPalabrasClave(palabrasClaveParada);
			parada47.setHorariosDeAtencion(horariosParada6);
			
			//Creo Bancos
			
			List<String> palabrasClaveBanco = Arrays.asList("banco","tarjeta");

			Banco banco1= new Banco();
			banco1.setNombre("Banco_HSBC");
			Punto coordenadaBanco1 = new Punto(22.9577, 34.0056);
			Domicilio domicilioBanco1 = new Domicilio("Av.Gaona", 2168, "Franklin", "Av.Boyaca", 1350, 0, 0, 0, 1861);
			Region regionBanco1 = new Region("CABA", "Flores", "Buenos_Aires", "Argentina");
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
			banco2.setNombre("Banco_Frances");
			Punto coordenadaBanco2 = new Punto(32.1507, 43.0563);
			Domicilio domicilioBanco2 = new Domicilio("Sunchales", 1668, "Herrera", "Av.Las_Tres", 445, 0, 0, 0, 1201);
			Region regionBanco2 = new Region("Grutas", "Puerto_Madryn", "Rio_Negro", "Argentina");
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
			banco3.setNombre("Banco_Comafi");
			Punto coordenadaBanco3 = new Punto(132.6577, 37.8845);
			Domicilio domicilioBanco3 = new Domicilio("Belgrano", 4168, "Lloyt", "Bolivar", 496, 0, 0, 0, 1771);
			Region regionBanco3 = new Region("Las_Ñecas", "San_Rafael", "Mendoza", "Argentina");
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
			comercio1.setNombre("Mimo_&_Co");
			Punto coordenadaComercio1 = new Punto(234.6747, 55.8845);
			Domicilio domicilioComercio1 = new Domicilio("Valnia", 3324, "Birios", "Papuka", 1866, 0, 0, 0, 1313);
			Region regionComercio1 = new Region("Lagunita", "Santo_Tome", "Corrientes", "Argentina");
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
			comercio2.setNombre("Vans_S.R.L");
			Punto coordenadaComercio2 = new Punto(6.9856, 137.0324);
			Domicilio domicilioComercio2 = new Domicilio("Chacras", 68, "Lloyters", "Variase", 2496, 0, 0, 0, 1631);
			Region regionComercio2 = new Region("Las_Viboras", "Tilcara", "Jujuy", "Argentina");
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
			comercio3.setNombre("Solo_Deportes_S.A");
			Punto coordenadaComercio3 = new Punto(63.9788,83.2264);
			Domicilio domicilioComercio3 = new Domicilio("Av.Rivadavia", 6577, "Puan", "Curapaligue", 1654, 0, 0, 0, 1465);
			Region regionComercio3 = new Region("CABA", "Caballito", "Buenos_Aires", "Argentina");
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
			
			Terminal abasto = new Terminal("Terminal_Abasto", servicios,new Usuario("terminalAbasto", "Abasto123", "TERMINAL"));
			Punto coordenadasa = new Punto(34.6030, -58.4107);
			abasto.setCoordenadaDispositivoMovil(coordenadasa);
			abasto.setComunaTerminal("3");
			Terminal villacrespo = new Terminal("Terminal_VillaCrespo", servicios,new Usuario("terminalVC","elmejorbarrio123","TERMINAL"));
			Punto coordenadasVC = new Punto(-34.5972, 58.4415);
			villacrespo.setCoordenadaDispositivoMovil(coordenadasVC);
			villacrespo.setComunaTerminal("15");
			
			Terminal retiro = new Terminal("Terminal_Retiro", servicios,new Usuario("terminalRetiro","retiro","TERMINAL"));
			Punto coordenadasRetiro = new Punto(-34.5872, -58.3744);
			retiro.setCoordenadaDispositivoMovil(coordenadasRetiro);
			retiro.setComunaTerminal("1");
			
			Terminal flores = new Terminal("Terminal_Flores", servicios,new Usuario("terminalFlores","flores","TERMINAL"));
			Punto coordenadasFlores = new Punto(-34.6377, -58.4593);
			flores.setCoordenadaDispositivoMovil(coordenadasFlores);
			flores.setComunaTerminal("7");
			
			Terminal palermo = new Terminal("Terminal_Palermo", servicios,new Usuario("terminalPalermo","palermo","TERMINAL"));
			Punto coordenadasPalermo = new Punto(-34.5710, -58.4232);
			palermo.setCoordenadaDispositivoMovil(coordenadasPalermo);
			palermo.setComunaTerminal("14");
		
			List<Poi> poisEncontrados=new ArrayList<Poi>();
			poisEncontrados.add(banco1);
			poisEncontrados.add(banco2);
			poisEncontrados.add(banco3);
			List<Poi> poisEncontrados2=new ArrayList<Poi>();
			poisEncontrados2.add(parada37);
			poisEncontrados2.add(parada114);
			poisEncontrados2.add(parada146);
			List<Poi> poisEncontrados3=new ArrayList<Poi>();
			poisEncontrados3.add(comercio1);
			poisEncontrados3.add(comercio2);
			poisEncontrados3.add(comercio3);
			List<Poi> poisEncontrados4=new ArrayList<Poi>();
			poisEncontrados4.add(cgpComuna1);
			poisEncontrados4.add(cgpComuna2);
			poisEncontrados4.add(cgpComuna3);
			Resultado resultado1=new Resultado(new LocalDate("2005-07-16"),"Banco",abasto,poisEncontrados);
			Resultado resultado2=new Resultado(new LocalDate("2006-03-10"),"CGP",villacrespo,poisEncontrados2);
			Resultado resultado3=new Resultado(new LocalDate("2005-07-16"),"Banco",abasto,poisEncontrados3);
			Resultado resultado4=new Resultado(new LocalDate("2005-07-16"),"Banco",villacrespo,poisEncontrados4);
			datastore.save(resultado1);
			datastore.save(resultado2);
			datastore.save(resultado3);
			datastore.save(resultado4);
			
			beginTransaction();
		    withTransaction(() -> {
		    persist(cgpComuna1);
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
		    persist(parada19);
		    persist(parada47);
		    persist(parada70);
		    persist(comercio3);
		    persist(abasto);
			persist(villacrespo);
			persist(retiro);
			persist(flores);
			persist(palermo);
			persist(admin);
		    });
		    commitTransaction();
	  }

			
	
	  }
