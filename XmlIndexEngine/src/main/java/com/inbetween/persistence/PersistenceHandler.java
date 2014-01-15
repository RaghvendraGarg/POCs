/**
 * 
 */
package com.inbetween.persistence;

import java.util.List;
import java.util.Set;

import com.inbetween.domain.BookValueObject;
import com.inbetween.exception.IndexEngineException;


/**
 * PersistenceHandler is an interface and provides all the persistence API to the
 * Provider. This interface is implemented by specific technology Handlers like
 * Hibernate, Hadoop, iBatis, JDBC etc. Each of the implementation is specific to a
 * technology framework.
 * Date Feb 26, 2013
 * @author Raghvendra Garg
 *
 */
public interface PersistenceHandler {
	
	/**
	 * Method returns all the books available
	 * Feb 26, 2013
	 * @return List<BookValueObject>
	 * @author Raghvendra Garg
	 */
	public List<BookValueObject> getAllBooks();
	
	
	/**
	 * Method returns the specific book asked for the given id
	 * Feb 26, 2013
	 * @param isbnNumber
	 * @return Book
	 * @author Raghvendra Garg
	 */
	public BookValueObject getBook(String id);
	
	/**
	 * Method persists the given list of books in DB
	 * Feb 26, 2013
	 * @param books void
	 * @author Raghvendra Garg
	 * @throws IndexEngineException 
	 */
	public void persistBooks(List<BookValueObject> books) throws IndexEngineException;
	
	/**
	 * Method returns the list of books with given IDs
	 * Feb 26, 2013
	 * @param iDs
	 * @author Raghvendra Garg
	 */
	public List<BookValueObject> getBooks(Set<String> iDs);
	
}
