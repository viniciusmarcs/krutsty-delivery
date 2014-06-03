/**
 * 
 */
package br.com.walmart.inegocio;

import br.com.walmart.entity.Pedido;
import br.com.walmart.vo.Pedidos;

/**
 *
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 31/05/2014
 * @class br.com.walmart.inegocio.IPedidoServices.java
 * @version 1.0
 */
public interface IPedidoServices {

	/**
	 * 
	 * @param pedido
	 */
	public void inserirPedido(final Pedido pedido);
	
	/**
	 * Pedidos com Estatus em Aberto
	 * 
	 * @return List<PEDIDO> pedidos
	 */
	public Pedidos pedidosEmAberto();
}
