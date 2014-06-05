/**
 * 
 */
package br.com.walmart.negocio;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jfast.core.persistence.JPAAbstractDAO;
import jfast.core.persistence.PersistenceException;

import org.apache.log4j.Logger;

import br.com.walmart.dao.PedidoDAO;
import br.com.walmart.entity.Pedido;
import br.com.walmart.entity.Produto;
import br.com.walmart.inegocio.IPedidoServices;
import br.com.walmart.vo.Pedidos;
import br.com.walmart.vo.Produtos;

/**
 * 
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 31/05/2014
 * @class br.com.walmart.negocio.PedidoServicesBean.java
 * @version 1.0
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PedidoServicesBean implements IPedidoServices {

	@PersistenceContext
	private EntityManager em;

	// atributo do Logger
	private static Logger logger;
	
	// inicialização do Logger
	static {
		try {
			logger = Logger.getLogger(JPAAbstractDAO.class.getName());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * 
	 */
	public PedidoServicesBean() {
		// TODO Auto-generated constructor stub
	}

	public void inserirPedido(Pedido pedido) {
		try {
			PedidoDAO dao = new PedidoDAO(em);
			logger.info("PedidoDAO - inserirPedido.: " + dao);
			dao.insert(pedido);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * encapsula um List<Pedido> em um objeto Pedidos
	 * 
	 * @return ( Pedidos ) List<Pedido> pedidos
	 * @throws PersistenceException 
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Pedidos getPedidosEmAberto() throws PersistenceException {
		PedidoDAO dao = new PedidoDAO(em);
		logger.info("PedidoDAO - getPedidosEmAberto.: " + dao);
		HashMap params = new HashMap();
		params.put("novo", "Novo");
		params.put("atendimento", "Em atendimento");
		final List<Pedido> pedidos = dao.select("Pedido.buscarNovosPedidosAberto", 0,
				params, true);
		if(logger.isDebugEnabled() ){
			logger.debug("GetPedidos: " + pedidos);
		}
		return new Pedidos(pedidos);
	}

	/**
	 * encapsula um List<Pedido> em um objeto Pedidos
	 * 
	 * @return ( Produtos ) List<Produtos> produtos
	 * @throws PersistenceException 
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Produtos getProdutos() throws PersistenceException {
		PedidoDAO dao = new PedidoDAO(em);
		logger.info("PedidoDAO - getProdutos.: " + dao);
		final List<Produto> produtos = dao.select("SELECT p FROM Produto p",
				0, null, false);

		return new Produtos(produtos);
	}

}
