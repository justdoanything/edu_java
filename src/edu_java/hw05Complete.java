/*
 * 곰과 물고기 
 */

package edu_java;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//*************************************************** 부모 life 클래스
abstract class life
{
	//행(row), 열(col)
	int row, col;
	
	//이동하는 것을 구현한 move메소드
	//boolean 값을 이용해 이동하는 곳이 유효한 값이면 true, 아니면 false를 반환해 main 함수에서 유효한 턴을 저장하는 Cnt변수 증감에 이용한다.
	abstract boolean move(String direction);
	
	//이동하는 값이 유효한 값인지 아닌지 boolean 값으로 판별, 유효하면 true;
	boolean checkPosition(int row, int col)
	{
		if(row == -1 || row == 10)
			return false;
		if(col == -1 || col == 20)
			return false;
		
		return true;
	}
}


//*************************************************** 자식 bear 클래스
class bear extends life	// 부모 life로부터 변수와 move메소드를 상속 받는 bear 클래스
{
	bear()
	{
		this.row = 0;
		this.col =0;
	}
	
	bear(int row, int col)
	{
		this.row = row;
		this.col = col;
	}

	boolean move(String direction)// 부모 life로 부터 상속받은 추상메소드 move 구현
	{
		switch(direction)
		{
		case "UP" :
			if(checkPosition((this.row-1), -2))	//checkPosition 이라는 boolean 함수를 사용해 이동하는 곳이 true면 이동하고 false면 이동하지 않는다.
			{
				hw05Complete.field[this.row][this.col] = "-";		//과거의 위치를 지우고
				this.row -= 1;									//이동할 위치를 기록하고
				hw05Complete.field[this.row][this.col] = "B";		//이동한 위치에 표시해준다.
				return true;		//이동할 값이 유효하므로 true 반환
			}
			else
			{
				System.out.println("      [ ERROR : Bear는 그곳으로 이동할 수 없습니다. ]\n");
				return false; 		//이동할 수 없는 곳이므로 false 반환
			}
			
		case "DOWN" :
			if(checkPosition((this.row+1),-2))
			{
				hw05Complete.field[this.row][this.col] = "-";
				this.row += 1;
				hw05Complete.field[this.row][this.col] = "B";
				return true;
			}
			else
			{
				System.out.println("      [ ERROR : Bear는 그곳으로 이동할 수 없습니다. ]\n");
				return false;
			}
		
		case "LEFT" :
			if(checkPosition(-2,(this.col-1)))
			{
				hw05Complete.field[this.row][this.col] = "-";
				this.col -= 1;
				hw05Complete.field[this.row][this.col] = "B";
				return true;
			}
			
			else
			{
				System.out.println("      [ ERROR : Bear는 그곳으로 이동할 수 없습니다. ]\n");
				return false;
			}
		
		case "RIGHT" :
			if(checkPosition(-2, this.col+1))
			{
				hw05Complete.field[this.row][this.col] = "-";
				this.col += 1;
				hw05Complete.field[this.row][this.col] = "B";
				return true;
			}
			else
			{
				System.out.println("      [ ERROR : Bear는 그곳으로 이동할 수 없습니다. ]\n");
				return false;
			}
		
		default :	//예외 처리 : 오류 위치 출력
			System.out.println("bear 클래스 안에 있는 move 메소드의 매개변수 값이 잘못되었다.");
			return false;
		}
	}
}


//*************************************************** 자식 fish 클래스
class fish extends life		// 부모 life로부터 변수와 메소드를 상속 받는 bear 클래스
{
	fish()
	{
		this.row = 0;
		this.col =0;
	}
	
