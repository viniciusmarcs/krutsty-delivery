package jfast.core.database;

import java.sql.SQLException;

/**
 * Classe responsavel pela falhas causadas por acesso a banco
 * 
 * @author vinicius@informanager.com.br - Marcos Vinicius
 * 
 * @date 03/06/2014 : 15:14:16
 * 
 * @Class jfast.core.database.DatabaseException.java
 * 
 * @versin 1.0
 */
public class DatabaseException extends SQLException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7746369932178485165L;

	// causa da exceção
	private Throwable cause;

	/**
     * 
     */
	public DatabaseException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param reason
	 * @param SQLState
	 */
	public DatabaseException(String reason, String SQLState) {
		super(reason, SQLState);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param reason
	 * @param SQLState
	 * @param vendorCode
	 */
	public DatabaseException(String reason, String SQLState, int vendorCode) {
		super(reason, SQLState, vendorCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da Exceção com mensagem
	 * 
	 * @param message
	 */
	public DatabaseException(String message) {
		super(message);
	}

	/**
	 * Construtor da exceção com mensagem e causa
	 * 
	 * @param message
	 * @param cause
	 */
	public DatabaseException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
	}

	/**
	 * @return cause - se houver
	 */
	public Throwable getCause() {
		return cause;
	}

}
