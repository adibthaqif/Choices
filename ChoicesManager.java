

import java.io.*;
import java.util.*;

public class ChoicesManager {
    private static Event event;
    private static EventHashTable table = new EventHashTable(73);
    private static EventHeap heap = new EventHeap(6);
    private static ArrayList<Event> graveyard = new ArrayList<Event>();
    private static HashMap<String,Integer> AspectMap = new HashMap<>();
    private static int numEvents = 0;
    private static int rounds = 0;
    private static boolean isGameSaved = false;
    private static boolean isPlayed = false;

    public static void main(String[] args)throws FileNotFoundException {
        AspectMap.put("class",50);
        AspectMap.put("job",50);
        AspectMap.put("life",50);
        //Prints Introduction to the screen
        System.out.println("Welcome to the game of Choices!");
        System.out.println("Reading from Choices.txt...");
        choicesManage("Choices.txt");
        boolean x = true;


        String command = "";
        Scanner console = new Scanner(System.in);
        while(!command.equalsIgnoreCase("q")|| !isGameOver()) {
            if(rounds <= 0){
                printCommands();
                command = console.nextLine();
            }else{
                System.out.println("Let's continue!");
                command = "p";

            }


            //Command to print instructions to the user.
            if (command.equalsIgnoreCase("i")) {
                printInstructions();
                System.out.println();
                String answer = console.nextLine();
                if(answer.equalsIgnoreCase("y")){
                    command = "p";
                }else if(answer.equalsIgnoreCase("n")){
                    break;
                }
            }

            //Command to initiate the game
            else if(command.equalsIgnoreCase("p")) {
                isPlayed = true;
                if(rounds <= 0){
                    System.out.println("Do you want to play a new game or load a previously saved one?");
                    System.out.println("Enter 'y' for new game, 'n' to load a previous game.");
                }else{
                    //System.out.println("Let's play!");
                }
                String answer = console.nextLine();
                //User wants to start a new game
                if (answer.equalsIgnoreCase("y")) {
                    while (!heap.isFull()) {
                        load();
                    }
                }
                //User wants to load a previously saved game
                else if (answer.equalsIgnoreCase("n")) {
                    loadSaved();
                }

                while(!isRoundOver()) {
                    play();
                    numEvents++;
                    if(warning()){
                        break;
                    }
                }

                numEvents = 0;
                rounds++;


            } else if (command.equalsIgnoreCase("s")) {
                if(isPlayed){
                    System.out.println("Saving to Choice_Saved.txt...");
                    save();
                    System.out.println("Saved!");
                }else{
                    System.out.println("Please play one game first before saving");
                }

            } else if (command.equalsIgnoreCase("q")) {
                if(!isGameSaved && isPlayed){
                    System.out.println("Do you want to save your game?");
                    String answer = console.nextLine();
                    if(!letsSave(answer)){
                        x = false;
                        System.out.println("Thanks for playing!");
                    }

                }else{
                    System.out.println("See you soon!");
                    break;
                }


            } else {
                System.out.println("That's not a valid selection. Please try again.");
                System.out.println();
            }
            if(rounds!= 0){
                if(!warning()){
                    System.out.println("Good job on finishing round " + rounds + "!");
                    if(rounds < 4){
                        System.out.println("Do you want to continue to round " + (rounds + 1) + "?");
                        System.out.println("Enter 'y' if yes, 'n' if no");
                    }else{
                        System.out.println("Good job in finishing the game!");
                        break;
                    }
                }else{
                    System.out.println("BRO YOU LOST!!");
                    break;

                }

                String answer = console.nextLine();

                if(answer.equalsIgnoreCase("y")){
                    saveAspects();
                    //command = "p";

                }else if(answer.equalsIgnoreCase("n")){
                    System.out.println("Do you want to save your game?");
                    String response = console.nextLine();
                    letsSave(response);
                    save();
                    command = "q";
                    break;
                }
            }else{

            }

        }
        displayResults(rounds,numEvents);
    }

    public static void saveAspects(){

    }

    public static boolean letsSave(String answer){
        boolean x = false;
        if(answer.equalsIgnoreCase("y")){
            save();
            System.out.println("Saved!");
            x = true;
        }else if(answer.equalsIgnoreCase("n")){
            x = false;
        }else{
            throw new IllegalArgumentException();
        }
        return x;
    }

    public static void displayResults(int rounds,int numEvents){
        System.out.println("Number of rounds: " + rounds);
        System.out.println("Number of events: " + rounds*4);
        System.out.println("Final aspect values: ");
        printAspect();
    }

    //  public static void saveHighScores(String fileName){
//       File scoreFile = new File(fileName);
//       PrintStream output = new PrintStream(scoreFile);
//       output.println("S : " + AspectMap.get("class"));
//       output.println("W : " + AspectMap.get("job"));
//       output.println("L : " + AspectMap.get("life"));
//
//    }

