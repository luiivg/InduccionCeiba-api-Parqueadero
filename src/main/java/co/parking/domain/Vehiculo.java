package co.parking.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import co.parking.domain.enumeration.TipoVehiculo;

@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -711425543772122452L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoVehiculo tipo;
	
	@NotNull
	private String placa;

	private int cilindraje;

	private boolean activo;
	
	@NotNull
	@Column(name = "fecha_ingreso")
	private LocalDateTime fechaIngreso;

	public Vehiculo() {

	}
		
	

	public Vehiculo(Long id, @NotNull TipoVehiculo tipo, @NotNull String placa, int cilindraje, boolean activo,
			@NotNull LocalDateTime fechaIngreso) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.activo = activo;
		this.fechaIngreso = fechaIngreso;
	}
	
	public Vehiculo(@NotNull TipoVehiculo tipo, @NotNull String placa, int cilindraje, boolean activo,
			@NotNull LocalDateTime fechaIngreso) {
		super();
		this.tipo = tipo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.activo = activo;
		this.fechaIngreso = fechaIngreso;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 * the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the tipo
	 */
	public TipoVehiculo getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 * the tipo to set
	 */
	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa
	 * the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return the cilindraje
	 */
	public int getCilindraje() {
		return cilindraje;
	}

	/**
	 * @param cilindraje
	 * the cilindraje to set
	 */
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo
	 * the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}



	/**
	 * @return the fechaIngreso
	 */
	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}



	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	


}