	fish(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	boolean move(String direction)//bear의 move 메소드와 같은 원리이고 출력만 'B'가 아닌 'F'로 해준다.
	{
		switch(direction)
		{
		case "UP" :
			if(checkPosition((this.row-1), -2))
			{
				hw05Complete.field[this.row][this.col] = "-";
				this.row -= 1;
				hw05Complete.field[this.row][this.col] = "F";
				return true;
			}
			else
			{
				System.out.println("      [ ERROR : Fish는 그곳으로 이동할 수 없습니다. ]\n");
				return false;
			}
			
		case "DOWN" :
			if(checkPosition((this.row+1),-2))
			{
				hw05Complete.field[this.row][this.col] = "-";
				this.row += 1;
				hw05Complete.field[this.row][this.col] = "F";
				return true;
			}
			else
			{
				System.out.println("      [ ERROR : Fish는 그곳으로 이동할 수 없습니다. ]\n");
				return false;
			}
		
		case "LEFT" :
			if(checkPosition(-2,(this.col-1)))
			{
				hw05Complete.field[this.row][this.col] = "-";
				this.col -= 1;
				hw05Complete.field[this.row][this.col] = "F";
				return true;
			}
			
			else
			{
				System.out.println("      [ ERROR : Fish는 그곳으로 이동할 수 없습니다. ]\n");
				return false;
			}
		
		case "RIGHT" :
			if(checkPosition(-2, this.col+1))
			{
				hw05Complete.field[this.row][this.col] = "-";
				this.col += 1;
				hw05Complete.field[this.row][this.col] = "F";
				return true;
			}
			else
			{
				System.out.println("      [ ERROR : Fish는 그곳으로 이동할 수 없습니다. ]\n");
				return false;
			}
		
		default :
			System.out.println("FISH 클래스 안에 있는 move 메소드의 매개변수 값이 잘못되었다.");
			return false;
		}
	}
}


public class hw05Complete 
{	
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	
	public static String[][] field = new String[10][20];	//10행 20열의 field 배열
	
	static bear[] bear = new bear[3];	//bear 클래스 
	static fish[] fish = new fish[10]; //fish 클래스
	
	public static void main(String[] args) 
	{
		
		fieldShow(0);	//필드 초기화
		makeLife();		//곰, 물고기 생성 및 필드 출력
		
		moveLife(bear[0]);	//곰, 물고기 이동
	}
	

	static void makeLife()//곰과 물고기 객체를 생성하는 클래스
	{
		//10행(y) 20열(x)		
		int bearCnt, fishCnt;
		int bearRow, bearCol, fishRow, fishCol;
		boolean ErrorChk = false;
		
		do
		{
			System.out.print("?곰 몇 마리 생산할래? (최대 3마리야) >> ");
			bearCnt = inputExcep();
			//예외 처리 : input 값 정수로 한정
			
			if(bearCnt >= 1 && bearCnt <= 3)	//입력 범위 한정
			{
				for(int i=0; i<bearCnt; i++)
				{
					bearRow = rand.nextInt(9);	//랜덤 좌표 생성
					bearCol = rand.nextInt(19);	//랜덤 좌표 생성
					
					bear[i] = new bear(bearRow, bearCol);	//랜덤 좌표로 bear 생성
					field[bearRow][bearCol] = "B";				//필드에 입력
				}
				System.out.println("  [ GOOD : 곰 "+bearCnt+"마리가 생산되었다! ]\n");
				ErrorChk=false;	//입력값 이상없다.
			}
			else
			{
				System.out.println("  [ FAIL : 곰은 1~3마리만 생산할 수 있어! ]\n");
				ErrorChk = true;	//입력 범위 벗어날 경우 ErrorChk = true, 재입력 받는다.
			}
				
		}while(ErrorChk);//ErrorChk 변수로 입력값 오류 처리
		
		do
		{
			//bear 생성과 같은 원리, 입력값의 변위만 조금 다르다.
			System.out.print("?물고기는 몇마리 생산할래? (최대 10마리야) >> ");
			fishCnt = inputExcep();
			//예외 처리 : input 값 정수로 한정
			
			if(fishCnt >= 1 && fishCnt <= 10)
			{
				for(int i=0; i<fishCnt; i++)
				{
					fishRow = rand.nextInt(9);
					fishCol = rand.nextInt(19);
					
					
					fish[i] = new fish(fishRow, fishCol);
					field[fishRow][fishCol] = "F";
					
				}
				System.out.println("  [ GOOD : 물고기 "+fishCnt+"마리가 생산되었다! ]\n");
				ErrorChk=false;
			}
			else
			{
				System.out.println("  [FAIL : 물고기는 1~10마리만 생산할 수 있어! ]\n");
				ErrorChk=true;
			}
		}while(fishCnt < 1 || fishCnt > 10);
		
		
		//곰과 물고기를 넣은 필드 보여주기
		System.out.println("\n[[ FIELD ]]");
		fieldShow(1);	
	}
	
	
	static void fieldShow(int set)//set = 0 이면 초기화, set = 1이면 출력
	{
		if(set==0)	//필드 초기화 
		{
			for(int i=0; i<field.length; i++)
			{
				for(int j=0; j<field[0].length; j++)
					field[i][j]="-";
			}
		}
		
		else if(set==1)	//필드 출력
		{
			for(int i=0; i<field.length; i++)
			{
				for(int j=0; j<field[0].length; j++)
					System.out.print(field[i][j]+" ");
				System.out.println();
			}
		}
		
		else	//예외처리 : 오류 부분 출력
			System.out.println("fieldShow 메소드에 매개변수 값이 잘못되었습니다.");
	}


