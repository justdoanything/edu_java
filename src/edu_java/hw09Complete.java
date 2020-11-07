/*
 * 은행계좌 프로그램
 */
package edu_java;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

class bankCustomer
{
	String cusName;
	Hashtable<String, Integer> Account = new Hashtable<>();
				// 계좌번호,   잔액
	
	int accountCnt=1;
	
	bankCustomer()
	{
		this.cusName = null;
		this.Account = new Hashtable<>(); 
		this.accountCnt = 0;
	}
	
	bankCustomer(String cusName, String accountNumber)
	{
		this.cusName = cusName;
		this.accountCnt = 1;
		this.Account.put(accountNumber, 0);
	}
}



public class hw09Complete 
{
	static Scanner scan = new Scanner(System.in);
	static bankCustomer[] Bcus = new bankCustomer[100];
	static int cusCnt = 0;

	
	public static void main(String[] args) 
	{
		while(true)
		{
			System.out.print("[[ (1)신규계좌발급, (2)입금, 출금, 송금 (3)조회, (4)종료 ]] >> ");
			int menuInput = inputExcep();
			
			if(menuInput == 1)
				makeAccount();
			else if(menuInput == 2)
				selectMoney();
			else if(menuInput == 3)
				readAccount();	
			else if(menuInput == 4)
			{
				System.out.println("  [[ 프로그램을 종료합니다. ]]");
				break;
			}
			else
				System.out.println("  [ 메뉴 선택값이 잘못되었습니다. 다시 입력해주세요. ]\n");
		}
	}
	
	static void makeAccount()//신규계좌발급
	{
		String input_cusName;
		String accountNum;
		int index;
		
		do
		{
			scan.nextLine();
			System.out.print("    [ 본인의 이름을 입력하세요 ] >> ");
			input_cusName = scan.nextLine();
			
			index = jungbok(input_cusName);
			
			if(index != -1)
			{
				System.out.print("      [ 이름이 이미 존재합니다. 새로운 계좌만 만드시겠습니까? (Y/N) ] >> ");
				String inputYN = scan.nextLine();
				
				if(inputYN.equals("Y") || inputYN.equals("y"))
				{
					accountNum = makeRandAccountNumber();
					Bcus[index].Account.put(accountNum, 0);
					Bcus[index].accountCnt++;
					System.out.println("        [ 새로운 계좌("+accountNum+")가 등록되었습니다. ]\n");
					break;
				}
				
				else if(inputYN.equals("N") || inputYN.endsWith("n"))
				{
					System.out.println();
					break;
				}
					
				else
					System.out.println("        [ Y/N 입력이 잘못되었습니다. Enter 키를 누르세요]");
			}
			
			else if(index == -1)
			{
				accountNum = makeRandAccountNumber();
				
				Bcus[cusCnt] = new bankCustomer(input_cusName,accountNum);
				System.out.println("      [ 신규 고객이 가입되었습니다. ]");
				System.out.println("        - 이름 : " + Bcus[cusCnt].cusName);
				System.out.println("        - 계좌번호 : " + accountNum+"\n");
				cusCnt++;
				break;
			}
			
			else
			{
				System.out.println("makeAccount() 메소드에서 오류");
				break;
			}
		}while(true);
	}
	
	static String makeRandAccountNumber()//랜덤 계좌번호 반환
	{
		Random rand = new Random();
		String[] accountRand = new String[5];
		String accountNumber = ""; 
		
		do
		{
			accountRand[0] =String.valueOf(rand.nextInt(899)+100);
			accountRand[1] = "-";
			accountRand[2] = String.valueOf(rand.nextInt(899)+100);
			accountRand[3] = "-";
			accountRand[4] = String.valueOf(rand.nextInt(899999)+100000);
			
			for(int i=0; i<5; i++)
				accountNumber = accountNumber.concat(accountRand[i]);		
		}while(jungbok(accountNumber) != -1);
		
		return accountNumber;
	}
	
