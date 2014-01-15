/**
 * 
 */
package com.inbetween.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inbetween.constants.IndexEngineConstants;
import com.inbetween.converters.UnMarshaller;
import com.inbetween.domain.BookValueObject;
import com.inbetween.domain.SearchCriteria;
import com.inbetween.exception.IndexEngineException;
import com.inbetween.index.IndexCache;
import com.inbetween.persistence.PersistenceHandler;
import com.inbetween.stringutils.comparator.StringComparator;
import com.inbetween.stringutils.normalizer.StringNormalizer;

/**
 *<code>Search</code> processes the search request based on the criteria recieved 
 * Date Feb 26, 2013
 * @author Raghvendra Garg
 */
@Service
public class Search implements IndexEngineConstants {
	
	@Autowired
	private StringComparator comparator;
	
	@Autowired
	private UnMarshaller unMarshaller;
	
	@Autowired
	StringNormalizer normalizer; 
	
	
	@Autowired
	PersistenceHandler handler;
	
	public List<BookValueObject> getSearchDetails(SearchCriteria criteria) throws IndexEngineException{
		Set<String> isbnNosSearchResult = null;
		Set<String> bookNamesSearchResult = null;
		Set<String> authorNamesSearchResult = null;
		List<BookValueObject> bookObjs = new ArrayList<BookValueObject>();
		IndexCache cache = IndexCache.getInstance();
		if(cache.getIsbnNumbers() != null){
			Set<String> searchResults = (Set<String>) cache.getIsbnNumbers().clone();
			if(StringUtils.isNotBlank(criteria.getIsbn())){
				boolean isStrictSearch = false;
				if(criteria.getWhere1().equalsIgnoreCase(EXACT_MATCH)){
					isStrictSearch = true;
				}
				isbnNosSearchResult = searchInCache(cache.getIsbnNumbers(), normalizer.normalize(criteria.getIsbn()), isStrictSearch, cache.getIndexToIsbnMap());
			}
			
			if(StringUtils.isNotBlank(criteria.getBookName())){
				boolean isStrictSearch = false;
				if(criteria.getWhere2().equalsIgnoreCase(EXACT_MATCH)){
					isStrictSearch = true;
				}
				bookNamesSearchResult = searchInCache(cache.getBookNames(), normalizer.normalize(criteria.getBookName()), isStrictSearch, cache.getIndexToIsbnMap());
			}
			
			if(StringUtils.isNotBlank(criteria.getAuthorName())){
				boolean isStrictSearch = false;
				if(criteria.getWhere3().equalsIgnoreCase(EXACT_MATCH)){
					isStrictSearch = true;
				}
				authorNamesSearchResult = searchAuthorNameInCache(cache.getAuthorNames(), normalizer.normalize(criteria.getAuthorName()), isStrictSearch, cache.getIndexToIsbnMap());
			}
			
			if(isbnNosSearchResult != null){
				searchResults.retainAll(isbnNosSearchResult);
			}
			if(bookNamesSearchResult != null){
				searchResults.retainAll(bookNamesSearchResult);
			}
			if(authorNamesSearchResult != null){
				searchResults.retainAll(authorNamesSearchResult);
			}
			
			if(isbnNosSearchResult == null & bookNamesSearchResult == null & authorNamesSearchResult == null){
				searchResults = null;
			}
			if(searchResults != null && !searchResults.isEmpty()){
				for (String bookId : searchResults) {
					bookObjs.add(handler.getBook(bookId));
				}
				
			}
		}
		return bookObjs;
	}
	
	/**
	 * This method will parse the searched book object and will return the list
	 * @param bookObjs
	 * @return
	 * @throws IndexEngineException 
	 *//*
	private List<Book> populateBookObject(List<BookValueObject> bookObjs) throws IndexEngineException {
		List<Book> books = null;
		InputStream is = null;
		if(bookObjs != null && !bookObjs.isEmpty()){
			books = new ArrayList<Book>();
			for (BookValueObject bookObj : bookObjs) {
				is = new ByteArrayInputStream(bookObj.getBookDetails().getBytes());
				books.add((Book) unMarshaller.unmarshall(is, Book.class));
			}
		}
		return books;
	}*/


	/**
	 * Method returns the list of ISBN codes which matches the criteria 
	 * @param list
	 * @param valueToSearch
	 * @param isStrictSearch
	 * @param map 
	 * @return
	 */
	private Set<String> searchInCache(Collection<String> collection,
			String valueToSearch, boolean isStrictSearch, Map<Integer, String> map) {
		Set<String> result = new HashSet<String>();
		int isbnIndex = 0;
		if (collection != null && !collection.isEmpty()) {
			for (String actualVal : collection) {
				if(comparator.compare(valueToSearch, actualVal, isStrictSearch)){
					result.add(map.get(isbnIndex));
				}
				isbnIndex++;
			}

		}
		return result;
	}
	
	/**
	 * This method searches the author names in the cache 
	 * in case of strict search is set to false then it calls searchInCache method
	 * and in case if strict search is true then two step process is followed
	 * 	1.searched string is checked whether the author name is contained in the '|' separated String.
	 * 	2.if yes then list of author name is cache are splitted by '|' and each string is matched 
	 * 	with the search string. 
	 * @param collection
	 * @param valueToSearch
	 * @param isStrictSearch
	 * @param map
	 * @return
	 */
	private Set<String> searchAuthorNameInCache(Collection<String> collection,
			String valueToSearch, boolean isStrictSearch, Map<Integer, String> map) {
		Set<String> result = new HashSet<String>();
		if(!isStrictSearch){
			return searchInCache(collection, valueToSearch, isStrictSearch, map);
		}else{
			int isbnIndex = 0;
			for (String actualVal : collection) {
				if(comparator.compare(valueToSearch, actualVal, false)){
					String[] authorNamesArray = actualVal.split(AUTHOR_NAMES_SEPERATOR);
					if(authorNamesArray != null && authorNamesArray.length > 0){
						for (int i = 0; i < authorNamesArray.length; i++) {
							if(comparator.compare(valueToSearch, authorNamesArray[i], isStrictSearch)){
								result.add(map.get(isbnIndex));
							}
						}
						
					}
					
				}
				isbnIndex++;
			}
		}
		return result;
		
	}
	
	

}
