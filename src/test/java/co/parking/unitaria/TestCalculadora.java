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
import co.parking.domain.Factura;
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
		LocalDateTime fechaSalida = LocalDateTime.now();
		
		Factura factura = new Factura();
		factura.setFechaIngreso(fechaIngreso);
		factura.setFechaSalida(fechaSalida);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		
		assertEquals(500.0, calculadora.calcularValorAPagar(factura, vehiculo), 0.0);
	}
	
	
	@Test
	public void calcularHoraCarro(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(6);
		LocalDateTime fechaSalida = LocalDateTime.now();
		
		Factura factura = new Factura();
		factura.setFechaIngreso(fechaIngreso);
		factura.setFechaSalida(fechaSalida);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		
		assertEquals(6000.0, calculadora.calcularValorAPagar(factura, vehiculo), 0.0);
		
	}
	
	@Test
	public void calcularDiaCarro(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(9);
		LocalDateTime fechaSalida = LocalDateTime.now();
		
		Factura factura = new Factura();
		factura.setFechaIngreso(fechaIngreso);
		factura.setFechaSalida(fechaSalida);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		
		assertEquals(8000.0, calculadora.calcularValorAPagar(factura, vehiculo), 0.0);
		
	}
	
	@Test
	public void calcularDiaHorasCarro(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(26);
		LocalDateTime fechaSalida = LocalDateTime.now();
		
		Factura factura = new Factura();
		factura.setFechaIngreso(fechaIngreso);
		factura.setFechaSalida(fechaSalida);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.CARRO);
		
		assertEquals(10000.0, calculadora.calcularValorAPagar(factura, vehiculo), 0.0);
	}
	
	@Test
	public void calcularHoraMoto(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(8);
		LocalDateTime fechaSalida = LocalDateTime.now();
		
		Factura factura = new Factura();
		factura.setFechaIngreso(fechaIngreso);
		factura.setFechaSalida(fechaSalida);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setCilindraje(180);
		
		assertEquals(4000.0, calculadora.calcularValorAPagar(factura, vehiculo), 0.0);
	}
	
	@Test
	public void calcularDiaMoto(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(15);
		LocalDateTime fechaSalida = LocalDateTime.now();
		
		Factura factura = new Factura();
		factura.setFechaIngreso(fechaIngreso);
		factura.setFechaSalida(fechaSalida);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setCilindraje(180);
		
		assertEquals(4000.0, calculadora.calcularValorAPagar(factura, vehiculo), 0.0);
	}
	
	@Test
	public void calcularDiaHorasMoto(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(25);
		LocalDateTime fechaSalida = LocalDateTime.now();
		
		Factura factura = new Factura();
		factura.setFechaIngreso(fechaIngreso);
		factura.setFechaSalida(fechaSalida);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setCilindraje(180);
		
		assertEquals(4500.0, calculadora.calcularValorAPagar(factura, vehiculo), 0.0);
	}
	
	@Test
	public void caclularValorAdicionalMoto(){
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(25);
		LocalDateTime fechaSalida = LocalDateTime.now();
		
		Factura factura = new Factura();
		factura.setFechaIngreso(fechaIngreso);
		factura.setFechaSalida(fechaSalida);
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipo(TipoVehiculo.MOTO);
		vehiculo.setCilindraje(501);
		
		assertEquals(6500.0, calculadora.calcularValorAPagar(factura, vehiculo), 0.0);
	}
	
	
}
