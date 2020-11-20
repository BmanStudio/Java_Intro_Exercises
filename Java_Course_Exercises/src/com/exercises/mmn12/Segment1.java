package com.exercises.mmn12;

/**
 * Segment1 represents a line (parallel to the x-axis) using two Points.
 * @author Ori Ben Nun
 * @version 16/11/2020
 */
public class Segment1 {
    // Constants:
    private final double DEFAULT_X_Y_VALUE = 0.0;
    private final double COMPARE_THRESHOLD = 0.01;

    // Instance Variables:

    Point _poLeft;
    Point _poRight;

    // Constructors:

    public Segment1(Point left, Point right) {
        _poLeft = new Point(left);
        _poRight = new Point(right);
        setRightYtoLeftYifNotParallel();
    }

    public Segment1(double leftX ,double leftY,
                    double rightX ,double rightY) {
        _poLeft = new Point(leftX, leftY);
        _poRight = new Point(rightX, rightY);
        setRightYtoLeftYifNotParallel();
    }

    public Segment1(Segment1 other) {
        _poLeft = new Point(other._poLeft);
        _poRight = new Point(other._poRight);
    }

    // Private Methods:

    private void setRightYtoLeftYifNotParallel() {
        if (_poLeft.getY() != _poRight.getY()){
            _poRight.setY(_poLeft.getY());
        }
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
        newPointLeft.move(delta, 0);

        Point newPointRight = new Point(_poRight);
        newPointRight.move(delta, 0);

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
        newPointLeft.move(0, delta);

        Point newPointRight = new Point(_poRight);
        newPointRight.move(0, delta);

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

        if (newSize < 0) return;

        _poRight.move(delta, 0);
    }

    public boolean pointOnSegment (Point p) {
        if (p.isAbove(_poLeft) || p.isUnder(_poLeft)) return false;
        // if one the edge, means equals to one of the points.
        else if (p.equals(_poLeft) || p.equals(_poRight)) return true;
        // if between the points
        else if (p.isLeft(_poRight) && p.isRight(_poLeft)) return true;

        return false;
    }
}
