/*Whatcom Community College - Winter 2019
 CS240 Data Structures and Algorithm Analysis
 Professor Ryan Parsons
 AUTHORS: Adib Thaqif, Andrew Jacobi, Donald Strong, and Micah Miller
 Heap implementation for choosing an event in the rewards feature. 
 Implemented using an array
 
*/
import java.io.*;
import java.util.*;
import java.util.Comparator;

public class EventHeap{
   private int[] Heap;
   private int size = 0;
   private int maxSize;

   public EventHeap(int maxSize){
      this.size = size;
      this.maxSize = maxSize;
      Heap = new int[maxSize + 1];
      
   }
   
   private int parent(int pos){
      return Heap[pos/2];
   }
   
   private int leftChild(int pos){
      return Heap[2*pos];
   }
   
   private int rightChild(int pos){
      return Heap[2*pos + 1];
   }
   
   private boolean isLeaf(int pos){
      return (pos >= (size/2) && pos <= size);
   }
   
   private boolean isEmpty(){
      return (size == 0);
   }
   
   private boolean isFull(){
      return (size == Heap.length-1); 
   }
   
   public String toString(){
    //   if(isEmpty()){
   //          throw new IllegalArgumentException();
   //       }
      return Arrays.toString(Heap);
   }
   
   private int findMin(){
      if(isEmpty()){
         throw new IllegalArgumentException("Heap is empty");
      }
      return Heap[1];
   }
   
   private void emptyHeap(){
      size = 0;
   }
   
   private void resize(){
      size++;
     
   }
   
  //  public String toString(){
//    return 
//    
//    }
    
   public void insert(int value){
      if (isFull()){
         
      }
      size++;
      //System.out.println("size = " + size);
      //Heap[size+1] = value;
      int i = percolateUp(size,value);
      Heap[i] = value;
      
   }
   
   public int deleteMin(){
      if(isEmpty()){
         throw new IllegalArgumentException();
      }
      int minItem = Heap[1];
      int hole = percolateDown(1,Heap[size]);
      Heap[hole] = Heap[size];
      size--;
      return minItem;
   }
   
   private int percolateUp(int hole, int value){
      //System.out.println(Heap[hole/2] + "");
      while(hole > 1 && (value < Heap[hole/2])){
         Heap[hole] = Heap[hole/2];
         hole = hole/2;
      }
      return hole;
      //System.out.println(hole);
   }
   
   private int percolateDown(int hole, int value){
      int target;
      while(!isLeaf(hole)){
         int left = 2*hole;
         int right = left + 1;
         
         if(Heap[left] < Heap[right] || right > size){
            target = left;   
         
         }else{
            target = right;
         }
         
         if(Heap[target] < value){
            Heap[hole] = Heap[target];
            hole = target;
         }else{
            break;
         }
      }
      return hole;
   }
   
   //using Floyd's method to buildHeap
   public void buildHeap(){
      for (int i = size/2; i > 0; i--){
         int value = Heap[i];
         int hole = percolateDown(i,value);
         Heap[hole] = value;
      }
   }
   
}