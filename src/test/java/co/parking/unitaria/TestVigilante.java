/**
 * 
 */
package co.parking.unitaria;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.parking.dao.VehiculoDao;
import co.parking.domain.Vigilante;

/**
 * @author luisa.vargas
 *
 */
public class TestVigilante {
	
	Vigilante vigilante;
	
	@Mock
	private VehiculoDao vehiculoDao;
	
	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		vigilante = Vigilante.getInstance();
	}
	
	@Test
	public void validarEstaRegistrado(){
		long resultado = 0;	
		
	}
	

}
