package controllers;

import OrigenesDeDatos.Mapa;
import Pois.Poi;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministracionPoisController {

	public ModelAndView mostrar(Request request, Response response) {
		if (request.cookie("role").toLowerCase().equals("administrador")) {
			return new ModelAndView(null, "admPois.hbs");
		} else {
			response.redirect("/access-denied");
			return null;
			
		}

	}

	public Void eliminar(Request request, Response response) {

		Mapa miMapa = Mapa.getInstance();

		Poi miPoi = miMapa.buscarPois(request.queryParams("nombrePoi"), "").get(0);

		miMapa.eliminarUnPoi(miPoi);

		response.redirect("/administracionPois");

		return null;
	}

}