	static void moveLife(bear bear)
	{
		String direction;	//이동 방향 저장할 String 변수
		boolean inputOK = true;	//이동 방향이 유효한지 저장하는 boolean 변수, move메소드의 boolean 값을 받는다.
		int Cnt=0;	//유효한 판을 카운트하는 변수
		int randNum;	//fish의 랜덤 이동값을 저장하기 위한 변수
		
		scan.nextLine();
		while(true)
		{
			System.out.println("  * 곰이 움직일 방향을 선택하세요. ");
			System.out.print("  * 위(U) / 아래(D) / 왼쪽(L) / 오른쪽(R) >> ");
			direction = scan.nextLine();
			
		//************* 물고기 이동
			if(Cnt > 0 && Cnt%2 == 0)
			{
				//fish는 3턴에 1번씩 랜덤값으로 움직인다.
				randNum = rand.nextInt(3);
				
				if(randNum == 0)
					fish[0].move("UP");
				else if(randNum == 1)
					fish[0].move("DOWN");
				else if(randNum == 2)
					fish[0].move("LEFT");
				else if(randNum == 3)
					fish[0].move("RIGHT");
				else	//예외처리 : 오류 위치 출력
					System.out.println("메인함수에 fish.move를 호출하는 매개변수가 잘못되었습니다.");
			}
		//************* 곰 이동
			switch(direction)
			{
				case "u" :
				case "U" :
					inputOK = bear.move("UP");	//입력한 방향에 맞게 위치를 이동시키고 이동하는 값이 유효하지 않다면 false를 반환하면서 inputOK 값에 저장
					break;
					
				case "d" :
				case "D" :
					inputOK = bear.move("DOWN");
					break;
					
				case "l" :
				case "L" :
					inputOK = bear.move("LEFT");
					break;
					
				case "r" :
				case "R" :
					inputOK = bear.move("RIGHT");
					break;
					
				default :
					//예외처리 :입력값이 범위를 벗어났을 때 처리
					System.out.println("    [ FAIL : 방향키 입력이 잘못되었습니다. ]\n");  
					inputOK = false;		//입력 값이 오류이기 때문에 inputOK = false;
					break;
			}
			
			//곰과 물고기가 이동 후 만났을 때 게임이 끝난다.
			if(bear.row == fish[0].row && bear.col == fish[0].col)
			{
				fieldShow(1);	//필드 출력
				System.out.println("  [ GAME COMPLETE : 곰이 물고기를 잡았다!!!! ]");
				break;
			}
			
			fieldShow(1);	//게임이 끝나지 않았을 때 필드 출력
			System.out.println();
			if(inputOK==true) Cnt++;	//유효한 판이면 Cnt 증가
		}
	}

	
	static int inputExcep()//입력 값을 정수로 한정
	{
		int output = -1;
		
		try 
		{
			output = scan.nextInt();
		}
		
		catch(InputMismatchException e)
		{
			scan.nextLine();
		}
		
		return output;
	}	
}
