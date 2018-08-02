package co.parking.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.parking.domain.Vehiculo;
import co.parking.service.VehiculoService;
import co.parking.utils.Utils;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping(value = "/api/vehiculo/")
public class VehiculoRestController {

	private static Logger LOGGER = LoggerFactory.getLogger(VehiculoRestController.class);

	@Autowired
	VehiculoService vehiculoService;

	@PostMapping(value = "/save")
	public ResponseEntity<Boolean> signupVoucher(@RequestBody Vehiculo vehiculo) {
		try {
			vehiculo.setActivo(true);
			return new ResponseEntity<Boolean>(vehiculoService.signupVehiculo(vehiculo), HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error("Error al registrar el vehiculo con placa --{}{}", vehiculo.getPlaca());
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@GetMapping(value = "/find")
	public ResponseEntity<Vehiculo> consultarVehiculoPorPlaca(@RequestParam String placa) {
		try {
			Vehiculo vehiculo = vehiculoService.consultarVehiculoPorMatricula(placa);
			if (!Utils.isNull(vehiculo)) {
				return new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
			} else {
				return new ResponseEntity<Vehiculo>(new Vehiculo(), HttpStatus.OK);
			}
		} catch (Exception e) {
			LOGGER.error("Error al consultar el vehiculo por placa --{}{}", placa, e);
			return new ResponseEntity<Vehiculo>(new Vehiculo(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/findall")
	public ResponseEntity<List<Vehiculo>> consultarTodosLosVehiculos() {
		try {
			List<Vehiculo> vehiculos = vehiculoService.consultarTodosLosVehiculos();
			if (!Utils.isNull(vehiculos)) {
				return new ResponseEntity<List<Vehiculo>>(vehiculos, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Vehiculo>>(new ArrayList<>(), HttpStatus.OK);
			}
		} catch (Exception e) {
			LOGGER.error("Error al obtener los vehiculos --{}{}", e);
			return new ResponseEntity<List<Vehiculo>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
