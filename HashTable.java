public class HashTable {

   public HashTable {
   
      private int tableSize = 120;
      private HashEntry[] hashTable = new HashEntry[tableSize];
      private int hashAlgorithm, index;
      }
   
   public Event getEvent(int key) {
      return hashTable[key]
   }
   
   
   public void put(int key, Event value) {
      hashAlgorithm = key % tableSize;
      switch (hashAlgorithm) {
         case 3:
            index = 0;
            while (hashTable[index] != null) {
               index = index + 1;      
            }
            hashTable[index] = value;
         case 4: 
            index = 40;
            while (hashTable[index] != null) {
               index = index + 1;      
            }
            hashTable[index] = value;
         case 5:
            index = 80;
            while (hashTable[index] != null) {
               index = index + 1;      
            }
            hashTable[index] = value;
      }
   
   }

}