package co.parking.service.impl;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import co.parking.dao.VehiculoDao;
import co.parking.dao.FacturaDao;
import co.parking.domain.Factura;
import co.parking.domain.Vehiculo;
import co.parking.domain.Vigilante;
import co.parking.domain.enumeration.TipoVehiculo;
import co.parking.service.VehiculoService;
import co.parking.service.exception.ServiceException;


@Service
public class VehiculoServiceImpl implements VehiculoService {

	private static Logger LOGGER = LoggerFactory.getLogger(VehiculoServiceImpl.class);
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@Autowired
	private FacturaDao facturaDao;
	
	@Autowired
	private Vigilante vigilante;
	

	@Override
	public Boolean signupVehiculo(Vehiculo vehiculo) throws ServiceException {
		try {
			Long id = vigilante.validarEstaRegistrado(consultarVehiculoPorMatricula(vehiculo.getPlaca()));
			vigilante.celdaDisponible(vehiculo.getTipo(), vehiculosEstacionados(vehiculo.getTipo()));
			
			Calendar calendario = Calendar.getInstance();
			int dia = calendario.get(Calendar.DAY_OF_WEEK);
			vigilante.validarAutorizacionDias(vehiculo.getPlaca(), dia);
			
			if(id != null){
				vehiculo.setId(id);
			}
			
			Vehiculo vehiculoguardado = vehiculoDao.save(vehiculo);
			if(vehiculoguardado!=null){
				Factura factura = new Factura();
				factura.setIdVehiculo(vehiculoguardado.getId());
				factura.setFechaIngreso(LocalDateTime.now());
				factura.setTotalAPagar(0);
				facturaDao.save(factura);
				return true;
			}else{
				LOGGER.error("Ocurrio un error al iniciar facturacion para el vehiculo" );
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("Error en el servicio al registrar el vehiculo por placa--{}{}", vehiculo.getPlaca(), e);
			throw new ServiceException("Error en el servicio al registrar el vehiculo por placa--{}{}", e);
		}
	}
	
	@Override
	public int vehiculosEstacionados(TipoVehiculo tipo)  throws ServiceException{
		return vehiculoDao.vehiculosParqueados(tipo);
	}

	@Override
	public List<Vehiculo> consultarTodosLosVehiculos() throws ServiceException {
		try {
			return (List<Vehiculo>) vehiculoDao.findAll();
		} catch (Exception e) {
			LOGGER.error("Error al obtener los vehiculos --{}{}", e);
			throw new ServiceException("Error al obtener los vehiculos --{}{}", e);
		}
	}

	@Override
	public Vehiculo consultarVehiculoPorMatricula(String placa) throws ServiceException {
			return vehiculoDao.consultarVehiculoPorPlaca(placa);
			
	}

	
	
	
	

}
