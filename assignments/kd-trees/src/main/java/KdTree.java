/**
 * Created by CAEADOM on 7/22/2014.
 */

/*************************************************************************
 * Name: Earl Dombowsky
 * Email:
 *
 * Compilation: javac PointSET.java
 * Execution:
 *
 * Description: A mutable data type that uses a 2d-tree to implement the
 *              same API as in PointSET (but replace PointSET with KdTree).
 *
 *              A 2d-tree is a generalization of a BST to two-dimensional
 *              keys. The idea is to build a BST with points in the nodes,
 *              using the x- and y-coordinates of the points as keys in
 *              strictly alternating sequence.
 *
 *              The implementation supports insert() and contains() in time
 *              proportional to the logarithm of the number of points in the
 *              set in the worst case,  It also supports nearest() and range()
 *              in time proportional to the number of points in the set.
 *
 *************************************************************************/

public class KdTree
{
    /**
     * Construct an empty kd-tree
     */
    public KdTree()
    {

    }

    /**
     * Is the set empty?
     *
     * @return
     */
    public boolean isEmpty()
    {

    }

    /**
     * Number of points in the tree
     *
     * @return
     */
    public int size()
    {

    }

    /**
     * Add the point p to the tree (if it is not already in the tree).
     *
     * @param p
     */
    public void insert(Point2D p)
    {

    }

    /**
     * Does the tree contain the point p?
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p)
    {

    }

    /**
     * Draw all of the points to standard draw
     */
    public void draw()
    {

    }

    /**
     * All points in the tree that are inside the rectangle
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect)
    {

    }

    /**
     * A nearest neighbor in the tree to p; null if set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p)
    {

    }
}