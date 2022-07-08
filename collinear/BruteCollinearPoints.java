import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints
{
   
    private LineSegment[] ls;

    public BruteCollinearPoints(Point[] points)
    {
        if (isNullPoints(points)) {
            throw new NullPointerException("Points array can't be null or contain null values");
        }

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
       
        Arrays.sort(pointsCopy);

        if (isDuplicatedPoints(pointsCopy)) {
            throw new IllegalArgumentException("Points array can't contain duplicated points");
        }

        int pointsLength = pointsCopy.length;
        ArrayList<LineSegment> lsList = new ArrayList<>();

       
        for (int p = 0; p < (pointsLength - 3); p++) {
            for (int q = (p + 1); q < (pointsLength - 2); q++) {
                for (int r = (q + 1); r < (pointsLength - 1); r++) {
                    
                    if (pointsCopy[p].slopeTo(pointsCopy[q]) != pointsCopy[p].slopeTo(pointsCopy[r])) {
                        continue;
                    }

                    for (int s = (r + 1); s < pointsLength; s++) {
                        if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
                            lsList.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }
            }
        }

        
        ls = lsList.toArray(new LineSegment[lsList.size()]);
    }

    
    public int numberOfSegments()
    {
        return ls.length;
    }

    public LineSegment[] s()
    {
        return Arrays.copyOf(ls, ls.length);
    }

    
    private boolean isNullPoints(Point[] points)
    {
        if (points == null) {
            return true;
        }

        for (Point point: points) {
            if (point == null) {
                return true;
            }
        }

        return false;
    }

   
    private boolean isDuplicatedPoints(Point[] points)
    {
        for (int i = 0; i < (points.length - 1); i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }

        return false;
    }

    
    public static void main(String[] args) {

        
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int a = in.readInt();
            int b = in.readInt();
            points[i] = new Point(a, b);
        }

        
        StdDraw.enableDoubleBuffering();
        StdDraw.setAscale(0, 32768);
        StdDraw.setBscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

    
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment s : collinear.s()) {
            StdOut.println(s);
            s.draw();
        }
        StdDraw.show();
    }
}
