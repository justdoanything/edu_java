/*
 * 도형 삽입/종류별 삭제
 */
package edu_java;

import java.util.InputMismatchException;
import java.util.Scanner;

interface printFigure
{
	void print_Figure();
}

class saveFigure implements printFigure
{
	String figureName;
	
	saveFigure()
	{
		figureName = "";
	}
	
	saveFigure(String name)
	{
		this.figureName = name; 
	}

	@Override
	public void print_Figure() 
	{
		System.out.print(" " + this.figureName + " ");
	}
}


public class hw06Complete 
{
	static Scanner scan = new Scanner(System.in);
	
	static saveFigure[] save_figure = new saveFigure[100];
	
	static String[] figureSet = {"ㅡ", "■","●"};
	static int figureCnt = 0;
	
	public static void main(String[] args) 
	{
		int menuSelect;
		
		while(true)
		{
			System.out.print("\n[ 삽입(1), 조회(2), 삭제(3), 종료(4) ] >> ");
			menuSelect = inputExcep();
			
			if(menuSelect == 1)
				insert();
			
			else if(menuSelect == 2)
				search();
			
			else if(menuSelect == 3)
				deleteMenu();

			else if(menuSelect == 4)
			{
				System.out.println("  [ 프로그램이 종료됩니다. ]");
				break;
			}

			else 
				System.out.println("메뉴에 해당하지 않는 번호입니다. 다시 입력해주세요.");
		}
	}
		
//***************************************************** 삽입
	static void insert()
	{
		int figureSelect; 		//도형 선택 번호
		
		do
		{
			System.out.print("  [ 삽입할 도형 종류 : Line(1), Rect(2), Circle(3), 이전메뉴(0) ] >> ");
			figureSelect = inputExcep();
		
			// 메뉴 선택 번호를 받고 figureSet.length를 통해 도형이 추가되었을 때 가변적으로 사용
			if(figureSelect >= 0 && figureSelect <= figureSet.length )
			{
				//상위 메뉴로
				if(figureSelect==0)	
					return;
				
				else
				{
					//도형 생성, 도형 수 증가
					save_figure[figureCnt] = new saveFigure(figureSet[figureSelect-1]);	
					figureCnt++;
					
					System.out.println("    [ SUCCESS : "+ figureSet[figureSelect-1] +" 이 삽입되었습니다. ]\n");
					return;
				}
			}

			//예외처리 : 메뉴 선택 번호 오류
			else
				System.out.println("    [ FAIL : 입력값이 잘못되었습니다. 0~"+figureSet.length+"사이의 정수 값을 입력해주세요. ]\n");
		}while(true);
		
	}
	
	
//***************************************************** 삭제 메뉴( 삭제 도형 및 삭제 유형 선택)
	static void deleteMenu()
	{
		int figureSelect;	// 도형 선택
		int delMenuSelect;	// 삭제 유형 선택
		
		do
		{
			System.out.print("  [ 삭제할 도형 종류 : Line(1), Rect(2), Circle(3), 이전메뉴(0)] >> ");
			figureSelect = inputExcep();
			
			if(figureSelect == 0)	//메인메뉴로
				return;
			
			// 메뉴 선택 번호를 받고 figureSet.length를 통해 도형이 추가되었을 때 가변적으로 사용
			else if(figureSelect >= 1 && figureSelect <= figureSet.length )
			{
				if(checkFigure(figureSelect))	//삭제 할 도형이 리스트에 있는지 먼저 체크
				{
					do		//삭제할 도형이 리스트에 있으면 실행
					{
						System.out.print("    [ LIFO삭제 (1), FIFO삭제(2), 전체삭제(3), 이전메뉴(0) ] >> ");
						delMenuSelect = inputExcep();
						
						if(delMenuSelect == 0)	//상위 메뉴로
							break;	
	
						else if(delMenuSelect >= 1 && delMenuSelect <= 3 )	
						{	
							//LIFO 삭제
							if(delMenuSelect==1) 
								deleteFigureLIFO(figureSelect);
					
							//FIFO, ALL 삭제
							else if(delMenuSelect==2 || delMenuSelect == 3) 
								deleteFigureFIFOALL(figureSelect,delMenuSelect);
							
							//예외 처리
							else System.out.println("    [ FAIL : 입력값이 잘못되었습니다. 0~3 사이의 정수 값을 입력해주세요. ]\n");
							
							return;
						}
						
						//예외처리 : 메뉴 선택 오류
						else
							System.out.println("      [ FAIL : 입력값이 잘못되었습니다. 0~3 사이의 정수 값을 입력해주세요. ]\n");
					}while(true);
				}
				else	//해당하는 도형이 없으면 안내문 출력하고 재입력 받기
					System.out.println("    [ FAIL : 해당하는 도형이 없어서 삭제할 수 없습니다. ]\n");
			}

			//예외처리 : 메뉴 선택 오류
			else
				System.out.println("    [ FAIL : 입력값이 잘못되었습니다. 0~"+figureSet.length+"사이의 정수 값을 입력해주세요. ]\n");
		}while(true);
	}


//*****************************************************  삭제 도형 번호, 삭제 유형을 전달받고 해당하는 삭제 실행
	static void deleteFigureLIFO(int figSelect)
	{
		int LIFO_index = -1;
		
		//탐색 후 삭제 작업
		for(int i = (figureCnt-1); i >= 0; i--)
		{
			if(save_figure[i].figureName.equals(figureSet[figSelect-1]))
			{
				save_figure[i] = new saveFigure();	//초기화
				figureCnt--;									//도형 카운트 감소
				
				LIFO_index = i;			//삭제한 인덱스 저장, 인덱스 뒤에 이름 앞으로 땡겨올때 사용
				System.out.println("      [ SUCCESS : 가장 마지막에 넣은 " + figureSet[figSelect-1] + " 도형을 삭제했습니다. ]\n");
				break;						//탐색 종료(for문 탈출)
			}
		}

		//삭제후 처리 작업
		if(LIFO_index == -1)	//일치하는 도형이 없을 때
		{
			System.out.println("      [ FAIL : 일치하는 도형이 없어서 삭제를 실패했습니다. ]\n");
			return;
		}
			else	//삭제 후 null값 된 자리를 뒤에 이름들을 땡겨와서 채운다.
		{
			for(int i=LIFO_index; i<figureCnt; i++)
				save_figure[i].figureName = save_figure[i+1].figureName;
			return;
		}
	}

