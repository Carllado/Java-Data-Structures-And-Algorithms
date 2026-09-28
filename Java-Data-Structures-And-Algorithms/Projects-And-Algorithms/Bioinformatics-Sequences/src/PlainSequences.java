/*
  Name: Yi-Chieh Chu (Carl Chu)
  Class: CS 143
  Assignment: Implement class for manipulating DNA sequence in plain format 
 */

import java.util.*;
import java.io.*;

public class PlainSequences implements Sequences {

   private List<String> sequences;
   private List<String> descriptions;

// Pre: none
// Post: sequences and descriptions are initialized as empty lists
   public PlainSequences() {
      sequences = new ArrayList<String>();
      descriptions = new ArrayList<String>();
   }

// Pre: none
// Post: returns a list containing descriptions of all stored sequences
   public List<String> getDescriptions() {
      return descriptions;
   }
   
// Pre: none
// Post: returns a list containing all stored DNA sequences
   public List<String> getSequences() {
      return sequences;
   }

// Pre: fileName refers to a valid readable file containing DNA sequence data
// Post: clears existing data and stores one sequence read from the file;
// descriptions contains the file name;
// sequences contains a single concatenated DNA sequence
// Throws: FileNotFoundException if the file cannot be opened
   public void readSequences(String fileName) throws FileNotFoundException {
      sequences.clear();
      descriptions.clear();

      Scanner input = new Scanner(new File(fileName));
      String sequence = "";

      while (input.hasNextLine()) {
         sequence = sequence + input.nextLine().trim();
      }

      input.close();

      descriptions.add(fileName);
      sequences.add(sequence);
   }

// Pre: index is within the bounds of the sequences list
// Post: returns true if the sequence at the given index contains
//       only valid DNA/RNA nucleotide characters; false otherwise
   public boolean isValidSequence(int index) {
      String sequence = sequences.get(index);

      for (int x = 0; x < sequence.length(); x++) {
         char dnaBase = sequence.charAt(x);
         if (!(
            dnaBase == 'A' ||
            dnaBase == 'C' ||
            dnaBase == 'G' ||
            dnaBase == 'T' ||
            dnaBase == 'U' ||
            dnaBase == 'R' ||
            dnaBase == 'Y' ||
            dnaBase == 'K' ||
            dnaBase == 'M' ||
            dnaBase == 'S' ||
            dnaBase == 'W' ||
            dnaBase == 'B' ||
            dnaBase == 'D' ||
            dnaBase == 'H' ||
            dnaBase == 'V' ||
            dnaBase == 'N'
         )) {
            return false;
         }
      }
      return true;
   }
   
// Pre: index is within bounds of the sequences list;
// basesPerGroup > 0;
// groupsPerLine > 0
// Post: returns the sequence at the given index formatted into
// groups of basesPerGroup characters, with groupsPerLine
// groups per line

   public String formatInGroups(int index, int basesPerGroup, int groupsPerLine) {
      String sequence = sequences.get(index);
      StringBuilder formatted = new StringBuilder();

      int baseCount = 0;
      int groupCount = 0;

      for (int i = 0; i < sequence.length(); i++) {
         formatted.append(sequence.charAt(i));
         baseCount++;

         if (baseCount == basesPerGroup) {
            baseCount = 0;
            groupCount++;

         if (groupCount == groupsPerLine) {
            formatted.append(" \n");
            groupCount = 0;
         } else {
            formatted.append(" ");
            }
         }
      }

      return formatted.toString().trim();
   }
   
   
   // pre: assumes the index specified a sequence that exist in the sequences list
   // post: returns a map that shows the frequeny of occurance of all unique substrings from the specified sequence.
   public Map<String, Integer> generateFrequencies(int index) {

      Map<String, Integer> frequencies = new HashMap<String, Integer>();
      String sequence = sequences.get(index);

      // starting position
      for (int i = 0; i < sequence.length(); i++) {

         // ending position
         for (int j = i + 1; j <= sequence.length(); j++) {

            String sub = sequence.substring(i, j);

            if (frequencies.containsKey(sub)) {
            frequencies.put(sub, frequencies.get(sub) + 1);
            } else {
             frequencies.put(sub, 1);
            }
         }
      }

      return frequencies;
   }

 //PRE: The sequence at the given index has already been read.
 //POST: Returns a List<String> containing all distinct substrings of the sequence at the given index, sorted in alphabetical order.
 //The original data structures are not modified.

    public List<String> getSortedListOfSubstrings(int index) {
    Map<String, Integer> frequencies = generateFrequencies(index);
    Set<String> substrings = frequencies.keySet();
    List<String> sortedList = new ArrayList<>(substrings);
    Collections.sort(sortedList);
    return sortedList;
    }

   // Pre: index is within bounds of the sequences list
   // Post: returns the reverse complement of the DNA sequence at the index
   // Reverses the order of letters using a Queue and a Stack
   // Substitutes bases: A<->T, G<->C
   public String getReverseComplement(int index) {
      String originalSequence = sequences.get(index);
      
      Queue<Character> sequenceQueue = new LinkedList<Character>();
      for (int x = 0; x < originalSequence.length(); x++) {
         sequenceQueue.add(originalSequence.charAt(x));
      }

   // This reversal happens because a Stack is Last In First Out
   Stack<Character> sequenceStack = new Stack<Character>();
   while (!sequenceQueue.isEmpty()) {
      Character code = sequenceQueue.remove();
      sequenceStack.push(code);
   }

   StringBuilder reverseComplement = new StringBuilder();
   while (!sequenceStack.empty()) {
      Character code = sequenceStack.pop();

      if (code == 'A' || code == 'a') {
         code = 'T';
      } else if (code == 'T' || code == 't') {
         code = 'A';
      } else if (code == 'G' || code == 'g') {
         code = 'C';
      } else if (code == 'C' || code == 'c') {
         code = 'G';
      }
      
      reverseComplement.append(code);
   }

   return reverseComplement.toString();
}
}
