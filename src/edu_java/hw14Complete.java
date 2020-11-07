/*
 * hangman
 */
package edu_java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Answer
{
	StringBuffer underWord = new StringBuffer();
	StringBuffer fullWord = new StringBuffer();
	StringBuffer Qword = new StringBuffer();
	
}

public class hw14Complete 
{
	static Answer A = new Answer();
	
	public static void main(String[] args) 
	{
		List<String> wordList = new ArrayList<>();

		fileInput(wordList);

		writeWord(wordList);
		
		checkWord();
		
	}
	
	static void fileInput(List<String> List)
	{
		Scanner scan = new Scanner(System.in);
		String directory = "D:\\words.txt";	
		
		try
		{
			scan = new Scanner(new File(directory));
			
			while(scan.hasNextLine())
			{
				List.add(scan.nextLine());	
			}
			scan.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("파일 입력받을 때 오류 발생 : " + e.getMessage());
		}
		System.out.println("단어의 수 : " + List.size());	

	}

	static void writeWord(List<String> wordsList)
	{
		Random rand = new Random();
		
		int QselectNum= rand.nextInt(wordsList.size());		// QselectNum : 리스트 사이즈보다 작은 수의 랜덤 넘버 저장
		
		int underbarCnt = (rand.nextInt(3)+1);			// underbarCnt : 언더바가 몇개 있을건지 1~3개
		int underbarNum;
		int cnt=0;	
		
		do
		{
			 A.Qword.append(wordsList.get(QselectNum));		//리스트 안에 있는 랜덤 문자 선택해서 Qword에 저장
		}while(A.Qword.length() < 3);									//선택된 Qword가 짧으면 다시 선택
		
		
		A.fullWord.append(A.Qword);
	
		while(cnt < underbarCnt)
		{
			underbarNum = rand.nextInt(A.Qword.length());		//선택된 Qword에 몇번째에 _를 넣을건지
			A.underWord.append(underbarNum);
			A.Qword.setCharAt(underbarNum, '_');
			cnt++;
		}
	}

	static void checkWord()
	{
		Scanner scanf = new Scanner(System.in);
		int cnt=0;
		int Max = 5;	//최대 횟수 조절
		String input;
		
		while(cnt<Max)	
		{
			System.out.println("문제는 : " + A.Qword);
			System.out.print("알파벳 입력 >> ");
			input = scanf.nextLine();
			
			for(int i=0; i<A.underWord.length(); i++)
			{
				int underNum = A.underWord.charAt(i)-48;
				
				if(input.equals(String.valueOf(A.fullWord.charAt(underNum))))
				{
					A.Qword.setCharAt(underNum, A.fullWord.charAt(underNum));
				}
			}
			if(String.valueOf(A.fullWord).equals(String.valueOf(A.Qword)))
			{
				System.out.println("정답쓰~" + A.fullWord);
				break;
			}
			cnt++;
		}
		if(cnt==Max) System.out.println("죽었으~");
		
		scanf.close();
	}
}
