/**
 * 
 */
package co.parking.integracion;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.parking.config.ApiParqueaderoApplication;
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
	private VehiculoServiceImpl vehiculoImplement;
	
	@Test
	public void guardarVehiculo() throws Exception{
		String json = "{ \"tipo\": \"MOTO\" , \"placa\": \"MOTO\" , \"cilindraje\": \"500\"}";
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/vehiculo/save").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());
	}

}
