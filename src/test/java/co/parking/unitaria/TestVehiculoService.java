package co.parking.unitaria;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.parking.dao.FacturaDao;
import co.parking.dao.VehiculoDao;
import co.parking.databuilder.FacturaTestDataBuilder;
import co.parking.databuilder.VehiculoTestDataBuilder;
import co.parking.domain.Factura;
import co.parking.domain.Vehiculo;
import co.parking.domain.Vigilante;
import co.parking.domain.exception.VehiculoNoAutorizadoException;
import co.parking.service.exception.ServiceException;
import co.parking.service.impl.VehiculoServiceImpl;


public class TestVehiculoService {


	private static final int CELDAS_OCUPADAS = 0;
	private static final long ID_VEHICULO = 1;
	private static final int DIA_AUTORIZADO = 3;

	
	
	@InjectMocks
	private VehiculoServiceImpl vehiculoService;
	
	@Mock
	private VehiculoDao vehiculoDao;
	
	@Mock
	private FacturaDao facturaDao;
	
	@Mock
	private Vigilante vigilante;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	

	
	@Test
	public void signupVehiculoTest() throws VehiculoNoAutorizadoException, ServiceException{
		
		VehiculoTestDataBuilder vehiculoBuild = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoBuild.build();
		Mockito.when(vigilante.validarEstaRegistrado(vehiculo)).thenReturn(ID_VEHICULO);
		Mockito.when(vigilante.celdaDisponible(vehiculo.getTipo(), CELDAS_OCUPADAS)).thenReturn(true);
		Mockito.when(vigilante.validarAutorizacionDias(vehiculo.getPlaca(), DIA_AUTORIZADO)).thenReturn(true);
		Mockito.when(vehiculoDao.consultarVehiculoPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);
		Mockito.when(vehiculoDao.save(vehiculo)).thenReturn(vehiculo);
		
		LocalDateTime fechaIngreso = LocalDateTime.now().minusHours(3);
		LocalDateTime fechaSalida = LocalDateTime.now();
		FacturaTestDataBuilder facturaBuild = new FacturaTestDataBuilder().setFechaIngreso(fechaIngreso).setFechaSalida(fechaSalida).setIdVehiculo(ID_VEHICULO).setTotalAPagar(1000.00);
		Factura factura = facturaBuild.build();
		Mockito.when(facturaDao.save(factura)).thenReturn(factura);
		
		Vehiculo vehiculoResponse = vehiculoService.signupVehiculo(vehiculo);
		
		//assert
		assertTrue(vehiculoResponse.isActivo());
	} 
	
		
	@Test
	public void consultarvehiculosnulo() throws ServiceException{
		
		List<Vehiculo> vehiculos = new ArrayList<>();
		Mockito.when(vehiculoDao.findAll()).thenReturn(vehiculos);
		
		assertTrue(vehiculoService.consultarTodosLosVehiculos().isEmpty());
		
	}
	
		
	@Test
	public void consultarvehiculos() throws ServiceException{
		
		List<Vehiculo> vehiculos = new ArrayList<>();
		VehiculoTestDataBuilder vehiculoBuild = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoBuild.build();
		vehiculos.add(vehiculo);
		
		Mockito.when(vehiculoDao.findAll()).thenReturn(vehiculos);
		
		assertFalse(vehiculoService.consultarTodosLosVehiculos().isEmpty());
		
	}
	
	
	
	
}
