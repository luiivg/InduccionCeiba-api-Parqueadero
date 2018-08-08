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
import co.parking.service.exception.ServiceException;
import co.parking.service.impl.FacturaServiceImpl;

public class TestFacturaService {

	private static final long ID_VEHICULO = 1;
	private static final String EXCEPCION = "Error en el servicio al liquidar factura para la placa--{}{}";
	
	@InjectMocks
	private FacturaServiceImpl faturaService;
	
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
		
		VehiculoTestDataBuilder vehiculobuild = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculobuild.build();
		
		Factura fact =  faturaService.buscarFacturaVehiculo(vehiculo);
		
		assertNotNull(fact);
	}
	
	@Test
	public void noEncontrarFacura() throws ServiceException{
		
		Mockito.when(facturaDao.consultarVehiculo(ID_VEHICULO)).thenReturn(null);
		
		Vehiculo vehiculo = new Vehiculo();
		
		Factura fact =  faturaService.buscarFacturaVehiculo(vehiculo);
		
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
		
		Factura fact = faturaService.liquidarFactura(factura);
		
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
			faturaService.liquidarFactura(factura);
			fail();		
		}catch(ServiceException e){
			assertEquals(EXCEPCION,e.getMessage());
		}
		
		
		
	}
	
	
	
}
