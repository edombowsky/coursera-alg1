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
    private static class Node
    {
        private Node leftBranch;
        private Node rightBranch;
        private Point2D point;
        private final boolean vertical;

        public Node(final Point2D p,
                    final Node lb,
                    final Node rb,
                    final boolean v)
        {
            this.point       = p;
            this.leftBranch  = lb;
            this.rightBranch = rb;
            this.vertical    = v;

        }
    }

    private static final RectHV CONTAINER = new RectHV(0, 0, 1, 1);
    private Node root;
    private int size;

    /**
     * Construct an empty kd-tree
     */
    public KdTree()
    {
        root = null;
        size = 0;
    }

    /**
     * Is the set empty?
     *
     * @return
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Number of points in the tree
     *
     * @return
     */
    public int size()
    {
        return size;
    }

    /**
     * Add the point p to the tree (if it is not already in the tree).
     *
     * @param p
     */
    public void insert(Point2D p)
    {
        root = insert(root, p, true);
    }

    // helper: add point p to subtree rooted at node
    private Node insert(final Node node,
                        final Point2D p,
                        final boolean vertical)
    {
        // if new node, create it
        if (node == null)
        {
            size++;
            return new Node(p, null, null, vertical);
        }

        // if already in, return it
        if (node.point.x() == p.x() && node.point.y() == p.y()) return node;

        // else, insert it where corresponds (left - right recursive call)
        if (node.vertical && p.x() < node.point.x() || !node.vertical && p.y() < node.point.y())
            node.leftBranch = insert(node.leftBranch, p, !node.vertical);
        else
            node.rightBranch = insert(node.rightBranch, p, !node.vertical);

        return node;
    }

    /**
     * Does the tree contain the point p?
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p)
    {
        return contains(root, p.x(), p.y());
    }

    // helper: does the subtree rooted at node contain (x, y)?
    private boolean contains(Node node, double x, double y)
    {
        if (node == null) return false;
        if (node.point.x() == x && node.point.y() == y) return true;

        if (node.vertical && x < node.point.x() || !node.vertical && y < node.point.y())
        {
            return contains(node.leftBranch, x, y);
        }
        else
        {
            return contains(node.rightBranch, x, y);
        }
    }

    /**
     * Draw all of the points to standard draw
     */
    public void draw()
    {
        StdDraw.setScale(0, 1);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        CONTAINER.draw();

        draw(root, CONTAINER);
    }

    // helper: draw node point and its division line (given by rect)
    private void draw(final Node node, final RectHV rect)
    {
        if (node == null) return;

        // draw the point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        new Point2D(node.point.x(), node.point.y()).draw();

        // get the min and max points of division line
        Point2D min, max;
        if (node.vertical)
        {
            StdDraw.setPenColor(StdDraw.RED);
            min = new Point2D(node.point.x(), rect.ymin());
            max = new Point2D(node.point.x(), rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            min = new Point2D(rect.xmin(), node.point.y());
            max = new Point2D(rect.xmax(), node.point.y());
        }

        // draw that division line
        StdDraw.setPenRadius();
        min.drawTo(max);

        // recursively draw children
        draw(node.leftBranch, leftRect(rect, node));
        draw(node.rightBranch, rightRect(rect, node));
    }

    // helper: get the left rectangle of node inside parent's rect
    private RectHV leftRect(final RectHV rect, final Node node)
    {
        if (node.vertical)
            return new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
        else
            return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
    }

    // helper: get the right rectangle of node inside parent's rect
    private RectHV rightRect(final RectHV rect, final Node node)
    {
        if (node.vertical)
            return new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
        else
            return new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
    }

    /**
     * All points in the tree that are inside the rectangle
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect)
    {
        final Queue<Point2D> queue = new Queue<Point2D>();
        range(root, CONTAINER, rect, queue);

        return queue;
    }

    // helper: points in subtree rooted at node inside rect
    private void range(final Node node,
                       final RectHV nrect,
                       final RectHV rect,
                       final Queue<Point2D> queue)
    {
        if (node == null) return;

        if (rect.intersects(nrect))
        {
            final Point2D p = new Point2D(node.point.x(), node.point.y());
            if (rect.contains(p)) queue.enqueue(p);
            range(node.leftBranch, leftRect(nrect, node), rect, queue);
            range(node.rightBranch, rightRect(nrect, node), rect, queue);
        }
    }

    /**
     * A nearest neighbor in the tree to p; null if set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p)
    {
        return nearestNeighbour(root, CONTAINER, p.x(), p.y(), null);
    }

    // helper: nearest neighbor of (x,y) in subtree rooted at node
    private Point2D nearestNeighbour(final Node node,
                            final RectHV rect,
                            final double x,
                            final double y,
                            final Point2D candidate)
    {
        if (node == null) return candidate;

        double dqn = 0.0;
        double drq = 0.0;
        RectHV left = null;
        RectHV rigt = null;
        final Point2D query = new Point2D(x, y);
        Point2D nearest = candidate;

        if (nearest != null)
        {
            dqn = query.distanceSquaredTo(nearest);
            drq = rect.distanceSquaredTo(query);
        }

        if (nearest == null || dqn > drq)
        {
            final Point2D point = new Point2D(node.point.x(), node.point.y());
            if (nearest == null || dqn > query.distanceSquaredTo(point))
                nearest = point;

            if (node.vertical)
            {
                left = new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
                rigt = new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());

                if (x < node.point.x())
                {
                    nearest = nearestNeighbour(node.leftBranch, left, x, y, nearest);
                    nearest = nearestNeighbour(node.rightBranch, rigt, x, y, nearest);
                }
                else
                {
                    nearest = nearestNeighbour(node.rightBranch, rigt, x, y, nearest);
                    nearest = nearestNeighbour(node.leftBranch, left, x, y, nearest);
                }
            }
            else
            {
                left = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
                rigt = new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());

                if (y < node.point.y())
                {
                    nearest = nearestNeighbour(node.leftBranch, left, x, y, nearest);
                    nearest = nearestNeighbour(node.rightBranch, rigt, x, y, nearest);
                }
                else
                {
                    nearest = nearestNeighbour(node.rightBranch, rigt, x, y, nearest);
                    nearest = nearestNeighbour(node.leftBranch, left, x, y, nearest);
                }
            }
        }

        return nearest;
    }
}