    public static void printHighScores(){

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
        System.out.println("\t Play game - p");
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
        while (!heap.isFull()) {
            checkBeforeInsert();
        }
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
                System.out.println(e);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    //JGrasp formatting
    public static void printAspect() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|                         Your current aspect scores are...                             |");
        System.out.println("|\t\t\t\t\t\t       Work: " + AspectMap.get("job") + "\tLife: " + AspectMap.get("life") +
                "\t  School: " + AspectMap.get("class") + "\t\t\t\t\t\t\t\t       |");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
    }

//    IntelliJ Formatting
//    public static void printAspect() {
//        System.out.println();
//        System.out.println("-----------------------------------------------------------------------------------------");
//        System.out.println("|                         Your current aspect scores are...                             |");
//        System.out.println("|\t\t\t\t\t       Work: " + AspectMap.get("job") + "\tLife: " + AspectMap.get("life") +
//                "\tSchool: " + AspectMap.get("class") + "\t\t\t\t\t\t\t    |");
//        System.out.println("-----------------------------------------------------------------------------------------");
//        System.out.println();
//    }

    public static void play() {

        printAspect();
        Scanner console = new Scanner(System.in);

        Event current = heap.deleteMin();
        graveyard.add(current);
        System.out.println(current.toString());
        System.out.println("Enter 'y' if yes, 'n' if no");
        String answer = console.nextLine();
        changeAspect(answer, current);
        checkBeforeInsert();
        //return warning(school,work,life);


    }

    public static void checkBeforeInsert() {
        int n = new Random().nextInt(3) + 3;
        if (!graveyard.contains(table.get(n))) {
            heap.insert(table.get(n));
        } else {
            checkBeforeInsert();
        }
    }

    public static boolean warning(){
        boolean x = false;
        int school = AspectMap.get("class");
        int work = AspectMap.get("job");
        int life = AspectMap.get("life");
        if(school <= 25 || work <= 25 || life <= 25){
            x = true;
        }else if(school <= 30 || work <= 30 || life <= 30){
            System.out.println("WARNING! One or more of your aspects is low!. Be careful or you'll lose the game!");
        }
        return x;

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

    public static boolean isGameOver() {
        return (rounds == 4);
    }

    private static void viewGraveyard(){
        if(graveyard.isEmpty()){
            throw new IllegalArgumentException();
        }else{
            for(int i = 0; i < graveyard.size(); i++){
                System.out.println(graveyard.get(i));
            }
        }
    }

    private static void changeAspect(String answer, Event event) {
        if(answer.equals(null) || event.equals(null)){
            throw new IllegalArgumentException();
        }
        //System.out.println("Event aspect: " + event.aspect.toLowerCase());
        String aspect = event.aspect.toLowerCase();
        //int val = AspectMap.get(aspect);
        if (answer.equalsIgnoreCase("y")) {
            if(aspect.equalsIgnoreCase("life")){
                AspectMap.put("life",AspectMap.get("life") + event.reward);
                AspectMap.put("job", AspectMap.get("job") - event.reward/2);
                AspectMap.put("class",AspectMap.get("class") - event.reward/2);

            }else if(aspect.equalsIgnoreCase("class")){
                AspectMap.put("class", AspectMap.get("class") + event.reward);
                AspectMap.put("life", AspectMap.get("life") - event.reward);

            }else if(aspect.equalsIgnoreCase("job")){
                AspectMap.put("job", AspectMap.get("job") + event.reward);
                AspectMap.put("life", AspectMap.get("life") - event.reward);
            }
        } else if (answer.equalsIgnoreCase("n")) {
            if(aspect.equalsIgnoreCase("life")){
                AspectMap.put("life", AspectMap.get("life") + event.punishment);
                AspectMap.put("job", AspectMap.get("job") - event.punishment/2);
            }else if(aspect.equalsIgnoreCase("class")){
                AspectMap.put("class", AspectMap.get("class") + event.punishment);
                AspectMap.put("life",AspectMap.get("life") - event.punishment);

            }else if(aspect.equalsIgnoreCase("job")){
                AspectMap.put("job", AspectMap.get("job") + event.punishment);
                AspectMap.put("life", AspectMap.get("life") - event.punishment);
            }

        }
    }


    private static boolean isRoundOver(){
        boolean x = true;
        int s = AspectMap.get("class");
        int w = AspectMap.get("job");
        int l = AspectMap.get("life");
        if(numEvents != (int)Math.pow(2,rounds)*4|| s <= 25 || w <= 25 || l <= 25){
            x = false;
        }
        return x;
    }

} //End of class.
