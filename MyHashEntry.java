public class MyHashEntry {
      private int key;
      private int value;

      public MyHashEntry() {
            this.key = 0;
            this.value = 0;
      }      
 
      public MyHashEntry(int key, int value) {
            this.key = key;
            this.value = value;
      }     
 
      public int getKey() {
            return key;
      }
 
      public int getValue() {
            return value;
      }

      public void setValue(int val){
            value = val;
      }
}