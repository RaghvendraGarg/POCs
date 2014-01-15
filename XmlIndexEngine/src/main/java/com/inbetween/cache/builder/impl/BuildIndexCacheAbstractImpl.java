package com.inbetween.cache.builder.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.inbetween.cache.BuildIndexCache;
import com.inbetween.constants.IndexEngineConstants;
import com.inbetween.domain.BookValueObject;
import com.inbetween.exception.IndexEngineException;
import com.inbetween.index.IndexCache;
import com.inbetween.persistence.PersistenceHandler;

/**
 * <code>BuildIndexCacheAbstractImpl</code> this class provides the abstract implementation of
 * {@link BuildIndexCache}.
 * Date Feb 25, 2013
 * @author Raghvendra Garg
 */
public abstract class BuildIndexCacheAbstractImpl implements BuildIndexCache, IndexEngineConstants {
	
	private static Log logger = LogFactory.getLog(BuildIndexCacheAbstractImpl.class);
	
	@Autowired
	public PersistenceHandler handler;
	
	/**
	 * This method updates the cache object in memory from the object received from 
	 * getBooksObject
	 * marking this method as synchronized, hence making sure that only one thread updates the cache at a time
	 */
	public synchronized void  updateCache() throws IndexEngineException {
		logger.info("Updating the cache");
		List<BookValueObject> books = getBooks();
		List<String> authorNames = new ArrayList<String>();
		List<String> bookNames = new ArrayList<String>();
		if(books != null && !books.isEmpty()){
			IndexCache cache = IndexCache.getInstance();
			if(cache.getIsbnNumbers() == null){
				cache.setIsbnNumbers(new LinkedHashSet<String>());
			}
			for (BookValueObject book : books) {
				if(book !=null){
					// making sure that the book does not exist in our database and cache already
					if(cache.getIsbnNumbers().add(book.getBookIsbnNumber())){
						bookNames.add(book.getBookName());
						authorNames.add(book.getAuthorNames());
					}
				}
			}
			if(cache.getAuthorNames() == null){
				cache.setAuthorNames(authorNames);
			}else{
				cache.getAuthorNames().addAll(authorNames);
			}
			if(cache.getBookNames() == null){
				cache.setBookNames(bookNames);
			}else{
				cache.getBookNames().addAll(bookNames);
			}
			updateIsbnIndexMap(cache);
		}
		logger.info("Cache updated succesfully");
	}
	
	/**
	 * Method updates the isbn map index into the cache object
	 * @param cache
	 */
	private void updateIsbnIndexMap(IndexCache cache) {
		Map<Integer, String> isbnMap = new HashMap<Integer, String>();
		Set<String> isbnList = cache.getIsbnNumbers();
		int isbnIndex = 0;
		for (String isbn : isbnList) {
			isbnMap.put(isbnIndex++, isbn);
		}
		cache.setIndexToIsbnMap(isbnMap);
	}
	

}
