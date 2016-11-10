package main;

import static spark.Spark.*;
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
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		port(8079);
		get("/", home::mostrar, engine);
		get("/login", login::mostrar, engine);
		get("/administracionPois",admPois::mostrar,engine);
	}
}
