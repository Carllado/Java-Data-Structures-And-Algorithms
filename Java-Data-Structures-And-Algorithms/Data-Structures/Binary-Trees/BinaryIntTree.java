/*
  Yi-Chieh Chu(Carl Chu)
  Class: CS 143
  Assignment: HW #6: BinaryIntTree 
*/

class BinaryIntTree {

   private IntTreeNode overallRoot;
   
   // pre: overallRoot has nothing important in it because the old values will be lost
   // post: overallRoot points to the root of a tree that contains max nodes   
   public void buildTree(int max) {
      overallRoot = buildTree(1, max);
   
   }
   
   // pre: n is the value to be assigned to the new node
   // post: a new subtree has been created with data value n
   private IntTreeNode buildTree(int n, int max) {
      if (n > max) {
         return null;
      } else {
         IntTreeNode left = buildTree(2*n, max);
         IntTreeNode right = buildTree(2*n +1, max);
         return new IntTreeNode(n, left, right); 
      }  
   }
   
   // pre: none
   // post: returns the data values of the tree using a preorder traversal
   public String getPreOrder() {
      String result = getPreOrder(overallRoot, "");
      return result.strip();
   }
   
   // pre: none
   // post: the values of the subtree are added to the result string in preorder traversal (Root, Left, Right)
   private String getPreOrder(IntTreeNode root, String result) {
      if (root != null) {
         result = result + root.data + " ";
         result = getPreOrder(root.left, result);
         result = getPreOrder(root.right, result);
      }
      return result;
   }
   
   // pre: none
   // post: returns the data values of the tree using a inorder traversal
   public String getInOrder() {
      String result = "";
      result = getInOrder(overallRoot, result);
      return result.strip();
   }
   
   // pre: none
   // post: the values of the sub tree are  added to the result string in inorder traverseral order
   private String getInOrder(IntTreeNode root, String result) {
      if (null != root) {
          result = getInOrder(root.left, result);
          result = result + root.data + " ";
          result = getInOrder(root.right, result);
      }   
      return result;
   }

   // pre: none
   // post: returns the data values of the tree using a postorder traversal
   public String getPostOrder() {
      String result = getPostOrder(overallRoot, "");
      return result.strip();
   }
   
   // pre: none
   // post: the values of the subtree are added to the result string in postorder traversal (Left, Right, Root)
   private String getPostOrder(IntTreeNode root, String result) {
      if (root != null) {
         result = getPostOrder(root.left, result);
         result = getPostOrder(root.right, result);
         result = result + root.data + " ";
      }
      return result;
   }

   // pre: none
   // post: the tree is print to console rotated 90 degree so the root is on the far left
   public void printSidewaysIndented() {
      printSidewaysIndented(overallRoot, 0);
   }
   
   // pre: none
   // post: the subtree is printed spaced out to appropriately for the level specified
   private void printSidewaysIndented(IntTreeNode root, int level) {
      if (null != root) {
         printSidewaysIndented(root.right, level + 1);
         for (int i = 0; i < level; i ++) {
            System.out.print("    ");
         }
         System.out.println(root.data);
         printSidewaysIndented(root.left, level + 1);
      }   
   }

}

/*
  ----jGRASP exec: java junit_runner.JgrRunner 58159 BinaryIntTreeTest
 Running 4 JUnit tests.
     3
 1
         5
     2
         4
 
 Completed 4 tests  4 passed
 
  ----jGRASP: Operation complete.
 
*/