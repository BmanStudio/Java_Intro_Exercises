package com.exercises.mmn12;

/**
 * Segment2 represents a line (parallel to the x-axis) using a center point and length.
 * @author Ori Ben Nun
 * @version 25/11/2020
 */
public class Segment2 {

    // Constants:
    private final double COMPARE_THRESHOLD = 0.01;

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
     * @param leftPoint the left point of the segment
     * @param rightPoint the right point of the segment, which will be fixed to the left point's Y value
     */
    private void forceParallelSegment(Point leftPoint, Point rightPoint) {
        rightPoint.setY(leftPoint.getY());
    }

    /**
     * Setting the segment's length and center point according to left and right points delivered
     * @param leftPoint the left end point of the segment
     * @param rightPoint the right end point of the segment
     */
    private void setLengthAndPoCenter(Point leftPoint, Point rightPoint) {
        _length = leftPoint.distance(rightPoint);
        // getting the center point's X value by dividing the length by 2 in order to find the half point
        double centerX = rightPoint.getX() - (_length / 2);
        _poCenter = new Point(centerX, leftPoint.getY());
    }

    // Public methods:

    /**
     * Returns the left point of the segment.
     * @return The left point of the segment
     */
    public Point getPoLeft() {
        double leftPointX = _poCenter.getX() - (_length / 2);
        double leftPointY = _poCenter.getY();
        return new Point(leftPointX, leftPointY);
    }

    /**
     * Returns the right point of the segment.
     * @return The right point of the segment
     */
    public Point getPoRight() {
        double rightPointX = _poCenter.getX() + (_length / 2);
        double rightPointY = _poCenter.getY();
        return new Point(rightPointX, rightPointY);
    }

    /**
     * Returns the segment length.
     * @return The segment length
     */
    public double getLength() {
        return _length;
    }

    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     * @return String representation of this segment
     */
    public String toString() {
        return getPoLeft() + "---" + getPoRight();
    }

    /**
     * Check if the reference segment is equal to this segment.
     * @param other the reference segment
     * @return True if the reference segment is equal to this segment
     */
    public boolean equals(Segment2 other) {
        return (this.getPoRight().equals(other.getPoRight()) && this.getPoLeft().equals(other.getPoLeft()));
    }

    /**
     * Check if this segment is above a reference segment.
     * @param other the reference segment
     * @return True if this segment is above the reference segment
     */
    public boolean isAbove(Segment2 other) {
        return (this.getPoLeft().isAbove(other.getPoLeft()));
    }

    /**
     * Check if this segment is under a reference segment.
     * @param other the reference segment
     * @return True if this segment is under the reference segment
     */
    public boolean isUnder(Segment2 other) {
        return (other.getPoLeft().isAbove(this.getPoLeft()));
    }

    /**
     * Check if this segment is left of a received segment.
     * @param other the reference segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment2 other) {
        return (this.getPoRight().isLeft(other.getPoLeft()));
    }

    /**
     * Check if this segment is right of a received segment.
     * @param other the reference segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight(Segment2 other) {
        return (other.isLeft(this));
    }

    /**
     * Move the segment horizontally by delta. Will be implemented only for a valid delta
     * @param delta the displacement size
     */
    public void moveHorizontal(double delta) {

        if (getPoLeft().getX() + delta < 0) {
            return;
        }

        _poCenter.move(delta, 0);
    }

    /**
     * Move the segment vertically by delta. Will be implemented only for a valid delta
     * @param delta the displacement size
     */
    public void moveVertical(double delta) {

        if (_poCenter.getY() + delta < 0) {
            return;
        }

        _poCenter.move(0, delta);
    }

    /**
     * Change the segment size by moving the right point by delta.
     * Will be implemented only for a valid delta: only if the new right point remains the right point.
     * @param delta the length change
     */
    public void changeSize(double delta) {
        double newSize = getLength() + delta;

        if (newSize < 0) return;

        Point newRightPoint = new Point(getPoRight());
        newRightPoint.move(delta, 0);

        setLengthAndPoCenter(getPoLeft(), newRightPoint);
    }

    /**
     * Check if a point is located on the segment.
     * @param p a point to be checked
     * @return True if p is on this segment
     */
    public boolean pointOnSegment(Point p) {

        if (p.isAbove(getPoLeft()) || p.isUnder(getPoLeft())) {
            return false;
        }

        // if one the edge, means equals to one of the points.
        else if (p.equals(getPoLeft()) || p.equals(getPoRight())) {
            return true;
        }

        // if between the points
        else return p.isLeft(getPoRight()) && p.isRight(getPoLeft());
    }

    /**
     * Check if this segment is bigger than a reference segment.
     * @param other the reference segment
     * @return True if this segment is bigger than the reference segment
     */
    public boolean isBigger(Segment2 other) {
        return this.getLength() - other.getLength() > COMPARE_THRESHOLD;
    }

    /**
     * Returns the overlap size of this segment and a reference segment.
     * @param other the reference segment
     * @return The overlap size
     */
    public double overlap(Segment2 other) {

        // We will create a copy of the other segment and we'll set its Y value (level) to our level.
        double myLvl = this._poCenter.getY();
        Point otherCenterMyLvl = new Point(other._poCenter.getX(), myLvl);
        Segment2 sameLevelOtherSeg = new Segment2(otherCenterMyLvl, other._length);

        // If no interaction at all, return 0 as requested
        if (sameLevelOtherSeg.isLeft(this) || sameLevelOtherSeg.isRight(this)) {
            return 0;
        }

        // else, means they got some interaction point/s:

        // if other's left point is on our segment we will check if our right point is on other's segment
            // if it does, we will return the distance between other's left and our right point
            // if it doesnt, we will return other's length (cuz the overlap is all of its length)
        if (this.pointOnSegment(sameLevelOtherSeg.getPoLeft())) {
            if (sameLevelOtherSeg.pointOnSegment(this.getPoRight())){
                // equal segments should enter here, and it should return their (same) length
                return getPoRight().distance(sameLevelOtherSeg.getPoLeft());
            }
            else {
                return sameLevelOtherSeg.getLength();
            }
        }

        // if other's right point is on our segment we will check if our left point is on other's segment
            // if it does, we will return the distance between other's right and our left point
            // if it doesnt, means other's point left is also on our segment, means the first if statement will catch it.
        else if (this.pointOnSegment(sameLevelOtherSeg.getPoRight())) {
            if (sameLevelOtherSeg.pointOnSegment(this.getPoLeft())) {
                return getPoLeft().distance(sameLevelOtherSeg.getPoRight());
            }
        }

        // if got here, means something unexpected happen, we will return 0 as default
        return 0;
    }

    /**
     * Compute the trapeze perimeter, which constructed by this segment and a reference segment.
     * @param other the reference segment
     * @return The trapeze perimeter
     */
    public double trapezePerimeter(Segment2 other) {
        double leftSideLength = this.getPoLeft().distance(other.getPoLeft()); // distance between left points
        double rightSideLength = this.getPoRight().distance(other.getPoRight()); // distance between right points
        return this.getLength() + leftSideLength + other.getLength() + rightSideLength;
    }
}
