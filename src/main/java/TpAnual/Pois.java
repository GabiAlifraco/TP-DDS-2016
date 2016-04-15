package TpAnual;

public class Pois {
	//Declaro los atributos
	private double latitud,longitud;
	private String nombre,calle,rubro;
	private int altura;
	
	//Armo el contructor
public Pois(double latitud,double longitud,String nombre,String calle,int altura,String rubro ){
	this.latitud=latitud;
	this.longitud=longitud;
	this.nombre=nombre;
	this.calle=calle;
	this.altura=altura;
	this.rubro=rubro;
}

public String getNombre() {
	return nombre;
}


}