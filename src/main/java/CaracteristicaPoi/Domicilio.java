package CaracteristicaPoi;

import javax.persistence.*;
@Entity
@Table(name="Domicilio")
public class Domicilio {
	@Id
	@GeneratedValue
	private Long domicilioID;
	
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
	public Domicilio(){
		
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

	public long getDomicilioID() {
		return domicilioID;
	}
	public void setDomicilioID(long domicilioID) {
		this.domicilioID = domicilioID;
	}
	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}
	public void setAlturaPrincipal(int alturaPrincipal) {
		this.alturaPrincipal = alturaPrincipal;
	}
	public void setEntreUnaCalle(String entreUnaCalle) {
		this.entreUnaCalle = entreUnaCalle;
	}
	public void setHastaOtraCalle(String hastaOtraCalle) {
		this.hastaOtraCalle = hastaOtraCalle;
	}
	public void setAlturaCalles(int alturaCalles) {
		this.alturaCalles = alturaCalles;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}
	public void setUnidad(int unidad) {
		this.unidad = unidad;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
}
