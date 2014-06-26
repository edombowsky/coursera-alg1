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
    private static final long MEGABYTE = 1024L * 1024L;

    private Node<Item> front;   // pointer to front of the list
    private Node<Item> rear;    // pointer to end of the list
    private int size;           // Size of the queue

    /*
    * Construct an empty deque
    */
    public Deque()
    {
        front = null;
        rear  = null;
        size  = 0;
    }

    /**
     * Linked list nodes. Stores links for previous, next and item in the node.
     *
     * @param <Item>
     */
    private class Node<Item>
    {
        private Node<Item> previous;    // Pointer to previous node
        private Node<Item> next;        // Pointer to next node
        private Item value;             // Storage for data item

        public Node<Item> getPrevious()
        {
            return previous;
        }

        public void setPrevious(Node<Item> prev)
        {
            this.previous = prev;
        }

        public Node<Item> getNext()
        {
            return next;
        }

        public void setNext(Node<Item> next)
        {
            this.next = next;
        }

        public Item getValue()
        {
            return value;
        }

        public void setValue(Item value)
        {
            this.value = value;
        }
    }


    /**
     * Add element at the beginning of the queue
     *
     * @param item
     */
    public void addFirst(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException("cannot enqueue null items");
        }

        Node<Item> node = new Node<Item>();
        node.setValue(item);
        node.setNext(front);
        if (front != null) front.setPrevious(node);
        if (front == null) rear = node;
        front = node;
        size++;
    }

    /**
     * Add element at the end of the queue
     *
     * @param item
     */
    public void addLast(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException("cannot enqueue null items");
        }

        Node<Item> node = new Node<Item>();
        node.setValue(item);
        node.setPrevious(rear);
        if (rear != null) rear.setNext(node);
        if (rear == null) front = node;

        rear = node;
        size++;
    }

    /**
     * Remove an item from the beginning of the queue
     */
    public Item removeFirst()
    {
        if (front == null)
        {
            throw new NoSuchElementException("Deque underflow!! unable to remove.");
        }

        Node<Item> tmpFront = front.getNext();
        if (tmpFront != null) tmpFront.setPrevious(null);
        if (tmpFront == null) rear = null;
        Item revovedItem = front.getValue();
        front = tmpFront;
        size--;

        return revovedItem;
    }

    /**
     * Remove an item from the beginning of the queue
     */
    public Item removeLast()
    {
        if (rear == null)
        {
            throw new NoSuchElementException("Deque underflow!! unable to remove.");
        }

        Node<Item> tmpRear = rear.getPrevious();
        if (tmpRear != null) tmpRear.setNext(null);
        if (tmpRear == null) front = null;
        Item removedItem = rear.getValue();
        rear = tmpRear;
        size--;

        return removedItem;
    }

    /**
     * Return the number of items in the deque
     *
     * @return the number of items in the deque
     */
    public int size()
    {
        return size;
    }

    /**
     * Is the deque empty?
     *
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     *
     * @return an iterator over items in order from front to end
     */
    @Override
    public Iterator<Item> iterator()
    {
        Iterator<Item> it = new Iterator<Item>()
        {
            private Node current = front;

            @Override
            public boolean hasNext()
            {
                return current != null;
            }

            @Override
            public Item next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException("no items left in the queue");
                }
                Item item = (Item) current.getValue();
                current = current.next;

                return item;
            }

            @Override
            public void remove()
            {
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
        Deque<Integer> deque = new Deque<Integer>();

        try
        {
            Stopwatch sw = new Stopwatch();

            deque.addFirst(34);
            deque.addFirst(67);
            deque.addFirst(29);
            deque.addFirst(765);
            deque.addFirst(34);
            deque.addFirst(67);
            deque.addFirst(29);
            deque.addFirst(765);
            deque.addFirst(34);
            deque.addFirst(67);
            deque.addFirst(29);
            deque.addFirst(765);
            deque.addFirst(34);
            deque.addFirst(67);
            deque.addFirst(29);
            deque.addFirst(765);
            deque.removeFirst();
            deque.removeFirst();
            deque.removeFirst();
            deque.addLast(43);
            deque.addLast(83);
            deque.addLast(84);
            deque.addLast(546);
            deque.addLast(356);
            deque.removeLast();
            deque.removeLast();
            deque.removeLast();
            deque.removeLast();
            deque.removeFirst();
            deque.removeFirst();
            deque.removeFirst();
            System.out.println("Size of queue = " + deque.size());
            System.out.println("elapsed time  = " + sw.elapsedTime());

            Stopwatch sw1 = new Stopwatch();

            StdOut.println("Contents of deque: [ ");
//            Iterator itr = deque.iterator();
//            while (itr.hasNext())
//            {
//                Object element = itr.next();
//                StdOut.print(element + " ");
//            }
            for (int aDeque : deque)
            {
                StdOut.print(aDeque + " ");
            }
            StdOut.println("]");
            StdOut.println();

            int N  = Integer.parseInt(args[0]);

            // add elements 1, ..., N using addFirst
            StdOut.println(N + " random integers between 0 and 99");
            Deque<Integer> list = new Deque<Integer>();
            for (int i = 0; i < N; i++)
            {
                list.addFirst((int) (100 * Math.random()));
            }

            for (int aDeque : list)
            {
                StdOut.print(aDeque + " ");
            }
            StdOut.println();

            // add elements 1, ..., N using addLast
            StdOut.println(N + " random integers between 0 and 99");
            Deque<Integer> list1 = new Deque<Integer>();
            for (int i = 0; i < N; i++)
            {
                list1.addLast((int) (100 * Math.random()));
            }
            for (int aDeque : list1)
            {
                StdOut.print(aDeque + " ");
            }
            StdOut.println();
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
