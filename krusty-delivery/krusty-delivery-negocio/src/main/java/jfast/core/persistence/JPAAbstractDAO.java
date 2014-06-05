/**
 * 
 */
package jfast.core.persistence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

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

	// inicializa��o do Logger
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
	 * Inicializa a inst�ncia do DAO com um EntityManager obtido via Annotation
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
			throw new PersistenceException("Imposs�vel inserir Objeto "
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
			throw new PersistenceException("Imposs�vel atualizar objeto",
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
			throw new PersistenceException("Imposs�vel excluir objeto.",
					e.getCause());
		}
	}

	/**
	 * Cria uma Query baseada nos padr�es JPA
	 * 
	 * @param jpaQuery
	 *            - uma query no padr�o JPQL
	 * @param startRecord
	 *            - n�mero do registro inicial ( 0 a todos )
	 * @param maxResults
	 *            - n�mero m�ximo de resultados ( 0 - todos )
	 * @param named
	 *            - usado quando jpaQuery for uma NamedQuery definida no Entity
	 * @throws Exception
	 *             - se a cria��o da Query falhar
	 * @since 1.0
	 */
	public final Query createQuery(String jpaQuery, int startRecord,
			int maxResults, boolean named) throws Exception {
		if (jpaQuery == null) {
			throw new PersistenceException(
					"Imposs�vel realizar opera��o de persist�ncia, consulta inv�lida");
		}
		if (em == null || !em.isOpen()) {
			throw new PersistenceException(
					"Imposs�vel realizar opera��o de persist�ncia, n�o conectado");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Using jpaQuery: " + jpaQuery + ", start: "
					+ startRecord + ", maxResults: " + maxResults + ", named: "
					+ named);
		}
		Query query = (named ? em.createNamedQuery(jpaQuery) : em
				.createQuery(jpaQuery));

		query.setFlushMode(FlushModeType.AUTO);
		if (logger.isDebugEnabled()) {
			logger.debug("Created query: " + query);
		}
		return query;
	}

	/**
	 * Executa uma JPQL de Consulta de dados de objetos em Lista
	 * 
	 * @param jpaQuery
	 *            - uma query no padr�o JPQL
	 * @param maxResults
	 *            - n�mero m�ximo de resultados ( 0 - todos )
	 * @param parameters
	 *            - parametros de dados
	 * @param named
	 *            - usado quando jpaQuery for uma NamedQuery definida no Entity
	 * @return list - uma lista de objetos pesquisados
	 * @throws PersistenceException
	 *             - se a consulta da Query falhar
	 * @since 1.0
	 */
	public final List select(String jpaQuery, int maxResults,
			HashMap parameters, boolean named) throws PersistenceException {
		List objects = null;
		long init = System.currentTimeMillis();
		try {
			Query query = createQuery(jpaQuery, 0, maxResults, named);
			fillParameters(query, parameters);
			objects = query.getResultList();
			if( objects != null && objects.size() > 0 ){
				if( logger.isDebugEnabled() ){
					logger.debug("Results: " + objects );
				}
			}
		} catch (Exception e) {
			logger.error("Unable to execute query: " + jpaQuery, e);
			throw new PersistenceException("Imposs�vel consultar dados", e);
		} 
		
		return objects;
	}

	/**
	 * M�todo auxiliar para gera��o de parametros de JPQL
	 * 
	 * @param query
	 * @param parameters
	 */
	private void fillParameters(Query query, HashMap<String, Object> parameters) {
		if (parameters != null) {
			Iterator<String> it = parameters.keySet().iterator();
			while (it.hasNext()) {
				String param = it.next();
				Object value = parameters.get(param);
				query.setParameter(param, value);
				if (logger.isDebugEnabled()) {
					logger.debug("Query.setParameter param: " + param
							+ ", value: " + value);
				}
			}
		}
	}

}
