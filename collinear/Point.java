import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int a;     
    private final int b;   

    private class PointComparator implements Comparator<Point>
    {
     
        public int compare(Point p1, Point p2)
        {
            double p1Slope = slopeTo(p1);
            double p2Slope = slopeTo(p2);

            if (p1Slope > p2Slope) {
                return 1;
            }

            if (p1Slope < p2Slope) {
                return -1;
            }

            
            return 0;
        }
    }

    public Point(int a, int b) {
        
        this.a = a;
        this.b = b;
    }
  
    public void draw() {
        
        StdDraw.point(a, b);
    }
  
    public void drawTo(Point that) {
        
        StdDraw.line(this.a, this.b, that.a, that.b);
    }

    
    public double slopeTo(Point that) {
        double bSlope = that.b - b;
        double aSlope = that.a - a;

       
        if (bSlope == 0 && aSlope == 0) {
            return Double.NEGATIVE_INFINITY;
        }

        
        if (bSlope == 0) {
            return +0.0;
        }

        
        if (aSlope == 0) {
            return Double.POSITIVE_INFINITY;
        }

        return (bSlope / aSlope);
    }

    
    public int compareTo(Point that) {
        
        if (b > that.b) {
            return 1;
        }

        if (b < that.b) {
            return -1;
        }

       
        if (a > that.a) {
            return 1;
        }

        if (a < that.a) {
            return -1;
        }

        return 0;
    }

    
    public Comparator<Point> slopeOrder() {
        return new PointComparator();
    }

    public String toString() {
     
        return "(" + a + ", " + b + ")";
    }

    
    public static void main(String[] args) {
        
        Point p1 = new Point(10, 20);
        Point p2 = new Point(20, 25);
        Point p3 = new Point(10, 20);

        Point p4 = new Point(10, 20);
        Point p5 = new Point(1, 20);
        Point p6 = new Point(5, 1);
        Point p7 = new Point(32128, 32128);
        Point p8 = new Point(32128, 32128);
        Point p9 = new Point(10, 123);

        Point p10 = new Point(10, 30);
        Point p11 = new Point(30, 20);

        assert (p1.compareTo(p2) < 0) : p1 + " " + p2;
        assert (p1.compareTo(p3) == 0) : p1 + " " + p3;
        assert (p2.compareTo(p3) > 0) : p2 + " " + p3;

        assert (p4.compareTo(p5) > 0) : p4 + " " + p5;
        assert (p5.compareTo(p4) < 0) : p5 + " " + p4;
        assert (p5.compareTo(p6) > 0) : p5 + " " + p6;
        assert (p4.compareTo(p6) > 0) : p4 + " " + p6;
        assert (p7.compareTo(p8) == 0) : p7 + " " + p8;


        assert (Double.compare(p7.slopeTo(p8), Double.NEGATIVE_INFINITY) == 0) : "Wrong value for degraded line";
        assert (Double.compare(p4.slopeTo(p9), Double.POSITIVE_INFINITY) == 0) : "Wrong value for vertical line";
        assert (Double.compare(p4.slopeTo(p5), +0.0) == 0) : "Wrong value for horizontal line";
        assert (Double.compare(p1.slopeTo(p2), 0.5) == 0);
        assert (Double.compare(p10.slopeTo(p11), -0.5) == 0);


        assert (p4.slopeOrder().compare(p3, p5) < 0);
        assert (p4.slopeOrder().compare(p5, p3) > 0);
        assert (p1.slopeOrder().compare(p7, p8) == 0);
    }
}
