/*
  Yi-Chieh Chu(Carl Chu)
  Class: CS 143
  Assignment: HW #5: LinkedIntList  
*/

public class LinkedIntList implements IntList {

   private Node start; 
   private int size;   

   // Pre: None.
   // Post: Constructs an empty LinkedIntList.
   public LinkedIntList() {
      start = null;
      size = 0;
   }

   // Mutators
   
   // Pre: None.
   // Post: value is added to the end of the list.
   public void add(int value) {
      add(size, value);
   }

   // Pre: 0 <= index <= size.
   // Post: The value specified is added to the list at the given index.
   public void add(int index, int value) {
      if (index < 0 || index > size) {
         throw new IndexOutOfBoundsException("index: " + index);
      }

      if (index == 0) {
         start = new Node(value, start);
      } else {
         Node current = nodeAt(index - 1);
         current.next = new Node(value, current.next);
      }
      size++;
   }

   // Pre: 0 <= index < size.
   // Post: Element at the specified index is removed from the list.
   public void remove(int index) {
      checkIndex(index);

      if (index == 0) {
         start = start.next;
      } else {
         Node current = nodeAt(index - 1);
         current.next = current.next.next;
      }
      size--;
   }

   // Pre: index >= 0.
   // Post: Inserts all elements of the given list into this list at the specified index.
   public void insertList(int index, LinkedIntList list) {
      Node otherCurrent = list.start;
      int currentInsertIndex = index;
      while (otherCurrent != null) {
         this.add(currentInsertIndex, otherCurrent.data);
         otherCurrent = otherCurrent.next;
         currentInsertIndex++;
      }
   }

   // Accessors

   // Pre: o is any Object.
   // Post: Returns true if o is a LinkedIntList with the same size and elements in order.
   public boolean equals(Object o) {
      if (this == o) 
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      LinkedIntList other = (LinkedIntList) o;
      if (this.size != other.size) {
         return false;
      }

      Node current1 = this.start;
      Node current2 = other.start;
      while (current1 != null) {
         if (current1.data != current2.data) {
            return false;
         }
         current1 = current1.next;
         current2 = otherCurrent(current2); 
      }
      return true;
   }
   
   // Helper for equals to move to next node safely
   private Node otherCurrent(Node n) {
       return n.next;
   }

   // Pre: None.
   // Post: Returns the number of elements currently in the list.
   public int size() {
      return size;
   }

   // Pre: 0 <= index < size.
   // Post: Returns the integer value located at the specified index.
   public int get(int index) {
      checkIndex(index);
      return nodeAt(index).data;
   }

   // Pre: None.
   // Post: Returns the index of the first occurrence of value, or -1 if not found.
   public int indexOf(int value) {
      Node current = start;
      for (int i = 0; i < size; i++) {
         if (current.data == value) {
            return i;
         }
         current = current.next;
      }
      return -1;
   }

   // Pre: None.
   // Post: Returns list contents as a String in [a, b, c] format.
   public String toString() {
      if (size == 0) {
         return "[]";
      }
      String result = "[" + start.data;
      Node current = start.next;
      while (current != null) {
         result += ", " + current.data;
         current = current.next;
      }
      return result + "]";
   }

   // Helpers

   // Pre: 0 <= index < size.
   // Post: Returns the Node object located at the specified index.
   private Node nodeAt(int index) {
      Node current = start;
      for (int i = 0; i < index; i++) {
         current = current.next;
      }
      return current;
   }

   // Pre: 0 <= index < size.
   // Post: Throws IndexOutOfBoundsException if the index is not valid.
   private void checkIndex(int index) {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }
}