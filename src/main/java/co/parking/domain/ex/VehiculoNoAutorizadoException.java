package co.parking.domain.ex;

public class VehiculoNoAutorizadoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5463367432927717985L;
	
	
	public VehiculoNoAutorizadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public VehiculoNoAutorizadoException(String message) {
		super(message);
	}
	

}
