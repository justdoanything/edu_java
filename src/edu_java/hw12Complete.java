/*
 * 포인트 관리
 */
package edu_java;

import java.util.Scanner;
import java.util.Vector;

class Point
{
	String Name;
	int Point;
	
	
	
	Point(String name, int point)
	{
		this.Name = name;
		this.Point = point;
	}
}

public class hw12Complete 
{
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		String input;
		
		Vector<Point> list = new Vector<>();
		
		while(true)
		{
			System.out.print("이름과 포인트 입력 >> (이용우,100)");
			input = scan.nextLine();
			String inputArr[] = input.split(",");
			
			
			list.add(new Point(inputArr[0],Integer.parseInt(inputArr[1])));
			
			for(int i=0; i<list.size(); i++)
				System.out.print("("+list.get(i).Name + "," + list.get(i).Point+") "); 
			System.out.println("\n");
		}	
	}
}
