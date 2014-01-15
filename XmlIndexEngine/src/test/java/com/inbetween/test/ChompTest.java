package com.inbetween.test;

import java.io.IOException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class ChompTest {

	public static void main(String[] args) throws IOException {

		String str1 = " \n  Being a Dog Is a Full-Time Job\n";
		String str2 = "Chomp 2\r";
		String str3 = "Chomp 3\r\n";
		String str4 = "Chomp 4\n \t \r"; // will remove \r but not \n

		String str1Chomp = StringUtils.chop(str1);
		/*String str2Chomp = StringUtils.chomp(str2);
		String str3Chomp = StringUtils.chomp(str3);*/
		String str4Chomp = StringUtils.chomp(str1, "\n");	
		str1 = str1.trim();
		System.out.println("#1:" + StringEscapeUtils.escapeJava(str1));
		System.out.println("#1:" + StringEscapeUtils.escapeJava(str4Chomp));
		/*if(str4Chomp.equals("Chomp 4\n \t \r")){
			System.out.println(true);
		}*/

		/*System.out.println(str4Chomp);
		System.out.println("Results after chomp (displayed with escaped Java to see special characters)");
		System.out.println("#1:" + StringEscapeUtils.escapeJava(str1Chomp));
		System.out.println("#2:" + StringEscapeUtils.escapeJava(str2Chomp));
		System.out.println("#3:" + StringEscapeUtils.escapeJava(str3Chomp));

		System.out.println("#4:" + StringEscapeUtils.escapeJava(str4Chomp));*/
	}

}