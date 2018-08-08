/**
 * 
 */
package co.parking.unitaria;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.parking.dao.FacturaDao;
import co.parking.domain.Calculadora;
import co.parking.domain.Vehiculo;
import co.parking.domain.enumeration.TipoVehiculo;

/**
 * @author luisa.vargas
 *
 */
public class TestCalculadora {
	
	Calculadora calculadora;
	
	@Mock
	private FacturaDao facturaDao;
	
	@Before
	public void mocksInitialization() {
		MockitoAnnotations.initMocks(this);

		calculadora = Calculadora.getInstance();
	}
	
	@Test
	public void calcularValorMenorHora(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusMinutes(40);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setFechaIngreso(fechaIngreso);
		
		assertEquals(500.0, calculadora.calcularValorAPagar( vehiculo), 0.0);
	}
	
	
	@Test
	public void calcularHoraCarro(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(6);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		vehiculo.setFechaIngreso(fechaIngreso);
		
		assertEquals(6000.0, calculadora.calcularValorAPagar(vehiculo), 0.0);
		
	}
	
	@Test
	public void calcularDiaCarro(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(9);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		vehiculo.setFechaIngreso(fechaIngreso);
		
		assertEquals(8000.0, calculadora.calcularValorAPagar(vehiculo), 0.0);
		
	}
	
	@Test
	public void calcularDiaHorasCarro(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(26);
			
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		vehiculo.setFechaIngreso(fechaIngreso);
		
		assertEquals(10000.0, calculadora.calcularValorAPagar(vehiculo), 0.0);
	}
	
	@Test
	public void calcularHoraMoto(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(8);
	
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setCilindraje(180);
		vehiculo.setFechaIngreso(fechaIngreso);
		
		assertEquals(4000.0, calculadora.calcularValorAPagar(vehiculo), 0.0);
	}
	
	@Test
	public void calcularDiaMoto(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(15);
				
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setCilindraje(180);
		vehiculo.setFechaIngreso(fechaIngreso);
		
		assertEquals(4000.0, calculadora.calcularValorAPagar(vehiculo), 0.0);
	}
	
	@Test
	public void calcularDiaHorasMoto(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(25);
				
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setCilindraje(180);
		vehiculo.setFechaIngreso(fechaIngreso);
		
		assertEquals(4500.0, calculadora.calcularValorAPagar(vehiculo), 0.0);
	}
	
	@Test
	public void caclularValorAdicionalMoto(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(25);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setCilindraje(501);
		vehiculo.setFechaIngreso(fechaIngreso);
		
		assertEquals(6500.0, calculadora.calcularValorAPagar(vehiculo), 0.0);
	}
	
	
}
