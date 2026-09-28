/*
  Yi-Chieh Chu(Carl Chu)
  Class: CS 143
  Assignment: HW #5: LinkedIntList  
*/

class Node {
   public int data; // data stored in this node
   public Node next; // link to next node in the list

   // Pre: None.
   // Post: Constructs a node with data 0 and a null link.
   public Node() {
      this(0, null);
   }

   // Pre: None.
   // Post: Constructs a node with the given data and a null link.
   public Node(int data) {
      this(data, null);
   }

   // Pre: None.
   // Post: Constructs a node with the given data and the given link.
   public Node(int data, Node next) {
      this.data = data;
      this.next = next;
   }
}