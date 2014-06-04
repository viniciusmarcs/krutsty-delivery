package jfast.core.persistence;

/**
 * Classe de erro de persistência
 * 
 * @author <a href="mailto: vinicius.marcs@gmail.com"> Marcos Vinicius
 * 
 * @Class jfast.core.database.DatabaseException.PersistenceException
 * @version 1.0
 */
public class PersistenceException extends Exception {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2623771183267770063L;

	public PersistenceException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param String message
	 * @param Throwable cause
	 */
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

}
