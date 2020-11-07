package edu_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Pae
{
	String[][] pae = {
			{"일피", "일피", "일홍띠", "일광"}			//1
			,{"이피", "이피", "이홍띠", "이동물"}		//2
			,{"삼피", "삼피", "삼홍띠", "삼광"}		//3
			,{"사피", "사피", "사초띠", "사동물"}		//4
			,{"오피", "오피", "오초띠", "오동물"}		//5
			,{"육피", "육피", "육청띠", "육동물"}		//6
			,{"칠피", "칠피", "칠초띠", "칠동물"}		//7
			,{"팔피", "팔피", "팔동물", "팔광"}		//8
			,{"구피", "구피", "구청띠", "구피피"}		//9
			,{"십피", "십피", "십청띠", "십동물"}		//10	
			,{"똥피", "똥피", "똥피피", "똥광"}		//11 (똥)
			,{"비피피", "비띠", "비동물", "비광"}};	//12 (비)
	
	
	int fieldCnt;
	List<String> haveField = new ArrayList<>();
	
	Pae()
	{
		this.fieldCnt=0;
	}
	
	void showField()
	{
		System.out.print("     [[ Field("+this.fieldCnt+") : ");
		for(int i=0; i<this.haveField.size(); i++)
			System.out.print(this.haveField.get(i)+" ");
		System.out.println(" ]]");
	}
}

class GoStopPlayer
{
	List<String> havePae = new ArrayList<>();
	List<String> eatPae = new ArrayList<>();
	
	String name;
	int Point;
	
	int lastPI, lastDDI, lastDONG, lastGWANG;
	int cntPI, cntDDI, cntDONG, cntGWANG;
	
	int paeCnt;
	
	GoStopPlayer(String Name)
	{
		this.name = Name;
		this.Point = 0;
		this.paeCnt = 0;
		
		this.lastPI = 9;
		this.lastDDI = 4;
		this.lastDONG = 4;
		this.lastGWANG = 2;
		
		this.cntPI = 0;
		this.cntDDI = 0;
		this.cntDONG = 0;
		this.cntGWANG = 0;
	}
	
	void showHavePae()
	{
		System.out.print("  * 가지고 있는패("+this.havePae.size() + ") : ");
		
		for(int i=0; i<this.havePae.size(); i++)
			System.out.print(" " + havePae.get(i) + " ");
		System.out.println();
	}
	
	void showEatPae()
	{
		System.out.print("  * 먹은 패 : ");
		for(int i=0; i<this.eatPae.size(); i++)
			System.out.print(" " + eatPae.get(i) + " ");
		System.out.println();
	}
}

public class hwatoo 
{
	static Scanner scan = new Scanner(System.in);	
	static Pae field = new Pae();
	
	static Random rand = new Random();
	static int pae_Row;
	static int pae_Col;
	
	//3명이서 치고 7개씩 가지고 6개를 깐다.
	public static void main(String[] args) 
	{
		GoStopPlayer player1 = new GoStopPlayer("용우");
		GoStopPlayer player2 = new GoStopPlayer("재언");
		GoStopPlayer player3 = new GoStopPlayer("성아");

		sharePae(player1, player2, player3);
		showPae(player1, player2, player3);
		System.out.println("\n");
		
		while(true)
		{
			if(playGame(player1))	//playGame하고 났으면 true를 반환해 탈출
				break;
			if(playGame(player2))
				break;
			if(playGame(player3))
				break;
		}
		
		

	}
/***********************  패 나눠주고 깔고 보여주고 */
	static void giveToPlayer(GoStopPlayer player)
	{
		while(player.paeCnt<7)
		{
			pae_Row = rand.nextInt(12);
			pae_Col = rand.nextInt(4);
			
			if(field.pae[pae_Row][pae_Col].equals("-")==false)
			{
				player.havePae.add(field.pae[pae_Row][pae_Col]);
				field.pae[pae_Row][pae_Col] = "-";
				player.paeCnt++;
			}
			
		}
	}
	
	static void giveToField()
	{
		while(field.fieldCnt<6)
		{
			pae_Row = rand.nextInt(12);
			pae_Col = rand.nextInt(4);
			
			if(field.pae[pae_Row][pae_Col].equals("-")==false)
			{
				field.haveField.add(field.pae[pae_Row][pae_Col]);
				field.pae[pae_Row][pae_Col]="-";
				field.fieldCnt++;
			}
		}
	}
	
	static void sharePae(GoStopPlayer player1, GoStopPlayer player2, GoStopPlayer player3)
	{
		giveToPlayer(player1); 
		giveToPlayer(player2); 
		giveToPlayer(player3);
		giveToField();
	}
	
