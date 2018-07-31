package co.parking.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "factura")
public class Factura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5510748860024252812L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_ingreso")
	private LocalDateTime fechaIngreso;

	@Column(name = "fecha_salida")
	private LocalDateTime fechaSalida;

	@Column(name = "total_pagar")
	private double totalAPagar;

	@Column(name = "vehiculo_id")
	private Long idVehiculo;

	public Factura() {

	}

	/**
	 * @param placa
	 * @param totalAPagar
	 * @param fechaIngreso
	 * @param fechaSalida
	 */
	public Factura(String placa, double totalAPagar, LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
		super();
		this.totalAPagar = totalAPagar;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the totalAPagar
	 */
	public double getTotalAPagar() {
		return totalAPagar;
	}

	/**
	 * @param totalAPagar
	 *            the totalAPagar to set
	 */
	public void setTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	/**
	 * @return the idVehiculo
	 */
	public Long getIdVehiculo() {
		return idVehiculo;
	}

	/**
	 * @param idVehiculo the idVehiculo to set
	 */
	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	
}
