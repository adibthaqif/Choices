/*Whatcom Community College - Winter 2019
CS240 Data Structures and Algorithm Analysis
Professor Ryan Parsons
AUTHORS: Adib Thaqif, Andrew Jacobi, Donald Strong, and Micah Miller*/

public class Main
{
	private static int round=1;
	private static int eventCounter=0;
	private static int[]aspectValues=new int[2];
	
	public static void main(String[]args)
	{
		while(!isGameOver(round, aspectValues))
		{
			while(!isRoundOver(eventCounter))
			{
				//play();
				
				
				
				round=round++;
			}
		}
	}
	
	public static void play()
	{
		//ChoicesManager();
        //viewHighScore();
        //save();
        //quit();
		eventCounter=eventCounter++;
	}
	
	public static void save()
    {
    	//if(keyPress=='s')
		//
    }
    
    public static void quit()
    {
        /*if(keyPress=='q')
        return isGameOver=true;
         */
    }
    
	public static void viewHighScore()
	{
		
	}
	
	public static boolean isGameOver(int round,int[]aspectValue)
	{
		if(round == 1)
		{
			return true;
		}else if(aspectValue[0]<=25||aspectValue[1]<=25||aspectValue[2]<=25){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isRoundOver(int eventCounter)
	{
		if(eventCounter==eventCounter*round)
		{
			return true;
		}else{
			return false;
		}
	}
	
	public static void aspectMax(int[]aspectValues)
    {
        boolean x=false;
        while(aspectValues[0]>=80||aspectValues[1]>=80||aspectValues[2]>=80)
        {
            System.out.println("One of your aspects is too high.\n" +
                    "If it remains at that height another one of your aspects will decrease.");
            x=true;
            if(x=true)
            {
                aspectValues[0]=aspectValues[0]-8;
            }
        }
        x=false;
    }
}