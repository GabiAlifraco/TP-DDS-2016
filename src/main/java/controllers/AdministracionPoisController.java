package controllers;

import java.util.HashMap;
import java.util.List;
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
		
		String filtro1=request.queryParams("filtroNombre");
		String filtro2=request.queryParams("filtroTipo");
		String filtroPoi;
		
		List<Poi> pois;

		if(filtro1.isEmpty()){
			filtroPoi=filtro2;
		}else{
			filtroPoi=filtro1;
		}
	    
	    if (Objects.isNull(filtroPoi) || filtroPoi.isEmpty()) {
	      pois = Mapa.getInstance().getPois();
	    } else {
	    	if(filtroPoi.equals(filtro1)){
	    		pois = Mapa.getInstance().buscarPorNombre(filtroPoi);
	    	}else{
	    		pois = Mapa.getInstance().buscarPorTipo(filtroPoi);
	    	}
	    }

	    HashMap<String, Object> viewModel = new HashMap<>();
	    viewModel.put("pois", pois);
	    viewModel.put("filtroPoi", filtroPoi);
		
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(viewModel, "admPois.hbs"));
	}
	
	public ModelAndView mostrarTerminales(Request request, Response response){
		
		List<Terminal> terminales;

	    String filtroComuna = request.queryParams("filtroComuna");
	    if (Objects.isNull(filtroComuna) || filtroComuna.isEmpty()) {
	      terminales = AdministradorTerminales.instancia.listar();
	    } else {
	      terminales = AdministradorTerminales.instancia.buscarPorComuna(filtroComuna);
	    }

	    HashMap<String, Object> viewModel = new HashMap<>();
	    viewModel.put("terminales", terminales);
	    viewModel.put("filtroComuna", filtroComuna);
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(viewModel, "admTerminal.hbs"));
	}
	
	
	public ModelAndView mostrarHistorial(Request request, Response response){
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(null, "admHistorial.hbs"));
	}

	public Void eliminar(Request request, Response response) {

		Mapa miMapa = Mapa.getInstance();

		Poi miPoi = miMapa.buscarPoi(request.queryParams("nombrePoi"));

		miMapa.eliminarUnPoi(miPoi);

		response.redirect("/administracionPois");

		return null;
	}
}