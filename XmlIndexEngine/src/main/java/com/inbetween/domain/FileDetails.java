/**
 * 
 */
package com.inbetween.domain;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

/**
 * <code>FileDetails</code> This class holds the details of file after uploading.
 * Date Feb 26, 2013
 * @author Raghvendra Garg
 *
 */
public class FileDetails implements Serializable {

	private static final long serialVersionUID = 2697769608536055009L;
	
	private String fileName;
	
	private MultipartFile xmlFile;

	public MultipartFile getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(MultipartFile xmlFile) {
		this.xmlFile = xmlFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
