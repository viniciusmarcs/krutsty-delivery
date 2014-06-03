/**
 * 
 */
package br.com.walmart.vo;

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
public class Pedidos {
	private List<Pedido> pedidos;

	public Pedidos(final List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public String asJson() {
		final Gson gson = new Gson();
		final String json = gson.toJson(this);
		return json;
	}
}
