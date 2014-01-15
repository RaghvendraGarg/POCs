package com.inbetween.stringutils.normalizer;
/**
 * The <code>StringNormalizer</code> interface defines a string normalization method.
 * Implementations of this interface will transform the input string to make them more
 * suitable for comparison purposes.
 * 
 * For instance: An implementation may convert given input string to lower case to enable
 * case insensitive comparisons.  Another implementation may remove non-alphabetic characters. 
 * 
 * Date Feb 25, 2013
 * @author Raghvendra Garg
 */
public interface StringNormalizer {
	
	/**
	 * Normalizes a string for comparison purposes 
	 * @param input Input string
	 * @return Normalized string
	 */
	String normalize(String input);

}
