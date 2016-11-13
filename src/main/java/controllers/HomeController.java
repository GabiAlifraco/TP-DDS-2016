package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController extends Controller {

	public ModelAndView mostrar(Request request, Response response) {
		return new ModelAndView(null, "home.hbs");
	}

	public ModelAndView mostrarHomeAdmin(Request request, Response response) {
		return this.redirigirSegunPermisos(request, response, "administrador", new ModelAndView(null, "home-administrador.hbs"));
	}
	
	public ModelAndView mostrarHomeTerminal(Request request, Response response) {
		return this.redirigirSegunPermisos(request, response, "terminal", new ModelAndView(null, "home-terminal.hbs"));
	}

}
