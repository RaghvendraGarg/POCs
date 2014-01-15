/**
 * 
 */
package com.inbetween.cache;

import java.util.List;

import com.inbetween.domain.BookValueObject;
import com.inbetween.exception.IndexEngineException;
import com.inbetween.index.IndexCache;

/**
 * The <code>CreateIndexCache</code> interface defines the methods to build the application
 * cache for searching purpose. 
 * Date Feb 25, 2013
 * @author Raghvendra Garg
 *
 */
public interface BuildIndexCache {
	
	public void updateCache() throws IndexEngineException;
	
	public List<BookValueObject> getBooks() throws IndexEngineException;
	
	public IndexCache getCacheObject() throws IndexEngineException;
	
}
