/*
 * 알파벳 히스토그램
 */
package edu_java;

import java.util.Scanner;

public class hw07Complete 
{
	static Scanner scan = new Scanner(System.in);
	
	static String[] Alpha = new String[26];
	
	static void initAlpha()
	{
		char cr = 'a';
		for(int i=0; i<26; i++)
		{
			Alpha[i] = String.valueOf(cr);
			cr++;
		}
	}
	
	public static void main(String[] args) 
	{
		initAlpha();
		
		System.out.println("영어 문장을 입력하세요");
		
		String inputStr = scan.nextLine();
		String littleStr = inputStr.toLowerCase();
		
		for(int i=0; i<littleStr.length(); i++)
		{	
			if(littleStr.charAt(i)==';' && (littleStr.length()-1) == i)
				break;
			
			for(int j=0; j<26; j++)
				if(littleStr.charAt(i) == Alpha[j].charAt(0))
					Alpha[j] = Alpha[j].concat(" - ");
		}
		
		for(int i=0; i<25; i++)
			System.out.println(Alpha[i]);
	}
}

