package main;

import static spark.Spark.*;

import controllers.HomeController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		staticFileLocation("/public");
		HomeController home = new HomeController();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		port(8082);

		get("/", home::mostrar, engine);
		
	}
}
