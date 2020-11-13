/**
 * Represents 2 dimensional points.
 */
public class Point {
    // Constants:
    //
    private final double NINTY_DEGREES_RADIANS = Math.PI / 2;
    private final double HUNDRED_EIGHTY_DEGREES_RADIANS = Math.PI;
    private final double DEFAULT_X_Y_VALUE = 0.0;

    // Private variables:
    //
    private double _radius; // sqrt(x^2+Y^2)
    private double _alpha; // IN RADIANS arctan(x/y)

    // Constructors:
    //
    /**
     * Constructor for objects of class Point.
     * @param x the X value of the point (in the first quadrant)
     * @param y - the Y value of the point (in the first quadrant)
     */
    public Point(double x, double y) {

        // Reset negative values:
        if (x < 0) {
            x = DEFAULT_X_Y_VALUE;
        }
        if (y < 0) {
            y = DEFAULT_X_Y_VALUE;
        }

        // Setting alpha in radians:
        setAlpha(x,y);

        setRadius(x,y);
    }

    /**
     * Constructor for objects of class Point.
     * @param other - the Point object which it's values will be copied to the new Point.
     */
    public Point(Point other){
        _radius = other._radius;
        _alpha = other._alpha;
    }

    // Private Methods:
    //

    private void setAlpha(double x, double y){
        if (x == 0) {
            _alpha = NINTY_DEGREES_RADIANS;
        }
        else {
            _alpha = Math.atan(y / x);
        }
    }

    private void setRadius(double x, double y){
        _radius = Math.sqrt(
                Math.pow(x, 2) + Math.pow(y ,2));
    }

    // Public Methods:
    //

    /**
     * This method returns the x coordinate of the point.
     * @return the x coordinate of the point.
     */
    public double getX(){
        double xValue = Math.cos(_alpha) * _radius;
        return xValue;
    }

    /**
     * This method returns the y coordinate of the point.
     * @return the y coordinate of the point.
     */
    public double getY(){
        double yValue = Math.sin(_alpha) * _radius;
        return yValue;
    }

    /**
     * This method sets the x coordinate of the point.
     * @param x - the new x coordinate of the point.
     */
    public void setX(double x){
        // Reset negative value:
        if (x < 0) {
            return;
        }

        setAlpha(x, getY());

        setRadius(x, getY());
    }

    /**
     * This method sets the y coordinate of the point.
     * @param y - the new y coordinate of the point.
     */
    public void setY(double y){
        // Reset negative value:
        if (y < 0) {
            return;
        }

        setAlpha(getX(), y);

        setRadius(getX(), y);
    }

    /**
     * Returns a string representation of Point in the format (x,y).
     * @return a string representation of Point in the format (x,y).
     */
    public String toString(){
        double roundedX = Math.round(getX() * 10000) / (double)10000;
        double roundedY = Math.round(getY() * 10000) / (double)10000;

        return roundedX + "," + roundedY;
    }

    /**
     * Check if the given point is equal to this point.
     * @param other the point to be compared with
     * @return true if points coordinates are equal, and false if not.
     */
    public boolean equals(Point other){
        return (_radius == other._radius && _alpha == other._alpha);
    }

    /**
     * Check if this point is above a received point.
     * @param other the point to be compared with
     * @return true if this point is above the given point, and false if not (if under or on the same Y axis value)
     */
    public boolean isAbove(Point other){
        return getY() > other.getY();
    }

    /**
     * Check if this point is below a received point.
     * @param other the point to be compared with.
     * @return true if this point is under the given point, and false if not (if above or on the same Y axis value)
     */
    public boolean isUnder(Point other){
        return other.isAbove(this);
    }

    /**
     * Check if this point is left of a received point.
     * @param other the point to be compared with
     * @return true if this point is left to the given point, and false if not (if right or on the same X axis value)
     */
    public boolean isLeft(Point other){
        return getX() < other.getX();
    }

    /**
     * Check if this point is right of a received point.
     * @param other the point to be compared with
     * @return true if this point is right to the given point, and false if not (if left or on the same X axis value)
     */
    public boolean isRight(Point other){
        return other.isLeft(this);
    }

    /**
     * Check the distance between this point and a given point.
     * @param other - the other point which the "distance to" will be returned
     * @return the distance between this point and the given point.
     */
    public double distance(Point other){
        double yDiffPow = Math.pow(getY() - other.getY(), 2);
        double xDiffPow = Math.pow(getX() - other.getX(), 2);
        return Math.sqrt(yDiffPow + xDiffPow);
    }

    /**
     * Moves a point.
     * @param dx the delta which X will move by
     * @param dy the delta which Y will move by
     */
    public void move(double dx, double dy){
        double newX = getX() + dx;
        double newY = getY() + dy;

        // returns if the point will move outside of the first quadrant
        if (newX < 0 || newY < 0){
            return;
        }

        // else:
        setX(newX);
        setY(newY);
    }
}
