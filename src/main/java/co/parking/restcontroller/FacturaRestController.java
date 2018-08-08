package co.parking.restcontroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@GetMapping("/generarFactura/{placa}")
	public ResponseEntity<Factura> buscarFactura( @PathVariable String placa) throws ServiceException {
		Vehiculo vehiculoActual = vehiculoService.consultarVehiculoPorMatricula(placa);
		if(vehiculoActual==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			Factura facturaALiquidar = facturaService.buscarFacturaVehiculo(vehiculoActual);
			return new ResponseEntity<>(facturaALiquidar, HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/liquidar")
	public ResponseEntity<Factura> generarFactura(@RequestBody Factura factura) throws ServiceException {
		try {
			return new ResponseEntity<>(this.facturaService.liquidarFactura(factura), HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error("Error al registrar el vehiculo con placa --{}{}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
