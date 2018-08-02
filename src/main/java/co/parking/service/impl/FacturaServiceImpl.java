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
	public Factura buscarFacturaVehiculo(long idVehiculo) throws ServiceException {
		return facturaDao.consultarVehiculo(idVehiculo);
	}

	@Override
	public Factura liquidarFactura(Factura factura, Vehiculo vehiculo) throws ServiceException {
		try{
			factura.setId(factura.getId());
			factura.setFechaSalida(LocalDateTime.now());
			factura.setTotalAPagar(calculadora.calcularValorAPagar(factura, vehiculo));
			Factura facturaLiquidada = facturaDao.save(factura);
			if(facturaLiquidada.getFechaSalida() != null){
				vehiculo.setId(vehiculo.getId());
				vehiculo.setActivo(false);
				vehiculoDao.save(vehiculo);
			}
			return facturaLiquidada;
		}catch (Exception e) {
			LOGGER.error("Error en el servicio al registrar el vehiculo por placa--{}{}", vehiculo.getPlaca(), e);
			throw new ServiceException("Error en el servicio al registrar el vehiculo por placa--{}{}", e);
		}
		
	}
	
	

}