	static void showPae(GoStopPlayer player1, GoStopPlayer player2, GoStopPlayer player3)
	{
		player1.showHavePae(); 
		player2.showHavePae(); 
		player3.showHavePae();
		field.showField();
	}
/******************************************/

/********************************  플레이 게임 */
/*** eatingPlayer -> eatingCenterPae -> winChk **/	
	static boolean playGame(GoStopPlayer player)
	{		
		String have;
		
		System.out.println(" << " + player.name + " >>");
		player.showHavePae();
		player.showEatPae();
		field.showField();
		
		do
		{
			System.out.print("      [[ 뭐낼꺼야? ]] >> ");
			have = scan.nextLine();
			
			if(!(player.havePae.contains(have)))
			{
				System.out.println("해당 패를 가지고 있지 않아!! 낼수 없다.");
			}
			
			else if(field.haveField.size()==0)
			{
				System.out.println("필드에 패가 없다.");
				return true;
			}
			
			else if(player.havePae.size()==0)
			{
				System.out.println(player.name + "의 패가 없다.");
				return true;
			}
			else
			{
				eatingPlayer(player,have);
				eatingCenterPae(player,DDIGGIGI());
				player.showEatPae();
				System.out.println();
				return winChk(player);
			}
		}while(true);
	}
	
	static void eatingPlayer(GoStopPlayer player, String have)
	{
		String eat="";
		String eatType="";
		String haveType="";
		boolean canEAT = false;	//먹으면 true가 됨
		
		List<String> canEatList = new ArrayList<String>();
		String eatSelect;
		
		for(int i=0; i<field.haveField.size(); i++)
		{
			if(have.substring(0,1).equals(field.haveField.get(i).substring(0,1)))
				canEatList.add(field.haveField.get(i));
		}		
		
		if(canEatList.size() > 1)
		{
			for(int j=0; j<canEatList.size(); j++)
			{
				System.out.print( (j+1) + ". "+canEatList.get(j)+"  ");
			}
			while(true)
			{
				System.out.print("어떤거를 먹을래요? >>");
				eatSelect = scan.nextLine();
				
				if(!(canEatList.contains(eatSelect)))
					System.out.println("먹을 수 없는 패인데요?");
				else
				{
					canEAT = true;
					eat = eatSelect;
					haveType = have.substring(1);	//낸 패의 종류
					eatType = eat.substring(1);		//먹은 패의 종류
					break;
				}
			}
		}
		
		if(canEatList.size()==1)
		{
			canEAT = true; 
			eat = canEatList.get(0);
			haveType = have.substring(1);	//낸 패의 종류
			eatType = eat.substring(1);		//먹은 패의 종류
		}
		
		if(canEAT == true)
		{
			if(haveType.equals("피") || haveType.equals("피피") || eatType.equals("피") || eatType.equals("피피")) 
			{
				if(haveType.equals("피") && eatType.equals("피"))
					player.cntPI += 2;
				else if(haveType.equals("피피") && eatType.equals("피"))
					player.cntPI += 3;
				else if(haveType.equals("피") && eatType.equals("피피"))
					player.cntPI += 3;
				else
					player.cntPI += 1;
			}
				
			if(have.length() == 3 && have.substring(2).equals("띠"))
				player.cntDDI += 1;
					
			if(eat.length() == 3 && eat.substring(2).equals("띠"))
				player.cntDDI += 1;
			
			if(haveType.equals("동물") || eatType.equals("동물"))
			{
				if(haveType.equals("동물") && eatType.equals("동물"))
					player.cntDONG += 2;
				else
					player.cntDONG += 1;
			}
			
			if(haveType.equals("광") || eatType.equals("광"))
			{
				if(haveType.equals("광") && eatType.equals("광"))
					player.cntGWANG += 2;
				else
					player.cntGWANG += 1;
			}
								
			player.havePae.remove(have);	
			player.paeCnt = player.havePae.size();						
			player.eatPae.add(have); 			
			
				
			field.haveField.remove(eat);		
			field.fieldCnt = field.haveField.size();						
			player.eatPae.add(eat);			
		}
		
		if(canEAT==false)	//먹은게 없음
		{
			player.havePae.remove(have);	
			field.haveField.add(have);		
			field.fieldCnt = field.haveField.size();
		}
	}

