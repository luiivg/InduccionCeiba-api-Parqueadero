/**
 * 
 */
package co.parking.service;

import co.parking.entity.Factura;
import co.parking.entity.Vehiculo;
import co.parking.service.exception.ServiceException;


/**
 * @author luisa.vargas
 *
 */
public interface FacturaService {

	public Factura buscarFacturaVehiculo(long idVehiculo) throws ServiceException;
	
	public Factura liquidarFactura(Factura factura, Vehiculo vehiculo) throws ServiceException;
}
