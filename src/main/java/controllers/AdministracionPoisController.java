package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import OrigenesDeDatos.Mapa;
import Pois.Poi;
import Terminal.AdministradorTerminales;
import Terminal.Terminal;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionPoisController extends Controller{

	public ModelAndView mostrarPois(Request request, Response response) {
		
		List<Poi> filtrados;
		String filtroNombre=request.queryParams("filtroNombre");
		filtrados=Mapa.getInstance().getPois(); //Devuelve todos los pois persistidos.	
		if (Objects.isNull(filtroNombre) || filtroNombre.isEmpty()) {
		      filtrados = Mapa.getInstance().getPois();
		    } else {
		    		filtrados = Mapa.getInstance().buscarPorNombre(filtroNombre);
		    }
		Map<String, Object> pois=new HashMap<>();	
		pois.put("filtrados", filtrados);
		pois.put("filtroNombre", filtroNombre);
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(pois, "admPois.hbs"));
	}
	
	public ModelAndView mostrarPoisPorNombre(Request request, Response response) {
		
		String filtroPoi=request.queryParams("filtroPoi");
		
		Map<String, List<Poi>> pois=new HashMap<>();	
		List<Poi> filtrados= new ArrayList<>();

	    if (Objects.isNull(filtroPoi) || filtroPoi.isEmpty()) {
	      filtrados = Mapa.getInstance().getPois();
	    } else {
	    		filtrados = Mapa.getInstance().buscarPorNombre(filtroPoi);
	    }

	    pois.put("filtrados", filtrados);
		
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(filtrados, "admPois.hbs"));
	}

	public ModelAndView mostrarPoisPorTipo(Request request, Response response) {
	
	String filtroPoi=request.queryParams("filtroPoi");
	
	List<Poi> pois;

    if (Objects.isNull(filtroPoi) || filtroPoi.isEmpty()) {
      pois = Mapa.getInstance().getPois();
    } else {
    		pois = Mapa.getInstance().buscarPorTipo(filtroPoi);
    }

    HashMap<String, Object> viewModel = new HashMap<>();
    viewModel.put("pois", pois);
    viewModel.put("filtroPoi", filtroPoi);
	
	return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(viewModel, "admPois.hbs"));
	}
	
	
	public Void eliminar(Request request, Response response) {

		Mapa miMapa = Mapa.getInstance();

		Poi miPoi = miMapa.buscarPoi(request.queryParams("nombrePoi"));

		miMapa.eliminarUnPoi(miPoi);

		response.redirect("/administrador/pois");

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