	static int jungbok(String nameORaccount)//이름, 계좌번호가 같은지 체크, 같으면 해당 인덱스 반환, 중복이 없으면 -1 반환
	{		
		for(int i=0; i<cusCnt; i++)
		{
			if(Bcus[i].cusName.equals(nameORaccount)) 
				return i;
			
			if(Bcus[i].Account.contains(nameORaccount))
			{
				System.out.println("계좌번호가 중복되었습니다.");
				return i;
			}
		}
		return -1;
	}
	
	static void selectMoney()//계좌 조회 -> 입금,출금,송금 선택
	{
		String input_name, accountNum;
		
		if(cusCnt ==0 )
		{
			System.out.println("  [ 등록된 회원이 없습니다. ]");
			return;
		}
		
		scan.nextLine();
		System.out.print("  [ 이름을 검색하세요. ] >> ");
		input_name = scan.nextLine();
	
		if(searchNameAccount(input_name))
		{
			int index = jungbok(input_name);
		
			while(true)
			{
				System.out.print("\n    [ 사용할 계좌번호를 입력하세요. ] >> ");
				accountNum = scan.nextLine();
				
				if(Bcus[index].Account.containsKey(accountNum) == false)
					System.out.println("        [ 계좌 번호 올바르게 입력해야 합니다. Enter를 치세요.]");
				
				else
				{
					System.out.println("        ※ 유효한 계좌입니다.");
					
					System.out.print("      [ (1) 입금,  (2) 출금,  (3) 송금 ] >> ");
					int menu_input = inputExcep();
					
					if(menu_input == 1)
					{
						depositMoney(input_name, accountNum);
						break;
					}
					else if(menu_input == 2)
					{
						withdrawMoney(input_name, accountNum);
						break;
					}
					else if(menu_input == 3)
					{
						sendMoney(input_name, accountNum);
						break;
					}
					else
						System.out.println("      [ 입력값이 잘못되었습니다. 다시 입력해주세요.]\n");
				}
			}
		}
			
		else
		{
			System.out.println();
			return;
		}
	}
		
	static void depositMoney(String input_name, String accountNum)//입금
	{
		int money;

		while(true)
		{
			scan.nextLine();

			int index = jungbok(input_name);
			
			System.out.print("        [ 입금하실 금액($)을 입력하세요. ] >> ");
			money = inputExcep();
				
			Bcus[index].Account.put(accountNum, Bcus[index].Account.get(accountNum)+money );
			System.out.println("          [ 성공!! 입금 후 잔액 : " + Bcus[index].Account.get(accountNum)+"원 ]\n");
			break;
		}
	}
	
	static void withdrawMoney(String input_name, String accountNum)//출금
	{
		int money;
		
		while(true)
		{
			scan.nextLine();
			
			int index = jungbok(input_name);
			
			System.out.print("        [ 출금하실 금액($)을 입력하세요. ] >> ");
			money = inputExcep();
				
			if(Bcus[index].Account.get(accountNum) < money)
			{
				System.out.println("잔액 부족");
				return;
			}
					
			Bcus[index].Account.put(accountNum, Bcus[index].Account.get(accountNum)-money );
			System.out.println("          [ 성공!! 출금 후 잔액 : " + Bcus[index].Account.get(accountNum)+"원 ]\n");
			break;
		}
	}
	
