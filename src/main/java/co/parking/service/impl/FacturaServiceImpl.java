/**
 * 
 */
package co.parking.service.impl;

import java.time.LocalDateTime;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.parking.dao.VehiculoDao;
import co.parking.dao.FacturaDao;
import co.parking.domain.Calculadora;
import co.parking.domain.Factura;
import co.parking.domain.Vehiculo;
import co.parking.domain.Vigilante;
import co.parking.service.FacturaService;
import co.parking.service.exception.ServiceException;
import co.parking.utils.Utils;

/**
 * @author luisa.vargas
 *
 */
@Service
public class FacturaServiceImpl implements FacturaService{

	private static Logger LOGGER = LoggerFactory.getLogger(FacturaServiceImpl.class);

	@Autowired
	private VehiculoDao vehiculoDao;
	
	@Autowired
	private FacturaDao facturaDao;
	
	Vigilante vigilante = Vigilante.getInstance();
	Calculadora calculadora = Calculadora.getInstance();

	
	@Override
	public Factura buscarFacturaVehiculo(Vehiculo vehiculo) throws ServiceException {
		Factura facturaALiquidar = facturaDao.consultarVehiculo(vehiculo.getId());
		facturaALiquidar.setFechaSalida(LocalDateTime.now());
		facturaALiquidar.setTotalAPagar(calculadora.calcularValorAPagar(vehiculo));
		return facturaALiquidar;
	}

	@Override
	public Factura liquidarFactura(Factura factura) throws ServiceException {
		try{
			Factura facturaLiquidada = facturaDao.save(factura);
			if(!Utils.isNull(facturaLiquidada.getFechaSalida())){
				Vehiculo vehiculoActualizar = vehiculoDao.consultarVehiculoPorId(facturaLiquidada.getIdVehiculo());
				vehiculoActualizar.setActivo(false);
				vehiculoDao.save(vehiculoActualizar);
			}
			return facturaLiquidada;
		}catch (Exception e) {
			LOGGER.error("Error en el servicio al liquidar factura para la placa--{}{}", e);
			throw new ServiceException("Error en el servicio al liquidar factura para la placa--{}{}", e);
		}
		
	}
	
	
	
	

}
