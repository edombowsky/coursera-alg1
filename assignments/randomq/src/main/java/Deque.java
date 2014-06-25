import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque (pronounced "deck") is a generalization of a
 * stack and a queue that supports inserting and removing items from either the
 * front or the back of the data structure.
 *
 */
public class Deque<Item> implements Iterable<Item>
{
    private List<Item> deque = new ArrayList<Item>();

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
        // TODO: implement this

        return deque.isEmpty();
    }

    /**
     * Return the number of items in the deque
     *
     * @return the number of items in the deque
     */
    public int size()
    {
        // TODO: implement this

        return deque.size();
    }

    /**
     * Insert an item at the front of the queue
     *
     * @param item    item to be inserted at the front of the queue
     */
    public void addFirst(Item item)
    {
        // TODO: implement this
        if (item == null)
        {
            throw new NullPointerException("cannot enqueue null items");
        }

        System.out.println("adding at front: " + item);
        deque.add(0, item);

        System.out.println(deque);
    }

    /**
     * Insert an item at the end of the queue
     *
     * @param item    item to inserted at the end of the queue
     */
    public void addLast(Item item)
    {
        // TODO: implement this
        if (item == null)
        {
            throw new NullPointerException("cannot enqueue null items");
        }

        System.out.println("adding at rear: " + item);
        deque.add(item);
        System.out.println(deque);
    }

    /**
     * Delete and return the item at the front
     *
     * @return the item at the front of the queue
     */
    public Item removeFirst()
    {
        // TODO: implement this

        if (deque.isEmpty())
        {
            throw new NoSuchElementException("Deque underflow!! unable to remove.");
        }

        // remove an item from the beginning of the queue
        Item rem = deque.remove(0);

        System.out.println("removed from front: " + rem);
        System.out.println(deque);

        return rem;
    }

    /**
     * Delete and return the item at the end
     *
     * @return the item at the end of the queue
     */
    public Item removeLast()
    {
        // TODO: implement this
        if (deque.isEmpty())
        {
            throw new NoSuchElementException("Deque underflow!! unable to remove.");
        }

        // remove an item from the beginning of the queue
        Item rem = deque.remove(deque.size() - 1);

        System.out.println("removed from front: " + rem);
        System.out.println(deque);

        return rem;
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
                //
                //        ______________
                //       /.--------------.\
                //      //                \\
                //     //                  \\
                //    || .-..----. .-. .--. ||
                //    ||( ( '-..-'|.-.||.-.|||
                //    || \ \  ||  || ||||_||||
                //    ||._) ) ||  \'-'/||-' ||
                //     \\'-'  `'   `-' `'  //
                //      \\                //
                //       \\______________//
                //        '--------------'
                //              |_|_
                //       ____ _/ _)_)
                //           '  | (_)
                //        .--'"\| ()
                //              | |
                //              | |
                //              |_|

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
        Deque<Integer> deque = new Deque();

        try
        {
            deque.addFirst(34);
            deque.addLast(45);
            deque.addLast(null);
            deque.removeFirst();
            deque.removeFirst();
            if (deque.isEmpty()) System.out.println("is empty");
            deque.removeFirst();
            deque.addFirst(21);
            deque.addFirst(98);
            deque.addLast(5);
            deque.addFirst(43);
            deque.removeLast();
            System.out.println("Size of queue = " + deque.size());
        }
        catch (UnsupportedOperationException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NoSuchElementException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
