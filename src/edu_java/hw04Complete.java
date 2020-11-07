/*
 * 공연예약시스템
 */

package edu_java;

import java.util.InputMismatchException;
import java.util.Scanner;

class Customer
{
	String Name;
	int seatClass;
	int seatNo;
	boolean reserveOK;
	
	Customer()
	{
		this.Name = null;
		this.seatClass = 0;
		this.seatNo = 0;
		this.reserveOK = false;
	}
	
	Customer(String Name, int seatNo)
	{
		this.Name = Name;
		this.seatClass = 0;
		this.seatNo = seatNo;
		this.reserveOK = false;
	}
}

public class hw04Complete 
{
	//******************************************* 전역 변수 선언
	static Scanner scan = new Scanner(System.in);
	static Customer[] person = new Customer[50];
	static int cusCnt = 1;		//예약자 수	
	
	//좌석 배열
	static String[][] Seat = {
			{"S","-","-","-","-","-","-","-","-","-","-"},
			{"A","-","-","-","-","-","-","-","-","-","-"},
			{"B","-","-","-","-","-","-","-","-","-","-"},
	};	
	//******************************************************* 
	
	public static void main(String[] args) 
	{
		int menuInput=0;
		person[0] = new Customer();

		do
		{
			System.out.print("\n[[ 예약(1) , 조회(2) , 취소(3) , 종료(4) ]] >> ");	
//****** 예외처리 : inputMismtachExcep
			menuInput = inputExcep();   
			
			if(menuInput == 1)					
				reserve();	
					
			else if(menuInput ==2)
				search(0);		// search(0) : 전체 좌석 조회;
			
			else if(menuInput == 3)
				cancelReserve();
						
			else if(menuInput == 4)
			{
				System.out.println("\n  [ 프로그램을 종료합니다. ] ");
				break;
			}
			
//****** 예외처리 : 메뉴 번호 범위 초과했을 때
			else
				System.out.println("메뉴에 해당하지 않는 번호입니다. 다시 입력해주세요!!");
		}while(true);
	}
	
			
	
			
			
	
	
//************************************************************** 예약(1)
	static void reserve()
	{
		int cusSeatClass=-1;
		int cusSeatNo = -1;
		String cusName;
		
		
		// 클래스 선택 오류 체크
		do{
			System.out.print("\n  [[ S클래스(1) , A클래스(2), B클래스(3) ]] >> ");
			cusSeatClass = inputExcep();
//****** 예외처리 : inputMismtachExcep
			
		}while(search(cusSeatClass)==1);	
		//***** 예외처리 : 클래스 번호 범위 벗어날 경우
		
		// 이름 중복 체크
		do{	
			scan.nextLine();
			System.out.print("\n  - 예약자 이름 >> ");
			cusName = scan.nextLine();
		}while(jungbok(cusName) != 0);	//*************** 이름 중복 체크
		
		// 좌석 중복 체크
		do{
			System.out.print("  - 좌석번호 [ 1 ~ 10 ] >> ");
				cusSeatNo = inputExcep();
//****** 예외처리 : inputMismtachExcep
		}while(jungbok(cusSeatClass, cusSeatNo) != 0);
		
		
		//예약자 생성
		person[cusCnt] = new Customer(cusName,cusSeatNo);
		
		Seat[cusSeatClass-1][cusSeatNo] = cusName;			
		person[cusCnt].reserveOK = true;
		person[cusCnt].seatNo = cusSeatNo;
		person[cusCnt].seatClass = cusSeatClass;
		cusCnt++;
	}	
	

//************************************************************** 조회(2)
	static int search(int seatClass)
	{
		//전체 좌석 출력
		if(seatClass ==0)				
		{
			System.out.println("  [ 현재 예약자 수 : " + (cusCnt-1) + "명 ]");
			for(int row=0; row<Seat.length; row++)
			{
				for(int col = 0; col<Seat[0].length; col++)
					System.out.print("  [ " + Seat[row][col] + " ] ");
				System.out.println();
			}
			
			return 0;
		}
		
		//해당 등급 좌석 출력
		else if(seatClass >0 && seatClass <= 3)			
		{
			for(int col=0; col < Seat[seatClass-1].length; col++)
				System.out.print("  [ " + Seat[seatClass-1][col] + " ] " );
			System.out.println();
			
			return 0;
		}
		
		//예외 처리
		else
		{
			System.out.println("    좌석 선택 등급 값이 잘못되었습니다! 다시 입력해주세요");
			System.out.println("    좌석 선택은 숫자로 1~3 사이값 입니다.");
			return 1;
		}
	}


//************************************************************** 취소(3)
	static void cancelReserve()
	{
		int select = 0;
		String cusName, input;
		
		scan.nextLine();
		System.out.print("\n   예약자 이름을 입력해주세요 >> ");
		cusName = scan.nextLine();
		
		//예약자 이름에 해당하는 고객 인덱스 저장
		select = jungbok(cusName);
		
		if(select == 0)	//예약된 이름이 없을 때
		{
			System.out.println("   [[ 취소실패 : 예약되어있지 않은 사용자입니다. ]]");
		}
		else
		{
			System.out.print(" ※ 위 정보의 좌석을 취소하시겠습니까? (동의[Y]/거절[아무키나입력하세요.]) ");
			input = scan.nextLine();
			if(input.equals("Y") || input.equals("y"))
			{
				Seat[person[select].seatClass-1][person[select].seatNo]="-";
				person[select] = new Customer();
				System.out.println("\n  [[ 취소가 완료되었습니다. ]]");
			}
			else
			{
				System.out.println("\n  좌석 취소를 취소합니다. \n  메인메뉴로 돌아갑니다.");
			}
		}
	}
	
	
//************************************************************** 중복 체크(오류 없음 : 0)	
	static int jungbok(int seatClass, int seatNo)
	{
		if(seatNo <= 0 || seatNo > 10)
		{
			System.out.println("좌석 번호가 잘못되었습니다. 다시 입력해주세요.\n");
			return 1;
		}
		
		for(int i=0; i<cusCnt; i++)
		{
			if(seatClass == person[i].seatClass && seatNo == person[i].seatNo)
			{
				System.out.println("해당 좌석은 이미 예약된 자석입니다.");
				return 1;
			}	
		}
		
		return 0;
	}
	
	
	static int jungbok(String Name)
	{
		for(int i=0; i<cusCnt; i++)
		{
			if(Name.equals(person[i].Name))
			{
				System.out.println("\n  [[ 예약되어있는 고객입니다. ]]");
				System.out.println("   - 예약자 이름 : " + person[i].Name);
				System.out.println("   - 예약석 등급 : " + seatClassOutput(person[i].seatClass));
				System.out.println("   - 예약석 번호 : " + person[i].seatNo);
				System.out.println("\n  확인하셨으면 [Enter]를 입력하세요.");
				
				return i;	//중복된 이름의 인덱스 출력
			}
		}
		
		return 0;
	}


//...
	static String seatClassOutput(int seatClass)
	{
		if(seatClass==1)
			return "S";
		else if(seatClass==2)
			return "A";
		else if(seatClass==3)
			return "B";
		else
		{
			System.out.println("등급이 잘못선택 되었습니다.");
			return null;
		}
	}
	
	static int inputExcep()
	{
		int outInput = -1;

		try
		{
			outInput = scan.nextInt();
		}
		catch(InputMismatchException e)
		{
			scan.nextLine();
		}
		
		return outInput; 
	}
}