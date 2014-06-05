package jfast.com.service.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jfast.core.persistence.PersistenceException;

import org.apache.log4j.Logger;

import br.com.walmart.inegocio.IPedidoServices;
import br.com.walmart.vo.Pedidos;

/**
 * 
 *
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 04/06/2014
 * @class jfast.com.service.servlet.BuscaPedidosServlet.java
 * @version 1.0
 */
@WebServlet(urlPatterns="/pedidos", loadOnStartup = 1)
public class BuscaPedidosServlet extends HttpServlet {

	private static final long serialVersionUID = -5883823536178568608L;
	
	// atributo do Logger
	private static Logger logger;

	@EJB
	private IPedidoServices pedidoService;
	
	// inicialização do Logger
	static {
		try {
			logger = Logger.getLogger(BuscaPedidosServlet.class.getName());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * @param HttpServletRequest req
	 * @param HttpServletResponse resp
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		if( logger.isDebugEnabled()){
			logger.debug("doGet - GetPedidos Em Aberto para Notificacao: ");
		}
		Pedidos pedidos = null;
		try {
			resp.setStatus(200);
			try {
				pedidos = pedidoService.getPedidosEmAberto();
				if( logger.isDebugEnabled() ){
					logger.debug("Pedidos 1 - : " + pedidos );
				}
				if( pedidos != null ){
					resp.getWriter().write( pedidos.asJson());					
				}

				if( logger.isDebugEnabled() ){
					logger.debug("Pedidos 2 - : " + pedidos );
				}
			} catch (PersistenceException e) {
				logger.error("Erro, não foi possível pesquisar pedidos: " + pedidos );
				e.printStackTrace();
			}
		} catch (final RuntimeException e) {
			logger.error("Erro, " + e.getMessage() );
			resp.sendError(500, e.getMessage());
		}
	}
}
