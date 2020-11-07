/*
 * 단어 뜻 맞추기
 */

package edu_java;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

class Word
{
	HashMap<String, String> hashWord = new HashMap<>(); 
	
	String ENG;
	String KOR;
	
	Word(String Eng, String Kor)
	{
		this.ENG = Eng;
		this.KOR = Kor;
		hashWord.put(Eng, Kor);
	}
}


public class hw10Complete 
{
	static Vector<Word> word =new Vector<Word>();
	static Random ran = new Random();
	static Scanner scan  = new Scanner(System.in);
	
	static int Qrand;
	static int Aran1;
	static int Aran2;
	static int Aran3;
	
	public static void main(String[] args) 
	{
		word.add(new Word("eyes","눈"));
		word.add(new Word("nose","코"));
		word.add(new Word("mouse","입"));
		word.add(new Word("legs","다리"));
		word.add(new Word("hands","손"));
		word.add(new Word("heap","엉덩이"));
		word.add(new Word("ears","귀"));
		word.add(new Word("head","머리"));
		
		Qrand = ran.nextInt((word.size()-1));
		Aran1=ran.nextInt((word.size()-1));
		Aran2=ran.nextInt((word.size()-1));
		Aran3=ran.nextInt((word.size()-1));
		
		System.out.println("사전의 등록된 단어 수 : "+word.size());
		
		ranNum(); //랜덤 숫자 중복 방지
		
		System.out.println(word.get(Qrand).ENG + "??");
		System.out.print("1."+word.get(Aran1).KOR + "   ");
		System.out.print("2."+word.get(Aran2).KOR + "   ");
		System.out.print("3."+word.get(Aran3).KOR + "   ");
		System.out.println("4. 답이없다.");
		
		System.out.print("정답은 ?? ");
		int result = scan.nextInt();
		
		if(result==1)
		{
			if(word.get(Qrand).KOR.equals(word.get(Aran1).KOR))
			{
				System.out.println("정답");
				return;
			}
		}
		
		else if(result==2)
		{
			if(word.get(Qrand).KOR.equals(word.get(Aran2).KOR))
			{
				System.out.println("정답");
				return;
			}
		}
		else if(result==3)
		{
			if(word.get(Qrand).KOR.equals(word.get(Aran3).KOR))
			{
				System.out.println("정답");
				return;
			}
		}
		else if(result==4)
		{
			if((word.get(Qrand).KOR.equals(word.get(Aran1).KOR)==false)
				&& (word.get(Qrand).KOR.equals(word.get(Aran2).KOR)==false)
				&& (word.get(Qrand).KOR.equals(word.get(Aran3).KOR)==false))
				{
					System.out.println("정답");
					return;
				}
		}
		
		System.out.println("틀림");
				
	}

	static void ranNum()
	{
		while(true)
		{
			if(Aran1==Aran2)
				Aran2 = ran.nextInt((word.size()-1));
		
			if(Aran2 == Aran3)
				Aran3 = ran.nextInt((word.size()-1));
			
			if(Aran3 == Aran1)
				Aran3 = ran.nextInt((word.size()-1));
			
			if(Aran1 != Aran2 && Aran2 != Aran3 && Aran1 != Aran3)
				return;
		}
	}
}
