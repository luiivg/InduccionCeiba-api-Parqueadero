package co.parking.databuilder;



import co.parking.domain.Vehiculo;
import co.parking.domain.enumeration.TipoVehiculo;

public class VehiculoTestDataBuilder {
	
	private long id;
	private TipoVehiculo tipo;
	private String placa;
	private int cilindraje;
	private boolean activo;


	public VehiculoTestDataBuilder setId(long id) {
		this.id = id;
		return this;

	}

	public VehiculoTestDataBuilder setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
		return this;
	}

	public VehiculoTestDataBuilder setPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoTestDataBuilder setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public VehiculoTestDataBuilder setActivo(boolean activo) {
		this.activo = activo;
		return this;
	} 
	
	public Vehiculo buildNew(){
		return new Vehiculo(this.tipo, this.placa, this.cilindraje, this.activo);
	}
	
	public Vehiculo build(){
		return new Vehiculo(this.id, this.tipo, this.placa, this.cilindraje, this.activo);
	}
}
