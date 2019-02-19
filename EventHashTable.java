import java.util.*;
public class EventHashTable {

    private int size = 0;
    private int maxSize, hashAlgorithm, index, key, value;
    private int[] eventHashTable; //change [] type to event
    private Event e1, e2;
    private Random rand;

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

    public int get(int key) {
        switch (key) {
            case 3:
                int randNum1 = rand.nextInt((maxSize / 3));
                value = eventHashTable[randNum1];
            case 4:
                int randNum2 = rand.nextInt(((maxSize * 2 ) / 3) - 1);
                value = eventHashTable[randNum2];
            case 5:
                int randNum3 = rand.nextInt(maxSize);
                value = eventHashTable[randNum3];
        }
        return value;
    }



    public void printHashTable() {
        for (int x : eventHashTable) {
            System.out.print(x + ", ");
        }
    }
