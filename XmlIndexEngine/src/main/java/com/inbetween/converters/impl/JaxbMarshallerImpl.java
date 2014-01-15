package com.inbetween.converters.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.inbetween.converters.Marshaller;
import com.inbetween.exception.IndexEngineException;
/**
 * The <code>JaxbMarshallerImpl</code> class provides the jaxb implementation to {@link Marshaller}
 * Date Feb 25, 2013
 * @author Raghvendra Garg
 *
 */
@Component("jaxbMarshaller")
public class JaxbMarshallerImpl implements Marshaller {
	
	private static Log logger = LogFactory.getLog(JaxbMarshallerImpl.class);

	@Override
	public String marshall(Object obj) throws IndexEngineException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String marshallStr = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance();
			jaxbContext.createMarshaller().marshal(obj, outputStream);
			marshallStr = new String(outputStream.toByteArray());
		} catch (JAXBException e) {
			logger.error("Error while unmarshalling the file" , e);
			throw new IndexEngineException(
					"Error while unmarshalling the file", e);
		}finally{
			try {
				outputStream.flush();
			} catch (IOException e) {
				logger.error("Error while unmarshalling the file" , e);
				throw new IndexEngineException(
						"Error while unmarshalling the file", e);
			}
		}
		return marshallStr;
	}

}
