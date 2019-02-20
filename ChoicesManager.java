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
    private static EventHashTable table = new EventHashTable(9);


    public static void main(String[] args)throws FileNotFoundException {

        Scanner console = new Scanner(System.in);
        System.out.println("Please input file name");
        String fileName = console.nextLine();

        if(fileName == null) {
            throw new IllegalArgumentException();
        }

        choicesManage(fileName);

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
            event = new Event(eventStr, reward, punishment);
            table.put(key, event);

        }

        table.printHashTable();
        System.out.println();
        System.out.println(table.get(3));
    }




}
