package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.mongodb.MongoClient;

import converter.LocalDateConverter;
import Accesos.Usuario;
import CaracteristicaPoi.Punto;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import OrigenesDeDatos.ProveedorBancos;
import OrigenesDeDatos.ProveedorCGPs;
import Pois.Poi;
import Resultado.Resultado;
import Terminal.AdministradorTerminales;
import Terminal.Terminal;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionPoisController extends Controller implements WithGlobalEntityManager, TransactionalOps{

	public ModelAndView mostrarPois(Request request, Response response) {
		
		List<Poi> filtrados;
		String filtroTipo=request.queryParams("filtroTipo");
		String filtroNombre=request.queryParams("filtroNombre");	
		if ((Objects.isNull(filtroNombre)&& Objects.isNull(filtroTipo)) || (filtroNombre.isEmpty() && filtroTipo.equals("Poi"))) {
		      filtrados = Mapa.getInstance().getPois();
		    } else {
		    	if(filtroNombre.isEmpty() && filtroTipo!=null){
		    		filtrados = Mapa.getInstance().buscarPorTipo(filtroTipo);
		    	}else{
		    		filtrados = Mapa.getInstance().buscarPorNombre(filtroNombre);
	
		    	}
		    }
		Map<String, List<Poi>> pois=new HashMap<>();	
		pois.put("filtrados", filtrados);
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(pois, "admPois.hbs"));
	}
	
	
	public ModelAndView eliminar(Request request, Response response) {

		Long id = Long.parseLong(request.params(":id"));
		List<Poi> filtrados=Mapa.getInstance().buscarPois(id);
		Poi puntoInteres=filtrados.get(0);
		withTransaction(() ->{
    		Mapa.getInstance().eliminarUnPoi(puntoInteres);
    	});
		response.redirect("/administrador");
    	return null;
	}
	
	
	public ModelAndView modificarPoi(Request request, Response response) {
		Long id = Long.parseLong(request.params(":id"));
		List<Poi> filtrados=Mapa.getInstance().buscarPois(id);
		Poi poi=filtrados.get(0);
		Map<String, Poi> pois=new HashMap<>();	
		pois.put("poi", poi);
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(pois, "modificarPoi.hbs"));
		
	}
	
	public ModelAndView actualizarPoi(Request request, Response response) {
		
		Long id = Long.parseLong(request.params(":id"));
		List<Poi> filtrados=Mapa.getInstance().buscarPois(id);
		
		Poi puntoInteres=filtrados.get(0);
		puntoInteres.setNombre(request.queryParams("nombrePoi"));
		puntoInteres.getDomicilio().setCallePrincipal(request.queryParams("domicilioPoi"));
		puntoInteres.getDomicilio().setAlturaPrincipal(Integer.parseInt(request.queryParams("alturaPoi")));
		puntoInteres.getCoordenada().setLatitud(Double.parseDouble(request.queryParams("latitudPoi")));
		puntoInteres.getCoordenada().setLongitud(Double.parseDouble(request.queryParams("longitudPoi")));
		withTransaction(() ->{
    		Mapa.getInstance().agregarUnPoi(puntoInteres);
    	});
		response.redirect("/administrador");
    	return null;
		
	}

	public ModelAndView mostrarTerminales(Request request, Response response){
		
		Map<String, List<Terminal>> terminales=new HashMap<>();	
		List<Terminal> filtradas= new ArrayList<>();
		String filtroNombre=request.queryParams("filtroNombre");
		if (Objects.isNull(filtroNombre) || filtroNombre.isEmpty()){
			filtradas=AdministradorTerminales.getInstance().listar(); //Devuelve todas las terminales persistidas.	
		}else{
			filtradas=AdministradorTerminales.getInstance().buscarPorComuna(filtroNombre);
		}
			terminales.put("filtradas", filtradas);
		
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(terminales, "admTerminal.hbs"));
	}
	
	public ModelAndView eliminarTerminal(Request request, Response response) {

		Long id = Long.parseLong(request.params(":id"));
		List<Terminal> filtradas=AdministradorTerminales.getInstance().buscarTerminales(id);
		Terminal terminal=filtradas.get(0);
		withTransaction(() ->{
    		AdministradorTerminales.getInstance().eliminarTerminal(terminal);
    	});
		response.redirect("/administrador");
    	return null;
	}
	
	public ModelAndView nueva(Request request, Response response){
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(null, "terminal.hbs"));
	}
	
	public ModelAndView agregarTerminal(Request request, Response response){
		
		Mapa mapa = Mapa.getInstance();
		ProveedorBancos bancos = new ProveedorBancos();
		ProveedorCGPs cgps = new ProveedorCGPs();
		List<OrigenDeDatos> servicios = new ArrayList<OrigenDeDatos>();
		servicios.add(mapa);
		servicios.add(bancos);
		servicios.add(cgps);
		String nombreTerminal=request.queryParams("nombre");
		String comuna=request.queryParams("comuna");
		String pass=request.queryParams("pass");
		double x=Math.random()*100;
		double y=Math.random()*100;
		double latitud=redondearDecimales(x, 4);
		double longitud=redondearDecimales(y, 4);
		Punto coordenadasVC = new Punto(latitud,longitud);
		Terminal terminal= new Terminal(nombreTerminal,servicios,new Usuario(nombreTerminal, pass, "TERMINAL"));
		terminal.setComunaTerminal(comuna);
		terminal.setCoordenadaDispositivoMovil(coordenadasVC);
		withTransaction(() ->{
    		AdministradorTerminales.getInstance().agregarTerminal(terminal);
    	});
		
		
		response.redirect("/administrador");
		return null;
	}
	
	public ModelAndView modificarTerminal(Request request, Response response) {
		Long id = Long.parseLong(request.params(":id"));
		Terminal terminal=AdministradorTerminales.getInstance().buscarTerminal(id);
		Map<String, Terminal> terminales=new HashMap<>();	
		terminales.put("terminal",terminal);
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(terminales, "modificarTerminal.hbs"));
		
	}
	
