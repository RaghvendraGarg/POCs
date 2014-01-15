/**
 * 
 */
package com.inbetween.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.inbetween.domain.BookValueObject;
import com.inbetween.domain.SearchCriteria;
import com.inbetween.exception.IndexEngineException;
import com.inbetween.search.Search;

/**
 *<code>SearchController</code> processes any requests coming for
 * searching the details. 
 * Date Feb 26, 2013
 * @author Raghvendra Garg
 */
@Controller
public class SearchController {
	
	private static Log logger = LogFactory.getLog(SearchController.class);
	
	@Autowired
	private Search search;
	
	/**
	 * Method renders the search page
	 * @param request
	 * @param response
	 * @param searchCriteria
	 * @return
	 */
	@RequestMapping(value = "/search.do", method=RequestMethod.GET)
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response, SearchCriteria searchCriteria){
		Map<String, SearchCriteria> map = new HashMap<String, SearchCriteria>(); 
		map.put("searchCriteria", searchCriteria);
		ModelAndView view = new ModelAndView("SearchDetails", map);
		return view;
	}
	
	/**
	 * Method returns the search results based on the criteria
	 * @param request
	 * @param response
	 * @param searchCriteria
	 * @return
	 */
	@RequestMapping(value = "/searchDetails.do", method=RequestMethod.POST)
	public ModelAndView searchDetails(HttpServletRequest request, HttpServletResponse response, SearchCriteria searchCriteria){
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("searchCriteria", searchCriteria);
		try {
			List<BookValueObject> books = search.getSearchDetails(searchCriteria);
			if(!(books != null && !books.isEmpty())){
				map.put("noresults", "No results found, please chcek your search criteria");
			}
			map.put("searchResult", books);
		} catch (IndexEngineException e) {
			logger.error("unable to perform search, please try again later");
			map.put("error", "Error while performing search");
		}
		return new ModelAndView("SearchResults", map);
	}
}
