package jfast.core.persistence;

import jfast.core.database.DatabaseException;

/**
 * Classe de erro para falha de conexão do EntityManager da JPA
 * 
 * @author <a href="mailto: vinicius.marcs@gmail.com"> Marcos Vinicius
 * @Class jfast.core.database.DatabaseException.PersistenceConectionException
 * @version 1.0
 */
public class PersistenceConectionException extends DatabaseException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8628878298821972116L;

	public PersistenceConectionException(String message) {
		super(message);
	}

	public PersistenceConectionException(String message, Throwable cause) {
		super(message, cause);
	}

}
