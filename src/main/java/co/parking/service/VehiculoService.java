package co.parking.service;

import java.util.List;


import co.parking.domain.Vehiculo;
import co.parking.domain.en.TipoVehiculo;
import co.parking.service.ex.ServiceException;

public interface VehiculoService {

	public Boolean signupVehiculo(Vehiculo vehiculo) throws ServiceException;
	
	public int vehiculosEstacionados(TipoVehiculo tipo)  throws ServiceException;

	public List<Vehiculo> consultarTodosLosVehiculos() throws ServiceException;

	public Vehiculo consultarVehiculoPorMatricula(String placa) throws ServiceException;

}
