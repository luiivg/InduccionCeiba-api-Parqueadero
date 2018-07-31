package co.parking.dao.ex;

import java.sql.SQLException;

public class DaoException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8719660901892947144L;

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

}
