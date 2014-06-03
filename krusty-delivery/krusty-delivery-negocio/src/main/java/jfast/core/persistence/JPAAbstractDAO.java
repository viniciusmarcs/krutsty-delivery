/**
 * 
 */
package jfast.core.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

/**
 * 
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 30/05/2014
 * @class jfast.core.persistence.JPAAbstractDAO.java
 * @version 1.0
 */
public class JPAAbstractDAO {

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

	// atributo do PersistenceContext
	protected EntityManager em;

	/**
	 * 
	 */
	public JPAAbstractDAO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Inicializa a instância do DAO com um EntityManager obtido via Annotation
	 * (Resource Injection/ Injection Dependecy)
	 * 
	 * @param entityManager
	 *            EntityManager instance
	 * @since 1.0
	 */
	public JPAAbstractDAO(EntityManager entityManager) {
		this.em = entityManager;
	}

	/**
	 * Persist Object
	 * 
	 * @param obj
	 * @throws PersistenceException
	 */
	public Object insert(Object obj) throws PersistenceException {
		try {

			if (logger.isDebugEnabled()) {
				logger.debug("insert - Object.: " + obj);
			}
			em.persist(obj);

			if (logger.isDebugEnabled()) {
				logger.debug("Persisted.: " + obj);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new PersistenceException("Impossível inserir Objeto "
					+ e.getMessage());
		}

		return obj;
	}

	/**
	 * Update Object
	 * 
	 * @param obj
	 * @throws PersistenceException
	 */
	public Object update(Object obj) throws PersistenceException {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("update - Object.: " + obj);
			}
			obj = em.merge(obj);
		} catch (Exception e) {
			logger.error(e);
			throw new PersistenceException("Impossível atualizar objeto",
					e.getCause());
		}
		
		return obj;
	}

	/**
	 * Remove objeto
	 * 
	 * @param obj
	 * @throws PersistenceException
	 */
	public void delete(Object obj) throws PersistenceException {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("update - Object.: " + obj);
			}
			em.remove(obj);
		} catch (Exception e) {
			logger.error(e);
			throw new PersistenceException("Impossível excluir objeto.",
					e.getCause());
		}
	}

}
