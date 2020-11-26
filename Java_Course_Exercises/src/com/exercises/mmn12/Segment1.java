package com.exercises.mmn12;

/**
 * Segment1 represents a line (parallel to the x-axis) using two Points.
 * @author Ori Ben Nun
 * @version 26/11/2020
 */
public class Segment1 {
    // Constants:
    private final double COMPARE_THRESHOLD = 0.01;

    // Instance Variables:

    private Point _poLeft;
    private Point _poRight;

    // Constructors:

    /**
     * Constructs a new segment using two Points. If the y coordinates are different,
     * change the y of the right point to be equal to the y of the left point.
     * @param left the left point of the segment
     * @param right the right point of the segment
     */
    public Segment1(Point left, Point right) {
        _poLeft = new Point(left);
        _poRight = new Point(right);
        forceParallelSegment();
    }

    /**
     * Constructs a new segment using 4 specified x y coordinates: Two coordinates for the left point and two coordinates for the right point. If the y coordinates are different,
     * change the y of the right point to be equal to the y of the left point.
     * @param leftX X value of left point
     * @param leftY Y value of left point
     * @param rightX X value of right point
     * @param rightY Y value of right point
     */
    public Segment1(double leftX ,double leftY,
                    double rightX ,double rightY) {
        _poLeft = new Point(leftX, leftY);
        _poRight = new Point(rightX, rightY);
        forceParallelSegment();
    }

    /**
     * Copy Constructor. Construct a segment using a reference segment.
     * @param other the reference segment
     */
    public Segment1(Segment1 other) {
        _poLeft = new Point(other._poLeft);
        _poRight = new Point(other._poRight);
    }

    // Private Methods:

    /**
     * Forcing the segment to be parallel to the X axis
     * by setting the right Y value to left's Y value.
     */
    private void forceParallelSegment() {
        _poRight.setY(_poLeft.getY());
    }

    // Public Methods:

    /**
     * Returns the left point of the segment.
     * @return The left point of the segment
     */
    public Point getPoLeft() {
        return new Point(_poLeft);
    }

    /**
     * Returns the right point of the segment.
     * @return The right point of the segment
     */
    public Point getPoRight() {
        return new Point(_poRight);
    }

    /**
     * Returns the segment length.
     * @return The segment length
     */
    public double getLength() {
        return _poLeft.distance(_poRight);
    }

    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
     * @return String representation of this segment
     */
    public String toString() {
        return _poLeft + "---" + _poRight;
    }

    /**
     * Check if the reference segment is equal to this segment.
     * @param other the reference segment
     * @return True if the reference segment is equal to this segment
     */
    public boolean equals(Segment1 other) {
        return (this._poRight.equals(other._poRight) && this._poLeft.equals(other._poLeft));
    }

    /**
     * Check if this segment is above a reference segment.
     * @param other the reference segment
     * @return True if this segment is above the reference segment
     */
    public boolean isAbove(Segment1 other) {
        return (this._poLeft.isAbove(other._poLeft));
    }

    /**
     * Check if this segment is under a reference segment.
     * @param other the reference segment
     * @return True if this segment is under the reference segment
     */
    public boolean isUnder(Segment1 other) {
        return (other._poLeft.isAbove(this._poLeft));
    }

    /**
     * Check if this segment is left of a received segment.
     * @param other the reference segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment1 other) {
        return (this._poRight.isLeft(other._poLeft));
    }

    /**
     * Check if this segment is right of a received segment.
     * @param other the reference segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight(Segment1 other) {
        return (other.isLeft(this));
    }

    /**
     * Move the segment horizontally by delta.
     * @param delta the displacement size
     */
    public void moveHorizontal(double delta) {
        Point newPointLeft = new Point(_poLeft);
        newPointLeft.move(delta, 0);

        Point newPointRight = new Point(_poRight);
        newPointRight.move(delta, 0);

        // In order to check if one of the points will be outside of the
        // first quadrant after the movement, checking if one of the
        // Point.Move calls didn't changed the pos of the point, which means it won't move.
        // So if one of the new points is equals to the old ones, it means it got outside of the first quadrant.
        // if true => do nothing.

        if (newPointLeft.equals(_poLeft) || newPointRight.equals(_poRight)) {
            return;
        }

        this._poLeft = newPointLeft;
        this._poRight = newPointRight;
    }

    /**
     * Move the segment vertically by delta.
     * @param delta the displacement size
     */
    public void moveVertical(double delta) {
        Point newPointLeft = new Point(_poLeft);
        newPointLeft.move(0, delta);

        Point newPointRight = new Point(_poRight);
        newPointRight.move(0, delta);

        // In order to check if one of the points will be outside of the
        // first quadrant after the movement, checking if the one of the
        // Point.Move calls didn't changed the pos of the point, which means it won't move.
        // So if one of the new points is equals to the old ones, it means it got outside of the first quadrant.
        // if true => do nothing.

        if (newPointLeft.equals(_poLeft) || newPointRight.equals(_poRight)) {
            return;
        }

        this._poLeft = newPointLeft;
        this._poRight = newPointRight;
    }

    /**
     * Change the segment size by moving the right point by delta.
     * Will be implemented only for a valid delta:
     * only if the new right point remains the right point.
     * @param delta the length change
     */
    public void changeSize(double delta) {
        double newSize = getLength() + delta;

        if (newSize < 0) return;

        _poRight.move(delta, 0);
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
    public boolean isBigger(Segment1 other) {
        return this.getLength() - other.getLength() > COMPARE_THRESHOLD;
    }

    /**
     * Returns the overlap size of this segment and a reference segment.
     * @param other the reference segment
     * @return The overlap size
     */
    public double overlap(Segment1 other) {
        // We will create a copy of the other segment and we'll set its Y value (level) to our level.
        double myLevel = this.getPoLeft().getY();
        Segment1 sameLevelOtherSeg = new Segment1(other._poLeft.getX(), myLevel,
                                            other.getPoRight().getX(), myLevel);
        // If no interaction at all, return 0 as requested
        if (sameLevelOtherSeg.isLeft(this) || sameLevelOtherSeg.isRight(this)) {
            return 0;
        }
        // If not, means they got some interaction point/s:

        // if other's left point is on our segment we will check if our right point is on other's segment
        // if it does, we will check the distance between other's left and our right point:
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
        // if it does, we will check the distance between other's right and our left point:
        // if it doesnt, means other's point left is also on our segment, means the first if will catch it.
        else if (this.pointOnSegment(sameLevelOtherSeg.getPoRight())) {
            if (sameLevelOtherSeg.pointOnSegment(this.getPoLeft())) {
                return getPoLeft().distance(sameLevelOtherSeg._poRight);
            }
        }

        // if got here, means something unexpected happen, we will return 0 as default
        return 0;
    }

    /**
     * Compute the trapeze perimeter,
     * which is constructed by this segment and a reference segment.
     * @param other the reference segment
     * @return The trapeze perimeter
     */
    public double trapezePerimeter(Segment1 other) {
        double leftSideLength = this.getPoLeft().distance(other.getPoLeft()); // distance between left points
        double rightSideLength = this.getPoRight().distance(other.getPoRight()); // distance between right points
        return this.getLength() + leftSideLength + other.getLength() + rightSideLength;
    }
}
