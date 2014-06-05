package jfast.com.service.websocket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import jfast.core.persistence.PersistenceException;

import org.apache.log4j.Logger;

import br.com.walmart.service.PedidoServicesFacade;
import br.com.walmart.vo.Pedidos;

/**
 * Classe responsavel pela notificacao do pedidos em aberto atualiza a sessao
 * 
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 31/05/2014
 * @class .AtualizacaoWebSockectServices.java
 * @version 1.0
 */
@Singleton
@ServerEndpoint("/notificacao")
public class AtualizacaoWebSockectServices {

	private Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

	private static Logger logger;

	@EJB
	private PedidoServicesFacade pedidoService;

	static {
		logger = Logger.getLogger(AtualizacaoWebSockectServices.class);
	}

	public void notificar() {
		try {

			if (logger.isDebugEnabled()) {
				logger.debug("notificar sobre pedidos em aberto");
			}

			for (final Session s : queue) {
				if (s.isOpen()) {
					logger.debug("Session is Open.: " + s);
					String pedidos = pedidoService.getListPedidosEmAberto()
							.asJson();
					s.getBasicRemote().sendText(pedidos);
				} else {
					logger.debug("Session is Closed.: " + s);
				}
			}
		} catch (final IOException e) {
			logger.error("Ocorreu um erro ao enviar a mensagem:", e);
		} catch (PersistenceException e) {
			logger.error("Erro de Persistencia:", e);
			e.printStackTrace();
		}
	}

	/**
	 * Inicia Sessao
	 * 
	 * @param javax
	 *            .websocket.Sessionsession
	 */
	@OnOpen
	public void open(final Session session) {
		if (logger.isDebugEnabled()) {
			logger.debug("open session");
		}

		queue.add(session);
	}

	/**
	 * Fecha Sessao
	 * 
	 * @param javax
	 *            .websocket.Session session
	 */
	@OnClose
	public void close(final Session session) {
		if (logger.isDebugEnabled()) {
			logger.debug("close session");
		}
		queue.remove(session);
	}

	/**
	 * Remove uma instancia
	 * 
	 * @param javax
	 *            .websocket.Session session
	 * @param cause
	 */
	@OnError
	public void onError(final Session session, final Throwable cause) {
		if (logger.isDebugEnabled()) {
			logger.debug("Erro session");
		}

		queue.remove(session);
	}

}
