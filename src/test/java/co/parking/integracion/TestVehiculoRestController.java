/**
 * 
 */
package co.parking.integracion;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import co.parking.domain.Vehiculo;
import co.parking.domain.enumeration.TipoVehiculo;
import co.parking.service.impl.VehiculoServiceImpl;


/**
 * @author luisa.vargas
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiParqueaderoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestVehiculoRestController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VehiculoServiceImpl vehiculoService;
	
	@Test
	public void guardarVehiculo() throws Exception{
		String json = "{ \"tipo\": \"MOTO\" , \"placa\": \"MOTO\" , \"cilindraje\": \"500\"}";
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/vehiculo/save").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());
	}
	
	@Test
	public void listarVehiculos() throws Exception{
		
		List<Vehiculo> vehiculos = Arrays.asList(new Vehiculo(TipoVehiculo.CARRO, "SRQ69E", 200, true, LocalDateTime.now()));
		
		when(vehiculoService.consultarTodosLosVehiculos()).thenReturn(vehiculos);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculo/listar").accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void listarVehiculosNoEncontrados() throws Exception{
		
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		when(vehiculoService.consultarTodosLosVehiculos()).thenReturn(vehiculos);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/vehiculo/listar").accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isNoContent()).andReturn();
		
	}
	

}
