package edu_java;

class SharedPrinter	
{
	ing p1 = new ing();

	SharedPrinter()
	{
	
	}
	
	class ingg 
	{
		ingg()
		{
			p1.start();
			p1.start();
		}
	
	}
	
	class ing extends Thread
	{
		public void run()
		{
			for(int i=0; i<10; i++)
				System.out.println(i);
		}
	}
	
}

public class test 
{
	public static void main(String[] args) 
	{
		 
		
		

	}
}
