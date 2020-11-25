package com.exercises.mmn12;

/**
 * Segment2 represents a line (parallel to the x-axis) using a center point and length.
 * @author Ori Ben Nun
 * @version 25/11/2020
 */
public class Segment2 {

    // Constants:
    private final double TWO = 2.0;

    // Private variables:

    private Point _poCenter;
    private double _length;

    // Constructors:

    /**
     * Constructs a new segment using 4 specified x y coordinates:
     * two coordinates for the left point and two coordinates for the right point.
     * If the y coordinates are different, change the y of the right point
     * to be equal to the y of the left point.
     * @param leftX X value of left point
     * @param leftY Y value of left point
     * @param rightX X value of right point
     * @param rightY Y value of right point
     */
    public Segment2(double leftX, double leftY,
                    double rightX, double rightY) {
        Point leftPoint = new Point(leftX, leftY);
        Point rightPoint = new Point(rightX, rightY);

        forceParallelSegment(leftPoint, rightPoint);

        setLengthAndPoCenter(leftPoint, rightPoint);
    }


    /**
     * Constructs a new segment using a center point and the segment length.
     * @param poCenter the Center Point
     * @param length the segment length
     */
    public Segment2(Point poCenter, double length) {
        this._poCenter = new Point(poCenter);
        this._length = length;
    }

    /**
     * Constructs a new segment using two Points. If the y coordinates are different,
     * change the y of the right point to be equal to the y of the left point.
     * @param left the left point of the segment
     * @param right the right point of the segment
     */
    public Segment2(Point left, Point right) {
        Point leftPoint = new Point(left);
        Point rightPoint = new Point(right);

        forceParallelSegment(leftPoint, rightPoint);

        setLengthAndPoCenter(leftPoint, rightPoint);
    }

    /**
     * Copy Constructor. Construct a segment using a reference segment.
     * @param other the reference segment
     */
    public Segment2(Segment2 other) {
        this._length = other._length;
        this._poCenter = new Point(other._poCenter);
    }

    // Private methods:

    /**
     * Forcing the segment to be parallel to the X axis
     * Taking two points and setting the right Y value to left's Y value.
     * Make sure to use the same Points that were delivered to this method!
     * @param leftPoint
     * @param rightPoint
     */
    private void forceParallelSegment(Point leftPoint, Point rightPoint) {
        rightPoint.setY(leftPoint.getY());
    }

    private void setLengthAndPoCenter(Point leftPoint, Point rightPoint) {
        _length = leftPoint.distance(rightPoint);
        // getting the center point's X value by dividing the length by 2 in order to find the half point
        double centerX = rightPoint.getX() - (_length / TWO);
        _poCenter = new Point(centerX, leftPoint.getY());
    }

    // Public methods:

    /**
     * Returns the left point of the segment.
     * @return The left point of the segment
     */
    public Point getPoLeft() {
        double leftPointX = _poCenter.getX() - (_length / TWO);
        double leftPointY = _poCenter.getY();
        return new Point(leftPointX, leftPointY);
    }

    /**
     * Returns the right point of the segment.
     * @return The right point of the segment
     */
    public Point getPoRight() {
        double rightPointX = _poCenter.getX() + (_length / TWO);
        double rightPointY = _poCenter.getY();
        return new Point(rightPointX, rightPointY);
    }
}
