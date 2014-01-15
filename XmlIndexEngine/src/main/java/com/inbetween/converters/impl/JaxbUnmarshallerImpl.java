/**
 * 
 */
package com.inbetween.converters.impl;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.inbetween.converters.UnMarshaller;
import com.inbetween.exception.IndexEngineException;

/**
 * The <code>JaxbUnmarshallerImpl</code> class provides the jaxb implementation to {@link UnMarshaller}
 * Date Feb 25, 2013
 * @author Raghvendra Garg
 */
@Component("jaxbUnmarshaller")
public class JaxbUnmarshallerImpl implements UnMarshaller {
	
	private static Log logger = LogFactory.getLog(JaxbUnmarshallerImpl.class);

	/* (non-Javadoc)
	 * @see com.inbetween.converters.UnMarshaller#unmarshall(java.lang.Class)
	 */
	@Override
	public Object unmarshall(InputStream is, Class clazz) throws IndexEngineException {
		Object obj = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz); 
			obj = jaxbContext.createUnmarshaller().unmarshal(is);
		} catch (JAXBException e) {
			logger.error("Error while unmarshalling the file" , e);
			throw new IndexEngineException(
					"File does not comply with XSD, please check your XML", e);
		}
		return obj;
	}

}
