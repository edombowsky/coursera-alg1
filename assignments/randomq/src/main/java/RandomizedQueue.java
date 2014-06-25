import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at random from items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item>
{
    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue()
    {
        // TODO: implement this
    }

    /**
     * Is the queue empty?
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty()
    {
        // TODO: implement this
        return false;
    }

    /**
     * Returns the number of items on the queue
     *
     * @return the number of items in the queue
     */
    public int size()
    {
        // TODO: implement this
        return 0;
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

        // TODO: implement this
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

        // TODO: implement this
        return null;
    }

    /**
     * Return (but do not delete) a random item
     *
     * @return a random item from the queue
     */
    public Item sample()
    {
        // TODO: implement this
        return null;
    }

    /**
     * Returns an independent iterator over items in random order
     *
     * @return
     */
    @Override
    public Iterator<Item> iterator()
    {
        // TODO: implement this

        Iterator<Item> it = new Iterator<Item>()
        {
            private int currentIndex = 0;

            @Override
            public boolean hasNext()
            {
                // TODO: implement this
//                return currentIndex < currentSize && arrayList[currentIndex] != null;
                return false;
            }

            @Override
            public Item next()
            {
                // TODO: implement this
//                return arrayList[currentIndex++];
                return null;
            }

            @Override
            public void remove()
            {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("unsupported action");
            }
        };

        return it;
    }

    /**
     * For unit testing
     *
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO: implement this
    }
}
