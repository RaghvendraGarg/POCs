/**
 * 
 */
package com.inbetween.domain;

import java.io.Serializable;

/**
 * <code>SearchCriteria</code> this class holds the search criteria.
 * @author raghvendra garg
 *
 */
public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = -5553349519250824307L;
	
	private String isbn;
	
	private String where1;
	
	
	private String bookName;
	
	private String where2;
	

	private String authorName;
	
	private String where3;

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the where1
	 */
	public String getWhere1() {
		return where1;
	}

	/**
	 * @param where1 the where1 to set
	 */
	public void setWhere1(String where1) {
		this.where1 = where1;
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the where2
	 */
	public String getWhere2() {
		return where2;
	}

	/**
	 * @param where2 the where2 to set
	 */
	public void setWhere2(String where2) {
		this.where2 = where2;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return the where3
	 */
	public String getWhere3() {
		return where3;
	}

	/**
	 * @param where3 the where3 to set
	 */
	public void setWhere3(String where3) {
		this.where3 = where3;
	}

	

}
