package br.com.walmart.vo;

import java.io.Serializable;

/**
 * 
 *
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 04/06/2014
 * @class br.com.walmart.vo.Resultado.java
 * @version 1.0
 */
public class Resultado implements Serializable {

	private static final long serialVersionUID = 2170294401108447645L;
	private boolean success;
	private String message;

	public Resultado() {	
	}

	public Resultado(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}