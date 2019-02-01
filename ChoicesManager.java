/*Whatcom Community College - Winter 2019
  CS240 Data Structures and Algorithm Analysis
  Professor Ryan Parsons
  AUTHORS: Adib Thaqif, Andrew Jacobi, Donald Strong, and Micah Miller

  Write a manager that reads in text file, create a new event object with text as data field,
  ints as punishment and reward fields.
  use if else to check if it starts with  E, Y or .
*/

import java.io.*;
import java.util.Scanner;
import java.util.Hashtable;


public class ChoicesManager {
   private Event event;
   //private int reward;
   //private int punishment;
   //private String prompt;
   private static int size = 5;
   private Event[] eventArray = new Event[size];
   private static int count = 0;

   public static void main(String[] args)throws FileNotFoundException {
   
      Scanner console = new Scanner(System.in);
      System.out.println("Please input file name");
      String fileName = console.nextLine();
   
      if(fileName == null) {
         throw new IllegalArgumentException();
      }
   
      choicesManage(fileName);
   
   }

   public static void choicesManage(String file){
   
      File fileName = new File(file);
      Scanner scanner = new Scanner(fileName);
   
      while(scanner.hasNextLine()){
      
         //store first string, first int and second int in event objec
         String prompt = scanner.nextLine();
         int reward = scanner.nextInt();
         int punishment = scanner.nextInt();
      
         Event event = new Event(prompt,reward,punishment);
      
         eventArray[count] = event;
         count++;
      }
   }

   public String printEvent(){
      return Event.toString();
   
   }



}