/**
 * 
 */
package br.com.walmart.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.walmart.entity.Pedido;
import br.com.walmart.entity.ProdutosEnum;

import com.google.gson.Gson;

/**
 *
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 03/06/2014
 * @class br.com.walmart.negocio.Produtos.java
 * @version 1.0
 */
public class Produtos implements Serializable{
	
	/**
	 * lista para encapsulamento
	 */
	private List<Produtos> produtos;
	
	/**
	 * auxiliar na paginacao
	 */
	private int totalCount;
	
	public Produtos() {
		// TODO Auto-generated constructor stub
	}

	public Produtos(final List<Produtos> produtos) {
		this.produtos = produtos;
		if( produtos != null && !produtos.isEmpty() ){
			this.totalCount = produtos.size();
		} else {
			this.totalCount = new ArrayList<>().size();
		}
	}

	public List<Produtos> getProdutos() {
		return produtos;
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 
	 * @return JSon( List<Produto> )
	 */
	public String asJson() {
		return new Gson().toJson(this);
	}
}
