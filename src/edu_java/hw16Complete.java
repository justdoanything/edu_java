package edu_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class NameMap
{
	HashMap<String, String> NameMap = new HashMap<>();
	
	NameMap(String nameFilePath) throws IOException
	{
		makeNameMap(nameFilePath);
	}
	
	void makeNameMap(String nameFilePath) throws IOException
	{
		String nameInput;
		String[] nameInputArr;
		
		BufferedReader input = new BufferedReader(new FileReader(nameFilePath));
		
		try
		{	
			while( (nameInput = input.readLine()) != null)
			{
				nameInputArr = nameInput.split(",");
				NameMap.put(nameInputArr[0], nameInputArr[1]);
			}
		}
		catch(FileNotFoundException FE1)
		{
			System.out.println("파일 입력받을 때 오류 발생 : " + FE1.getMessage());
		}
		
		input.close();
	}
}


class Price extends NameMap	//파일 1번만 입력받기 위해서 하나의 클래스로 작성
{
	HashMap<String, Double> PriceRiseRate = new HashMap<>();
	HashMap<String, Double> PriceDecRate = new HashMap<>();
	List<Double> upList = new ArrayList<>();
	List<Double> downList = new ArrayList<>();
	
	Price(String nameFilePath, String FilePath) throws IOException 
	{
		super(nameFilePath);		//NameMap 생성
		
		makePriceList(FilePath);	//HashMap 2개 생성 - PriceRiseRate, PriceDecRate
		
		makeUpList();
		makeDownList();
	}
	
	void makePriceList(String priceFilePath)
	{
		Scanner scan = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("###.###");	//포맷 설정
		
		String priceInput;
		String[] priceInputArr;
		double priceRise = 0;
		
		try
		{
			scan = new Scanner(new File(priceFilePath));
			scan.nextLine();
			while(scan.hasNextLine())
			{
				priceInput = scan.nextLine();
				priceInputArr = priceInput.split(",");
				
				try
				{
					if(!(priceInputArr[3].equals("0")) && !(priceInputArr[2].equals("0")))	//시가, 종가가 0 인건 계산에서 제외
					{
						// 상승률 : ((종가/시가) - 1) * 100
						priceRise = ((Double.parseDouble(priceInputArr[2]) / Double.parseDouble(priceInputArr[3]))-1)*100;
						priceRise = Double.parseDouble(df.format(priceRise));
					}
				}
				catch(Exception E)	//값이 이상한 항목 출력
				{
					/*System.out.println("!!ERROR!! 항목 : " + NameMap.get(priceInputArr[0]) 
																		+ " / " + priceInputArr[0] 
																		+ " / " + priceInputArr[1] 
																		+ " / " + priceInputArr[2] 
																		+ " / " + priceInputArr[3]);*/
				}
				
				try
				{
					PriceRiseRate.put(priceInputArr[0], priceRise);					
				}
				catch(NullPointerException nullE)
				{
					PriceRiseRate.put(priceInputArr[0], priceRise);	//초기값 삽입
				}
				
				try
				{
					if(PriceDecRate.get(priceInputArr[0]) > priceRise)		// PriceDecRate : 더 작을때만 삽입
						PriceDecRate.put(priceInputArr[0], priceRise);
				}
				catch(NullPointerException nullE)
				{
					PriceDecRate.put(priceInputArr[0], priceRise);	//초기값 삽입
				}
			}
			scan.close();
		}
		catch(FileNotFoundException FE1)
		{
			System.out.println("파일 입력받을 때 오류 발생 : " + FE1.getMessage());
		}
	}
	
	
	
	void makeUpList()			// sort 라이브러리를 사용하기 위해 리스트에 저장
	{
		String cmpCode;
		
		Set<String> set = PriceRiseRate.keySet();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext())
		{
			cmpCode=it.next();
			if(!(PriceRiseRate.get(cmpCode).isInfinite()) && !(PriceRiseRate.get(cmpCode).isNaN()) && (PriceRiseRate.get(cmpCode) != -100))	//예외처리
				upList.add(PriceRiseRate.get(cmpCode));
		}
		Collections.sort(upList,Collections.reverseOrder());	// 내림차순 정렬
	}
	
	void printUpList(int no)	//몇 순위까지 뽑을건지 no로 설정
	{
		Set<String> set = PriceRiseRate.keySet();
		Iterator<String> it = set.iterator();
		
		String cmpCode;
	
		System.out.println("[[ 가격 상승률이 큰 종목 ]]");
			
		for(int i=0; i<no; i++)
		{
			it = set.iterator();
			while(it.hasNext())	
			{
				cmpCode = it.next();
				if(PriceRiseRate.get(cmpCode) == upList.get(i))		//중복값이 있어도 다 출력 가능
				{
					System.out.println((i+1)+"순위 : " + NameMap.get(cmpCode) + " >> " + upList.get(i) + "%");
				}
			}
		}
	}

	
	
	void makeDownList()
	{
		String cmpCode;
		
		Set<String> set = PriceDecRate.keySet();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext())
		{
			cmpCode=it.next();
			if(!(PriceDecRate.get(cmpCode).isInfinite()) && !(PriceDecRate.get(cmpCode).isNaN()) && (PriceDecRate.get(cmpCode) != -100))
				downList.add(PriceDecRate.get(cmpCode));
		}
		Collections.sort(downList);		// 오름차순 정렬
	}
	
	void printDownList(int no)
	{
		Set<String> set = PriceDecRate.keySet();
		Iterator<String> it = set.iterator();
		
		String cmpCode;
	
		System.out.println("[[ 가격 하락률이 큰 종목 ]]");
			
		for(int i=0; i<no; i++)
		{
			it = set.iterator();
			while(it.hasNext())
			{
				cmpCode = it.next();
				if(PriceDecRate.get(cmpCode) == downList.get(i))
				{
					System.out.println((i+1)+"순위 : " + NameMap.get(cmpCode) + " >> " + downList.get(i) + "%");
				}
			}
		}
	}

}

public class hw16Complete 
{
	public static void main(String[] args) throws IOException 
	{	
		Price pr = new Price("D:\\jongmok.csv", "D:\\stockdb.csv");		//NameMap 파일 경로, 가격 파일 경로
		
		pr.printUpList(5);			//몇순위까지 뽑을건지 정함
		
		System.out.println();
		
		pr.printDownList(5);
	}
}


