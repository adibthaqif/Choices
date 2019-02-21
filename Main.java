package hashTable;
/*Whatcom Community College - Winter 2019
CS240 Data Structures and Algorithm Analysis
Professor Ryan Parsons
AUTHORS: Adib Thaqif, Andrew Jacobi, Donald Strong, and Micah Miller*/

public class Main
{
	private static int year=1;
	private static int round=1;
	private static int eventCounter=0;
	
	public static void main(String[]args)
	{
		while(!isGameOver(round, 0)==true)
		{
			while(!isRoundOver(eventCounter)==true)
			{
				//play();
				eventCounter=eventCounter++;
				
				
				round=round++;
			}
		}
	}
	
	public static void play()
	{
		
	}
	public static void viewHighScore()
	{
		
	}
	
	public static boolean isGameOver(int round,int aspect)
	{
		if(round == 4)
		{
			return true;
		}else if(aspect<=30){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isRoundOver(int e)
	{
		if(e==e*year)
		{
			return true;
		}else{
			return false;
		}
	}
}