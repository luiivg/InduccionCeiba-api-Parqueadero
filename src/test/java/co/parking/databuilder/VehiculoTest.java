package co.parking.databuilder;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import co.parking.domain.Vehiculo;
import co.parking.domain.enumeration.TipoVehiculo;

public class VehiculoTest {

	private static final TipoVehiculo TIPO = TipoVehiculo.MOTO;
	private static final String PLACA = "NWK23D";
	private static final int CILINDRAJE = 160;
	private static final boolean ACTIVO = true;
	
	@Test
	public void crearVehiculoMotoTest(){
		
		/**
		 * Arrange
		 */
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(3);
		
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder().
				setTipo(TIPO).setPlaca(PLACA).setCilindraje(CILINDRAJE).setActivo(ACTIVO).setFechIngreso(fechaIngreso);
	
		/**
		 * Act
		 */
		Vehiculo vehiculo =  vehiculoDataBuilder.build();
		
		/**
		 * Asserts
		 */
		assertEquals(TIPO, vehiculo.getTipo());
		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(CILINDRAJE, vehiculo.getCilindraje());
		assertEquals(ACTIVO, vehiculo.isActivo());
		assertEquals(fechaIngreso, vehiculo.getFechaIngreso());
	}
}
