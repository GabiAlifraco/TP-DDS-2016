package CaracteristicaPoi;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.uqbar.geodds.Point;
import converter.PointConverter;


@Entity
public class Ubicacion {
	@Id
	@GeneratedValue
	private long ubicacionID;
	@Convert(converter=PointConverter.class)
	private Point coordenadas;
	@OneToOne
	@JoinColumn(name="ubicacionID")
	private Domicilio domicilio;
	@OneToOne
	@JoinColumn(name="ubicacionID")
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
}
