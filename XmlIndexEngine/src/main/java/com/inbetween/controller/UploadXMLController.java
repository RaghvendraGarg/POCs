/**
 * 
 */
package com.inbetween.controller;

import java.io.IOException;
import java.util.HashMap;
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

import com.inbetween.cache.builder.impl.BuildIndexCacheFrmFileImpl;
import com.inbetween.domain.FileDetails;
import com.inbetween.exception.IndexEngineException;

/**
 *<code>UploadXMLController</code> This controller handles the Upload and processing of 
 * XML data. 
 * Date Feb 26, 2013
 * @author Raghvendra Garg
 */
@Controller
public class UploadXMLController {
	
	private static Log logger = LogFactory.getLog(UploadXMLController.class);
	
	@Autowired
	private BuildIndexCacheFrmFileImpl buildIndexCache;
	
	@RequestMapping(value = "/uploadXml.do", method=RequestMethod.POST)
	public ModelAndView processXMLData(HttpServletRequest request, HttpServletResponse response,
			FileDetails detail) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		try {
			logger.info("File uploaded " + detail.getXmlFile().getName());
			buildIndexCache.setFileByteArray(detail.getXmlFile().getBytes());
			buildIndexCache.updateCache();
			requestMap.put("succesMesg", "File uploaded successfully, now you can search the contents");
		} catch (IOException e) {
			logger.error("Exception while reading XML data from file", e);
			requestMap.put("errorMesg", "Error while reading file please try again");
		} catch (IndexEngineException e) {
			logger.error("Exception while updating application cache with new details", e);
			requestMap.put("errorMesg", e.getMessage());
		}
		
		return new ModelAndView("Welcome", requestMap);
	}

}
