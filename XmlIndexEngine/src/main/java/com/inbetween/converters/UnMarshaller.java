/**
 * 
 */
package com.inbetween.converters;

import java.io.InputStream;

import com.inbetween.exception.IndexEngineException;

/**
 * The <code>UnMarshaller</code> This interface defines a method to convert a XML
 * into a java object. 
 * Date Feb 26, 2013
 * @author Raghvendra Garg
 */
public interface UnMarshaller {
	
	/**
	 * This method converts the XML string into the java object
	 * Feb 26, 2013
	 * @param clazz void
	 * @author Raghvendra Garg
	 * @throws IndexEngineException 
	 */
	public Object unmarshall(InputStream is, Class clazz) throws IndexEngineException;
}
