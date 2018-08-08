package co.parking.service;

import java.util.List;

import co.parking.domain.Vehiculo;
import co.parking.domain.enumeration.TipoVehiculo;
import co.parking.service.exception.ServiceException;

public interface VehiculoService {

	public Vehiculo signupVehiculo(Vehiculo vehiculo) throws ServiceException;
	
	public int vehiculosEstacionados(TipoVehiculo tipo)  throws ServiceException;

	public List<Vehiculo> consultarTodosLosVehiculos() throws ServiceException;

	public Vehiculo consultarVehiculoPorMatricula(String placa);
	
	public Vehiculo consultarVehiculoPorid(long id);

}
