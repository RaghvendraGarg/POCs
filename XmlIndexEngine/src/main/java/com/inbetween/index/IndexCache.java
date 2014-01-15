/**
 * 
 */
package com.inbetween.index;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.inbetween.exception.IndexEngineException;

/**
 * <code>IndexCache</code> class holds all the indexes required for indexing engine to search the given criteria.
 * also the isbn no is the mapping key between the database and application search cache.
 * Feb 25, 2013
 * @author Raghvendra Garg
 */
@Component
public class IndexCache {
	
	private static IndexCache cache;
	
	/**
	 * Holds the names of books present in library
	 */
	private List<String> bookNames;
	
	/**
	 * Holds the isbn numbers of books present in library
	 */
	private LinkedHashSet<String> isbnNumbers;
	
	/**
	 * Holds the author names of each book separated by "|"
	 */
	private List<String> authorNames;
	
	private Map<Integer, String> indexToIsbnMap;
	
	/**
	 * private constructor to restrict the creation of multiple objects
	 * @throws IndexEngineException 
	 */
	private IndexCache() {
		
	}
	
	/**
	 * Public method to get instance of the IndexGenerator class
	 * Feb 25, 2013
	 * @return IndexGenerator
	 * @throws IndexEngineException 
	 */
	public static IndexCache getInstance() throws IndexEngineException{
		if(cache == null){
			cache = new IndexCache();
		}
		return cache;
	}
	
	
	
	public List<String> getBookNames() {
		return bookNames;
	}

	public void setBookNames(List<String> bookNames) {
		this.bookNames = bookNames;
	}

	public LinkedHashSet<String> getIsbnNumbers() {
		return isbnNumbers;
	}

	public void setIsbnNumbers(LinkedHashSet<String> isbnNumbers) {
		this.isbnNumbers = isbnNumbers;
	}

	public List<String> getAuthorNames() {
		return authorNames;
	}

	public void setAuthorNames(List<String> authorNames) {
		this.authorNames = authorNames;
	}

	/**
	 * @return the indexToIsbnMap
	 */
	public Map<Integer, String> getIndexToIsbnMap() {
		return indexToIsbnMap;
	}

	/**
	 * @param indexToIsbnMap the indexToIsbnMap to set
	 */
	public void setIndexToIsbnMap(Map<Integer, String> indexToIsbnMap) {
		this.indexToIsbnMap = indexToIsbnMap;
	}


}
