package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;





import Accesos.Usuario;
import CaracteristicaPoi.Punto;
import OrigenesDeDatos.Mapa;
import OrigenesDeDatos.OrigenDeDatos;
import OrigenesDeDatos.ProveedorBancos;
import OrigenesDeDatos.ProveedorCGPs;
import Pois.Poi;
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
}