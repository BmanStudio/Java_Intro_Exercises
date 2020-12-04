/**
 * Represents Convex polygon.
 * A convex polygon is a simple polygon in which no line segment
 * between two points on the boundary ever goes outside the polygon,
 * and all of the interior angles are less than 180 degrees
 * @author Ori Ben Nun
 * @version 3/12/2020
 */
public class Polygon {

    // Constants:
    private final int MAX_VERTICES = 10;

    // Instance variables:
    private Point[] _vertices;
    private int _noOfVertices;

    // Constructor:

    /**
     * Constructor for objects of class Polygon.
     * Initialising an empty array of Points with a default maximum size of 10 points.
     */
    public Polygon() {
        _vertices = new Point[MAX_VERTICES];
        _noOfVertices = 0;
    }

    // Private methods:

    private double triangleArea(Point a, Point b, Point c) {
        // computing the lengths of the 3 sides of the triangles that is represented by the 3 points
        double segABLength = a.distance(b);
        double segBCLength = b.distance(c);
        double segCALength = c.distance(a);

        // in order to use Heron formula, we will compute the half of the triangle perimeter
        double triPerimeter = segABLength + segBCLength + segCALength;

        // Heron formula:
        double s = triPerimeter / 2.0;
        double triArea = Math.sqrt(s * (s - segABLength) * (s - segBCLength) * (s - segCALength));

        return triArea;
    }

    // Public methods:

    /**
     * Adding a new vertex to the polygon (as the next available point).
     * @param x The X value of the new vertex (will be forced to be in the first quadrant)
     * @param y The Y value of the new vertex (will be forced to be in the first quadrant)
     * @return True if the vertex added successfully, and false if haven't
     * (because the number of vertices in the polygon has reached the maximum value)
     */
    public boolean addVertex(double x, double y) {
        if (_noOfVertices == MAX_VERTICES) { return false; }

        _vertices[_noOfVertices++] = new Point(x, y);
        return true;
    }

    /**
     * Returns the highest vertex of the polygon (the vertex which has the biggest Y value).
     * In case of 2 "highest" vertices - the first one to be found is the one to be returned.
     * @return The highest vertex of the polygon, or Null in case the polygon has no vertices.
     */
    public Point highestVertex() {
        // if the array is empty, return null
        if (_noOfVertices == 0) { return null; }

        // Initializing the highest as the first vertex, which should be overridden
        Point highest = _vertices[0];

        // Finding the highest by iterating over all the vertices:
        // starting from vertices[1], for we initialised the highest as the 0's index,
        // so no need for checking for it
        for (int i = 1; i < _noOfVertices; i++) {
            if (_vertices[i].isAbove(highest)) {
                highest = _vertices[i];
            }
        }
        return new Point(highest);
    }

    /**
     * Returns a string representation of Polygon in the format
     * ((vertex's X, vertex's Y),(next vertex's X, next vertex's Y)).
     * @return A String representation of the Polygon
     */
    public String toString() {
        String base = "The polygon has ";
        if (_noOfVertices == 0) {
            base += _noOfVertices + " vertices.";
            return base;
        }

        base += _noOfVertices + " vertices:\n";
        String verticesStr = "(";
        // For FencePost reasons, we will iterate over all the vertices but the last one,
        // which will be printed last, outside the loop, without comma.
        for (int i = 0; i < _noOfVertices - 1; i++) {
            verticesStr += _vertices[i].toString() + ",";
        }
        verticesStr += _vertices[_noOfVertices - 1].toString() + ")";

        return base + verticesStr;
    }

    /**
     * Calculating the perimeter of the polygon.
     * @return The perimeter of the polygon as a real (double) number.
     */
    public double calcPerimeter() {
        double perimeter = 0;

        if (_noOfVertices == 0 || _noOfVertices == 1) {
            return 0;
        }
        else if (_noOfVertices == 2) {
            // if there are only 2 vertices, return the distance between them (must be the first and second vertices)
            return _vertices[0].distance(_vertices[1]);
        }
        // Calculating the distance between every vertex (but the last one) and the one next to it by adding to a total sum
        for (int i = 0; i < _noOfVertices - 1; i++) {
            perimeter += _vertices[i].distance(_vertices[i + 1]);
        }
        // Adding the distance between the "last" and the "first" vertices
        perimeter += _vertices[_noOfVertices - 1].distance(_vertices[0]);

        return perimeter;
    }

    public double calcArea() {
        if (_noOfVertices < 3) { return 0; }
        double area = 0;
        for (int i = 2; i <= _noOfVertices - 1; i++) {
            area += triangleArea(_vertices[0], _vertices[i - 1], _vertices[i]);
        }
        return area;
    }
}
