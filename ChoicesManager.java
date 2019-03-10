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


public class ChoicesManager {
   private static Event event;
   private static int key;
   private static EventHashTable table = new EventHashTable(23);
   private int min, max, range;
   private static EventHeap heap = new EventHeap(6);
   private static ArrayList<Event> Graveyard = new ArrayList<Event>();
   private static int[] aspectValues = new int[3];
   private static int school = aspectValues[0];
   private static int work = aspectValues[1];
   private static int life = aspectValues[2];
   private static int round = 0;

   public static void main(String[] args)throws FileNotFoundException {
           
      //Prints Introduction to the screen
      System.out.println("Welcome to the game of Choices!");
      printCommands();
      System.out.println("Reading from Choices.txt...");
      choicesManage("Choices.txt");
      Scanner console = new Scanner(System.in);
    
      while(!isGameOver(round,aspectValues)){
         String command = console.nextLine();
      
         if(command.equals('i')){
            printInstructions();
            System.out.println("So, are you ready to play?");
            String nextCommand = console.nextLine();
            printCommands();
         
         }else if(command.equals('p')){
            while(!isGameOver(round,aspectValues)){
               System.out.println("Do you want to continue playing the previous game or start a new one?");
               String answer = console.nextLine();
               if(answer.equals('y')){
               
               }else if(answer.equals('n')){
                  load();
               }
               String input = console.nextLine();
               play(input);
            }
         
         }else if(command.equals('s')){
            System.out.println("Please input file name for the game to be saved");
            String fileName = console.nextLine();
            PrintStream out = new PrintStream(new File(fileName));
            save(console, out);
         
         }else if(command.equals('q')){
            if(isGameSaved()){
            
            }else{
               quit();
            }
         
         }
      
      }
   }

   public static void choicesManage(String file)throws FileNotFoundException {
   
      File fileName = new File(file); 
      Scanner scanner = new Scanner(fileName);  
      while(scanner.hasNextLine()){
      
         String aspect = scanner.nextLine();
         key = aspect.length();
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
      System.out.println("\t Start a new game - p");
      System.out.println("\t Save current game - s");
      System.out.println("\t Quit - q");
      System.out.println();
   }

   public static void printInstructions() {
    
   }

   //Builds the heap with stuff in hashtable
   public static void load() {
      Event playEvent = new Event();
      //Keep inserting until Heap is full...
      while (!heap.isFull()) {
         //If random selected index is null, try again. Else, insert
         while (playEvent.text == null) {
            int n = new Random().nextInt(3) + 3;
            playEvent = table.get(n);
         }
         heap.insert(playEvent);
         playEvent = new Event();
      
      }
   }
   
   public static void play(String input) {
    
      Scanner console = new Scanner(System.in);
      for (int i = 0; i <5; i++) {
         Event current = heap.deleteMin();
         System.out.println(current.toString());
         int n = new Random().nextInt(3) + 3;
         heap.insert(table.get(n));
         //changeAspect(input, current);
         Graveyard.add(current);
         changeAspect(input,current);
      }
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
      if(answer.equals('y')){
         if(event.aspect == "school"){
            school += event.reward;
         }else if(event.aspect == "work"){
            work += event.reward;
         }else if(event.aspect == "life"){
            life += event.reward;
         }
      }else if(answer.equals('n')){
         if(event.aspect == "school"){
            school += event.punishment;
         }else if(event.aspect == "work"){
            work += event.punishment;
         }else if(event.aspect == "life"){
            life += event.punishment;
         }
      }
   }
   
   private static boolean isGameOver(int round, int[] aspectValues){
      boolean x = true;
   
      if(round != 1 || aspectValues[0] <= 25 || aspectValues[1] <= 25 || aspectValues[2] <= 25){
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
