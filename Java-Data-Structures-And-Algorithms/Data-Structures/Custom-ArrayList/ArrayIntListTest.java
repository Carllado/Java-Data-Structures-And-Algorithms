import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArrayIntListTest {


  @Test(expected = IllegalStateException.class)
  public void testIllegalStateExceptionIsThrown() {
    ArrayIntList tester = new ArrayIntList();
    for (int x = 0; x < 21; x++) {
        tester.add(x); // the capacity is default to 20 so this should throw an exception
    }
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testIndexOutOfBoundsExceptionIsThrown() {
    ArrayIntList tester = new ArrayIntList();
    tester.get(1000);
  }

  @Test
  public void testAddElementAtPosition() {
    ArrayIntList tester = new ArrayIntList();
    
    // load up an array with 5 elements
    tester.add(0);
    tester.add(1);
    tester.add(2);
    tester.add(3);
    tester.add(4);
    tester.add(5);
    
    // insert into that array a new element at specific positions
    tester.add(1, 100);
    assertEquals("after adding elements the new postion 1 should be", 100, tester.get(1));
    assertEquals("after adding elements the new postion 2 should be", 1, tester.get(2));
    tester.add(1, 200);
    assertEquals("after adding elements the new position 1 should be", 200, tester.get(1));
    assertEquals("after adding elements the new position 2 should be", 100, tester.get(2));
    assertEquals("after adding elements the new position 3 should be", 1, tester.get(3));
  }
  
  @Test
  public void testAddAndSize() {
    ArrayIntList tester = new ArrayIntList();
    tester.add(888);
    tester.add(100);
    tester.add(200);
    tester.add(300);
    tester.add(400);
    tester.add(500);
    tester.add(600);
    assertEquals("after adding 7 elements the size should be", 7, tester.size());
  }

  @Test
  public void testRemoveElementAtPositionAndGet() {
    ArrayIntList tester = new ArrayIntList();
    tester.add(888);
    tester.add(100);
    tester.add(200);
    tester.add(300);
    tester.add(400);
    tester.add(500);
    int x = tester.get(3);
    tester.remove(3);
 //   assertEquals("after removing the 3rd element the 3rd element should be", 400, tester.get(3));
    assertEquals("after remove value at index 3 no longer exist in array", -1, tester.indexOf(x));

   }

  @Test
  public void testIndexOf() {
    ArrayIntList tester = new ArrayIntList();
    tester.add(888);
    tester.add(100);
    tester.add(200);
    tester.add(300);
    tester.add(400);
    tester.add(500);
    tester.remove(3);
    assertEquals("after index of 200 should be", 2, tester.indexOf(200));
   }
   
  @Test
  public void testToString() {
    ArrayIntList tester = new ArrayIntList();
    tester.add(888);
    tester.add(100);
    tester.add(200);
    tester.add(300);
    assertEquals("after toString shoule be", "[888, 100, 200, 300]", tester+"");
   }

 @Test
  public void testEquals() {
  
    // check if two lists are equal
    ArrayIntList testList1 = new ArrayIntList();
    testList1.add(0);
    testList1.add(100);
    testList1.add(200);
    testList1.add(300);
    testList1.add(400);
    testList1.add(500);
    
    ArrayIntList testList2 = new ArrayIntList();
    testList2.add(0);
    testList2.add(100);
    testList2.add(200);
    testList2.add(300);
    testList2.add(400);
    testList2.add(500);

    assertEquals("list 1 should be eqaul to list 2", true, testList1.equals(testList2));
    
    // check if two lists are not equal
    ArrayIntList testList3 = new ArrayIntList();
    testList3.add(0);
    testList3.add(100);
    testList3.add(200);
    testList3.add(305);
    testList3.add(400);
    testList3.add(500);
    
    assertEquals("list 1 should not be eqaul to list 3", false, testList1.equals(testList3));
    
    // check if two empty lists are equal
    ArrayIntList testList4 = new ArrayIntList();
    ArrayIntList testList5 = new ArrayIntList();

    assertEquals("empty list 4 should eqaul to empty list 5", true, testList4.equals(testList5)); 
  }


}