import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RandomizedQueueTest
{
    private RandomizedQueue<String> randomQueue;
    private String a, b, c, d;

    @Before
    public void setup()
    {
        randomQueue = new RandomizedQueue<String>();
    }

    @Test
    public void testIsEmpty()
    {
        assertTrue(randomQueue.isEmpty());
    }

    @Test
    public void testSize()
    {
        assertEquals(randomQueue.size(), 0);
        randomQueue.enqueue("t");
        assertEquals(randomQueue.size(), 1);
        randomQueue.dequeue();
        assertEquals(randomQueue.size(), 0);
    }

    @Test
    public void testEnDe()
    {
        randomQueue.enqueue("t");
        randomQueue.enqueue("e");
        randomQueue.enqueue("s");
        randomQueue.enqueue("t");
        assertEquals(randomQueue.size(), 4);

        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        assertEquals(randomQueue.size(), 0);


//        assertTrue (a == "t" || a == "e" || a == "s" || a == "t");
//        assertTrue (b == "t" || b == "e" || b == "s" || b == "t");
//       assertTrue (c == "t" || c == "e" || c == "s" || c == "t");
//       assertTrue (d == "t" || d == "e" || d == "s" || d == "t");
    }


    @Test
    public void testIterator()
    {
        randomQueue.enqueue("t");
        randomQueue.enqueue("e");
        randomQueue.enqueue("s");
        randomQueue.enqueue("t");
        Iterator<String> it1 = randomQueue.iterator();
        assertTrue(it1.hasNext());
        a = it1.next();
        b = it1.next();
        c = it1.next();
        d = it1.next();
        assertTrue(a == "t" || a == "e"|| a == "s" || a == "t");
        assertTrue(b == "t" || b == "e"|| b == "s" || b == "t");
        assertTrue(c == "t" || c == "e"|| c == "s" || c == "t");
        assertTrue(d == "t" || d == "e"|| d == "s" || d == "t");
        assertTrue(!it1.hasNext());
        randomQueue.enqueue ("s");
        randomQueue.enqueue ("s");
        randomQueue.enqueue ("s");
//        assertTrue(it1.next() == "s");
        assertTrue(!it1.hasNext());
    }

//    @After
//    public void testPrintString()
//    {
//        System.out.println (a);
//        System.out.println (b);
//        System.out.println (c);
//        System.out.println (d);
//    }
}
