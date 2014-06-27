import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class DequeTest
{
    private Deque<String> sdque;
    private Deque<Integer> idque;

    @Before
    public void setUp()
    {
        sdque = new Deque<String>();
        idque = new Deque<Integer>();
    }

    @Test
    public void testIsEmpty()
    {
        assertTrue(sdque.isEmpty());
        sdque.addLast("test");
        sdque.removeFirst();
//        assertTrue("IsEmpty() test failure. Deque is not empty.", sdque.isEmpty());
        assertThat("IsEmpty() test failure. Deque is not empty.", sdque.isEmpty(), is(true));
    }

    @Test
    public void testSize()
    {
        assertTrue(sdque.size() == 0);
        sdque.addFirst("s");
        assertTrue(sdque.size() == 1);
    }

    @Test
    public void testAddFirst()
    {
        String expected = "testMessage";
        sdque.addFirst(expected);
        String obtained = sdque.removeFirst();
        assertEquals(expected + " expected, got " + obtained, expected, obtained);
    }

    @Test
    public void testAddLast()
    {
        String expected = "testMessage";
        sdque.addLast(expected);
        String obtained = sdque.removeLast();
        assertEquals(expected + " expected, got " + obtained, expected, obtained);
    }

    @Test
    public void testRemoveFirst()
    {
        assertTrue(sdque.size() == 0);
        sdque.addLast("s");
        assertTrue(sdque.size() == 1);
        String item = sdque.removeFirst();
        assertTrue(item == "s");
    }

    @Test
    public void testRemoveLast()
    {
        assertTrue(sdque.size() == 0);
        sdque.addFirst("s");
        assertTrue(sdque.size() == 1);
        String item = sdque.removeLast();
        assertTrue(item == "s");
    }

    @Test
    public void traverse_NonEmpty_Empty_NonEmpty()
    {
        String obtained;

        String item1 = "test1";
        String item2 = "test2";
        sdque.addFirst(item1);
        sdque.addFirst(item2);

        obtained = sdque.removeFirst();
        obtained = sdque.removeFirst();

        String item3 = "test3";
        sdque.addFirst(item3);
        obtained = sdque.removeFirst();
        assertEquals(item3 + " expected, got " + obtained, item3, obtained);
    }

    @Test (expected = java.lang.UnsupportedOperationException.class)
    public void testIteratorException()
    {
        Iterator<String> iter = sdque.iterator();
        iter.remove();
    }

    @Test (expected = java.util.NoSuchElementException.class)
    public void testIteratorNextException()
    {
        Iterator<String> iter = sdque.iterator();
        iter.next();
    }



    @Test (expected = java.lang.NullPointerException.class)
    public void testNullItemCheck()
    {
        sdque.addFirst(null);
    }

   @Test (expected = java.util.NoSuchElementException.class)
    public void testEmptyDequeCheck()
    {
        sdque.removeFirst();

    }

    @Test
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
        assertFalse("Iterators are equal", iter1.equals(iter2));
        iter1.next();
        String resultIter1 = iter1.next();
        iter2.next();
        iter2.next();
        String resultIter2 = iter2.next();
        assertEquals(item2 + " expected(with 1st Iter), got " + resultIter1, item2, resultIter1);
        assertEquals(item3 + " expected(with 2nd Iter), got " + resultIter2, item3, resultIter2);
    }


    // This performs n random operations on an Deque and Java's LinkedList, and
    // verifies that they end up the same. This is useful for testing because
    //
    //     1) it can test for very large examples and
    //     2) it tests enqueues and dequeues in all sorts of interleaved
    //        orderings, possibly picking out obscure bugs.
    //
    // Note: this uses random values, which means that running tests different
    // times could have different results (if, say, there is a bug in your code
    // but hitting it is rare)
    private void testNOperations(int n)
    {
        LinkedList<Integer> goodQueue = new LinkedList<Integer>();
        int num;

        for (int i=0; i<n; i++)
        {
            // Enqueue element if queue is empty, or on a 2/3 chance
            if (idque.isEmpty() || StdRandom.uniform(1, 3) < 2)
            {
                num = StdRandom.uniform(1, 100);
                idque.addFirst(num);
                goodQueue.add(num);
            }
            else //dequeue
            {
                assertTrue("Queues differ on dequeue",
                        goodQueue.remove() == idque.removeLast());
            }

            // Using different asserts, such as assertEquals, can clarify your
            // intent to people reading your code technically, when using
            // assertEquals, you're supposed to put the expected value first,
            // then the actual value
            assertEquals("goodQueue and queue do not match on isEmpty()",
                    goodQueue.isEmpty(), idque.isEmpty());
        }

        // Now that we're done going through n operations, dequeue until empty
        // and compare results
        while (!idque.isEmpty())
        {
            assertTrue("goodQueue is empty but queue isn't", !goodQueue.isEmpty());
            assertTrue("End dequeues do not match", goodQueue.remove() == idque.removeLast());
        }

        assertTrue("queue is empty but goodQueue isn't", goodQueue.isEmpty());
    }

    @Test
    public void test10()
    {
        testNOperations(10);
    }

    @Test
    public void test100()
    {
        testNOperations(100);
    }

    @Test
    public void test10000()
    {
        testNOperations(10000);
    }

    @Test
    public void testMillion()
    {
        testNOperations(1000000);
    }
}