import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class FastCollinearPoints
{
   
    private LineSegment[] ls;

    private ArrayList<LineSegment> lsList = new ArrayList<>();

 
    private HashMap<Double, ArrayList<Point>> foundStartingPoints = new HashMap<>();
  
    public FastCollinearPoints(Point[] points)
    {
        if (isNullPoints(points)) {
            throw new NullPointerException("Points array can't be null or contain null values");
        }

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
   
        Arrays.sort(pointsCopy);

        if (isDuplicatedPoints(pointsCopy)) {
            throw new IllegalArgumentException("Points array can't contain duplicated points");
        }

        for (Point startingPoint: points) {
    
            Arrays.sort(pointsCopy, startingPoint.slopeOrder());

            ArrayList<Point> slopePoints = new ArrayList<>();
            double currentSlope = 0;
            double previousSlope = Double.NEGATIVE_INFINITY;

            for (int i = 1; i < pointsCopy.length; i++) {
                currentSlope = startingPoint.slopeTo(pointsCopy[i]);

                if (currentSlope == previousSlope) {
                    slopePoints.add(pointsCopy[i]);
                } else {
                    addSegment(slopePoints, startingPoint, previousSlope);

                    slopePoints.clear();
               
                    slopePoints.add(pointsCopy[i]);
                }

                previousSlope = currentSlope;
            }

            addSegment(slopePoints, startingPoint, previousSlope);
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

    private void addSegment(ArrayList<Point> slopePoints, Point startingPoint, double slopeKey)
    {
        
        if (slopePoints.size() < 3) {
            return;
        }

     
        slopePoints.add(startingPoint);

       
        ArrayList<Point> startingPoints = foundStartingPoints.get(slopeKey);
        Collections.sort(slopePoints);

        Point startPoint = slopePoints.get(0);
        Point endPoint = slopePoints.get(slopePoints.size() - 1);

        if (startingPoints == null) {
            startingPoints = new ArrayList<>();
            startingPoints.add(startPoint);
            foundStartingPoints.put(slopeKey, startingPoints);
        } else {
     
            for (Point point: startingPoints) {
                if (startPoint.compareTo(point) == 0) {
                    return;
                }
            }
            startingPoints.add(startPoint);
        }

        lsList.add(new LineSegment(startPoint, endPoint));
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

     
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment s : collinear.s()) {
            StdOut.println(s);
            s.draw();
        }
        StdDraw.show();
    }
}
