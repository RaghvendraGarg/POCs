/**
 * 
 */
package com.inbetween.exception;

/**
 * The <code>IndexEngineException</code> class is a generic exception class for the
 * framework that supports exception chaining.
 * Date Feb 25, 2013
 * @author Raghvendra Garg
 *
 */
public class IndexEngineException extends Exception {

	private static final long serialVersionUID = 7330956139258412482L;

	public IndexEngineException() {
		super();
	}

	public IndexEngineException(String message, Throwable cause) {
		super(message, cause);
	}

	public IndexEngineException(String message) {
		super(message);
	}

	public IndexEngineException(Throwable cause) {
		super(cause);
	}
	
	

}
