/**
 * 
 */
package com.inbetween.converters;

import com.inbetween.exception.IndexEngineException;

/**
 * The <code>Marshaller</code> This interface defines a method to convert a java object
 * into XML.
 * Date Feb 26, 2013
 * @author Raghvendra Garg
 *
 */
public interface Marshaller {
	
	/**
	 * This method marshalls the java object to XML
	 * Feb 26, 2013
	 * @param clazz void
	 * @author Raghvendra Garg
	 * @throws IndexEngineException 
	 */
	public String marshall(Object obj) throws  IndexEngineException;
}