public ModelAndView actualizarTerminal(Request request, Response response) {
		
		Long id = Long.parseLong(request.params(":id"));
		Terminal terminal=AdministradorTerminales.getInstance().buscarTerminal(id);
		terminal.setNombreTerminal(request.queryParams("nombreTerminal"));
		terminal.setComunaTerminal(request.queryParams("comuna"));
		terminal.getUsuario().setUser(request.queryParams("nombreTerminal"));
		if(!Objects.isNull(request.queryParams("pass"))){
		terminal.getUsuario().setPassword(request.queryParams("pass"));
		}
		terminal.getCoordenadaDispositivoMovil().setLatitud(Double.parseDouble(request.queryParams("latitud")));
		terminal.getCoordenadaDispositivoMovil().setLongitud(Double.parseDouble(request.queryParams("longitud")));
		withTransaction(() ->{
			AdministradorTerminales.getInstance().modificarTerminal(terminal);
			response.redirect("/administrador");
		});
    	return null;
		
	}
	
	public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
	
	
	public ModelAndView mostrarHistorial(Request request, Response response){
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(null, "admHistorial.hbs"));
	}
	
public ModelAndView mostrarHistorialFiltroFecha(Request request, Response response){
	
		String desde=request.queryParams("desdeFecha");
		String hasta=request.queryParams("hastaFecha");
		
		final Morphia morphia=new Morphia();
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		morphia.mapPackage("Resultado");
		morphia.mapPackage("Pois");
  		final Datastore datastore = morphia.createDatastore(new MongoClient(), "tpAnual");
  		datastore.ensureIndexes();
  		List<Resultado> misResultados=datastore.createQuery(Resultado.class).asList();
		if(!desde.isEmpty()){
			
			Date desdeFecha=asDate(desde);
			misResultados=misResultados.stream().filter(res->res.getFecha().after(desdeFecha)).collect(Collectors.toList());
		}
		if(!hasta.isEmpty()){
			
			Date hastaFecha=asDate(hasta);
			misResultados=misResultados.stream().filter(res->res.getFecha().before(hastaFecha)).collect(Collectors.toList());
		}
		Map<String, List<Resultado>> historias = new HashMap<>();
		historias.put("misResultados", misResultados);
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(historias, "admHistorial.hbs"));
	}
	
	public ModelAndView mostrarHistorialFiltroResultados(Request request, Response response){
		
		String cantidad=request.queryParams("cantidadResultados");
		int valor=Integer.parseInt(cantidad);
		final Morphia morphia=new Morphia();
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		morphia.mapPackage("Resultado");
		morphia.mapPackage("Pois");
  		final Datastore datastore = morphia.createDatastore(new MongoClient(), "tpAnual");
  		datastore.ensureIndexes();
  		List<Resultado> misResultados=datastore.createQuery(Resultado.class).asList();
  		if(!cantidad.isEmpty()){
  		
  			misResultados=misResultados.stream().filter(res->res.getCantidadDeResultados()==valor).collect(Collectors.toList());
  			
  		}
  		Map<String, List<Resultado>> historias = new HashMap<>();
		historias.put("misResultados", misResultados);
			
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(historias, "admHistorial.hbs"));
	}
	
	public ModelAndView mostrarHistorialFiltroTerminal(Request request, Response response){
		
		String nombreTerminal=request.queryParams("terminal");
		final Morphia morphia=new Morphia();
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		morphia.mapPackage("Resultado");
		morphia.mapPackage("Pois");
  		final Datastore datastore = morphia.createDatastore(new MongoClient(), "tpAnual");
  		datastore.ensureIndexes();
  		List<Resultado> misResultados=datastore.createQuery(Resultado.class).asList();
  		List<Resultado> porTerminal=new ArrayList<Resultado>();
  		int valor=0;
  		List<Poi> poisTotales=new ArrayList<Poi>();
  		if(!nombreTerminal.isEmpty()){
  			
  			misResultados=misResultados.stream().filter(res->res.getTerminal().getNombreTerminal().equalsIgnoreCase(nombreTerminal)).collect(Collectors.toList());
  	
  			for(Resultado result:misResultados){
  				valor=valor+result.getCantidadDeResultados();
  				for(Poi poi:result.getPoisEncontrados()){
  					poisTotales.add(poi);
  				}
  			
  			}
  			Resultado terminalBuscada = new Resultado(LocalDate.now(),"TERMINAL",misResultados.get(0).getTerminal(),poisTotales);
  			porTerminal.add(terminalBuscada);
  		}
  		
  		Map<String, List<Resultado>> historias = new HashMap<>();
		historias.put("misResultados", porTerminal);
		
		
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(historias, "admHistorial.hbs"));
	}
	
	public static Date asDate(String localDate) {
	    return java.sql.Date.valueOf(localDate);
	  }
}