/**
 * 
 */
package co.parking.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import co.parking.domain.enumeration.TipoVehiculo;
import co.parking.utils.Constants;

/**
 * @author luisa
 *
 */
public class Calculadora {
	
	private static Calculadora calculadora;

	private Calculadora() {

	}

	public static Calculadora getInstance() {//inyeccion de dependencia
		if (calculadora == null) {
			calculadora = new Calculadora();
		}
		return calculadora;
	}
	
	private static final int MAXIMO_HORAS_DIA = 9;
	private static final int HORAS_DIA = 24;

	public double calcularValorAPagar(Factura factura, Vehiculo vehiculo){
		double totalPagar = 0;
		
		LocalDateTime fechaHoraActual = LocalDateTime.now();
		double horasEnParqueadero = ChronoUnit.HOURS.between(factura.getFechaIngreso(), fechaHoraActual);
		int diasParqueo = (int)horasEnParqueadero / HORAS_DIA;
		int horasresiduo = (int) horasEnParqueadero % HORAS_DIA;
		
		double valorHora = TipoVehiculo.MOTO == vehiculo.getTipo() ? Constants.VALOR_HORA_MOTO
				: Constants.VALOR_HORA_CARRO;
		double valorDia = TipoVehiculo.CARRO == vehiculo.getTipo() ? Constants.VALOR_DIA_CARRO
				: Constants.VALOR_DIA_MOTO;
		
		if (horasresiduo >= MAXIMO_HORAS_DIA) {
			diasParqueo += 1;
		} else {
			if(horasresiduo < 1){
				horasresiduo = 1;
			}
			double precioHoras = horasresiduo * valorHora;
			totalPagar += precioHoras;
		}

		if (diasParqueo > 0) {
			double precioDias = diasParqueo * valorDia;
			totalPagar += precioDias;
			
		}
		return totalPagar + sumarValorAdicionalCilindraje(vehiculo);
	}
	
	public double sumarValorAdicionalCilindraje(Vehiculo vehiculo) {
		double valorAdicional = 0;
		if (TipoVehiculo.MOTO == vehiculo.getTipo() && vehiculo.getCilindraje() > Constants.CILINDRAJE_MOTO_MAXIMO) {
			valorAdicional += Constants.VALOR_ADICIONAL_CILINDRAJE_MOTO;
		}
		return valorAdicional;
	}
		
}
