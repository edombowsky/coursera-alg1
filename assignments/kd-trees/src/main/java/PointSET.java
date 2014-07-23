/*************************************************************************
 * Name: Earl Dombowsky
 * Email:
 *
 * Compilation: javac PointSET.java
 * Execution:
 *
 * Description: A mutable data type that represents a set of points in the
 *              unit square implemented by using a red-black BST.
 *
 *              The implementation supports insert() and contains() in time
 *              proportional to the logarithm of the number of points in the
 *              set in the worst case,  It also supports nearest() and range()
 *              in time proportional to the number of points in the set.
 *
 *************************************************************************/

public class PointSET
{
    SET<Point2D> tree;

    /**
     * Construct an empty set of points
     */
    public PointSET()
    {
        tree = new SET<Point2D>();
    }

    /**
     * Is the set empty?
     *
     * @return
     */
    public boolean isEmpty()
    {
        return tree.isEmpty();
    }

    /**
     * Number of points in the set
     *
     * @return
     */
    public int size()
    {
        return tree.size();
    }

    /**
     * Add the point p to the set (if it is not already in the set).
     *
     * @param p
     */
    public void insert(Point2D p)
    {

    }

    /**
     * Does the set contain the point p?
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p)
    {
        // TODO: implement this
        return true;
    }

    /**
     * Draw all of the points to standard draw
     */
    public void draw()
    {

    }

    /**
     * All points in the set that are inside the rectangle
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect)
    {
        // TODO: implement this
        return null;
    }

    /**
     * A nearest neighbor in the set to p; null if set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p)
    {
        // TODO: implement this
        return null;
    }
}