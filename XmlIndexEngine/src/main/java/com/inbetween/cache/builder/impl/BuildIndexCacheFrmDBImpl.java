package com.inbetween.cache.builder.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.inbetween.domain.BookValueObject;
import com.inbetween.exception.IndexEngineException;
import com.inbetween.index.IndexCache;

/**
 * The <code>BuildIndexCacheFrmDBImpl</code> class defines methods to build cache the 
 * application cache from the database.
 * this class updates the complete cache replacing the old.
 * Date Feb 25, 2013
 * @author Raghvendra Garg
 */
@Service("buildIndexCacheFrmDBImpl")
public class BuildIndexCacheFrmDBImpl extends BuildIndexCacheAbstractImpl {
	
	private static Log logger = LogFactory.getLog(BuildIndexCacheFrmDBImpl.class);
	
	public List<BookValueObject> getBooks() throws IndexEngineException {
		logger.info("Updating the cache from database");
		return handler.getAllBooks();
	}

	/**
	 * Method sets the cache removes all the previous cached data from the {@link IndexCache} object.
	 */
	public IndexCache getCacheObject() throws IndexEngineException {
		IndexCache cache = IndexCache.getInstance();
//		cache.setAuthorNames(new ArrayList<String>());
//		cache.setBookNames(new ArrayList<String>());
//		cache.setIsbnNumbers(new LinkedHashSet<String>());
		return cache;
	}

	

}
