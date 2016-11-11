package main;

import static spark.Spark.*;

import controllers.HomeController;
import controllers.LoginController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		staticFileLocation("/public");
		HomeController home = new HomeController();
		LoginController login = new LoginController();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		port(8087);

		get("/", home::mostrar, engine);
		get("/login", login::mostrar, engine);
		post("/login", login::autenticar);
		get("/loginfailed", login::mostrarFail, engine);
		post("/loginfailed", login::autenticar);
		get("/home-administrador", home::mostrarHomeAdmin, engine);
	}
}