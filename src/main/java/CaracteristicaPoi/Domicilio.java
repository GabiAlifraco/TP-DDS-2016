package CaracteristicaPoi;

public class Domicilio {
	
	private String callePrincipal;
	private int alturaPrincipal;
	private String entreUnaCalle;
	private String hastaOtraCalle;
	private int alturaCalles;
	private int piso;
	private int departamento;
	private int unidad;
	private int codigoPostal;
	
	public Domicilio(String unaCallePrincipal,int unaAlturaPrincipal,String unaCalle,String otraCalle,
			        int unaAlturaCalles,int unPiso,int unDepartamento,int unaUnidad,int unCodigoPostal){
		this.callePrincipal = unaCallePrincipal;
		this.alturaPrincipal = unaAlturaPrincipal;
		this.entreUnaCalle = unaCalle;
		this.hastaOtraCalle = otraCalle;
		this.alturaCalles = unaAlturaCalles;
		this.piso = unPiso;
		this.departamento = unDepartamento;
		this.unidad = unaUnidad;
		this.codigoPostal = unCodigoPostal;
	}
    //Getters
	public String getCallePrincipal() {
		return callePrincipal;
	}

	public int getAlturaPrincipal() {
		return alturaPrincipal;
	}

	public String getEntreUnaCalle() {
		return entreUnaCalle;
	}

	public String getHastaOtraCalle() {
		return hastaOtraCalle;
	}

	public int getAlturaCalles() {
		return alturaCalles;
	}

	public int getPiso() {
		return piso;
	}

	public int getDepartamento() {
		return departamento;
	}

	public int getUnidad() {
		return unidad;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

}