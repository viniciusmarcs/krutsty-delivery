/**
 * 
 */
package br.com.walmart.service;

import javax.ejb.EJB;

import jfast.com.service.websocket.AtualizacaoWebSockectServices;
import jfast.core.persistence.PersistenceException;
import br.com.walmart.entity.Pedido;
import br.com.walmart.inegocio.IPedidoServices;
import br.com.walmart.vo.Pedidos;
import br.com.walmart.vo.Produtos;

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
	
	@EJB
	private AtualizacaoWebSockectServices atualizacaoWebSocket;
	
	public void insert( Pedido pedido ){
		//pedidoServices.inserirPedido(pedido);
		atualizacaoWebSocket.notificar();
	}
	
	/**
	 * Pedidos em Aberto
	 * @return List<Pedido> pedidos
	 * @throws PersistenceException 
	 */
	public Pedidos getListPedidosEmAberto() throws PersistenceException{
		return pedidoServices.getPedidosEmAberto();
	}
	
	public Produtos getListProdutos() throws PersistenceException{
		return pedidoServices.getProdutos();
	}
}
