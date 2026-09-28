/*
  Yi-Chieh Chu(Carl Chu)
  Class: CS 143
  Assignment: ArrayIntList Equals and Unit Test
 */

public class ArrayIntList implements IntList {

   private int[] data;   // array of integers
   private int size;     // number of elements in the list

   public static final int CAPACITY = 20;

   // constructor
   
   //Pre: None.
   //Post: Constructs an empty ArrayIntList with a fixed capacity.
   public ArrayIntList() {
      data = new int[CAPACITY];
      size = 0;
   }

   // mutators

   // pre: there is room in the underlying array
   // post: value is added to the end of the list
   public void add(int value) {
      checkCapacity(size + 1);
      data[size] = value;
      size++;
   }

   // pre: there is room in the underlying array for adding a new value to the list
   // post: the value specified is added to the list
   public void add(int index, int value) {
      if (index < 0 || index > size) {
         throw new IndexOutOfBoundsException("index: " + index);
      }

      checkCapacity(size + 1);

      for (int i = size; i > index; i--) {
         data[i] = data[i - 1];
      }

      data[index] = value;
      size++;
   }

   // pre: 0 <= index < size
   // post: element at index is removed, shifting elements left
   public void remove(int index) {
      checkIndex(index);

      for (int i = index; i < size - 1; i++) {
         data[i] = data[i + 1];
      }

      size--;
      data[size] = 0;
   }
   
   // Pre: o is any Object (may be null or a not ArrayIntList object)
   // Post: returns true if o is an ArrayIntList with the same size and
   //       identical elements in the same order; otherwise returns false
   public boolean equals(Object o) {
      //self check
      if (this == o) 
         return true;
      //null check
      if (o == null)
         return false;
      //type check and cast
      if (getClass() != o.getClass())
         return false;
      ArrayIntList list = (ArrayIntList) o;
      //list size comparison
      if (size != list.size()) {
         return false;
      }
      //value of each element comparison
      for (int x = 0; x < size; x++) {
         if (data[x] != list.get(x)) {
            return false;
         }
      }  
      return true;
   }

   // accessors

   // pre: none
   // post: returns the number of elements in the list
   public int size() {
      return size;
   }
   
   // pre: that the value of index is less than the size of the list
   // 0 <= index < size
   // post: the value at the index specified is returned
   public int get(int index) {
      checkIndex(index);
      return data[index];
   }

   // pre: none
   // post: the index where the value specified is found and returned. If not found, the value -1 is returned.
   public int indexOf(int value) {
      for (int i = 0; i < size; i++) {
         if (data[i] == value) {
            return i;
         }
      }
      return -1;
   }

   // pre: none
   // post: the values of the array are returned as a string dilemeted by commas surrounded in square brackets; 
   // returns list contents in [a, b, c] format
   public String toString() {
      if (size == 0) {
         return "[]";
      }

      String result = "[" + data[0];
      for (int i = 1; i < size; i++) {
         result += ", " + data[i];
      }
      return result + "]";
   }

   // post: throws an IndexOutOfBoundsException if the given index is
   // not a legal index of the current list
   // Pre: 0 <= index < size
   // Post: Throws IndexOutOfBoundsException if index is invalid
   private void checkIndex(int index) {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }
   
   // post: checks that the underlying array has the given capacity
   // throwing an IllegalStateException if it does not
   //Pre: capacity > 0
   //Post: Throws IllegalStateException if underlying array cannot hold the specified capacity
    
   private void checkCapacity(int capacity) {
      if (capacity > data.length) {
         throw new IllegalStateException("would exceed list capacity");
      }
   }
}
