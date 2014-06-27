import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at random from items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] theQueue;        // queue of elements
    private int N;                  // number of elements in queue

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue()
    {
        theQueue = (Item[]) new Object [2];
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
        if (item == null)
        {
            throw new NullPointerException("cannot enqueue null items");
        }

        if (N == theQueue.length) resize(2*N);

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
        if (this.isEmpty())
        {
            throw new NoSuchElementException("cannot dequeue from an empty queue");
        }

        Item item = theQueue[--N];

        theQueue[N] = null;     // to avoind loitering

        if (N > 0 && N == (theQueue.length/4)) resize(theQueue.length/2);

        return item;
    }

    /**
     * Return (but do not delete) a random item
     *
     * @return a random item from the queue
     */
    public Item sample()
    {
        if (isEmpty()) throw new NoSuchElementException("the queue is empty");

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

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int  it;
        private Item temp;
        private int  defaultNumOfVar;

        public RandomizedQueueIterator()
        {
            defaultNumOfVar = N;
            for (int i = 0; i < defaultNumOfVar; i++)
            {
                // Random number between i and hi
                int r = i + StdRandom.uniform(defaultNumOfVar - i);
                temp = theQueue[i];
                theQueue[i] = theQueue[r];
                theQueue[r] = temp;
            }

            it = 0;
        }

        public boolean hasNext()
        {
            if (it < (defaultNumOfVar - 1)) return true;

            return false;
        }

        public Item next()
        {
            if (it >= defaultNumOfVar) throw new NoSuchElementException();

            return theQueue[it++];
        }

        public void remove()
        {
            throw new UnsupportedOperationException("unsupported action");
        }
    }

    private void resize(int len)
    {
        StdOut.println("Calling resize");

        Item[] temp = (Item []) new Object[len];

        for (int i = 0; i < N; ++i)
        {
            temp[i] = theQueue[i];
        }

        theQueue = temp;
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

        Iterator I = C.iterator();
        Iterator J = C.iterator();

        StdOut.print("Two colours from first shuffle: ");
        StdOut.print(I.next()+" ");
        StdOut.print(I.next()+" ");

        StdOut.print("\nEntire second shuffle: ");
        while (J.hasNext()) StdOut.print(J.next()+" ");

        StdOut.print("\nRemaining two colours from first shuffle: ");
        StdOut.print(I.next()+" ");
        StdOut.println(I.next());
    }
}
