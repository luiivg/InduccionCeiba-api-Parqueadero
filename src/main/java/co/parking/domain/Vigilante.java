/**
 * 
 */
package co.parking.domain;


import java.util.Calendar;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.parking.domain.enumeration.TipoVehiculo;
import co.parking.domain.exception.VehiculoNoAutorizadoException;
import co.parking.utils.Constants;


/**
 * @author luisa.vargas
 *
 */

@Component
public class Vigilante {

	private static Vigilante vigilante;

	private Vigilante() {

	}

	public static Vigilante getInstance() {
		if (vigilante == null) {
			vigilante = new Vigilante();
		}
		return vigilante;
	}

	public Long validarEstaRegistrado(Vehiculo estaRegistrado) throws VehiculoNoAutorizadoException{
		if (estaRegistrado != null) {
			if(estaRegistrado.isActivo()){
				throw new VehiculoNoAutorizadoException("El vehiculo ya se encuentra registrado en el sistema");
			}else{
				return estaRegistrado.getId();
			}
		}
		return null;
	}

	public boolean celdaDisponible(TipoVehiculo tipoVehiculo, int vehiculosParqueados) throws VehiculoNoAutorizadoException {
		if ((TipoVehiculo.MOTO.equals(tipoVehiculo) && vehiculosParqueados > Constants.NUMERO_MOTOS_PERMITIDAS)
				|| (TipoVehiculo.CARRO.equals(tipoVehiculo) && vehiculosParqueados > Constants.NUMERO_CARROS_PERMITIDOS)) {
			throw new VehiculoNoAutorizadoException("No hay parqueadero disponible para el vehiculo");
		}
		return true;
	}

	public boolean validarAutorizacionDias(String placa, int dia) throws VehiculoNoAutorizadoException {
		if (Constants.PLACA_NO_PERMITIDA.equalsIgnoreCase(placa.substring(0, 1)) && !validarDia(dia)) {
			throw new VehiculoNoAutorizadoException("Vehiculo no Autorizado");
		}
		return true;
	}

	public boolean validarDia(int dia) {
		return dia == Calendar.SUNDAY || dia == Calendar.MONDAY;
	}
	
	
}
