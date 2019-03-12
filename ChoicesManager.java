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
import java.io.PrintStream;
import java.util.Scanner;
import java.util.HashMap;


public class ChoicesManager {
   private static Event event;
   private static EventHashTable table = new EventHashTable(23);
   private static EventHeap heap = new EventHeap(6);
   private static ArrayList<Event> Graveyard = new ArrayList<Event>();
   private static HashMap<String,Integer> AspectMap = new HashMap<>();
   private static int round = 0;
   

   public static void main(String[] args)throws FileNotFoundException {
      AspectMap.put("school",50);
      AspectMap.put("work",50);
      AspectMap.put("life",50);
      //Prints Introduction to the screen
      System.out.println("Welcome to the game of Choices!");
      printCommands();
      System.out.println("Reading from Choices.txt...");
      choicesManage("Choices.txt");
   
    
      //while(!isGameOver(round,aspectValues)){
         //String command = console.nextLine();
      boolean x = true;
      while(x){
         Scanner console = new Scanner(System.in);
         String command = console.nextLine();
         if(command.equals("i")){
            printInstructions();
            System.out.println("So, are you ready to play?");
            command = console.nextLine();
            printCommands();
            //command = console.nextLine();
         }
         
         if(command.equals("p")){
         
            System.out.println("Do you want to continue playing the previous game or start a new one?");
            System.out.println("Enter 'y' for new game, 'n' to load a previous game.");
            String answer = console.nextLine();
            while(!isGameOver(round,AspectMap)){
               load();
               if(answer.equals("y")){
               
               //Scanner scanner = new Scanner(System.in);
                  play();
                  round++;
                  //System.out.println("graveyard: "); 
                  //viewGraveyard();
               }else if(answer.equals("n")){
                 
               }
            //String input = console.nextLine();
            //play();
            }
         
         
         }else if(command.equals("s")){
            System.out.println("Please input file name for the game to be saved");
            String fileName = console.nextLine();
            PrintStream out = new PrintStream(new File(fileName));
            save(console, out);
            System.out.println("Saved!");
            printCommands();
         
         }else if(command.equals("q")){
            if(isGameSaved()){
            
            }else{
               quit();
            }
         
         }else{
            System.out.println("Please insert a correct command");
            System.out.println();
            printCommands();
         
         }
      
      }
   }
  

   public static void choicesManage(String file)throws FileNotFoundException {
   
      File fileName = new File(file); 
      Scanner scanner = new Scanner(fileName);  
      while(scanner.hasNextLine()){
      
         String aspect = scanner.nextLine();
         int key = aspect.length();
         String eventStr = scanner.nextLine();
         Integer reward = Integer.valueOf(scanner.nextLine());
         Integer punishment = Integer.valueOf(scanner.nextLine());
         event = new Event(aspect, eventStr, reward, punishment);
         table.put(key, event);
      
      }
   }

   public static void printCommands() {
      System.out.println("Please select one of the following: ");
      System.out.println("\t Instructions - i");
      System.out.println("\t Start game - p");
      System.out.println("\t Save current game - s");
      System.out.println("\t Quit - q");
      System.out.println();
   }

   public static void printInstructions() {
      System.out.println("Stuff..");
   }

   //Builds the heap with stuff in hashtable
   public static void load() {
      Event playEvent = new Event();
      //Keep inserting until Heap is full...
      while (!heap.isFull()) {
         //If random selected index is null, try again. Else, insert
         int n = new Random().nextInt(3) + 3;
         playEvent = table.get(n);
         heap.insert(playEvent);
         //return heap;
      }
   }
   
   public static void printAspect(){
      System.out.println();
      System.out.println("Aspect values are :");
      System.out.println("school: " + AspectMap.get("school"));
      System.out.println("work: " + AspectMap.get("work"));
      System.out.println("life: " + AspectMap.get("life"));
      System.out.println();
   }
   
   public static void play() {
   
      printAspect();
      Scanner console = new Scanner(System.in);
      
      Event current = heap.deleteMin();   
      System.out.println(current.toString());
      System.out.println("Enter 'y' if yes, 'n' if no");
      String answer = console.nextLine();
      changeAspect(answer, current);
      int n = new Random().nextInt(3) + 3;//if n = 3, get life, if n = 4, get school
      heap.insert(table.get(n));
      Graveyard.add(current);
        
      
   }
   
   public static void save(Scanner console, PrintStream output){
      for(int i = 0; i < heap.length(); i++){
         output.println(heap.get(i));
      }
   }
   public static boolean isGameSaved(){
      return true;
   }
   
   public static void quit(){
   
   }
   
   private static void viewGraveyard(){
      if(Graveyard.isEmpty()){
         throw new IllegalArgumentException();
      }else{
         for(int i = 0; i < Graveyard.size(); i++){
            System.out.println(Graveyard.get(i));
         }
      }
   }

   private static void changeAspect(String answer, Event event) {
      if(answer.equals(null) || event.equals(null)){
         throw new IllegalArgumentException();
      }
      System.out.println("event aspect: " + event.aspect.toLowerCase());
      String aspect = event.aspect.toLowerCase();
      if(answer.equals("y")){      
         AspectMap.put(aspect, AspectMap.get(aspect) + event.reward);
                 
      }else if(answer.equals("n")){
         AspectMap.put(aspect, AspectMap.get(aspect) + event.punishment);
      }
   }
   
   private static boolean isGameOver(int round, HashMap<String,Integer> AspectMap){
      boolean x = true;
      int s = AspectMap.get("school");
      int w = AspectMap.get("work");
      int l = AspectMap.get("life");
      if(round != 4|| s <= 25 || w <= 25 || l <= 25){
         x = false;
      }
      return x;
   }
   
   private static boolean isRoundOver(int eventCounter, int round){
      boolean x = true;
      if(eventCounter != eventCounter*round){
         x = false;
      }
      return x;
   }
   
 //   private static void warning(int[] aspectValues){
//    
//    }
   
   private static void punish(int[] aspectValue){
   
      if(aspectValue[0] >= 80){
         aspectValue[1] -= 8;
         aspectValue[2] -= 8;
      }
      if(aspectValue[1] >= 80){
         aspectValue[2] -= 8;
         aspectValue[0] -= 8;
      }
      if(aspectValue[2] >= 80){
         aspectValue[0] -= 8;
         aspectValue[1] -= 8;
      }
         
   }
   

}
    
   
  

