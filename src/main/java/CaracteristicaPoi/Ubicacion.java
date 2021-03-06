package CaracteristicaPoi;
import javax.persistence.CascadeType;

import org.bson.types.ObjectId;

@javax.persistence.Entity
@org.mongodb.morphia.annotations.Entity
@javax.persistence.Table(name="Ubicaciones")
public class Ubicacion {
	
	@javax.persistence.Transient
	@org.mongodb.morphia.annotations.Id
	ObjectId id;
	
	@javax.persistence.Id
	@javax.persistence.GeneratedValue
	@org.mongodb.morphia.annotations.Transient
	private long ubicacionID;
	
	@javax.persistence.Embedded
	@org.mongodb.morphia.annotations.Embedded
	private Punto coordenadas;
	
	@javax.persistence.OneToOne(cascade = CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "idDomicilio")
	private Domicilio domicilio;
	
	@javax.persistence.ManyToOne(cascade = CascadeType.ALL)
	@javax.persistence.JoinColumn(name = "idRegion")
	private Region region;
	
	public Ubicacion(Domicilio domicilio, Region region, Punto coordenadas){
		setDomicilio(domicilio);
		setRegion(region);
		setCoordenadas(coordenadas);
	}
	public Ubicacion(){
		
	}
	public Punto getCoordenadas() {
		return coordenadas;
	}
    public void setCoordenadas(Punto coordenadas) {
		this.coordenadas = coordenadas;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}
    public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Region getRegion() {
		return region;
	}
    public void setRegion(Region region) {
		this.region = region;
	}
    public long getUbicacionID() {
    	return ubicacionID;
    }
    public void setUbicacionID(long ubicacionID) {
    	this.ubicacionID = ubicacionID;
    }
}
