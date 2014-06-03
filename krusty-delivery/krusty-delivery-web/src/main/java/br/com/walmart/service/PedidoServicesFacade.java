/**
 * 
 */
package br.com.walmart.service;

import javax.ejb.EJB;

import br.com.walmart.entity.Pedido;
import br.com.walmart.inegocio.IPedidoServices;

/**
 *
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 30/05/2014
 * @class br.com.walmart.service.PedidoServicesFacade.java
 * @version 1.0
 */
public class PedidoServicesFacade {

	@EJB
	public static IPedidoServices pedidoServices;
	
	public void insert( Pedido pedido ){
		pedidoServices.inserirPedido(pedido);
	}
}