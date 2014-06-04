/**
 * 
 */
package br.com.walmart.dao;

import javax.persistence.EntityManager;

import jfast.core.persistence.JPAAbstractDAO;
import jfast.core.persistence.PersistenceException;

/**
 * Classe responsavel pela Persistencia de Objetos
 * 
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 31/05/2014
 * @class br.com.walmart.dao.PedidoDAO.java
 * @version 1.0
 */
public class PedidoDAO extends JPAAbstractDAO {

	/**
	 * 
	 */
	public PedidoDAO(EntityManager em) {
		super(em);
	}

	/**
	 * Persist Object
	 * 
	 * @param obj
	 * @throws PersistenceException
	 */
	public Object insert(Object obj) throws PersistenceException {
		try {

			obj = super.insert(obj);
		} catch (Exception e) {
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
			obj = super.update(obj);
		} catch (Exception e) {
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
			super.delete(obj);
		} catch (Exception e) {
			throw new PersistenceException("Imposs�vel excluir objeto.",
					e.getCause());
		}
	}

}
