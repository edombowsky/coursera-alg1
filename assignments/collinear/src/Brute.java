import java.util.Arrays;

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
    private static final int POINTS_IN_LINE = 4;

    private static Point[] readInputFile(String filename)
    {
        In in = new In(filename);
        int nPoints = in.readInt();
        Point[] points = new Point[nPoints];

        if (nPoints <= 0)
        {
            throw new IllegalArgumentException(
                    "Number of points must be greater than 0.");
        }

        for (int i = 0; i < nPoints; i++)
        {
            points[i] = new Point(in.readInt(), in.readInt());
            points[i].draw();
        }

        if (nPoints != points.length)
        {
            throw new IllegalArgumentException(
                    "Number of points in file must be = " + nPoints);
        }

        in.close();

        return points;
    }

    private static void brute(Point[] pts)
    {
        Point [] line = new Point[POINTS_IN_LINE];
        int N = pts.length;

        // loop through possible 4-tuples
        for (int i = 0; i < N - 3; i++)
        {
            for (int j = i + 1; j < N - 2; j++)
            {
                for (int k = j + 1; k < N - 1; k++)
                {
                    for (int m = k + 1; m < N; m++)
                    {
                        double s1 = pts[i].slopeTo(pts[j]);
                        double s2 = pts[i].slopeTo(pts[k]);
                        double s3 = pts[i].slopeTo(pts[m]);
                        if (s1 == s2 && s1 == s3)
                        {
                            line[0] = pts[i];
                            line[1] = pts[j];
                            line[2] = pts[k];
                            line[3] = pts[m];

                            Arrays.sort(line);

                            StdOut.print(line[0].toString() + " -> ");
                            StdOut.print(line[1].toString() + " -> ");
                            StdOut.print(line[2].toString() + " -> ");
                            StdOut.println(line[3].toString());
                            line[0].drawTo(line[3]);

                            StdDraw.show(0);
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
            throw new IllegalArgumentException(
                    "Specify a file path as the first argument!");
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        Point[] points = readInputFile(args[0]);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();

        final int fieldSize = 32768;
        StdDraw.setXscale(0, fieldSize);
        StdDraw.setYscale(0, fieldSize);
        StdDraw.show();

        Arrays.sort(points);

        //Stopwatch timer = new Stopwatch(); // start timer
        brute(points);
        //StdOut.println(timer.elapsedTime()); // stop and print timer

        StdDraw.show(0);
    }
}
