/**
 * 
 */
package co.parking.domain;

import java.time.LocalDateTime;

import co.parking.domain.enumeration.TipoVehiculo;
import co.parking.utils.Constants;
import co.parking.utils.DateUtils;

/**
 * @author luisa
 *
 */
public class Calculadora {
	
	private static Calculadora calculadora;

	private Calculadora() {

	}

	public static Calculadora getInstance() {
		if (calculadora == null) {
			calculadora = new Calculadora();
		}
		return calculadora;
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
