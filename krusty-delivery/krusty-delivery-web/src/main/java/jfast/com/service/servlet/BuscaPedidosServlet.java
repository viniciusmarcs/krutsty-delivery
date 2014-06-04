package jfast.com.service.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jfast.core.persistence.PersistenceException;
import br.com.walmart.inegocio.IPedidoServices;

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

	@EJB
	private IPedidoServices pedidoService;

	/**
	 * @param HttpServletRequest req
	 * @param HttpServletResponse resp
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		try {
			resp.setStatus(200);
			try {
				resp.getWriter().write(pedidoService.getPedidosEmAberto().asJson());
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (final RuntimeException e) {
			resp.sendError(500, e.getMessage());
		}
	}
}
