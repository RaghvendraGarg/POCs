/**
 * 
 */
package com.inbetween.stringutils.comparator;

/**
 * The <code>StringComparator</code> interface defines a string comparison method.
 * Implementations of this interface will allow strings to be compared with varying degrees 
 * of equality.  
 * 
 * For instance: An implementation can consider two strings to be equal if all words 
 * except one within the two strings are equal. Another implementation may compare two strings to be equal or unequal based on 
 * 
 * Date Feb 25, 2013
 * @author Raghvendra Garg
 *
 */
public interface StringComparator {
	
	/**
	 * Compares two strings.  The implementations class will define the semantics of comparison.
	 * @param testValue Value, that needs to be compared with actual value
	 * @param actualValue Actual value stored in application.
	 * @param isStrictComparison will define whether string has to be compared strictly or partial match 
	 * will also be fine
	 * @return true if values are equals, else false.
	 */
	boolean compare(String testValue, String actualValue, boolean isStrictComparison);
	
	
}
