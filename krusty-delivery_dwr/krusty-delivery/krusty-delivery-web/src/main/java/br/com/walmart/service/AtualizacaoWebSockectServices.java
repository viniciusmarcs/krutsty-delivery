package br.com.walmart.service;
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

import org.apache.log4j.Logger;

/**
 * Classe responsavel pela notificacao do pedidos em aberto
 * 
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 31/05/2014
 * @class .AtualizacaoWebSockectServices.java
 * @version 1.0
 */
// indica um session bean com inversao de controle e dependencia
// passando a responsabilidade de gerir e ser uma unica instancia
// indica que se comporta como um servidor websocket
@Singleton
@ServerEndpoint("/notificacao") // notificacao indica qual endereco http atende
public class AtualizacaoWebSockectServices {
	
	private Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
	
	private static Logger logger;
	
	@EJB
	private PedidoServicesFacade pedidoService;
	
	static{
		logger = Logger.getLogger(AtualizacaoWebSockectServices.class);
	}

	public void notificar() {
		try {
			
			if( logger.isDebugEnabled() ){
				logger.debug("notificar sobre pedidos em aberto");
			}
			
			for (final Session s : queue) {
				if (s.isOpen()) {
					s.getBasicRemote().sendText(
							pedidoService.getListPedidosEmAberto().asJson());
				}
			}
		} catch (final IOException e) {
			logger.error("Ocorreu um erro ao enviar a mensagem:", e);
		}
	}

	@OnOpen
	public void open(final Session session) {
		if( logger.isDebugEnabled() ){
			logger.debug("open session");
		}
		
		queue.add(session);
	}

	@OnClose
	public void close(final Session session) {
		if( logger.isDebugEnabled() ){
			logger.debug("close session");
		}
		queue.remove(session);
	}

	@OnError
	public void onError(final Session session, final Throwable cause) {
		if( logger.isDebugEnabled() ){
			logger.debug("Erro session");
		}

		queue.remove(session);
	}

}
