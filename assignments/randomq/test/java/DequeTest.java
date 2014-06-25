/*
 * Testing Deque
 * 
 * @author caeadom
 */
import junit.framework.TestCase;

import java.util.Iterator;

public class DequeTest extends TestCase
{
    Deque<String> sdque;

    protected void setUp()
    {
        sdque = new Deque<String>();
    }

    public void testIsEmpty()
    {
        sdque.addLast("test");
        sdque.removeFirst();
        assertTrue("IsEmpty() test failure. Deque is not empty.", sdque.isEmpty());
    }

    public void testAddFirst()
    {
        String expected = "testMessage";
        sdque.addFirst(expected);
        String obtained = sdque.removeFirst();
        assertEquals(expected + " expected, got " + obtained, expected,obtained );
    }

    public void testAddLast()
    {
        String expected = "testMessage";
        sdque.addLast(expected);
        String obtained = sdque.removeLast();
        assertEquals(expected + " expected, got " + obtained, expected,obtained );
    }

    public void testIterator()
    {
        String item1 = "test1";
        String item2 = "test2";
        String item3 = "test3";
        String item4 = "test4";
        sdque.addLast(item1);
        sdque.addLast(item2);
        sdque.addLast(item3);
        sdque.addLast(item4);
        Iterator<String> iter1 = sdque.iterator();
        Iterator<String> iter2 = sdque.iterator();
        assertFalse("Iterators are equal",iter1.equals(iter2));
        iter1.next();
        String resultIter1 = iter1.next();
        iter2.next();
        iter2.next();
        String resultIter2 = iter2.next();
        assertEquals(item2 + " expected(with 1st Iter), got " + resultIter1, item2,resultIter1 );
        assertEquals(item3 + " expected(with 2nd Iter), got " + resultIter2, item3,resultIter2 );
    }
}