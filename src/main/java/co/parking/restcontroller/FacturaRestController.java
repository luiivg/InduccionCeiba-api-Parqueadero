package co.parking.restcontroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.parking.domain.Factura;
import co.parking.domain.Vehiculo;
import co.parking.service.FacturaService;
import co.parking.service.VehiculoService;
import co.parking.service.exception.ServiceException;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(value = "/api/factura")
public class FacturaRestController {

	private static Logger LOGGER = LoggerFactory.getLogger(FacturaRestController.class);

	@Autowired
	private VehiculoService vehiculoService;
	
	@Autowired
	private FacturaService facturaService;
	
	@PostMapping(value = "/generarFactura/{placa}")
	public ResponseEntity<Factura> generarFactura(@PathVariable String placa) throws ServiceException {
		try {
			Vehiculo vehiculoActual = vehiculoService.consultarVehiculoPorMatricula(placa);
			if(vehiculoActual==null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else{
				Factura facturaALiquidar = facturaService.buscarFacturaVehiculo(vehiculoActual.getId());
				return new ResponseEntity<>(this.facturaService.liquidarFactura(facturaALiquidar, vehiculoActual), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			LOGGER.error("Error al registrar el vehiculo con placa --{}{}", placa);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
