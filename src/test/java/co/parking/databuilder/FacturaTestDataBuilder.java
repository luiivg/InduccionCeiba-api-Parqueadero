package co.parking.databuilder;

import java.time.LocalDateTime;

import co.parking.entity.Factura;


public class FacturaTestDataBuilder {
	
	private static final Long VEHICULO = 1L;
	private static final double TOTAL_A_PAGAR = 10000;
	
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private double totalAPagar;
	private Long idVehiculo;
	
	public FacturaTestDataBuilder(){
		this.fechaIngreso = LocalDateTime.now().minusHours(3);
		this.fechaSalida = LocalDateTime.now();
		this.totalAPagar = TOTAL_A_PAGAR;
		this.idVehiculo = VEHICULO;
	}

	
	public FacturaTestDataBuilder setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public FacturaTestDataBuilder setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public FacturaTestDataBuilder setTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
		return this;
	}

	public FacturaTestDataBuilder setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
		return this;
	}


	public Factura build() {
		return new Factura(this.fechaIngreso, this.fechaSalida, this.totalAPagar, this.idVehiculo);
	}
	
	 

}
