package com.inbetween.test;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
//		Set<String> set3 = new HashSet<String>();
		
		
		
		
		/*set1.add("1243");
		set1.add("147852");
		set1.add("14785214");*/
		
		set2.add("12343");
		set2.add("147852");
		set2.add("124785214");
		
		/*set3.add("12343");
		set3.add("147852");
		set3.add("124785214");*/
		System.out.println(set2.toString().replace("[",	 ""));
		set1.retainAll(set2);
		System.out.println(set1);
		
	}

}
