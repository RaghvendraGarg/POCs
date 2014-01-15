/**
 * 
 */
package com.inbetween.stringutils.comparator.impl;

import org.springframework.stereotype.Component;

import com.inbetween.stringutils.comparator.StringComparator;

/**
 * This class compares two strings for equality by comparing for strict
 * case-sensitive match.
 * Date Feb 25, 2013
 * @author Raghvendra Garg
 *
 */
@Component
public class StringMatcherImpl implements StringComparator {

	/**
	 * Method returns true if the testValue and actaulValue matches exactly.
	 */
	public boolean compare(String testValue, String actualValue, boolean isStrictSearch) {
		if(isStrictSearch){
			return testValue.equals(actualValue);
		}else{
			return actualValue.contains(testValue);
		}
	}

}
