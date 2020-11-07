/*

AAA/111/용우/남자/21/서울
BBB/222/준일/여자/22/경기
CCC/333/동균/남자/23/서울
DDD/444/우주/여자/24/서울
EEE/555/동혁/남자/25/서울
FFF/666/도연/여자/26/경기
GGG/777/요한/남자/27/서울
 
*/


package edu_java;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

class person
{
	String ID;
	String PWD;
	String NAME;
	String SEX;
	int AGE;
	String ADDR;
	
	person()
	{
		ID = null;
		PWD = null;
		NAME = null;
		SEX = null;
		AGE = 0;
		ADDR = null;
	}
}

public class hw02Complete 
{
	static Scanner scan = new Scanner(System.in);
	
	static boolean GoStop = true;		//계속 입력 받을지 말지
	static int personCnt = 0;				//입력 받은 사람 수
	
	
	//사람 정보 등록
	static void insertPeople(person saram, int cnt)
	{
		System.out.println((cnt+1) + "번째 사람의 ID, 비밀번호, 이름, 성별, 나이, 주소를 입력하세요 : (구문자는 / 입니다.)");
		System.out.println("그만 등록하려면 [e]를 입력하세요.");
		
		String input = scan.nextLine();
		
		if(input.equals("e"))
			GoStop=false;
				
		else
		{
			String inputArr[] = input.split("/");
			
			saram.ID = inputArr[0];
			saram.PWD = inputArr[1];
			saram.NAME = inputArr[2];
			saram.SEX = inputArr[3];
			saram.AGE = Integer.parseInt(inputArr[4]);
			saram.ADDR = inputArr[5];
		}
	}
	
	//평균 나이 계산 및 출력
	static void age_Avg(person obj[], int saramNum)
	{
		double sumAge=0.0;
			
		for(int i=0; i<saramNum; i++)
			sumAge += obj[i].AGE;
			
		System.out.println("가입자 연령 평균은 " + sumAge/saramNum + "세 입니다.");
	}
		
	//가입자 남,여 비율
	static void sex_per(person obj[], int saramNum)
	{
		int sex_man=0;
		
		for(int i=0; i<saramNum; i++)
			if(obj[i].SEX.equals("남자"))
				sex_man++;
		
		System.out.println("남자 비율 : " + (sex_man*100)/saramNum + "%");
		System.out.println("여자 비율 : " + (100-(sex_man*100)/saramNum) + "%");
	}
	
	//가입자 지역 분포 비율 && 가장 많이 사는 지역의 가장 나이 많은 여자의 신상정보
	static void location_per(person obj[], int saramNum)
	{
		Hashtable<String, Integer> city = new Hashtable<>();
		int location_max=0;
		String location_max_name = null;
		
		for(int i=0; i<saramNum; i++)
		{
			if(city.containsKey(obj[i].ADDR))
				city.put(obj[i].ADDR, city.get(obj[i].ADDR)+1);
			else
				city.put(obj[i].ADDR, 1);
		}
		
		//HashTable의 Key값 출력하는 부분
		Set<String> set = city.keySet();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) 
		{
			String area = it.next();		
			System.out.println(area + "지역의 비율 : " + (city.get(area)*100)/personCnt + "%");
			
			if(location_max < city.get(area))
			{
				location_max = city.get(area);
				location_max_name = area;
			}
		}
		
		
		//가장 많이 사는 지역의 가장 나이 많은 여자의 신상정보
		int age,olderNum = 0;
		int older = 0;
		
		for(int i=0; i<saramNum; i++)
		{
			if(obj[i].ADDR.equals(location_max_name) && obj[i].SEX.equals("여자"))
			{
				age = obj[i].AGE;
				if(age > older)
				{
					older = age;
					olderNum = i;
				}
			}
		}
		System.out.println("\n가장 많이 사는 동네("+location_max_name+")에 사는 여자 중 가장 나이 많은 사람의 정보 : " + obj[olderNum].ID 
				+ "  " + obj[olderNum].NAME
				+ "  " + obj[olderNum].SEX
				+ "  " + obj[olderNum].ADDR);
	}
		
	//서울 거주자 중 가장 어린 남자의 신상정보
	static void seoul_man(person obj[], int saramNum)
	{
		int young,youngerNum = 0;
		int younger = 120;
		
		for(int i=0; i<saramNum; i++)
		{
			if(obj[i].ADDR.equals("서울") && obj[i].SEX.equals("남자"))
			{
				young = obj[i].AGE;
				if(young < younger)
				{
					younger = young;
					youngerNum = i;
				}
			}
		}
		
		System.out.println("서울 사는 남자 중 가장 어린 사람의 정보 : " + obj[youngerNum].ID 
									+ "  " + obj[youngerNum].NAME
									+ "  " + obj[youngerNum].SEX
									+ "  " + obj[youngerNum].ADDR);
	}
	
	
	
	public static void main(String[] args) 
	{
		person saram[] = new person[500];
		
		//입력받기
		/*
		while(GoStop)
		{
			saram[personCnt] = new person();
			insertPeople(saram[personCnt], personCnt);
			if(GoStop)
				personCnt += 1;
		}*/

		//입력 받기(임시)
		System.out.println("사람의 ID, 비밀번호, 이름, 성별, 나이, 주소 순서로 입력하세요 : (구문자는 / 입니다.)");
		for(int i=0; i<7; i++)
		{			
			saram[i] = new person();
			String input = scan.nextLine();
			String inputArr[] = input.split("/");
				
			saram[i].ID = inputArr[0];
			saram[i].PWD = inputArr[1];
			saram[i].NAME = inputArr[2];
			saram[i].SEX = inputArr[3];
			saram[i].AGE = Integer.parseInt(inputArr[4]);
			saram[i].ADDR = inputArr[5];
		}
		personCnt = 7;
		
		//평균 나이
		age_Avg(saram, personCnt);
		System.out.println();
		
		//가입자 남,여 비율
		sex_per(saram, personCnt);
		System.out.println();
		
		//가입자 지역 분포 비율 && 가입자가 가장 많은 지역 거주자 중 가장 나이 많은 여자의 신상 명세
		location_per(saram, personCnt);
		System.out.println();
		
		//서울 거주자 중 가장 어린 남자의 신상 명세
		seoul_man(saram, personCnt);
	}
}