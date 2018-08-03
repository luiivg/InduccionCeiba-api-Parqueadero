/**
 * 
 */
package co.parking.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import co.parking.dao.VehiculoDao;
import co.parking.databuilder.VehiculoTestDataBuilder;
import co.parking.domain.Vehiculo;

/**
 * @author luisa.vargas
 *
 */
public class TestVehiculoDao {

	@Test
	public void estaRegistrado(){
		
		VehiculoTestDataBuilder vehiculobuild = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculobuild.build();
		
		VehiculoDao vehiculoDato = mock(VehiculoDao.class);
		when(vehiculoDato.consultarVehiculoPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

		Vehiculo vehiculoResgistrado = vehiculoDato.consultarVehiculoPorPlaca(vehiculo.getPlaca());
		
		assertEquals(vehiculo, vehiculoResgistrado);
	}
	
	@Test
	public void noEstaRegistrado(){
		
		VehiculoTestDataBuilder vehiculobuild = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculobuild.build();
		
		VehiculoDao vehiculoDato = mock(VehiculoDao.class);
		when(vehiculoDato.consultarVehiculoPorPlaca(vehiculo.getPlaca())).thenReturn(null);

		Vehiculo vehiculoResgistrado = vehiculoDato.consultarVehiculoPorPlaca(vehiculo.getPlaca());
		
		assertNull(vehiculoResgistrado);
	}
	
	@Test
	public void vehiculosParqueados(){
		
		VehiculoTestDataBuilder vehiculobuild = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculobuild.build();
		
		VehiculoDao vehiculoDato = mock(VehiculoDao.class);
		when(vehiculoDato.vehiculosParqueados(vehiculo.getTipo())).thenReturn(10);

		int vehiculoResgistrado = vehiculoDato.vehiculosParqueados(vehiculo.getTipo());
		
		assertEquals(10, vehiculoResgistrado);
	}
}
