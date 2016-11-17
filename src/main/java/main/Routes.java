package main;

import static spark.Spark.*;
import controllers.AccesosController;
import controllers.AdministracionPoisController;
import controllers.HomeController;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {
	public static void main(String[] args) {
		
		new Bootstrap().run();
		System.out.println("Iniciando servidor");
		staticFileLocation("/public");
		HomeController home = new HomeController();
		AccesosController accesos = new AccesosController();
		AdministracionPoisController administracion= new AdministracionPoisController();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		//PERFIL ADMINISTRADOR  USER:admin  PASS: admin
		
		port(8080);

		get("/", home::mostrar, engine); 
		get("/login", accesos::mostrarLogin, engine); //Se muestra cuando presiono en el boton de la pagina principal
		post("/login", accesos::autenticar); //Sucede cuando presionamos iniciar sesion luego de ingresar los datos
		get("/loginfailed", accesos::mostrarLoginFail, engine); //En caso que haya un error en los datos
		get("/administrador", home::mostrarHomeAdmin, engine);//Si entro como perfil administrador
		post("/administrador", accesos::cerrarSesion); //Si presiono cerrar sesion durante el uso de la aplicacion
		post("/administrador/*", accesos::cerrarSesion);
		get("/terminal",home::mostrarHomeTerminal,engine);//Si entro como perfil terminal
		get("/administrador/pois",administracion::mostrarPois,engine); //Se muestra cuando apreto boton admPois en perfil Administrador
		//post("/administrador/borradoPoi",administracion::eliminar);
		get("/administrador/terminales",administracion::mostrarTerminales,engine);
		get("/administrador/historial",administracion::mostrarHistorial,engine);
		get("/access-denied", accesos::denegarAcceso, engine); //Al querer ingresar en alguna pagina de la app sin estar logueado
		get("/administrador/historial/filtro",administracion::mostrarHistorialFiltros,engine);
		
	}
}