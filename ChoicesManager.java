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
        AspectMap.put("class",50);
        AspectMap.put("job",50);
        AspectMap.put("life",50);
        //Prints Introduction to the screen
        System.out.println("Welcome to the game of Choices!");
        System.out.println("Reading from Choices.txt...");
        choicesManage("Choices.txt");


        String command = "";
        while(!command.equalsIgnoreCase("q")) {
            printCommands();
            Scanner console = new Scanner(System.in);
            command = console.nextLine();

            //Command to print instructions to the user.
            if (command.equalsIgnoreCase("i")) {
                printInstructions();
                System.out.println();
            }

            //Command to initiate the game
            else if(command.equalsIgnoreCase("p")) {
                System.out.println("Do you want to continue playing the previous game or start a new one?");
                System.out.println("Enter 'y' for new game, 'n' to load a previous game.");
                String answer = console.nextLine();

                while(!isGameOver(round,AspectMap)) {

                    //User wants to start a new game
                    if (answer.equalsIgnoreCase("y")) {
                        load();
                        play();
                        round++;

                    }
                    //User wants to load a previously saved game
                    else if (answer.equalsIgnoreCase("n")) {
                        loadSaved();
                        play();
                        round++;

                    }
                }

            } else if (command.equalsIgnoreCase("s")) {
                System.out.println("Saving to Choice_Saved.txt...");
                save();
                System.out.println("Saved!");

            } else if (command.equalsIgnoreCase("q")) {
                System.out.println("Would you like to save your current game?");
                String answer = console.next();
                if (answer.equalsIgnoreCase("y)")) {
                    save();
                    quit();
                } else {
                    System.out.println("Thanks for playing!");
                    quit();
                }

            } else {
                System.out.println("That's not a valid selection. Please try again.");
                System.out.println();
            }

        }
    }

    //Takes in a txt file, creates Event objects, then stores them in the hashtable
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

    //Prints out the menu to the user
    public static void printCommands() {
        System.out.println("Please select one of the following: ");
        System.out.println("\t Instructions - i");
        System.out.println("\t Start game - p");
        System.out.println("\t Save current game - s");
        System.out.println("\t Quit - q");
        System.out.println();
    }

    //prints the instructions for the game to the user
    public static void printInstructions() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|                                     INSTRUCTIONS                                      |");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("You will be presented with a variety of scenarios to assess how you prioritize certain ");
        System.out.println("aspects of your life. All you have to do is answer Yes or No! As you progress through the");
        System.out.println("round, you will see your aspect scores (School, Work, or Life) change based on your ");
        System.out.println("answer. Make sure to keep an eye on those values, if one aspect gets too high the others ");
        System.out.println("will suffer. If any one of your three aspect values drop below 30%, you lose!");
        System.out.println();
        System.out.println("So are you ready to play?");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
    }

    //Builds the heap with stuff in hashtable
    public static void load() {

    }

    public static void loadSaved() {

        try {
            ArrayList<Event> list = new ArrayList<Event>();
            System.out.println("Loading from Choices_Saved.txt...");
            File infile = new File("Choices_Saved.txt");
            Scanner scanner = new Scanner(infile);
            while(scanner.hasNextLine()){
                String aspect = scanner.nextLine();
                int key = aspect.length();
                String eventStr = scanner.nextLine();
                Integer reward = Integer.valueOf(scanner.nextLine());
                Integer punishment = Integer.valueOf(scanner.nextLine());
                event = new Event(aspect, eventStr, reward, punishment);
                list.add(event);
            }

            for (Event e : list) {
                heap.insert(e);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void printAspect() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|                         Your current aspect scores are...                             |");
        System.out.println("|\t\t\t\t\t\t Work: " + AspectMap.get("job") + "\tLife: " + AspectMap.get("life") +
                "\tSchool: " + AspectMap.get("class") + "\t\t\t\t\t\t\t\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
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

    public static void save() {
        try {
            File outfile = new File("Choices_Saved.txt");
            PrintStream output = new PrintStream(outfile);
            for (int i = 1; i < heap.maxSize; i++) {
                output.println(heap.get(i).aspect.toUpperCase());
                output.println(heap.get(i).text);
                output.println(heap.get(i).reward);
                output.println(heap.get(i).punishment);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isGameSaved(){
        return true;
    }

    public static void quit() {

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
        System.out.println("Event aspect: " + event.aspect.toLowerCase());
        String aspect = event.aspect.toLowerCase();

        if (answer.equals("y") || answer.equals("Y")) {
            AspectMap.put(aspect, AspectMap.get(aspect) + event.reward);
        } else if (answer.equals("n") || answer.equals("N)")) {
            AspectMap.put(aspect, AspectMap.get(aspect) + event.punishment);
        }
    }

    private static boolean isGameOver(int round, HashMap<String,Integer> AspectMap){
        boolean x = true;
        int s = AspectMap.get("class");
        int w = AspectMap.get("job");
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


    private static void punish(int[] aspectValue){

        if (aspectValue[0] >= 80) {
            aspectValue[1] -= 8;
            aspectValue[2] -= 8;
        }
        if (aspectValue[1] >= 80) {
            aspectValue[2] -= 8;
            aspectValue[0] -= 8;
        }
        if (aspectValue[2] >= 80) {
            aspectValue[0] -= 8;
            aspectValue[1] -= 8;
        }

    }


} //End of class.
