package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;





import OrigenesDeDatos.Mapa;
import Pois.Poi;
import Terminal.AdministradorTerminales;
import Terminal.Terminal;
import main.listaPois;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController extends Controller {
	
	public ModelAndView mostrarBancos(Request request, Response response) {
		List<Poi> filtrados=Mapa.getInstance().buscarPorTipo("Banco");
		Map<String, List<Poi>> pois=new HashMap<>();	
		pois.put("filtrados", filtrados);
		return this.redirigirSegunPermisos(request, response, "terminal", new ModelAndView(pois, "poisTerminal.hbs"));
	}
	
	public ModelAndView mostrarCgps(Request request, Response response) {
		List<Poi> filtrados=Mapa.getInstance().buscarPorTipo("CGP");
		Map<String, List<Poi>> pois=new HashMap<>();	
		pois.put("filtrados", filtrados);
		return this.redirigirSegunPermisos(request, response, "terminal", new ModelAndView(pois, "poisTerminal.hbs"));
	}
	
	public ModelAndView mostrarLocales(Request request, Response response) {
		List<Poi> filtrados=Mapa.getInstance().buscarPorTipo("Comercio");
		Map<String, List<Poi>> pois=new HashMap<>();	
		pois.put("filtrados", filtrados);
		return this.redirigirSegunPermisos(request, response, "terminal", new ModelAndView(pois, "poisTerminal.hbs"));
		
	}
	
	public ModelAndView mostrarParadas(Request request, Response response) {
		List<Poi> filtrados=Mapa.getInstance().buscarPorTipo("ParadaColectivo");
		Map<String, List<Poi>> pois=new HashMap<>();	
		pois.put("filtrados", filtrados);
		return this.redirigirSegunPermisos(request, response, "terminal", new ModelAndView(pois, "poisTerminal.hbs"));
	}
	
	public ModelAndView mostrarDetalle(Request request, Response response) {
		Long id = Long.parseLong(request.params(":id"));
		List<Poi> filtrados=Mapa.getInstance().buscarPois(id);
		Poi poi=filtrados.get(0);
		Map<String, Poi> pois=new HashMap<>();	
		pois.put("poi", poi);
		return this.redirigirSegunPermisos(request, response, "terminal", new ModelAndView(pois, "detallePoi.hbs"));
		
	}
}
