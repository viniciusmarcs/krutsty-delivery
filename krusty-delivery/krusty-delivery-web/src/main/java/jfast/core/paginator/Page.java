package jfast.core.paginator;

import java.io.Serializable;

/**
 * Page Class for General Paging purpouses use with DWR
 * 
 * @author <a href="mailto:vinicius.marcs@gmail.com"> Marcos Vinicius </a>
 * @version 1.0
 */
public class Page implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4357167600499815360L;

	private int pageNumber;
	private int pages;
	private int start;
	private int count;
	private int totalSize;
	private Object[] data;
	private boolean success;

	public Page(Object[] data) {
		this.setData(data);
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean getSuccess() {
		return success;
	}

	public String toString() {
		return "Page: start: " + getStart() + ", count: " + getCount()
				+ ", pages: " + getPages() + ", pageNumber: " + getPageNumber()
				+ ", totalSize: " + getTotalSize() + ", data: " + getData();
	}

}
