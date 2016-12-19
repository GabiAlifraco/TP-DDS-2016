package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;


import OrigenesDeDatos.Mapa;
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
	

	public ModelAndView mostrarTerminales(Request request, Response response){
		
		Map<String, List<Terminal>> terminales=new HashMap<>();	
		List<Terminal> filtradas= new ArrayList<>();
		filtradas=AdministradorTerminales.getInstance().listar(); //Devuelve todas las terminales persistidas.	
	    terminales.put("filtradas", filtradas);
		
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(terminales, "admTerminal.hbs"));
	}
	
	public ModelAndView mostrarHistorial(Request request, Response response){
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(null, "admHistorial.hbs"));
	}
}