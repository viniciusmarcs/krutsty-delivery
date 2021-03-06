package jfast.com.service.servlet;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jfast.com.service.websocket.AtualizacaoWebSockectServices;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.walmart.entity.Pedido;
import br.com.walmart.inegocio.IPedidoServices;
import br.com.walmart.vo.Resultado;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/pedido", loadOnStartup = 1)
public class EfetuarPedidoServlet extends HttpServlet {

	private static final long serialVersionUID = -3309626245848149687L;

	private static Logger logger;
	
	@EJB
	private IPedidoServices pedidoService;

	@EJB
	private AtualizacaoWebSockectServices atualizacaoWebSocket;
	
	static {
		logger = Logger.getLogger(EfetuarPedidoServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Efetuar Pedido");
		}
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		if (session != null && StringUtils.isEmpty(session.getId())) {
			logger.info("HttpSession is OK.: " );
			final Gson gson = new Gson();
			try {
				final InputStreamReader inputStreamReader = new InputStreamReader(
						req.getInputStream());

				logger.info("InputStreamReader.: " + inputStreamReader);
				final Pedido pedido = gson.fromJson(inputStreamReader,
						Pedido.class);
				if (logger.isDebugEnabled()) {
					logger.debug("Get Pedido from Json -: " + pedido);
				}
				
				if( pedido != null ){
					pedidoService.inserirPedido(pedido);	
				} else {
					
				}
				
				atualizacaoWebSocket.notificar();

				resp.setStatus(200);
				resp.getWriter().write(
						gson.toJson(new Resultado(true,
								"Pedido realizado com exito")));
			} catch (final RuntimeException e) {
				resp.sendError(500, e.getMessage());
			}
		}

	}
}
