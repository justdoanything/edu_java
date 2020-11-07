/*
 * 단순한 매출 계산기
 */


package edu_java;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class hw01Complete 
{
	static int totalMoney = 0;									//총 매출액
	static String[] prodList = new String[1000];		//상품 이름 목록
	static int[] prodPrice = new int[1000];				//상품 가격 목록
	static int[] prodSellNum = new int[1000];			//상품 판매 개수 목록
	static int prodCnt =0;										//상품 개수 목록
	
	static Scanner scan = new Scanner(System.in);
	
	//상품 등록
	public static void insertProd()
	{		
		String ProdName;	
		
		while(true)
		{
			System.out.println((prodCnt+1) + "번째 상품의 이름을 입력하세요 : [ 그만 등록하시려면 'e'를 누르세요. ] ");
			ProdName = scan.nextLine();

			if(ProdName.equals("e"))
			{
				System.out.println("[[ 물품 등록을 종료합니다. ]]\n");
				return;
			}
			prodList[prodCnt] = ProdName;

			boolean excep = true;  //오류가 있으면 true 없으면 false
			while(excep == true)
			{
				System.out.print(prodList[prodCnt] + "의 가격을 입력하세요 :  ");
				excep = false;
				
				//숫자 형식만 받기 위한 오류 처리
				try		
				{
					prodPrice[prodCnt] = scan.nextInt();
				}
				catch(InputMismatchException e)
				{
					scan.nextLine();
					System.out.println("\n[[ ERROR ]] \n!! 입력형식 오류입니다. !!\n   -- 숫자로 다시 입력해 주세요. -- \n");
					excep = true;
				}
				catch(Exception e1)
				{
					scan.nextLine();
					System.out.println("\n[[ ERROR ]] \n!! 알 수 없는 오류입니다. !!\n -- 다시 입력해 주세요. -- \n");
					excep = true;	
				}
			}	
			
			System.out.println();
		
			scan.nextLine();
			prodCnt += 1;
		}
	}

	//상품 판매
	public static void sellProd()
	{
		Random rand = new Random();
		
		String input;		//프로그램 진행을 위한 변수
								//'c'일때 진행, 'e'일때 종료
		
		int sellectProd = 0;		//상품 선택 	
		int sellNum = 0;			//상품 판매 개수
		
		do
		{
			System.out.println("진행을 원하시면 [c]를, 끝내시려면 [e]를 입력하세요.");
			input = scan.nextLine();
			
			if(input.equals("c") || input.equals("C") )	//프로그램 계속 진행
			{			
				sellectProd = rand.nextInt(prodCnt);		//어떤 상품 선택했는지
				sellNum = rand.nextInt(9)+1;				//몇개팔았는지

				//물품 판매
				if(sellectProd < prodCnt)				
				{
					prodSellNum[sellectProd] += sellNum;
					
					System.out.println("\n[[ '" + prodList[sellectProd] + "' 상품을 " + sellNum +"개 팔았습니다! ]]\n");
					
					totalMoney += prodPrice[sellectProd] * sellNum;
					
					totalSell('n');	  //현재 총 매출 출력
				}
				else	//물품 선택을 잘못했을 경우(있을수는없다.)
					System.out.println("[[ !! 상품 선택이 잘못되었습니다. !! ]] \n - 다시 진행해주세요.\n\n");
			}
			
			//프로그램 종료
			else if(input.equals("e") || input.equals("E"))	
			{
				totalSell('y');		//마지막 총 매출 출력
				System.out.println("[[ 프로그램을 종료합니다 ]]");
				break;
			}
			
			//예외처리
			else	
			{
				System.out.println("입력 값이 잘못되었습니다. 'c'나 'e'를 눌러야합니다.");
				System.out.println("다시 눌러주세요.\n");
			}
		} while(true);
	}
	
	//판매 현황 출력 함수
	public static void totalSell(char end)
	{
		if(end=='n')
				System.out.println("[[현재 판매 현황 ]]");
		else if(end=='y')
				System.out.println("\n\n[[ 총 판매 현황 ]]");
		else
		{
			System.out.println("매출함수의 매개변수가 잘못되었습니다.");
			return;
		}
		
		for(int i =0; i<prodCnt; i++)
			System.out.println("   - " + prodList[i] + " 상품을 " + prodSellNum[i] + "개 팔았습니다.");
		
		System.out.println(" $$ 총 매출은 " + totalMoney +"원 입니다. $$ \n\n");
	}
	

	public static void main(String[] args) 
	{
		//물품 등록 함수
		insertProd();
		
		//등록한 물품이 없을 경우 프로그램 종료
		if(prodCnt == 0)
		{
			System.out.println("등록된 상품이 없습니다.");
			System.out.println("[[ 프로그램을 종료합니다. ]]");
			return;
		}
		
		//물품 판매 함수
		sellProd();
		
		scan.close();
	}
}
