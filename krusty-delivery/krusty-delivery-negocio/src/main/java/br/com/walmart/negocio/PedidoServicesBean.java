/**
 * 
 */
package br.com.walmart.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.walmart.dao.PedidoDAO;
import br.com.walmart.entity.Pedido;
import br.com.walmart.entity.StatusPedido;
import br.com.walmart.inegocio.IPedidoServices;
import br.com.walmart.vo.Pedidos;

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

	/**
	 * 
	 */
	public PedidoServicesBean() {
		// TODO Auto-generated constructor stub
	}

	public void inserirPedido(final Pedido pedido) {
		PedidoDAO dao = new PedidoDAO( em );
		dao.insert(pedido);
	}

	/**
	 * 
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Pedidos pedidosEmAberto() {
		PedidoDAO dao = new PedidoDAO(em);

		final List<Pedido> pedidos = dao.select("SELECT p FROM PEDIDO p", 0, null, false);
		return new Pedidos(pedidos);
	}
//
//	public void atender(final Long id, final String atendente) {
//		final Pedido pedido = entityManager.find(Pedido.class, id);
//		Preconditions.checkState(pedido.getStatus().equals(StatusPedido.N.name() ),
//				"Pedido ja em atendimento");
//		p.setStatus(StatusPedido.A.name());
//		p.setDataAtendimento(new Date());
//		p.setAtendente(atendente);
//	}

}
