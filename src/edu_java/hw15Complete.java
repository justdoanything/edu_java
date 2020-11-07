/*
 * GIF FILE 읽어서 복사하면서 10% 진행될때마다 화면에 _ 표시
 */
package edu_java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class hw15Complete 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		FileInputStream fileIn = new FileInputStream("D:\\Lighthouse.jpg");
		FileOutputStream fileOut = new FileOutputStream("D:\\copy.jpg");
		
		File baseImg = new File("D:\\Lighthouse.jpg");
		long baseImgSize = baseImg.length();
		int cnt =0;
		int per=10;
		
		int copy;
	
		try
		{
			while((copy=fileIn.read()) != -1)
			{
				fileOut.write((char)copy);
				cnt++;
				if(cnt*100/baseImgSize==per)
				{
					System.out.println(per+"%");
					per+=10;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		try
		{
			fileIn.close();
			fileOut.close();
		}
		
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
	}
}