	static void eatingCenterPae(GoStopPlayer player, String have)
	{
		String eat="";
		String eatType="";
		String haveType="";
		boolean canEAT = false;	//먹으면 true가 됨
		
		List<String> canEatList = new ArrayList<String>();
		String eatSelect;
		

		System.out.println("  ?? 띠껴서 나온패 : "+have);

		for(int i=0; i<field.haveField.size(); i++)
		{
			if(have.substring(0,1).equals(field.haveField.get(i).substring(0,1)))
				canEatList.add(field.haveField.get(i));
		}		
		
		if(canEatList.size() > 1)
		{
			for(int j=0; j<canEatList.size(); j++)
			{
				System.out.print( (j+1) + ". "+canEatList.get(j)+"  ");
			}

			System.out.print("어떤거를 먹을래요? >> ");
			eatSelect = scan.nextLine();
			
			if(!(canEatList.contains(eatSelect)))
				System.out.println("먹을 수 없는 패인데요?");
			else
			{
				canEAT = true;
				eat = eatSelect;
				haveType = have.substring(1);	//낸 패의 종류
				eatType = eat.substring(1);		//먹은 패의 종류
			}
		}
		
		if(canEatList.size()==1)
		{
			canEAT = true; 
			eat = canEatList.get(0);
			haveType = have.substring(1);	//낸 패의 종류
			eatType = eat.substring(1);		//먹은 패의 종류
		}
		
		if(canEAT == true)
		{
			if(haveType.equals("피") || haveType.equals("피피") || eatType.equals("피") || eatType.equals("피피")) 
			{
				if(haveType.equals("피") && eatType.equals("피"))
					player.cntPI += 2;
				else if(haveType.equals("피피") && eatType.equals("피"))
					player.cntPI += 3;
				else if(haveType.equals("피") && eatType.equals("피피"))
					player.cntPI += 3;
				else
					player.cntPI += 1;
			}
				
			if(have.length() == 3 && have.substring(2).equals("띠"))
				player.cntDDI += 1;
					
			if(eat.length() == 3 && eat.substring(2).equals("띠"))
				player.cntDDI += 1;
			
			if(haveType.equals("동물") || eatType.equals("동물"))
			{
				if(haveType.equals("동물") && eatType.equals("동물"))
					player.cntDONG += 2;
				else
					player.cntDONG += 1;
			}
			
			if(haveType.equals("광") || eatType.equals("광"))
			{
				if(haveType.equals("광") && eatType.equals("광"))
					player.cntGWANG += 2;
				else
					player.cntGWANG += 1;
			}
								
			player.eatPae.add(have); 			
			field.haveField.remove(eat);		
			field.fieldCnt = field.haveField.size();						
			player.eatPae.add(eat);			
		}
		
		if(canEAT==false)	//먹은게 없음
		{
			field.haveField.add(have);		
			field.fieldCnt = field.haveField.size();
		}
	}

	static String DDIGGIGI()	//중간꺼를 띠껴서 나온 패를 반환
	{
		String randPae;
		
		while(true)
		{
			pae_Row = rand.nextInt(12);
			pae_Col = rand.nextInt(4);
			randPae = field.pae[pae_Row][pae_Col];
			
			if(randPae.equals("-") == false)
			{
				field.pae[pae_Row][pae_Col] = "-";
				return randPae; 
			}
		}
	}
	
	static boolean winChk(GoStopPlayer player)	//점수 체크하고 이겼으면 true 반환
	{
		Godori(player);
		samDDI(player);
		PointPlus(player);
		
		if(player.Point >= 3)
		{
			System.out.println(player.name + "이 " + player.Point + "점으로 이겼다.");
			return true;
		}
			
		return false;
	}
/******************************************/	

	
/**************************  점수 더하는 메소드 */
	static void Godori(GoStopPlayer player)
	{
		if(player.eatPae.contains("이동물") && player.eatPae.contains("사동물") && player.eatPae.contains("팔동물"))
		{
			System.out.println(player + "가 고도리!");
			player.Point +=5;
		}
	}
	
	static void samDDI(GoStopPlayer player)
	{
		if(player.eatPae.contains("일홍띠") && player.eatPae.contains("이홍띠") && player.eatPae.contains("삼홍띠"))
		{
			System.out.println(player + "가 홍단!");
			player.Point +=3;
		}
		if(player.eatPae.contains("육청띠") && player.eatPae.contains("구청띠") && player.eatPae.contains("십청띠"))
		{
			System.out.println(player + "가 청단!");
			player.Point +=3;
		}
		if(player.eatPae.contains("사초띠") && player.eatPae.contains("오초띠") && player.eatPae.contains("칠초띠"))
		{
			System.out.println(player + "가 초단!");
			player.Point +=3;
		}
	}

	static void PointPlus(GoStopPlayer player)
	{			
		if(player.cntPI >= 10 && player.cntPI > player.lastPI)
		{			
			player.Point += (player.cntPI - player.lastPI);
			player.lastPI = player.cntPI;
		}
		
		if(player.cntDONG >= 5 && player.cntDONG > player.lastDONG)
		{
			player.Point +=(player.cntDONG - player.lastDONG);
			player.lastDONG = player.cntDONG;
		}
		
		if(player.cntDDI >= 5 && player.cntDDI > player.lastDDI)
		{
			player.Point +=(player.cntDDI - player.lastDDI);
			player.lastDDI = player.cntDDI;
		}	
		
		if(player.cntGWANG >= 3 && player.cntGWANG > player.lastGWANG)
		{
			if(player.cntGWANG == 3 && player.eatPae.contains("비광"))
			{
				player.Point += 2;
				player.lastGWANG = player.cntGWANG;
			}
			else if(player.cntGWANG == 3 && player.eatPae.contains("비광")==false)
			{
				player.Point += 3;
				player.lastGWANG = player.cntGWANG;
			}
			else
			{
				player.Point += player.cntGWANG - player.lastGWANG;
				player.lastGWANG = player.cntGWANG;
			}
		}
	}
/******************************************/

}
