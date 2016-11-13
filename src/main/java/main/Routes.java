package main;

import static spark.Spark.*;

import controllers.AccesosController;
import controllers.AdministracionPoisController;
import controllers.HomeController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		staticFileLocation("/public");
		HomeController home = new HomeController();
		AccesosController accesos = new AccesosController();
		AdministracionPoisController admPois= new AdministracionPoisController();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		port(8080);

		get("/", home::mostrar, engine);
		get("/login", accesos::mostrarLogin, engine);
		post("/login", accesos::autenticar);
		get("/loginfailed", accesos::mostrarLoginFail, engine);
		post("/loginfailed", accesos::autenticar);
		get("/administrador", home::mostrarHomeAdmin, engine);
		post("/administrador", accesos::cerrarSesion);
		post("/administrador/*", accesos::cerrarSesion);
		get("/terminal",home::mostrarHomeTerminal,engine);
		get("/administrador/administracionPois",admPois::mostrar,engine);
		post("/administrador/borradoPoi",admPois::eliminar);
		get("/access-denied", accesos::denegarAcceso, engine);
	}
}