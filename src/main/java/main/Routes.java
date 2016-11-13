package main;

import static spark.Spark.*;

import controllers.AccesosController;
import controllers.AdministracionPoisController;
import controllers.HomeController;
import controllers.LoginController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		staticFileLocation("/public");
		HomeController home = new HomeController();
		LoginController login = new LoginController();
		AdministracionPoisController admPois= new AdministracionPoisController();
		AccesosController accesos = new AccesosController();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		port(8080);

		get("/", home::mostrar, engine);
		get("/login", login::mostrar, engine);
		post("/login", login::autenticar);
		get("/loginfailed", login::mostrarFail, engine);
		post("/loginfailed", login::autenticar);
		get("/administrador", home::mostrarHomeAdmin, engine);
		get("/terminal",home::mostrarHomeTerminal,engine);
		get("/administrador/administracionPois",admPois::mostrar,engine);
		post("/administrador/borradoPoi",admPois::eliminar);
		get("/access-denied", accesos::mostrar, engine);
	}
}