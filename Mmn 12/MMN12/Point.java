/**
 * Represents 2 dimensional points.
 * @author Ori Ben Nun
 * @version 23/11/2020
 */
public class Point {

    // Constants:
    private final double NINETY_DEGREES_CELSIUS = 90;
    private final double CELSIUS_TO_RADIANS_RATIO = Math.PI / 180;
    private final double RADIANS_TO_CELSIUS_RATIO = 180 / Math.PI;
    private final double DEFAULT_X_Y_VALUE = 0.0;
    private final double COMPARE_THRESHOLD = 0.01;
    private final double ROUNDING_MULTIPLIER = 10000.0;

    // Private variables:
    private double _radius; // sqrt(x^2+Y^2)
    private double _alpha; // IN DEGREES. degrees to radians formula = degree * PI/180

    // Constructors

    /**
     * Constructor for objects of class Point. Construct a new point with the specified x y coordinates.
     * If the x coordinate is negative it is set to zero.
     * If the y coordinate is negative it is set to zero.
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Point(double x, double y) {

        // Reset negative values:
        if (x < DEFAULT_X_Y_VALUE) {
            x = DEFAULT_X_Y_VALUE;
        }
        if (y < DEFAULT_X_Y_VALUE) {
            y = DEFAULT_X_Y_VALUE;
        }

        // Setting alpha in radians:
        setAlphaAndRadius(x,y);
    }

    /**
     * Constructor for objects of class Point.
     * Copy constructor, construct a point using another point.
     * @param other The point from which to construct the new object
     */
    public Point(Point other){
        _radius = other._radius;
        _alpha = other._alpha;
    }

    // Private Methods

    private double celsiusToRadians(double celsius){
        return celsius * CELSIUS_TO_RADIANS_RATIO;
    }

    private double radiansToCelsius(double radians){
        return radians * RADIANS_TO_CELSIUS_RATIO;
    }

    private void setAlphaAndRadius(double x, double y){
        if (x == DEFAULT_X_Y_VALUE) {
            _alpha = NINETY_DEGREES_CELSIUS;
        }
        else {
            _alpha = radiansToCelsius(Math.atan(y / x));
        }

        _radius = Math.sqrt(
                Math.pow(x, 2) + Math.pow(y , 2));
    }

    // Public Methods:

    /**
     * This method returns the x coordinate of the point.
     * @return The x coordinate of the point
     */
    public double getX(){
        return Math.cos(celsiusToRadians(_alpha)) * _radius;
    }

    /**
     * This method returns the y coordinate of the point.
     * @return The y coordinate of the point
     */
    public double getY(){
        return Math.sin(celsiusToRadians(_alpha)) * _radius;
    }

    /**
     * This method sets the x coordinate of the point.
     * If the new x coordinate is negative the old x coordinate will remain unchanged.
     * @param x The new x coordinate
     */
    public void setX(double x){
        // Reset negative value:
        if (x < DEFAULT_X_Y_VALUE) {
            return;
        }

        setAlphaAndRadius(x, getY());
    }

    /**
     * This method sets the y coordinate of the point.
     * If the new y coordinate is negative the old y coordinate will remain unchanged.
     * @param y The new y coordinate
     */
    public void setY(double y){
        // Reset negative value:
        if (y < DEFAULT_X_Y_VALUE) {
            return;
        }

        setAlphaAndRadius(getX(), y);
    }

    /**
     * Returns a string representation of Point in the format (x,y).
     * @return A String representation of the Point
     */
    public String toString(){
        double roundedX = Math.round(getX() * ROUNDING_MULTIPLIER) / ROUNDING_MULTIPLIER;
        double roundedY = Math.round(getY() * ROUNDING_MULTIPLIER) / ROUNDING_MULTIPLIER;

        return "(" + roundedX + "," + roundedY + ")";
    }

    /**
     * Check if the given point is equal to this point.
     * @param other The point to check equality with
     * @return True if the given point is equal to this point
     */
    public boolean equals(Point other){
        //return (_radius == other._radius && _alpha == other._alpha);
        return this.distance(other) < COMPARE_THRESHOLD;
        //return (_radius - other._radius < COMPARE_THRESHOLD && _alpha - other._alpha < COMPARE_THRESHOLD);
    }

    /**
     * Check if this point is above a received point.
     * @param other The point to check if this point is above
     * @return True if this point is above the other point
     */
    public boolean isAbove(Point other){
        return getY() - other.getY() > COMPARE_THRESHOLD;
    }

    /**
     * Check if this point is below a received point.
     * @param other The point to check if this point is below
     * @return True if this point is below the other point
     */
    public boolean isUnder(Point other){
        return other.isAbove(this);
    }

    /**
     * Check if this point is left of a received point.
     * @param other The point to check if this point is left of
     * @return True if this point is left of the other point
     */
    public boolean isLeft(Point other){
        return other.getX() - getX() > COMPARE_THRESHOLD;
    }

    /**
     * Check if this point is right of a received point.
     * @param other The point to check if this point is right of
     * @return True if this point is right of the other point
     */
    public boolean isRight(Point other){
        return other.isLeft(this);
    }

    /**
     * Check the distance between this point and a given point.
     * @param other The point to check the distance from
     * @return The distance
     */
    public double distance(Point other){
        double yDiffPow = Math.pow(getY() - other.getY(), 2);
        double xDiffPow = Math.pow(getX() - other.getX(), 2);
        return Math.sqrt(yDiffPow + xDiffPow);
    }

    /**
     * Moves a point.
     * If either coordinate becomes negative the point remains unchanged.
     * @param dx The difference to add to x
     * @param dy The difference to add to y
     */
    public void move(double dx, double dy){
        double newX = getX() + dx;
        double newY = getY() + dy;

        // returns if the point will move outside of the first quadrant
        if (newX < DEFAULT_X_Y_VALUE || newY < DEFAULT_X_Y_VALUE){
            return;
        }

        // else:
        setX(newX);
        setY(newY);
    }
}
