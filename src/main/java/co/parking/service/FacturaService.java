/**
 * 
 */
package co.parking.service;

import co.parking.domain.Factura;
import co.parking.domain.Vehiculo;
import co.parking.service.ex.ServiceException;


/**
 * @author luisa.vargas
 *
 */
public interface FacturaService {

	public Factura buscarFacturaVehiculo(long idVehiculo) throws ServiceException;
	
	public Factura liquidarFactura(Factura factura, Vehiculo vehiculo) throws ServiceException;
}