	static void sendMoney(String input_name, String sendAccountNum)//송금
	{
		String receiveAccountNum;
		String YN;
		int money, sendIndex, receiveIndex;
		
		while(true)
		{
			scan.nextLine();
			
			sendIndex = jungbok(input_name);
			
			if(Bcus[sendIndex].Account.containsKey(sendAccountNum)==false)
				System.out.println("        [ 계좌 번호 올바르게 입력해야 합니다. Enter를 치세요.]");
				
			else
			{
				System.out.print("        [ 받을 계좌번호를 모두 입력하세요. ] >>");
				receiveAccountNum = scan.nextLine();
				
				receiveIndex = checkAccount(receiveAccountNum);
				if(receiveIndex != -1)
				{
					System.out.print("        [ 보내실 하실 금액($)을 입력하세요. ] >> ");
					money = inputExcep();
					
					scan.nextLine();
					System.out.print("          $$ " + sendAccountNum +" 에서 "+ receiveAccountNum +" 로 "+ money +"원을 보내는 것이 맞나요? (Y/N) >> ");
					YN = scan.nextLine();
					
					if(YN.equals("Y") || YN.equals("y"))
					{
						if(Bcus[sendIndex].Account.get(sendAccountNum) < money)
						{
							System.out.println("잔액 부족");
							return;
						}
						Bcus[sendIndex].Account.put(sendAccountNum, Bcus[sendIndex].Account.get(sendAccountNum)-money );
						Bcus[receiveIndex].Account.put(receiveAccountNum, Bcus[receiveIndex].Account.get(receiveAccountNum)+money);
						System.out.println("\n          [ 성공!! 송금 후 "+sendAccountNum+"의 잔액 : " + Bcus[sendIndex].Account.get(sendAccountNum)+"원 ]");
						System.out.println("          [ 성공!! 받은 통장 " + receiveAccountNum + "의 잔액 : " + Bcus[receiveIndex].Account.get(receiveAccountNum)+"원 ]\n");
						break;
					}
					else if(YN.equals("N") || YN.equals("n"))
						return;
					else
						System.out.println("Y 또는 N을 입력해야합니다.");
				}
				else
					System.out.println("        [ 유효하지 않은 통장번호 입니다. Enter 입력 후 다시 입력해주세요.]");
			}
		}
	}
	
	static void readAccount()//조회
	{
		int menuInput;
		String input_name;
		
		if(cusCnt==0)
		{
			System.out.println("  [ 등록된 회원이 없습니다. ]");
			return;
		}
		
		while(true)
		{
			System.out.print("  [ 1.전체조회, 2.이름 검색 ] >> ");
			menuInput = inputExcep();
			
			if(menuInput==1)
			{
				searhAllAccount();
				System.out.println();
				break;
			}
			
			else if(menuInput == 2)
			{				
				scan.nextLine();
				System.out.print("    [ 검색할 이름을 입력하세요. ] >> ");
				input_name = scan.nextLine();
				
				searchNameAccount(input_name);
				System.out.println();
				break;
			}
			
			else
				System.out.println("    [ 메뉴 선택 값이 잘못되었습니다. ]\n");
		}
	}

	static void searhAllAccount()//전체 계좌 조회
	{
		String AccountNum;
		
		for(int i=0; i<cusCnt; i++)
		{
			Set<String> set = Bcus[i].Account.keySet();
			Iterator<String> it = set.iterator();
			
			System.out.println("    * "+Bcus[i].cusName + "의 계좌 목록");
			while(it.hasNext())
			{
				AccountNum = it.next();
				System.out.println("      $ "+ AccountNum + " : " + Bcus[i].Account.get(AccountNum) + "원");
			}
			System.out.println();
		}
	}
	
	static boolean searchNameAccount(String input_name)//이름에 해당하는 계좌 조회, 해당 이름의 계좌가 없으면 false, 있으면 true 반환
	{
		String AccountNum;

		if(jungbok(input_name) == -1)
		{
			System.out.println("      [ 해당 회원은 가입되지 않은 회원입니다. ]");
			return false;
		}
		Set<String> set = Bcus[jungbok(input_name)].Account.keySet();
		Iterator<String> it = set.iterator();
		
		System.out.println("    * "+Bcus[jungbok(input_name)].cusName +"의 계좌목록");
		while(it.hasNext())
		{
			AccountNum = it.next();
			System.out.println("      $ "+ AccountNum+" : " + Bcus[jungbok(input_name)].Account.get(AccountNum)+"원");
		}
		return true;
	}
	
	static int checkAccount(String accountNum)//유효한 통장 번호인지 검사, 유효하면 인덱스, 무효하면 -1 반환
	{
		for(int i=0; i<cusCnt; i++)
		{
			Set<String> set = Bcus[i].Account.keySet();
			Iterator<String> it = set.iterator();
			
			while(it.hasNext())
			{
				if(accountNum.equals(it.next()))
				{
					System.out.println("        ※ 유효한 계좌입니다.");
					return i;
				}
			}
		}
		return -1;
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
