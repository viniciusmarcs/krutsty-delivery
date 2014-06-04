/**
 * 
 */
package br.com.walmart.entity;

/**
 *
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 04/06/2014
 * @class br.com.walmart.entity.ProdutosEnum.java
 * @version 1.0
 */
public enum ProdutosEnum {

	PIZZA("Pizza"), 
	FILE_MIGNON("File Mignon"),
	PICANHA("Pinha"),
	PARMEGIANA("File a Parmegiana");
	
	private String descricao;

	ProdutosEnum(final String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return descricao;
	}
	
	public ProdutosEnum[] get(){
		return ProdutosEnum.values();
	}
}
