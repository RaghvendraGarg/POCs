package com.inbetween.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import com.inbetween.cache.BuildIndexCache;
import com.inbetween.cache.builder.impl.BuildIndexCacheFrmFileImpl;
import com.inbetween.constants.IndexEngineConstants;
import com.inbetween.converters.impl.JaxbUnmarshallerImpl;
import com.inbetween.exception.IndexEngineException;
import com.inbetween.xsd.domain.Book;

public class TestUpdateCacheFileImpl implements IndexEngineConstants{

	/**
	 * Feb 25, 2013
	 * @param args void
	 * @author Raghvendra Garg
	 * @throws IndexEngineException 
	 */
	public static void main(String[] args) throws IndexEngineException {
		File file = new File("C:\\Users\\m1014346\\Desktop\\XML_Indexing_Engine\\XML_Indexing_Engine\\Library.xml");
		byte[] fileByteArray = null;
		JaxbUnmarshallerImpl js = new JaxbUnmarshallerImpl();
		InputStream inputStream = null;
		try {
			fileByteArray = FileUtils.readFileToByteArray(file);
			String fileData = new String(fileByteArray);
			String[]  strArray =  StringUtils.substringsBetween(fileData, BOOK_START_TAG, "</book>");
			for (int i = 0; i < strArray.length; i++) {
				StringBuilder stringBuilder = new StringBuilder("<book");
				stringBuilder.append(strArray[i]);
				stringBuilder.append("</book>");
				inputStream = new ByteArrayInputStream(stringBuilder.toString().getBytes());
				Object as = js.unmarshall(inputStream, Book.class);
				System.out.println(as);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*BuildIndexCache buildIndexCache = new BuildIndexCacheFrmFileImpl(fileByteArray);*/
		
		/*try {
			List<Book> books = buildIndexCache.getBooks();
			for (Book book : books) {
				System.out.println(book.getTitle().getValue());
			}
//			buildIndexCache.updateCache();
		} catch (IndexEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
