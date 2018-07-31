package co.parking.service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -6974683332114669345L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

}
