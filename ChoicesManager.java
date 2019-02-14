/*Whatcom Community College - Winter 2019
  CS240 Data Structures and Algorithm Analysis
  Professor Ryan Parsons
  AUTHORS: Adib Thaqif, Andrew Jacobi, Donald Strong, and Micah Miller
  Write a manager that reads in text file, create a new event object with text as data field,
  ints as punishment and reward fields.
  use if else to check if it starts with  E, Y or .
*/

import java.io.*;
import java.util.*;


public class ChoicesManager {
   private Event event;
   //private int reward;
   //private int punishment;
   //private String prompt;
   private static int size = 5;
   private static ArrayList<Event> eventSchool = new ArrayList<Event>();
   private static ArrayList<Event> eventWork = new ArrayList<Event>();
   private static ArrayList<Event> eventLife = new ArrayList<Event>();
  

   public static void main(String[] args)throws FileNotFoundException {
   
      Scanner console = new Scanner(System.in);
      System.out.println("Please input file name");
      String fileName = console.nextLine();
   
      if(fileName == null) {
         throw new IllegalArgumentException();
      }
   
      choicesManage(fileName);
   
   }

   public static void choicesManage(String file)throws FileNotFoundException{
   
      File fileName = new File(file);
      Scanner scanner = new Scanner(fileName);
     
   
      while(scanner.hasNextLine()){
      
         //store first string, first int and second int in event object
         
         String prompt = scanner.nextLine();
         String [] split = prompt.split(":");
         String aspect = split[0];
         //String prompt = split[1];
         
         if(aspect.equals("LIFE")){
            //System.out.println(aspect[0]);
            String reward = scanner.nextLine();
            Integer rewardInt = Integer.valueOf(reward);
            String punishment = scanner.nextLine();
            Integer punishmentInt = Integer.valueOf(punishment);
            Event event = new Event(prompt,rewardInt,punishmentInt);
            eventLife.add(event);
            System.out.println(event);
         }else if(aspect.equals("SCHOOL")){
            //System.out.println(aspect[0]);
            String reward = scanner.nextLine();
            Integer rewardInt = Integer.valueOf(reward);
            String punishment = scanner.nextLine();
            Integer punishmentInt = Integer.valueOf(punishment);
            Event event = new Event(prompt,rewardInt,punishmentInt);
            eventSchool.add(event);
         }else if(aspect.equals("WORK")){
            //System.out.println(aspect[0]);
            String reward = scanner.nextLine();
            Integer rewardInt = Integer.valueOf(reward);
            String punishment = scanner.nextLine();
            Integer punishmentInt = Integer.valueOf(punishment);
            Event event = new Event(prompt,rewardInt,punishmentInt);
            eventWork.add(event);
         
         }
         
      
      }
      
      for (Event e : eventSchool){
         System.out.println(e.toString());
      
      
      }
      
   }

   
   
 
}
