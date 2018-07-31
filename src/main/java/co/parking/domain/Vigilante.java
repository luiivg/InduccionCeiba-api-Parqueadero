/**
 * 
 */
package co.parking.domain;

import java.time.LocalDateTime;
import java.util.Calendar;

import co.parking.domain.en.TipoVehiculo;
import co.parking.domain.ex.VehiculoNoAutorizadoException;
import co.parking.utils.Constants;
import co.parking.utils.DateUtils;

/**
 * @author luisa.vargas
 *
 */
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

	public Long validarEstaRegistrado(Vehiculo estaRegistrado) throws VehiculoNoAutorizadoException {
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

	public boolean validarAutorizacionDias(String placa) throws VehiculoNoAutorizadoException {
		Calendar calendario = Calendar.getInstance();
		int dia = calendario.get(Calendar.DAY_OF_WEEK);
		if (Constants.PLACA_NO_PERMITIDA.equalsIgnoreCase(placa.substring(0, 1)) && !validarDia(dia)) {
			throw new VehiculoNoAutorizadoException("Vehiculo no permitido");
		}
		return true;
	}

	private boolean validarDia(int dia) {
		return dia == Calendar.SUNDAY || dia == Calendar.FRIDAY;
	}
	
	public double calcularValorAPagar(Factura factura, Vehiculo vehiculo){
		double totalPagar = 0;
		LocalDateTime fechaHoraActual = LocalDateTime.now();
		if(vehiculo.getTipo() == TipoVehiculo.MOTO){
			if(vehiculo.getCilindraje()>= Constants.CILINDRAJE_MOTO_MAXIMO){
				totalPagar = calcularValorMoto(factura.getFechaIngreso(), fechaHoraActual) + Constants.VALOR_ADICIONAL_CILINDRAJE_MOTO;
			}else{
				totalPagar = calcularValorMoto(factura.getFechaIngreso(), fechaHoraActual);
			}
		}else{
			totalPagar = calcularValorCarro(factura.getFechaIngreso(), fechaHoraActual);
		}
		return totalPagar;
	}
	
	private double calcularValorCarro(LocalDateTime start, LocalDateTime end) {
		double hours = DateUtils.getDiferentInHours(start, end);
		double valueHour = DateUtils.calculateHour((int)hours) * Constants.VALOR_HORA_CARRO;
		double valueDay = DateUtils.calculateDays((int)hours) * Constants.VALOR_DIA_CARRO;
		return valueDay + valueHour;
	}

	private double calcularValorMoto(LocalDateTime start, LocalDateTime end) {
		double hours = DateUtils.getDiferentInHours(start, end);
		double valueHour = DateUtils.calculateHour((int) hours) * Constants.VALOR_HORA_MOTO;
		double valueDay = DateUtils.calculateDays((int)hours) * Constants.VALOR_DIA_MOTO;
		return valueDay + valueHour;
	}
}
