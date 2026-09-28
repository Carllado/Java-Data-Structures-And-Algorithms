// Yi-Chieh Chu (Carl)
// CS 143
// HW #4: HTML Checker
import java.util.*;

public class HTMLManager {
   private Queue<HTMLTag> tags;

   //Pre:  The passed queue 'html' must not be null.
   //Post: The HTMLManager is initialized with the tags from the provided queue. 
   //Throws IllegalArgumentException if the parameter is null.
   public HTMLManager(Queue<HTMLTag> html) {
      if (html == null) {
         throw new IllegalArgumentException("Queue cannot be null");
      }
         this.tags = html;
   }

   //Pre:  None.
   //Post: Returns the current queue of HTMLTag objects managed by this class.
    public Queue<HTMLTag> getTags() {
        return tags;
    }

    //Pre:  None.
    //Post: Returns a String representation of the HTML tags. The state of the internal queue is unchanged (tags are re-added in original order). 
    //Tag strings are trimmed for compatibility with the checker.
    public String toString() {
        StringBuilder result = new StringBuilder();
        int size = tags.size();
        for (int i = 0; i < size; i++) {
            HTMLTag tag = tags.remove();
            result.append(tag.toString().trim());
            tags.add(tag);
        }
        return result.toString();
    }
    
    //Pre:  The internal queue contains the initial set of tags.
    //Post: The internal queue is updated to contain a valid, "fixed" version of the HTML. All opening tags have matching closing tags, and mismatched or extra closing tags are corrected or removed.
    public void fixHTML() {
        Stack<HTMLTag> stack = new Stack<>();
        int size = tags.size();

        for (int i = 0; i < size; i++) {
            HTMLTag current = tags.remove();

            if (current.isSelfClosing()) {
                tags.add(current);
            } else if (current.isOpening()) {
                tags.add(current);
                stack.push(current);
            } else if (current.isClosing()) {
                if (!stack.isEmpty()) {
                    HTMLTag top = stack.peek();
                    if (top.matches(current)) {
                        tags.add(current);
                        stack.pop();
                    } else {
                        tags.add(top.getMatching());
                        stack.pop();
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            tags.add(stack.pop().getMatching());
        }
    }
}

/*
  ----jGRASP exec: java HTMLChecker
 ===============================
 Processing tests/test3.html...
 ===============================
 HTML: <br /></p></p>
 Checking HTML for errors...
 HTML after fix: <br />
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test2.html...
 ===============================
 HTML: <a><a><a></a>
 Checking HTML for errors...
 HTML after fix: <a><a><a></a></a></a>
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test5.html...
 ===============================
 HTML: <div><h1></h1><div><img /><p><br /><br /><br /></div></div></table>
 Checking HTML for errors...
 HTML after fix: <div><h1></h1><div><img /><p><br /><br /><br /></p></div></div>
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test4.html...
 ===============================
 HTML: <div><div><ul><li></li><li></li><li></ul></div>
 Checking HTML for errors...
 HTML after fix: <div><div><ul><li></li><li></li><li></li></ul></div></div>
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test1.html...
 ===============================
 HTML: <b><i><br /></b></i>
 Checking HTML for errors...
 HTML after fix: <b><i><br /></i></b>
 ----> Result matches Expected Output!
 
 ===============================
         All tests passed!
 ===============================
 
  ----jGRASP: Operation complete.
 
*/