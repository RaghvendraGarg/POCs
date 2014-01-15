package com.inbetween.domain;

import java.io.Serializable;

public class BookValueObject implements Serializable {

	private static final long serialVersionUID = -8139425091467521341L;
	
	private String bookName;
	
	private String bookIsbnNumber;
	
	private String authorNames;
	
	private String bookDetails;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookIsbnNumber() {
		return bookIsbnNumber;
	}

	public void setBookIsbnNumber(String bookIsbnNumber) {
		this.bookIsbnNumber = bookIsbnNumber;
	}

	public String getAuthorNames() {
		return authorNames;
	}

	public void setAuthorNames(String authorNames) {
		this.authorNames = authorNames;
	}

	public String getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(String bookDetails) {
		this.bookDetails = bookDetails;
	}
	

}
