import java.util.*;
public class EventHashTable {

    public static int size = 0;
    public static int maxSize, hashAlgorithm, index, min, max;
    private int random = 0;
    private static Event[] eventHashTable;
    private static Event e1, e2, value;
    private static Random rand = new Random();
    private static boolean flag = true;

    public EventHashTable(int maxSize) {
        this.size = size;
        this.maxSize = maxSize;
        eventHashTable = new Event[maxSize];
    }

    public static void put(int key, Event value) {
        hashAlgorithm = key % maxSize;
        switch (hashAlgorithm) {
            case 3:
                index = 0;
                while (eventHashTable[index] != null && index <= (maxSize/3) - 1) {
                    index = index + 1;
                }
                if (eventHashTable[index] == null) {
                    eventHashTable[index] = value;
                    break;
                } else {
                    throw new ArrayStoreException("There is already something in this index!");
                }

            case 4:
                index = (maxSize / 3);
                while (eventHashTable[index] != null && index <= ((maxSize * 2) / 3) - 1) {
                    index = index + 1;
                }
                if (eventHashTable[index] == null) {
                    eventHashTable[index] = value;
                    break;
                } else {
                    throw new ArrayStoreException("There is already something in this index!");
                }
            case 5:
                index = ((maxSize * 2) / 3);
                while (eventHashTable[index] != null) {
                    index = index + 1;
                }
                if (eventHashTable[index] == null) {
                    eventHashTable[index] = value;
                    break;
                } else {
                    throw new ArrayStoreException("There is already something in this index!");
                }
        }

    }

    public boolean isEmpty() {
        for (int i = 0; i < maxSize; i++) {
            if (eventHashTable[i] != null) {
                flag = false;
            }
        }
        return flag;
    }

    public Event get(int key) {

        switch (key) {
            case 3:
                min = 0;
                max = (maxSize / 3) - 1;
                random = rand.nextInt((max + 1 - min) + min);
                if (eventHashTable[random] != null) {
                    value = eventHashTable[random];
                } else {
                    get(key);
                }
                break;
            case 4:
                min = (maxSize / 3); //4
                max = ((maxSize * 2) / 3) - 1; //7
                random = rand.nextInt(max + 1 - min) + min;
                if (eventHashTable[random] != null) {
                    value = eventHashTable[random];
                } else {
                    get(key);
                }
                break;
            case 5:
                min = ((maxSize * 2) / 3);
                max = (maxSize - 1);
                random = rand.nextInt(max + 1 - min) + min;
                if (eventHashTable[random] != null) {
                    value = eventHashTable[random];
                } else {
                    get(key);
                }
                break;
        }
        return value;
    }


    public void printHashTable() {
        for (int i = 0; i < maxSize; i++) {
            if (eventHashTable[i] == null) {
                System.out.println("null");
            }
            else {
                System.out.print(eventHashTable[i].toString() + "\n");
            }
        }
        System.out.println();
    }


}
