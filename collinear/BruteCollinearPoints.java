/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments = new LineSegment[1];
    private int numSegments = 0;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        Point[] temp = Arrays.copyOf(points, points.length);
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == null) throw new IllegalArgumentException();
        }
        Arrays.sort(temp);
        for (int i = 0; i < temp.length - 1; i++) {
            if (temp[i].slopeTo(temp[i + 1]) == Double.NEGATIVE_INFINITY)
                throw new IllegalArgumentException();
        }

        for (int i = 0; i < temp.length; i++) {
            for (int j = i + 1; j < temp.length; j++) {
                for (int k = j + 1; k < temp.length; k++) {
                    if (temp[i].slopeTo(temp[j]) == temp[i].slopeTo(temp[k])) {
                        for (int l = k + 1; l < temp.length; l++) {
                            if (temp[i].slopeTo(temp[j]) == temp[i].slopeTo(temp[k]) &&
                                    temp[i].slopeTo(temp[j]) == temp[i].slopeTo(temp[l])) {
                                numSegments++;
                                if (numSegments > segments.length) resize(segments.length * 2);

                                segments[numSegments - 1] = new LineSegment(temp[i], temp[l]);
                            }
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return numSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numSegments);
    }

    private void resize(int capacity) {
        LineSegment[] copy = new LineSegment[capacity];
        for (int i = 0; i < segments.length; i++) {
            copy[i] = segments[i];
        }
        segments = copy;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
