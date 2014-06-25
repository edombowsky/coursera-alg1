import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque (pronounced "deck") is a generalization of a
 * stack and a queue that supports inserting and removing items from either the
 * front or the back of the data structure.
 *
 */
public class Deque<Item> implements Iterable<Item>
{
    /**
     * Constructs an empty deque
     */
    public Deque()
    {
        // TODO: implement this
    }

    /**
     * Is the deque empty?
     *
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty()
    {
        // TODO: implment this

        return false;
    }

    /**
     * Return the number of items on the deque
     *
     * @return the number of items on the deque
     */
    public int size()
    {
        // TODO: implement this

        return 0;
    }

    /**
     * Insert an item at the front of the queue
     *
     * @param item    item to be inserted at the front of the queue
     */
    public void addFirst(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException("cannot enqueue null items");
        }

        // TODO: implement this
    }

    /**
     * Insert an item at the end of the queue
     *
     * @param item    item to inserted at the end of the queue
     */
    public void addLast(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException("cannot enqueue null items");
        }

        // TODO: implement this
    }

    /**
     * Delete and return the item at the front
     *
     * @return the item at the front of the queue
     */
    public Item removeFirst()
    {
        if (this.isEmpty())
        {
            throw new NoSuchElementException("cannot dequeue from an empty queue");
        }

        // TODO: implement this

        return null;
    }

    /**
     * Delete and return the item at the end
     *
     * @return the item at the end of the queue
     */
    public Item removeLast()
    {
        if (this.isEmpty())
        {
            throw new NoSuchElementException("cannot dequeue from an empty queue");
        }

        // TODO: implement this

        return null;
    }

    /**
     *
     * @return an iterator over items in order from front to end
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
     * For unit testing.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO: implement this
    }
}
