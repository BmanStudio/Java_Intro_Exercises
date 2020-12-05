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

    /**
     * Calculating the area of the Polygon (if has more than 2 vertices)
     * @return The area of the Polygon as a real number, or 0 in case the polygon has less than 3 vertices.
     */
    public double calcArea() {
        if (_noOfVertices < 3) { return 0; }

        double area = 0;
        // Iterating through the array (including the last one, excluding the first two),
        // starting from Point #3 and climbing up when always calculating the triangle
        // that is being created between Point#1, Point#i and Point#i-1
        // (the point that previous to the one that represented by i)
        // and finally adding the triangle area to the sum (var area),
        // by using the private method triangleArea (which based on Heron's formula)
        for (int i = 2; i <= _noOfVertices - 1; i++) {
            area += triangleArea(_vertices[0], _vertices[i - 1], _vertices[i]);
        }
        return area;
    }

    /**
     * Check if this polygon is bigger than a reference polygon.
     * @param other the reference polygon
     * @return True if this polygon is bigger than the reference polygon, false if smaller or equal.
     */
    public boolean isBigger(Polygon other) {
        return this.calcArea() > other.calcArea();
    }

    /**
     * Check if a given point is a vertex of the polygon, and returning its index.
     * @param p The reference point to be checked
     * @return The Point index (0 <= int) if it is a vertex of the polygon, and -1 if isn't.
     */
    public int findVertex(Point p) {
        // if the polygon has no vertices
        if (_noOfVertices == 0) { return -1; }

        for (int i = 0; i < _noOfVertices; i++){
            if (_vertices[i].equals(p)) {
                return i;
            }
        }

        // if got here, means the given point is not a vertex of the polygon:
        return -1;
    }

    /**
     * Returning the next vertex of the polygon relative to a given vertex (if exist).
     * @param p Point which represents a vertex of the polygon.
     * @return null if the given point is not a vertex of the polygon,or a copy of the next vertex to the given reference point
     */
    public Point getNextVertex(Point p) {
        // if there are no vertices in the polygon
        if (_noOfVertices == 0) { return null; }

        // if the given point is not a vertex of the polygon
        if (findVertex(p) == -1) { return null; }

        // if the given point is the only (and therefore the first) vertex of the polygon:
        // returning the first (and only) vertex.
        if (_noOfVertices == 1 && findVertex(p) == 0) { return _vertices[0]; }

        // if the given point is the last vertex of the polygon - returning the first vertex.
        if (findVertex(p) == _noOfVertices - 1) { return _vertices[0]; }

        // if got here, means that:
        // 1) the polygon has at least 1 vertex
        // 2) the given point is a vertex of the polygon
        // 3) the given point is not the only (and first) vertex of the polygon
        // 4) the given point is not the last vertex of the polygon.
        // So now we can safely tell that the given point has a next point
        int next = findVertex(p) + 1;
        return new Point(_vertices[next]);
    }
}
