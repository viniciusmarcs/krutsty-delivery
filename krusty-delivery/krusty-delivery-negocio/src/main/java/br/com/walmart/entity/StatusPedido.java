/**
 * 
 */
package br.com.walmart.entity;

/**
 * 
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 30/05/2014
 * @class br.com.walmart.entity.StatusPedido.java
 * @version 1.0
 */
public enum StatusPedido {

	N("Novo"), 
	A("Em atendimento"),
	F("Finalizado"),
	C("Cancelado"), 
	E("Entregue");
	
	private String descricao;

	StatusPedido(final String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return descricao;
	}

}
