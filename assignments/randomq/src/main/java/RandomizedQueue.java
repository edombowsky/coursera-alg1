import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at random from items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item>
{
    private static int INITIAL_QUEUE_SIZE         = 2;
    private static int NEXT_QUEUE_RESIZE_MULTIPLE = 2;

    private Item[] theQueue;        // queue of elements
    private int N;                  // number of elements in queue

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue()
    {
        theQueue = (Item[]) new Object [INITIAL_QUEUE_SIZE];
        N = 0;
    }

    /**
     * Is the queue empty?
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return N == 0;
    }

    /**
     * Returns the number of items on the queue
     *
     * @return the number of items in the queue
     */
    public int size()
    {
        return N;
    }

    /**
     * Add an item to the queue
     *
     * @param item    item to be added to the queue
     */
    public void enqueue(Item item)
    {
        validateItem(item);

        if (isFull()) resize(NEXT_QUEUE_RESIZE_MULTIPLE * N);

        theQueue[N] = item;

        if (N > 0)
        {
            int randomIndex = StdRandom.uniform(N + 1);
            Item tmpItem = theQueue[randomIndex];
            theQueue[randomIndex] = theQueue[N];
            theQueue[N] = tmpItem;
        }

        N++;
    }

    /**
     * Delete and return a random item
     *
     * @return a random item from the queue
     */
    public Item dequeue()
    {
        checkItemExists();

        int randomItemIndex = StdRandom.uniform(N);
        Item item = theQueue[randomItemIndex];
        N--;
        theQueue[randomItemIndex] = theQueue[N];
        theQueue[N] = null;       // to avoid loitering

        if (tooBig()) resize(theQueue.length / NEXT_QUEUE_RESIZE_MULTIPLE);

        return item;
    }

    /**
     * Return (but do not delete) a random item
     *
     * @return a random item from the queue
     */
    public Item sample()
    {
        checkItemExists();

        return theQueue[StdRandom.uniform(N)];
    }

    /**
     * Returns an independent iterator over items in random order
     *
     * @return
     */
    @Override
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }

    /**
     * An iterator, doesn't implement remove() since it's optional
     */
    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int[] random = new int[N];
        private int current  = N;

        RandomizedQueueIterator()
        {
            // Find next random pos
            while (current > 0)
            {
                int r = StdRandom.uniform(N);

                if (random[r] == 0)
                {
                    // Add to random but with offset + 1 to be able to
                    // differentiate from 0!
                    random[r] = current;
                    --current;
                }
            }
        }

        public boolean hasNext() { return current < N; }
        public void remove() { throw new UnsupportedOperationException("unsupported method"); }

        public Item next()
        {
            // Subtract offset and return item from the next position
            if (current >= 0 && current < N)
            {
                return theQueue[random[current++] - 1];
            }
            else
            {
                throw new NoSuchElementException("no more elements in queue");
            }
        }
    }

    /**
     * Resize the storage array.
     *
     * @param len the new size of the array
     */
    private void resize(int len)
    {
        Item[] temp = (Item []) new Object[len];

        int j = 0;
        for (int i = 0; i < N; ++i)
        {
            // Take only existing (non null) nodes
            if (theQueue[i] != null) temp[j++] = theQueue[i];
        }

        theQueue = temp;
    }

    /**
     * Validate that an item is not null.
     *
     * @param item
     */
    private void validateItem(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException("cannot enqueue null items");
        }
    }

    /**
     * Is the storage space full?
     *
     * @return
     */
    private boolean isFull()
    {
        return (N == theQueue.length);
    }

    /**
     *
     */
    private void checkItemExists()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("queue is empty");
        }
    }

    /**
     * Has the storage space become full and in need of resizing?
     *
     * @return
     */
    private boolean tooBig()
    {
        return (N > 0 && N == (theQueue.length / 4));
    }

    /**
     * Show the string representation of the queue
     *
     * @return
     */
    public String showQueue()
    {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
        {
            s.append(item + " ");
        }

        return s.toString();
    }


    /**
     * For unit testing
     *
     * @param args
     */
    public static void main(String[] args)
    {
        // Build a queue containing the Integers 1,2,...,6:
        RandomizedQueue<Integer> Q = new RandomizedQueue<Integer>();

        for (int i = 1; i < 7; ++i)
        {
            Q.enqueue(i); // autoboxing! cool!
        }

        StdOut.println("Queue containing the integers 1,2,...,6");
        StdOut.println(Q.showQueue());
        StdOut.println();

        // Print 30 die rolls to standard output
        StdOut.print("Some die rolls: ");
        for (int i = 1; i < 30; ++i)
        {
            StdOut.print(Q.sample() +" ");
        }
        StdOut.println();

        // Let's be more serious: do they really behave like die rolls?
        int[] rolls = new int [10000];
        for (int i = 0; i < 10000; ++i)
        {
            rolls[i] = Q.sample(); // autounboxing! Also cool!
        }
        StdOut.printf("Mean (should be around 3.5): %5.4f\n", StdStats.mean(rolls));
        StdOut.printf("Standard deviation (should be around 1.7): %5.4f\n",
                StdStats.stddev(rolls));

        // Let's look at the iterator. First, we make a queue of colours:
        RandomizedQueue<String> C = new RandomizedQueue<String>();
        C.enqueue("red");
        C.enqueue("blue");
        C.enqueue("green");
        C.enqueue("yellow");

        StdOut.println();
        StdOut.println("Queue containing some colours");
        StdOut.println(C.showQueue());
        StdOut.println();

        Iterator I = C.iterator();
        Iterator J = C.iterator();

        StdOut.print("Two colours from first shuffle: ");
        StdOut.print(I.next() + " ");
        StdOut.print(I.next() + " ");

        StdOut.print("\nEntire second shuffle: ");
        while (J.hasNext()) StdOut.print(J.next() + " ");

        StdOut.print("\nRemaining two colours from first shuffle: ");
        StdOut.print(I.next() + " ");
        StdOut.println(I.next());
    }
}
