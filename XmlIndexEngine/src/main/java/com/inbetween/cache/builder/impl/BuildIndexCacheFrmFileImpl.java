/**
 * 
 */
package com.inbetween.cache.builder.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.inbetween.converters.UnMarshaller;
import com.inbetween.domain.BookValueObject;
import com.inbetween.exception.IndexEngineException;
import com.inbetween.index.IndexCache;
import com.inbetween.stringutils.normalizer.StringNormalizer;
import com.inbetween.xsd.domain.Author;
import com.inbetween.xsd.domain.Book;

/**
 * The <code>BuildCacheFrmFileImpl</code> class defines methods to build cache the 
 * application cache from the file uploaded.
 * it updates the cache object expecting that cache object is always in sync with database.
 * Date Feb 25, 2013
 * @author Raghvendra Garg
 */
@Service("buildIndexCacheFrmFileImpl")
public class BuildIndexCacheFrmFileImpl extends BuildIndexCacheAbstractImpl {
	
	private static Log logger = LogFactory.getLog(BuildIndexCacheFrmFileImpl.class);
	
	private byte[] fileByteArray;
	
	@Autowired
	private UnMarshaller jaxbUnmarshaller;
	
	@Autowired
	@Qualifier("convertToLowerCase")
	public StringNormalizer normalizer;
	
	/**
	 * Parameterized constructor.
	 * @param fileByteArray
	 */
	public BuildIndexCacheFrmFileImpl(byte[] fileByteArray){
		this.fileByteArray = fileByteArray;
	}
	
	/**
	 * Default constructor
	 */
	public BuildIndexCacheFrmFileImpl(){
		
	}
	
	/**
	 * Method unmarshals the list of books from the file upload.
	 */
	public List<BookValueObject> getBooks() throws IndexEngineException {
		InputStream is = null;
		String fileData = new String(fileByteArray);
		String[] xmlBookDtls = null;
		List<BookValueObject> bookList = new ArrayList<BookValueObject>();
		BookValueObject object = null;
		if(StringUtils.isNotBlank(fileData)){
			xmlBookDtls =  StringUtils.substringsBetween(fileData, BOOK_START_TAG, BOOK_END_TAG);
			if(xmlBookDtls != null && xmlBookDtls.length > 0){
				for (int i = 0; i < xmlBookDtls.length; i++) {
					if(StringUtils.isNotBlank(xmlBookDtls[i])){
						StringBuilder stringBuilder = new StringBuilder(BOOK_START_TAG);
						stringBuilder.append(xmlBookDtls[i]);
						stringBuilder.append(BOOK_END_TAG);
						is = new ByteArrayInputStream(stringBuilder.toString().getBytes());
						Book book = (Book) jaxbUnmarshaller.unmarshall(is, Book.class);
						object = populateBookValueList(book);
						object.setBookDetails(stringBuilder.toString());
						bookList.add(object);
					}
				}
			}else{
				throw new IndexEngineException("File does not comply with XSD, please check your XML");
			}
		}
		logger.info("Updating the database with new book details "+bookList.size() );
		handler.persistBooks(bookList);
		return bookList;
	}
	
	/**
	 * Method populates the list of bookValueObjects that will be inserted into the database.
	 * Feb 26, 2013
	 * @param books
	 * @return List<BookValueObject>
	 * @author Raghvendra Garg
	 */
	private BookValueObject populateBookValueList(Book book) {
		BookValueObject bookObj = new BookValueObject();
		if(book != null){
			bookObj.setBookName(normalizer.normalize(book.getTitle().getValue()));
			bookObj.setBookIsbnNumber(normalizer.normalize(book.getIsbn()+""));
			bookObj.setBookName(normalizer.normalize(book.getTitle().getValue()));
			bookObj.setAuthorNames(normalizer.normalize(getPipeSeperatedAuthorNames(book.getAuthor())));
		}
		return bookObj;
	}
	
	/**
	 * This method returns the name of authors seperated by ', '
	 * for e.g. if for any book author names are Raghvendra, James, Regu then
	 * method will return "Raghvendra|James|Regu|"
	 * Feb 26, 2013
	 * @param authors
	 * @return String
	 * @author Raghvendra Garg
	 */
	private String getPipeSeperatedAuthorNames(List<Author> authors){
		StringBuilder names = new StringBuilder();
		if(authors != null && !authors.isEmpty()){
			for (Author author : authors) {
				if(StringUtils.isNotBlank(author.getName()))
				names.append(author.getName().trim()+AUTHOR_NAMES_SEPERATOR);
			}
		}
		return names.toString();
	}

	
	/**
	 * Method returns the {@link IndexCache} object.
	 */
	public IndexCache getCacheObject() throws IndexEngineException {
		return IndexCache.getInstance();
	}

	

	/**
	 * @return the fileByteArray
	 */
	public byte[] getFileByteArray() {
		return fileByteArray;
	}

	/**
	 * @param fileByteArray the fileByteArray to set
	 */
	public void setFileByteArray(byte[] fileByteArray) {
		this.fileByteArray = fileByteArray;
	}
	
	

}
