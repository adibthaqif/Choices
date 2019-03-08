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
    private static Event event;
    private static int key;
    private static EventHashTable table = new EventHashTable(23);
    private int min, max, range;
    private static EventHeap heap = new EventHeap(6);
    private static Scanner console = new Scanner(System.in);
    private static ArrayList<Event> graveyard = new ArrayList<Event>();

    public static void main(String[] args)throws FileNotFoundException {



        //Prints Introduction to the screen
        printIntro();
        System.out.println("Reading from Choices.txt...");
        choicesManage("Choices.txt");
        load();
        //while (!isGameOver) {
        play();
        //}
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

    public static void printIntro() {
        System.out.println("Welcome to the game of Choices!");
        System.out.println("Please select one of the following: ");
        System.out.println("\t Review instructions - i");
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
        Scanner console = new Scanner(System.in);
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

    public static void play() {
        Scanner console = new Scanner(System.in);
        for (int i=0; i <5; i++) {
            Event current = heap.deleteMin();
            System.out.println(current.toString());
            int n = new Random().nextInt(3) + 3;
            heap.insert(table.get(n));
            String input = console.nextLine();
            //changeAspect(input, current);
            graveyard.add(current);
        }
    }

    public static void changeAspect() {

    }

}
