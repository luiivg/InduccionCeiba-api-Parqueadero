/**
 * 
 */
package co.parking.integracion;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.parking.config.ApiParqueaderoApplication;
import co.parking.databuilder.FacturaTestDataBuilder;
import co.parking.databuilder.VehiculoTestDataBuilder;
import co.parking.domain.Factura;
import co.parking.domain.Vehiculo;
import co.parking.service.FacturaService;
import co.parking.service.VehiculoService;


/**
 * @author luisa.vargas
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiParqueaderoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestFacturaRestController {
	
	
	private static final String PLACA = "SRQ69E";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VehiculoService vehiculoService;
	
	@MockBean
	private FacturaService facturaService;
	
	@Test
	public void liqidarFactura500() throws Exception{
		
		Vehiculo vehiculo = new Vehiculo();
		
		when(vehiculoService.consultarVehiculoPorMatricula(PLACA)).thenReturn(vehiculo);
	
		FacturaTestDataBuilder facturaBuid = new FacturaTestDataBuilder();
		Factura factura = facturaBuid.build();
		
		when(facturaService.buscarFacturaVehiculo(-3)).thenReturn(factura);
		
		String json = "{}";
		
		this.mockMvc.perform((MockMvcRequestBuilders.post("/api/factura/generarFactura/{placa}", PLACA).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).content(json)).andExpect(status().isInternalServerError());
		
	}
	
	@Test
	public void liqidarFacturaNotFound() throws Exception{
		
		String json = "{}";
		
		this.mockMvc.perform((MockMvcRequestBuilders.post("/api/factura/generarFactura/{placa}", PLACA).accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)).content(json)).andExpect(status().isNotFound());
		
	}

	@Test
	public void liqidarFactura() throws Exception{
		
		VehiculoTestDataBuilder vehiculobuild = new VehiculoTestDataBuilder().setActivo(true).setCilindraje(200).setPlaca(PLACA).setId(1);
		Vehiculo vehiculo = vehiculobuild.build();
		
		when(vehiculoService.consultarVehiculoPorMatricula(PLACA)).thenReturn(vehiculo);
		
		FacturaTestDataBuilder facturaBuid = new FacturaTestDataBuilder();
		Factura factura = facturaBuid.build();
		
		when(facturaService.buscarFacturaVehiculo(vehiculo.getId())).thenReturn(factura);
		
		String json = "{}";
		
		this.mockMvc.perform((MockMvcRequestBuilders.post("/api/factura/generarFactura/{placa}", PLACA).accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)).content(json)).andExpect(status().isCreated());
		
	}
}
