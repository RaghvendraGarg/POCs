/**
 * 
 */
package com.inbetween.persistence;

/**
 * <code>IndexEngineQueries</code> this class holds the query constants 
 * for accessing index engine data base.
 * Date Feb 26, 2013
 * @author Raghvendra Garg
 *
 */
public abstract class IndexEngineQueries {

	private static StringBuilder allBookQuery = new StringBuilder("SELECT BOOK_ID, BOOK_NAME, AUTHOR_NAMES FROM LIBRARY_DETAILS");
	
	
	private static StringBuilder bookQuery = new StringBuilder("SELECT BOOK_ID,  BOOK_NAME, AUTHOR_NAMES, BOOK_DTLS FROM LIBRARY_DETAILS WHERE BOOK_ID = ?");
	
	
	private static StringBuilder booksQuery = new StringBuilder("SELECT BOOK_ID, BOOK_DTLS FROM LIBRARY_DETAILS WHERE BOOK_ID IN (?)");
	
	
	private static StringBuilder insertBook = new StringBuilder("INSERT  INTO LIBRARY_DETAILS(BOOK_ID, BOOK_NAME, AUTHOR_NAMES, BOOK_DTLS)  VALUES(?,?,?,?)");


	public static String getAllBookQuery() {
		return allBookQuery.toString();
	}


	public static String getBookQuery() {
		return bookQuery.toString();
	}


	public static String getInsertBook() {
		return insertBook.toString();
	}
	
	public static String getBooksQuery() {
		return booksQuery.toString();
	}
	
}
