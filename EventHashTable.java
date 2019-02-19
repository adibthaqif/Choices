import java.util.*;
public class EventHashTable {

    private int size = 0;
    private int maxSize, hashAlgorithm, index, value, min, max;
    private int random = 0;
    private int[] eventHashTable; //change [] type to event
    private Event e1, e2;
    private Random rand = new Random();
    private boolean flag = true;

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

    public boolean isEmpty() {
        for (int i = 0; i < maxSize; i++) {
            if (eventHashTable[i] != 0) {
                flag = false;
            }
        }
        return flag;
    }

    public int get(int key) {
        switch (key) {
            case 3:
                min = 0;
                max = (maxSize / 3) - 1;
                random = rand.nextInt((max + 1 - min) + min);
                value = eventHashTable[random];
                break;
            case 4:
                min = (maxSize / 3); //4
                max = ((maxSize * 2) / 3) - 1; //7
                random = rand.nextInt(max + 1 - min) + min;
                value = eventHashTable[random];
                break;
            case 5:
                min = ((maxSize * 2) / 3);
                max = (maxSize - 1);
                random = rand.nextInt(max + 1 - min) + min;
                value = eventHashTable[random];
                break;
        }
        return value;
    }


    public void printHashTable() {
        for (int i = 0; i < maxSize; i++) {
            System.out.print(eventHashTable[i] + ", ");
        }
        System.out.println();
    }


}
