package DTOs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Disponibilidad")
public class BancoDTO {
	@Id
	@GeneratedValue
	@Column(name="banco")
	private String banco;
	@Column(name="Coordenada X")
	private String x;
	@Column(name="Coordenada Y")
	private String y;
	@Transient
	private List<String> servicios = new ArrayList<String>();
	
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	
	public List<String> getServicios() {
		return servicios;
	}
	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

}
