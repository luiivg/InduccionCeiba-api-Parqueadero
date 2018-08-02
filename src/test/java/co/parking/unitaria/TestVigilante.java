/**
 * 
 */
package co.parking.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.parking.dao.VehiculoDao;
import co.parking.domain.Calculadora;
import co.parking.domain.Vehiculo;
import co.parking.domain.Vigilante;
import co.parking.domain.enumeration.TipoVehiculo;
import co.parking.domain.exception.VehiculoNoAutorizadoException;
import co.parking.utils.Constants;

/**
 * @author luisa.vargas
 *
 */
public class TestVigilante {
	
	Vigilante vigilante;
	private Boolean respuesta;
	private static final String PLACA_CON_A = "ADN04A";
	private static final String PLACA_SIN_A = "WRQ86D";
	private static final String PLACA_REGISTRADA = "DKP98P";
	private static final String PLACA_NO_REGISTRADA = "SRQ69E";
	
	@Mock
	private VehiculoDao vehiculoDao;
	
	
	
	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		vigilante = Vigilante.getInstance();
	}
	
	@Test
	public void validarEstaRegistradoVehiculo(){
						
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(PLACA_REGISTRADA);
		vehiculo.setActivo(true);
		Mockito.when(vehiculoDao.consultarVehiculoPorPlaca(PLACA_REGISTRADA)).thenReturn(vehiculo);
		
		try {
			vigilante.validarEstaRegistrado(vehiculo);
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("El vehiculo ya se encuentra registrado en el sistema", e.getMessage());
		}
	}
	
	@Test
	public void validarEstaRegistradoVehiculoNoActivo(){
						
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(PLACA_REGISTRADA);
		vehiculo.setActivo(false);
		Mockito.when(vehiculoDao.consultarVehiculoPorPlaca(PLACA_REGISTRADA)).thenReturn(vehiculo);
		
		try {
			vigilante.validarEstaRegistrado(vehiculo);
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("El vehiculo ya se encuentra registrado en el sistema", e.getMessage());
		}
	}
	
	@Test
	public void validarNoEstaRegistradoVehiculo(){
						
		Mockito.when(vehiculoDao.consultarVehiculoPorPlaca(PLACA_NO_REGISTRADA)).thenReturn(null);
		
		try {
			vigilante.validarEstaRegistrado(null);
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("El vehiculo ya se encuentra registrado en el sistema", e.getMessage());
		}
	}
	
	
	@Test
	public void validarCeldasDisponiblesCarro(){
		
		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO))
			.thenReturn(Constants.NUMERO_CARROS_PERMITIDOS -2);
		
		try {
			vigilante.celdaDisponible(TipoVehiculo.CARRO, Constants.NUMERO_CARROS_PERMITIDOS -2);
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}
	}
	
	@Test
	public void validarCeldasNoDisponiblesCarro(){
		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.CARRO))
		.thenReturn(Constants.NUMERO_CARROS_PERMITIDOS +1);
		
		try {
			vigilante.celdaDisponible(TipoVehiculo.CARRO, Constants.NUMERO_CARROS_PERMITIDOS +1);
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}
	}
	
	@Test
	public void validarCeldasDisponiblesMoto(){
		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.MOTO))
		.thenReturn(Constants.NUMERO_MOTOS_PERMITIDAS -2);
	
	try {
		vigilante.celdaDisponible(TipoVehiculo.MOTO, Constants.NUMERO_MOTOS_PERMITIDAS -2);
	} catch (VehiculoNoAutorizadoException e) {
		assertEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
	}
	}
	
	@Test
	public void validarCeldasNoDisponiblesMoto(){
		Mockito.when(vehiculoDao.vehiculosParqueados(TipoVehiculo.MOTO))
		.thenReturn(Constants.NUMERO_MOTOS_PERMITIDAS +1);
		
		try {
			vigilante.celdaDisponible(TipoVehiculo.MOTO, Constants.NUMERO_MOTOS_PERMITIDAS +1);
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("No hay parqueadero disponible para el vehiculo", e.getMessage());
		}
	}
	
	@Test
	public void validarIngresoPlacaSinA() throws VehiculoNoAutorizadoException{
		Calendar diaSemana = Calendar.getInstance();
		diaSemana.setTime(new Date());
		diaSemana.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		int dia = diaSemana.get(Calendar.DAY_OF_WEEK);
		
		assertTrue(vigilante.validarAutorizacionDias(PLACA_SIN_A, dia));
	}

	@Test
	public void validarIngresoPlacaA() throws VehiculoNoAutorizadoException{
		Calendar diaSemana = Calendar.getInstance();
		diaSemana.setTime(new Date());
		diaSemana.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		int dia = diaSemana.get(Calendar.DAY_OF_WEEK);
		
		assertTrue(vigilante.validarAutorizacionDias(PLACA_CON_A, dia));
	}
	
	@Test
	public void validarNoIngresoPlacaA()throws VehiculoNoAutorizadoException{
		Calendar diaSemana = Calendar.getInstance();
		diaSemana.setTime(new Date());
		diaSemana.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		int dia = diaSemana.get(Calendar.DAY_OF_WEEK);	
		
		try {
			vigilante.validarAutorizacionDias(PLACA_CON_A, dia);
		} catch (VehiculoNoAutorizadoException e) {
			assertEquals("Vehiculo no Autorizado", e.getMessage());
		}
		
	}
	

}
