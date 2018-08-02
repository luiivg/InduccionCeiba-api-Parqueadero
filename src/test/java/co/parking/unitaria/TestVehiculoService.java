package co.parking.unitaria;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.Date;

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
import co.parking.domain.enumeration.TipoVehiculo;
import co.parking.domain.exception.VehiculoNoAutorizadoException;
import co.parking.service.exception.ServiceException;
import co.parking.service.impl.VehiculoServiceImpl;

public class TestVehiculoService {


	private static final int CELDAS_OCUPADAS = 0;
	private static final long ID_VEHICULO = 1;
	private static final int DIA_AUTORIZADO = 3;
	
	
	@InjectMocks
	private VehiculoServiceImpl vehiculoImpl;
	
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
	public void noSignupVehiculoTest() throws VehiculoNoAutorizadoException, ServiceException{
		vehiculoDao = mock(VehiculoDao.class);
		
		
		VehiculoTestDataBuilder vehiculoMoto = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoMoto.build();
		Mockito.when(vigilante.validarEstaRegistrado(vehiculo)).thenReturn(ID_VEHICULO);
		Mockito.when(vigilante.celdaDisponible(vehiculo.getTipo(), CELDAS_OCUPADAS)).thenReturn(true);
		Mockito.when(vigilante.validarAutorizacionDias(vehiculo.getPlaca(), DIA_AUTORIZADO)).thenReturn(true);
		Mockito.when(vehiculoDao.consultarVehiculoPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);
		Mockito.when(vehiculoDao.save(vehiculo)).thenReturn(null);
		
		//assert
		assertFalse((vehiculoImpl.signupVehiculo(vehiculo)));
	}
	
	@Test
	public void signupVehiculoTest() throws VehiculoNoAutorizadoException, ServiceException{
		vehiculoDao = mock(VehiculoDao.class);
		
		VehiculoTestDataBuilder vehiculoBuild = new VehiculoTestDataBuilder().setActivo(true).setCilindraje(150).setPlaca("SGT480").setTipo(TipoVehiculo.CARRO);
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
		
		//assert
		assertFalse((vehiculoImpl.signupVehiculo(vehiculo)));
	}
	
	
}
