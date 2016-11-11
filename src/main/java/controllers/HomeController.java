package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

	public ModelAndView mostrar(Request request, Response response) {
		return new ModelAndView(null, "home.hbs");
	}

	public ModelAndView mostrarHomeAdmin(Request request, Response response) {
		return new ModelAndView(null, "home-administrador.hbs");
	}

}
