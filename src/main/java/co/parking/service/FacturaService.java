/**
 * 
 */
package co.parking.service;

import co.parking.domain.Factura;
import co.parking.domain.Vehiculo;
import co.parking.service.exception.ServiceException;


/**
 * @author luisa.vargas
 *
 */
public interface FacturaService {

	public Factura buscarFacturaVehiculo(Vehiculo vehiculo) throws ServiceException;
	
	public Factura liquidarFactura(Factura factura) throws ServiceException;
}
