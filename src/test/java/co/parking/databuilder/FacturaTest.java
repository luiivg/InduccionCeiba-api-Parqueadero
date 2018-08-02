package co.parking.databuilder;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import co.parking.domain.Factura;

public class FacturaTest {

	private static final Long VEHICULO = 1L;
	private static final double TOTAL_A_PAGAR = 10000;
	
	@Test
	public void crearFacturaTest(){
		/**
		 * Arrange
		 */
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(3);
		LocalDateTime fechaSalida = LocalDateTime.now();
		
		FacturaTestDataBuilder facturaTestDataBuilder = new FacturaTestDataBuilder().setFechaIngreso(fechaIngreso).
				setFechaSalida(fechaSalida).setTotalAPagar(TOTAL_A_PAGAR).setIdVehiculo(VEHICULO);
		
		/**
		 * Act
		 */
		Factura factura = facturaTestDataBuilder.build(); 
		
		/**
		 * Asserts
		 */
		Assert.assertEquals(TOTAL_A_PAGAR, factura.getTotalAPagar(), 0.00);
		assertEquals(fechaIngreso, factura.getFechaIngreso());
		assertEquals(fechaSalida, factura.getFechaSalida());	
		assertEquals(VEHICULO, factura.getIdVehiculo());
		
		
	}
}