	static void deleteFigureFIFOALL(int figSelect, int delSelect)
	{
		int FIFO_index = -1;
		int allCnt = figureCnt;	//전체 삭제에서 for문 돌릴때 범위를 설정하기 위해 전체 크기 저장

		//*** 탐색 후 삭제 작업
		for(int i=0; i<allCnt; i++)
		{
			if(save_figure[i].figureName.equals(figureSet[figSelect-1]))	
			{
				// [전체삭제] 이면 for문을 끝까지 돌린다.
				save_figure[i] = new saveFigure();		
				figureCnt--;									
						
				// [FIFO] 이면 처음 인덱스를 찾고 for문 탈출
				if(delSelect == 2)	
				{	
					FIFO_index = i;		//삭제한 인덱스 저장, 인덱스 뒤에 이름 앞으로 땡겨올때 사용
					System.out.println("      [ SUCCESS : 가장 처음에 넣은 " + figureSet[figSelect-1] + " 도형을 삭제했습니다. ]\n");		
					break;		//탐색 종료(for문 탈출)
				}
			}
		}
		
		//*** FIFO 삭제후 처리 작업
		if(delSelect == 2)
		{
			if(FIFO_index == -1)
			{
				System.out.println("    [ FAIL : 일치하는 도형이 없어서 삭제를 실패했습니다. ]\n");
				return;
			}
				
			else  //삭제 후 null값 된 자리를 뒤에 이름들을 땡겨와서 채운다.
			{	
				for(int i=FIFO_index; i<figureCnt; i++)
					save_figure[i].figureName = save_figure[i+1].figureName;
				return;
			}	
		}
		
		//*** 전체 삭제후 처리 작업
		else if(delSelect == 3)
		{
			System.out.println("      [ SUCCESS : " + figureSet[figSelect-1] + " 도형을 모두 삭제했습니다. ]\n");
			for(int i=0; i<allCnt; i++)
			{
				if(save_figure[i].figureName.equals(""))	//이름이 없는, 삭제한 객체 접근
				{
					for(int j=i+1; j<allCnt; j++)
					{
						if(save_figure[j].figureName.equals("")==false)	//이름이 있는 객체 접근
						{
							save_figure[i].figureName = save_figure[j].figureName;	//이름이 없는 객체 위치에 이름이 있는 객체 넣기
							save_figure[j] = new saveFigure();	//이름이 있는 객체를 넘겨줬기 때문에 그 객체 초기화
							break;
						}
					}
				}
			}
		}
		else
			System.out.println("      [ ERROR : deleteFigureFIFOALL 메소드의 delSelect 값 오류입니다.");
	}
	

//*****************************************************  조회
	static void search()
	{
		System.out.print("  [ Figure List : ");
		for(int i=0; i<figureCnt; i++)
			save_figure[i].print_Figure();
		System.out.print("]\n");
	}
	

//***************************************************** 도형이 리스트에 있는지 체크하고 있으면 true, 없으면 false를 반환하는 boolean 메소드
	static boolean checkFigure(int figSelect)
	{
		for(int i=0; i<figureCnt; i++)
		{
			if(save_figure[i].figureName.equals(figureSet[figSelect-1]))	
			{
				return true;
			}
		}
		return false;
	}

	
//...
	static int inputExcep()
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

