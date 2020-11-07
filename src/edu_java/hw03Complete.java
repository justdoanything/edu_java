/*
 * 같은 수가 3번 연속 나오면 이기는 게임
 */

package edu_java;

import java.util.Random;
import java.util.Scanner;

class Player
{
	String Name;
	
	Player(String name)
	{
		this.Name = name;
	}
	
	boolean gamePlay()		//win : true;
	{
		Random rand = new Random();
		int[] num = new int[3];
		
		System.out.print(this.Name + " >> ");
		for(int i=0; i<num.length; i++)
		{
			num[i] = rand.nextInt(3);
			System.out.print(num[i] + " ");
		}
		System.out.println();
		
		if(num[0] == num[1] && num[1] == num[2])
			return true;
		else
			return false;
	}
	
}


public class hw03Complete 
{	
	public static void main(String[] args) 
	{		
		Scanner scan = new Scanner(System.in);
		boolean play01Result = false, play02Result = false;
		
		System.out.print("첫 번째 플레이어의 이름을 입력하세요 >> ");
		Player play01 = new Player(scan.nextLine());
		
		System.out.print("두 번째 플레이어의 이름을 입력하세요 >> ");
		Player play02 = new Player(scan.nextLine());
		
		int cnt = 1;
		
		while(true)
		{
			System.out.println("아무키나 누르세요. ");
			scan.nextLine();
			System.out.println(cnt + "번째 게임을 진행합니다.");
			
			play01Result = play01.gamePlay();
			play02Result = play02.gamePlay();

			if(play01Result && play02Result)
				System.out.println("비겼습니다 . 다시 게임을 진행하세요.");
			
			if(play01Result != play02Result)
			{
				if(play01Result)
				{
					System.out.println(cnt + "번째 만에 " + play01.Name + "가 이겼습니다.");
					break;
				}
				else
				{
					System.out.println(cnt + "번째 만에 " + play02.Name + "가 이겼습니다.");
					break;
				}
			}
			
			else
				System.out.println("승자가 없습니다. 다시 게임을 진행하세요.");
			
			System.out.println();
			cnt++;
		}
		
		scan.close();
	}
}
