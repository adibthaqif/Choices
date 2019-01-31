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


public class ChoicesText {

   

   
   public static void main(String[] args)throws FileNotFoundException {
   
      Scanner console = new Scanner(System.in);
      choicesText(console);
   
   
   
   }
   
   public void choicesText(Scanner console){
      while(console.hasNextLine()){
      
         //store first string, first int and second int in event object
         String prompt = console.nextLine();
         int reward = console.nextInt();
         int punishment = console.nextInt();
         
         //Create a linked list of events 
         Event event = new Event(prompt,reward,punishment);
         Hashtable<Event, Integer> numbers = new Hashtable<Event, Integer>();
         
      }
   }
      
   public void printEvent(){
      
      Event;
   }
     
     
         
       
          
    
   
    
    
    

    
    
}


