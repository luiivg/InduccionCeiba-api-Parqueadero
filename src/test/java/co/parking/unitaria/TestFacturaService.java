package co.parking.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

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
import co.parking.domain.exception.VehiculoNoAutorizadoException;
import co.parking.service.exception.ServiceException;
import co.parking.service.impl.FacturaServiceImpl;

public class TestFacturaService {

	private static final long ID_VEHICULO = 1;
	private static final String EXCEPCION = "Error en el servicio al liquidar factura para la placa--{}{}";
	
	@InjectMocks
	private FacturaServiceImpl faturaServideImpl;
	
	@Mock
	private VehiculoDao vehiculoDao;
	
	@Mock
	private FacturaDao facturaDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void buscarFactura() throws ServiceException{
		
		FacturaTestDataBuilder fasturaVehiculo = new FacturaTestDataBuilder();
		Factura factura = fasturaVehiculo.build();
		Mockito.when(facturaDao.consultarVehiculo(ID_VEHICULO)).thenReturn(factura);
		
		Factura fact =  faturaServideImpl.buscarFacturaVehiculo(ID_VEHICULO);
		
		assertNotNull(fact);
	}
	
	@Test
	public void noEncontrarFacura() throws ServiceException{
		
		Mockito.when(facturaDao.consultarVehiculo(ID_VEHICULO)).thenReturn(null);
		
		Factura fact =  faturaServideImpl.buscarFacturaVehiculo(ID_VEHICULO);
		
		assertNull(fact);
	}
	
	@Test
	public void liquidarFactura() throws ServiceException{
		FacturaTestDataBuilder fasturaVehiculo = new FacturaTestDataBuilder();
		Factura factura = fasturaVehiculo.build();
		Mockito.when(facturaDao.save(factura)).thenReturn(factura);
		
		VehiculoTestDataBuilder vehiculoMoto = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoMoto.build();
		Mockito.when(vehiculoDao.save(vehiculo)).thenReturn(vehiculo);
		
		Factura fact = faturaServideImpl.liquidarFactura(factura, vehiculo);
		
		assertNotNull(fact);
	}
	
	@Test
	public void noLiquidarFactura(){
		
		FacturaTestDataBuilder fasturaVehiculo = new FacturaTestDataBuilder();
		Factura factura = fasturaVehiculo.build();
		Mockito.when(facturaDao.save(factura)).thenReturn(null);
		
		VehiculoTestDataBuilder vehiculoMoto = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoMoto.build();
		Mockito.when(vehiculoDao.save(vehiculo)).thenReturn(vehiculo);
		try{
			faturaServideImpl.liquidarFactura(factura, vehiculo);
			fail();		
		}catch(ServiceException e){
			assertEquals(EXCEPCION,e.getMessage());
		}
		
		
		
	}
	
	
	
}
