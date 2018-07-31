package co.parking.restcontroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.parking.entity.Factura;
import co.parking.entity.Vehiculo;
import co.parking.service.FacturaService;
import co.parking.service.VehiculoService;
import co.parking.service.exception.ServiceException;

@RestController
@RequestMapping(value = "/api/voucher")
public class VoucherRestController {

	private static Logger LOGGER = LoggerFactory.getLogger(VoucherRestController.class);

	@Autowired
	private VehiculoService voucherService;
	
	@Autowired
	private FacturaService facturaService;

	@PostMapping(value = "/save")
	public ResponseEntity<Boolean> signupVoucher(@RequestBody Vehiculo vehiculo) {
		try {
			vehiculo.setActivo(true);
			return new ResponseEntity<Boolean>(voucherService.signupVehiculo(vehiculo), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error al registrar el vehiculo con placa --{}{}", vehiculo.getPlaca());
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@PostMapping(value = "/generarFactura/{placa}")
	public ResponseEntity<Factura> generarFactura(@PathVariable String placa) throws ServiceException {
		try {
			Vehiculo vehiculoActual = voucherService.consultarVehiculoPorMatricula(placa);
			if(vehiculoActual.equals(null)){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Factura facturaALiquidar = facturaService.buscarFacturaVehiculo(vehiculoActual.getId());
			return new ResponseEntity<>(this.facturaService.liquidarFactura(facturaALiquidar, vehiculoActual), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Error al registrar el vehiculo con placa --{}{}", placa);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
