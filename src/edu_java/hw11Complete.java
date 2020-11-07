/*
 * 지명과 위치정보 관리
 */
package edu_java;

import java.util.HashMap;
import java.util.Scanner;

class Location
{
	String name;
	double hardness; //경도
	double latitude; 	//위도
	String HardLati;
	
	HashMap<String, String> location = new HashMap<>();
	
	
	
	Location(String Name, String hard, String lati)
	{
		this.name = Name;
		this.hardness = Double.parseDouble(hard);
		this.latitude = Double.parseDouble(lati);
		
		this.HardLati = "경도 : " + String.valueOf(hardness) +", 위도 : " + String.valueOf(latitude); 
		
		this.location.put(Name, HardLati);
	}
}


public class hw11Complete 
{
	static Scanner scan = new Scanner(System.in);
	static Location[] loc =new Location[100];
	
	public static void main(String[] args) 
	{
		String input;
		
		insertLocation();
		
		System.out.println("원하는 지명을 입력하세요 : ");
		input = scan.nextLine();
		
		check(input);
	}
	
	static void insertLocation()
	{
		String input;
		
		System.out.println("도시의 지명/경도/위도를 입력하세요.");
		for(int i=0; i<5; i++)
		{
			input = scan.nextLine();
			String inputArr[] = input.split("/");
			
			loc[i] = new Location(inputArr[0], inputArr[1], inputArr[2]);
		}
	}
	
	static void check(String input)
	{
		int i=0;
		
		for(i=0; i<5; i++)
		{
			if(loc[i].name.equals(input))
			{
				System.out.println(loc[i].location.get(input));
				return;
			}
		}
		if(i==5) System.out.println("찾으시는 장소가 목록에 없습니다.");
	}
}
