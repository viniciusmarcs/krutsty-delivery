/**
 * 
 */
package br.com.walmart.inegocio;

import javax.ejb.Remote;

import jfast.core.persistence.PersistenceException;
import br.com.walmart.entity.Pedido;
import br.com.walmart.vo.Pedidos;
import br.com.walmart.vo.Produtos;

/**
 *
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 31/05/2014
 * @class br.com.walmart.inegocio.IPedidoServices.java
 * @version 1.0
 */
@Remote
public interface IPedidoServices {

	/**
	 * 
	 * @param pedido
	 */
	public void inserirPedido(Pedido pedido);
	
	/**
	 * Pedidos com Estatus em Aberto
	 * 
	 * @return List<PEDIDO> pedidos
	 * @throws PersistenceException 
	 */
	public Pedidos getPedidosEmAberto() throws PersistenceException;
	
	public Produtos getProdutos() throws PersistenceException;
}
