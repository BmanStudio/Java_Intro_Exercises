package com.exercises.mmn12;

/**
 * Segment1 represents a line (parallel to the x-axis) using two Points.
 * @author Ori Ben Nun
 * @version 16/11/2020
 */
public class Segment1 {
    // Constants:
    private final double ZERO = 0.0;
    private final double COMPARE_THRESHOLD = 0.01;

    // Instance Variables:

    private Point _poLeft;
    private Point _poRight;

    // Constructors:

    public Segment1(Point left, Point right) {
        _poLeft = new Point(left);
        _poRight = new Point(right);
        forceParallelSegment();
    }

    public Segment1(double leftX ,double leftY,
                    double rightX ,double rightY) {
        _poLeft = new Point(leftX, leftY);
        _poRight = new Point(rightX, rightY);
        forceParallelSegment();
    }

    public Segment1(Segment1 other) {
        _poLeft = new Point(other._poLeft);
        _poRight = new Point(other._poRight);
    }

    // Private Methods:

    private void forceParallelSegment() {
        _poRight.setY(_poLeft.getY());
    }

    // Public Methods:

    public Point getPoLeft() {
        return new Point(_poLeft);
    }

    public Point getPoRight() {
        return new Point(_poRight);
    }

    public double getLength() {
        return _poLeft.distance(_poRight);
    }

    public String toString() {
        return _poLeft + "---" + _poRight;
    }

    public boolean equals (Segment1 other) {
        return (this._poRight.equals(other._poRight) && this._poLeft.equals(other._poLeft));
    }

    public boolean isAbove(Segment1 other) {
        return (this._poLeft.isAbove(other._poLeft));
    }

    public boolean isUnder(Segment1 other) {
        return (other._poLeft.isAbove(this._poLeft));
    }

    public boolean isLeft(Segment1 other) {
        return (this._poRight.isLeft(other._poLeft));
    }

    public boolean isRight(Segment1 other) {
        return (other.isLeft(this));
    }

    public void moveHorizontal(double delta) {
        Point newPointLeft = new Point(_poLeft);
        newPointLeft.move(delta, ZERO);

        Point newPointRight = new Point(_poRight);
        newPointRight.move(delta, ZERO);

        // In order to check if one of the points will be outside of the
        // first quadrant after the movement, checking if the one of the
        // Point.Move calls didn't changed the pos of the point, which means it won't move.
        // So if one of the new points is equals to the old ones, it means it got outside of the first quadrant.
        // if true => do nothing.

        if (newPointLeft.equals(_poLeft) || newPointRight.equals(_poRight)) return;

        this._poLeft = newPointLeft;
        this._poRight = newPointRight;
    }

    public void moveVertical(double delta) {
        Point newPointLeft = new Point(_poLeft);
        newPointLeft.move(ZERO, delta);

        Point newPointRight = new Point(_poRight);
        newPointRight.move(ZERO, delta);

        // In order to check if one of the points will be outside of the
        // first quadrant after the movement, checking if the one of the
        // Point.Move calls didn't changed the pos of the point, which means it won't move.
        // So if one of the new points is equals to the old ones, it means it got outside of the first quadrant.
        // if true => do nothing.

        if (newPointLeft.equals(_poLeft) || newPointRight.equals(_poRight)) return;

        this._poLeft = newPointLeft;
        this._poRight = newPointRight;
    }

    public void changeSize(double delta) {
        double newSize = getLength() + delta;

        if (newSize < ZERO) return;

        _poRight.move(delta, ZERO);
    }

    public boolean pointOnSegment (Point p) {
        if (p.isAbove(_poLeft) || p.isUnder(_poLeft)) return false;
        // if one the edge, means equals to one of the points.
        else if (p.equals(_poLeft) || p.equals(_poRight)) return true;
        // if between the points
        else if (p.isLeft(_poRight) && p.isRight(_poLeft)) return true;

        return false;
    }

    public boolean isBigger(Segment1 other) {
        return this.getLength() - other.getLength() > COMPARE_THRESHOLD;
    }

    public double overlap(Segment1 other) {
        // We will create a copy of the other segment and we'll set its Y value (level) to our level.
        double myLevel = this.getPoLeft().getY();
        Segment1 sameLevelOtherSeg = new Segment1(other._poLeft.getX(), myLevel,
                                            other.getPoRight().getX(), myLevel);
        // If no interaction at all, return 0 as requested
        if (sameLevelOtherSeg.isLeft(this) || sameLevelOtherSeg.isRight(this)) {
            return ZERO;
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
        return ZERO;
    }

    public double trapezePerimeter(Segment1 other) {
        double leftSideLength = this.getPoLeft().distance(other.getPoLeft()); // distance between left points
        double rightSideLength = this.getPoRight().distance(other.getPoRight()); // distance between right points
        return this.getLength() + leftSideLength + other.getLength() + rightSideLength;
    }
}
