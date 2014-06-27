/**
 * A client program Subset that takes a command-line integer k; reads in a
 * sequence of N strings from standard input using StdIn.readString(); and
 * prints out exactly k of them, uniformly at random. Each item from the
 * sequence can be printed out at most once. You may assume that k ≥ 0 and
 * no greater than the number of string N on standard input.
 *
 * % echo A B C D E F G H I | java Subset 3
 * C
 * G
 * A
 * CC
 *
 * % echo A B C D E F G H I | java Subset 3
 * E
 * F
 * G
 *
 * % echo AA BB BB BB BB BB CC CC | java Subset 8
 * BB
 * AA
 * BB
 * CC
 * BB
 * BB
 * CC
 * BB
 */
public class Subset
{
    public static void main(String[] args)
    {
        // TODO: implement this

        if (args.length != 1)
        {
            throw new IllegalArgumentException("Provide argument for k!");
        }

        int k = Integer.parseInt(args[0]);

        if (k < 0)
        {
            throw new IllegalArgumentException("k must be >= 0!");
        }

        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int numItems = 0;

        // read strings from std input:
        while (!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            StdOut.println("Entered: " + s);
            rq.enqueue(s);
            ++numItems;
        }

        if (k > numItems) k = numItems;

        for (int i = 0; i < k; i++)
        {
            StdOut.println(rq.dequeue());
        }
    }
}
