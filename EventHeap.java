/*Whatcom Community College - Winter 2019
 CS240 Data Structures and Algorithm Analysis
 Professor Ryan Parsons
 AUTHORS: Adib Thaqif, Andrew Jacobi, Donald Strong, and Micah Miller
 Heap implementation for choosing an event in the help feature. 
 Implemented using an array
 
 Problems: exceptions, Floyd's method not used 
           change mutators:
           deleteMin, insert, percolate Up and Down to consider the heap property
 
*/
import java.io.*;
import java.util.*;
import java.lang.Exception.*;

public class EventHeap{
    private Event[] Heap;
    private int size = 0;
    public int maxSize;
    //private int eventValue = 0;

    public EventHeap(int maxSize){
        this.size = size;
        this.maxSize = maxSize;
        Heap = new Event[maxSize + 1];
        //this.eventValue = event.reward + Math.abs(event.punishment);

    }

    public Event parent(int pos){
        return Heap[pos/2];
    }

    public Event leftChild(int pos){
        return Heap[2*pos];
    }

    public Event rightChild(int pos){
        return Heap[2*pos + 1];
    }

    private boolean isLeaf(int pos){
        return (pos >= (size/2) && pos <= size);
    }

    private boolean isEmpty(){
        return (size == 0);
    }

    private int eventValue(Event event){
        int x = 0;
        if(event != null){
            x = event.reward + Math.abs(event.punishment);
        }
        return x;
    }

    public boolean isFull(){
        return (size == Heap.length-1);
    }

    public String toString(){
        if(isEmpty()){
            throw new IllegalArgumentException();
        }
        return Arrays.toString(Heap);
    }

    public Event get(int pos) {
        return Heap[pos];
    }


    private Event findMin(){
        if(isEmpty()){
            throw new ArrayStoreException("Heap is empty");
        }
        return Heap[1];
    }
    private void emptyHeap(){
        for(int i = 0; i < size; i++){
            Heap[i] = null;
        }
    }


    private void resize(){
        if(size == maxSize - 1){
            throw new IllegalArgumentException("Size of heap is full!");
        }

    }


    public void insert(Event event){
        if (isFull()){
            resize();
        }
        size++;
        //System.out.println("size = " + size);
        //Heap[size+1] = value;
        int i = percolateUp(size,event);
        Heap[i] = event;

    }
    //check aspect values first
    //add from hashtable storage.
    public Event deleteMin(){
        if(isEmpty()){
            throw new IllegalArgumentException();
        }
        Event minEvent = Heap[1];
        int hole = percolateDown(1,Heap[size]);
        Heap[hole] = Heap[size];
        size--;
        return minEvent;
    }

    private int percolateUp(int hole, Event event){
        //System.out.println(Heap[hole/2] + "");
        while(hole > 1 && eventValue(event) < eventValue(Heap[hole/2])){
            Heap[hole] = Heap[hole/2];
            hole = hole/2;
        }
        return hole;
        //System.out.println(hole);
    }

    private int percolateDown(int hole, Event event){
        int target;
        while(!isLeaf(hole)){
            int left = 2*hole;
            int right = left + 1;

            if(eventValue(Heap[left]) < eventValue(Heap[right]) || (right > size)){
                target = left;

            }else{
                target = right;
            }

            if(eventValue(Heap[target]) < eventValue(event)){
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
            Event event = Heap[i];
            int hole = percolateDown(i,event);
            Heap[hole] = event ;
        }
    }

}
