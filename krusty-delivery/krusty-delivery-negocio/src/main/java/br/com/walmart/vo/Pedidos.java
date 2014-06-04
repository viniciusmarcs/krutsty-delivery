/**
 * 
 */
package br.com.walmart.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.walmart.entity.Pedido;

import com.google.gson.Gson;

/**
 * 
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 03/06/2014
 * @class br.com.walmart.vo.Pedidos.java
 * @version 1.0
 */
public class Pedidos implements Serializable {
	
	/**
	 * lista de Pedidos para
	 * encapsulamento
	 */
	private List<Pedido> pedidos;
	
	/**
	 * Para trabalhar com paginacao
	 */
	private int totalCount;

	public Pedidos(final List<Pedido> pedidos) {
		this.pedidos = pedidos;
		if( pedidos != null && !pedidos.isEmpty() ){
			this.totalCount = pedidos.size();
		} else {
			this.totalCount = new ArrayList<>().size();
		} 
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 
	 * @return JSon( List<Pedido> )
	 */
	public String asJson() {
		return new Gson().toJson(this);
	}
}
