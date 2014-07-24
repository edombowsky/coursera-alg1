import java.util.Iterator;

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
    private SET<Point2D> pointSet;

    /**
     * Construct an empty set of points
     */
    public PointSET()
    {
        pointSet = new SET<Point2D>();
    }

    /**
     * Is the set empty?
     *
     * @return  true if the set is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return pointSet.isEmpty();
    }

    /**
     * Number of points in the set
     *
     * @return  the number of points in the set
     */
    public int size()
    {
        return pointSet.size();
    }

    /**
     * Add the point p to the set (if it is not already in the set).
     *
     * @param p  point to be added to the set
     */
    public void insert(Point2D p)
    {
        pointSet.add(p);
    }

    /**
     * Does the set contain the point p?
     *
     * @param p  point to be check that it is in the set
     *
     * @return  true if point is in the set, false otherwise
     */
    public boolean contains(Point2D p)
    {
        return pointSet.contains(p);
    }

    /**
     * Draw all of the points to standard draw
     */
    public void draw()
    {
        for (Point2D point: pointSet)
        {
            point.draw();
        }
    }

    /**
     * All points in the set that are inside the rectangle
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect)
    {
        SET<Point2D> range = new SET<Point2D>();

        for (Point2D p : pointSet)
        {
            if (rect.contains(p)) range.add(p);
        }

        return range;
    }

    /**
     * A nearest neighbour in the set to p; null if set is empty
     *
     * @param p  point to check for its nearest neighbour
     *
     * @return  the point nearest to the input parameter or null if set is empty
     */
    public Point2D nearest(Point2D p)
    {
        if (pointSet.isEmpty()) return null;

        Point2D result;
        Iterator<Point2D> it = pointSet.iterator();
        result = it.next();
        double dist = p.distanceTo(result);

        while (it.hasNext())
        {
            Point2D temp = it.next();
            if (p.distanceTo(temp) < dist)
            {
                result = temp;
                dist = p.distanceTo(temp);
            }
        }

        return result;
    }

    public static void main(String [] args)
    {
        int N = Integer.parseInt(args[0]);
        int xsize = Integer.parseInt(args[1]);
        int ysize = Integer.parseInt(args[2]);

        long seed = System.currentTimeMillis();
        StdRandom.setSeed(seed);

        PointSET pointSet = new PointSET();

        for (int i = 0; i < N; i++)
        {
            pointSet.insert(new Point2D(StdRandom.uniform(xsize),
                    StdRandom.uniform(ysize)));
        }

        StdDraw.setCanvasSize(xsize, ysize);
        StdDraw.setXscale(0, xsize);
        StdDraw.setYscale(0, ysize);
        StdDraw.setPenRadius(0.01);
        System.out.println(pointSet.size());
        pointSet.draw();

        Point2D o = new Point2D(50, 50);
        Point2D n = pointSet.nearest(o);
        StdDraw.setPenColor(StdDraw.GREEN);
        n.draw();
        StdDraw.setPenColor(StdDraw.RED);
        o.draw();
    }
}