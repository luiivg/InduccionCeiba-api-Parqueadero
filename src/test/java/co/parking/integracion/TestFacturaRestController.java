/**
 * 
 */
package co.parking.integracion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.parking.config.ApiParqueaderoApplication;
import co.parking.service.impl.FacturaServiceImpl;

/**
 * @author luisa.vargas
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiParqueaderoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestFacturaRestController {
	
	
	@Test
	public void test(){
		assert(true);
	}

}
