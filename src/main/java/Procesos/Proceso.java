package Procesos;

public abstract class Proceso {

	public Cronograma cronograma;
	public boolean finalizoOK;
	
	public abstract boolean ejecutar();
}
