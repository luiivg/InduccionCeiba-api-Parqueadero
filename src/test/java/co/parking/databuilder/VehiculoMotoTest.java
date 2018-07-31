package co.parking.databuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import co.parking.domain.enumeration.TipoVehiculo;
import co.parking.entity.Vehiculo;

public class VehiculoMotoTest {

	private static final TipoVehiculo TIPO = TipoVehiculo.MOTO;
	private static final String PLACA = "NWK23D";
	private static final int CILINDRAJE = 160;
	private static final boolean ACTIVO = true;
	
	@Test
	public void crearVehiculoMotoTest(){
		
		/**
		 * Arrange
		 */
		VehiculoMotoTestDataBuilder vehiculoMotoDataBuilder = new VehiculoMotoTestDataBuilder().
				setTipo(TIPO).setPlaca(PLACA).setCilindraje(CILINDRAJE).setActivo(ACTIVO);
	
		/**
		 * Act
		 */
		Vehiculo vehiculoMoto =  vehiculoMotoDataBuilder.build();
		
		/**
		 * Asserts
		 */
		assertEquals(TIPO, vehiculoMoto.getTipo());
		assertEquals(PLACA, vehiculoMoto.getPlaca());
		assertEquals(CILINDRAJE, vehiculoMoto.getCilindraje());
		assertEquals(ACTIVO, vehiculoMoto.isActivo());
	}
}
