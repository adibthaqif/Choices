/*Whatcom Community College - Winter 2019
 CS240 Data Structures and Algorithm Analysis
 Professor Ryan Parsons
 AUTHORS: Adib Thaqif, Andrew Jacobi, Donald Strong, and Micah Miller
 Heap manager
 
 */
import java.io.*;
import java.util.*;


public class heapTester{

 
   public static void main(String[] args){
      int maxSize = 60;
      EventHeap heap = new EventHeap(maxSize);
   
      heap.insert(16);
      heap.insert(32);
      heap.insert(4);
      heap.insert(69);
      heap.insert(105);
      heap.insert(43);
      heap.insert(2);
      System.out.println(heap);
   
   }
}