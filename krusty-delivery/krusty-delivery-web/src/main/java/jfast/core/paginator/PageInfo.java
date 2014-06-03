/**
 * 
 */
package jfast.core.paginator;

import java.io.Serializable;
import java.util.List;

/**
 * Informações de uma página criada pelo Paginator.
 * 
 * @author vinicius@informanager.com.br - Marcos Vinicius
 * 
 * @date 30/05/2014 : 2:48:27
 * @Class jfast.core.paginator.PageInfo.java
 * @versin 1.0
 */
public class PageInfo implements Serializable {
	private static final long serialVersionUID = -7772914994127416284L;

	public static final int PAGE_NUMBER = 1;

	public static final int PAGE_SIZE = 10;

	private int pageNumber;
	private int pageCount;
	private int totalCount;
	private List data;

	// private int numeroPagina;

	/**
	 * Cria uma nova instância de PageInfo
	 * 
	 * @param pageNumber
	 *            Número da Página (1 até N).
	 * @param pageCount
	 *            Número total de páginas.
	 * @param totalCount
	 *            Número total de registros.
	 * @param SortType
	 *            tipo de Ordenacao ( ASCENDING, DESCENDING )
	 * @param data
	 *            Registros da página.
	 */
	public PageInfo(int pageNumber, int pageCount, int totalCount, List data) {
		this.pageNumber = pageNumber;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
		this.data = data;
	}

	public PageInfo() {
	}

	/**
	 * Retorna o número total de páginas.
	 * 
	 * @return Número total de páginas.
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * Retorna o número da página.
	 * 
	 * @return Número da página.
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * Retorna os registros da página.
	 * 
	 * @return Lista com os registros da página.
	 */
	public List getData() {
		return data;
	}

	/**
	 * Retorna o número total de registros.
	 * 
	 * @return Número total de registros.
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param pageCount
	 *            the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List data) {
		this.data = data;
	}

	// /**
	// * @return the numeroPagina
	// */
	// public int getNumeroPagina() {
	// return numeroPagina;
	// }
	//
	// /**
	// * @param numeroPagina
	// * the numeroPagina to set
	// */
	// public void setNumeroPagina(int numeroPagina) {
	// this.numeroPagina = numeroPagina;
	// }
}
