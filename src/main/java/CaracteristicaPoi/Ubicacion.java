package CaracteristicaPoi;
import org.uqbar.geodds.Point;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import converter.PointConverter;

import javax.persistence.*;

@Entity
@Table(name="Ubicacion")
public class Ubicacion implements WithGlobalEntityManager{
	@Id
	@GeneratedValue
	private long ubicacionID;
	@Convert(converter = PointConverter.class)
	private Point coordenadas;
	@OneToOne
	private Domicilio domicilio;
	@ManyToOne
	private Region region;
	
	public Ubicacion(Domicilio domicilio, Region region, Point coordenadas){
		setDomicilio(domicilio);
		setRegion(region);
		setCoordenadas(coordenadas);
	}
	public Ubicacion(){
		
	}
	//Getters y Setters
	public Point getCoordenadas() {
		return coordenadas;
	}
    public void setCoordenadas(Point coordenadas) {
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
