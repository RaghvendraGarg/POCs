/**
 * 
 */
package com.inbetween.stringutils.normalizer.impl;

import org.springframework.stereotype.Component;

import com.inbetween.stringutils.normalizer.StringNormalizer;

/**
 * An implementation of <code>StringNormalizer</code> interface, this class
 * converts the input string to lower case. Date Feb 25, 2013
 * 
 * @author Raghvendra Garg
 * 
 */
@Component("convertToLowerCase")
public class ConvertToLowerCaseNormalizer implements StringNormalizer {
	
	/**
	 * Method converts the string to all lower case.
	 */
	public String normalize(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Input parameter is null");
		}
		return input.toLowerCase().trim();
	}

}
