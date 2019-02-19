import java.util.*;
public class EventHashTable {

    private int size = 0;
    private int maxSize, hashAlgorithm, index;
    private int[] eventHashTable; //change [] type to event

    public EventHashTable(int maxSize) {
        this.size = size;
        this.maxSize = maxSize;
        eventHashTable = new int[maxSize]; //change [] type to event
    }

    public void put(int key, int value) { //change val to Event type
        hashAlgorithm = key % maxSize;
        switch (hashAlgorithm) {
            case 3:
                index = 0;
                while (eventHashTable[index] != 0 && index <= (maxSize/3) - 1) {
                    index = index + 1;
                }
                if (eventHashTable[index] == 0) {
                    eventHashTable[index] = value;
                    break;
                } else {
                    throw new ArrayStoreException("There is already something in this index!");
                }

            case 4:
                index = (maxSize / 3);
                while (eventHashTable[index] != 0 && index <= ((maxSize * 2) / 3) - 1) {
                    index = index + 1;
                }
                if (eventHashTable[index] == 0) {
                    eventHashTable[index] = value;
                    break;
                } else {
                    throw new ArrayStoreException("There is already something in this index!");
                }
            case 5:
                index = ((maxSize * 2) / 3);
                while (eventHashTable[index] != 0) {
                    index = index + 1;
                }
                if (eventHashTable[index] == 0) {
                    eventHashTable[index] = value;
                    break;
                } else {
                    throw new ArrayStoreException("There is already something in this index!");
                }
        }

    }

    //public int getValue() { //change to Event
      //  return value;
    //}

    //public int getKey() {
      //  return key;
    //}

    public void printHashTable() {
        for (int x : eventHashTable) {
            System.out.print(x + ", ");
        }
    }


}
