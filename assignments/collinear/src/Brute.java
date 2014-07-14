/**
 * Write a program Brute.java that examines 4 points at a time and checks whether
 * they all lie on the same line segment, printing out any such line segments to
 * standard output and drawing them using standard drawing. To check whether the
 * 4 points p, q, r, and s are collinear, check whether the slopes between
 * p and q, between p and r, and between p and s are all equal.
 *
 * The order of growth of the running time of your program should be N^4 in the
 * worst case and it should use space proportional to N.
 */
public class Brute
{
    private static boolean isCollinear(Point[] points)
    {
        if (points.length < 3)
        {
            return true;
        }

        for (int i = 2; i < points.length; i++)
        {
            if (points[0].SLOPE_ORDER.compare(points[1], points[i]) != 0)
            {
                return false;
            }
        }

        return true;
    }

    private static boolean isCollinearTestSuccessful()
    {
        Point[] points1 = new Point[] { new Point(0, 0), new Point(1, 1), new Point(2, 2), };
        Point[] points2 = new Point[] { new Point(0, 0), new Point(1, 1), new Point(2, 3), };

        return (isCollinear(points1) && !isCollinear(points2));
    }

    private static Point[] readInputFile(String filename)
    {
        In in = new In(filename);
        int nPoints = in.readInt();
        Point[] points = new Point[nPoints];

        for (int i = 0; i < nPoints; i++)
        {
            points[i] = new Point(in.readInt(), in.readInt());
        }

        assert nPoints == points.length;
        return points;
    }

    private static void brute(Point[] pts)
    {
        int i, j, k, l;

        for (i = 0; i < pts.length; i++)
        {
            pts[i].draw();
            for (j = i + 1; j < pts.length; j++)
            {
                for (k = j + 1; k < pts.length; k++)
                {
                    if (!isCollinear(new Point[] { pts[i], pts[j], pts[k] }))
                    {
                        continue;
                    }

                    for (l = k + 1; l < pts.length; l++)
                    {
                        if (isCollinear(new Point[] { pts[i], pts[j], pts[k], pts[l] }))
                        {
                            StdOut.println(pts[i] + " -> " + pts[j] + " -> "
                                    + pts[k] + " -> " + pts[l]);
                            pts[i].drawTo(pts[l]);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {

        if (args.length != 1)
        {
            throw new IllegalArgumentException("Specify a file path as the first argument!");
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        Point[] pts = readInputFile(args[0]);
        Quick.sort(pts);

        brute(pts);
    }
}
