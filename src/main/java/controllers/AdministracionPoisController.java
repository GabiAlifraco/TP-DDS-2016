package controllers;

import OrigenesDeDatos.Mapa;
import Pois.Poi;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionPoisController extends Controller{

	public ModelAndView administrarPois(Request request, Response response) {
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(null, "admPois.hbs"));
	}
	
	public ModelAndView administrarTerminales(Request request, Response response){
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(null, "admTerminal.hbs"));
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