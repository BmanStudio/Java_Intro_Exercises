package com.exercises.mmn12;

/**
 * Test Segment2 class methods
 *
 * @author Ariel Szabo
 */
public class ArielSeg2Tester {
    public static void main()
    {
        System.out.println("============ Testing class Point =============");
        Point pointLeft = new Point(1.0, 2.0);
        Point pointRight = new Point(5, 3);
        Segment2 segment = new Segment2(pointLeft, pointRight);

        Segment2 anotherSegment = new Segment2(10, 4, 13, 4);

        Segment2 segmentCopy = new Segment2(segment);

        // assert segment.getPoLeft().equals(pointLeft);
        assert !segment.getPoRight().equals(pointRight); // y axis changed based on left y

        pointLeft.move(1, 1);
        assert !segment.getPoLeft().equals(pointLeft); // test aliasing

        assert segment.getLength() == 4;
        assert getRoundedNumber(anotherSegment.getLength()) == 3;

        assert !anotherSegment.equals(segment);
        assert segmentCopy != segment;
        assert segmentCopy.equals(segment);


        assert anotherSegment.isAbove(segment);
        assert segment.isUnder(anotherSegment);


        assert anotherSegment.isRight(segment);
        assert segment.isLeft(anotherSegment);

        Segment2 overlapSegment = new Segment2(0, 7, 3, 7);
        assert !segment.isRight(overlapSegment);
        assert !segment.isLeft(overlapSegment);


        segmentCopy.moveHorizontal(10);
        assert segmentCopy.getPoLeft().toString().equals("(11.0,2.0)");
        assert segmentCopy.getPoRight().toString().equals("(15.0,2.0)");

        segmentCopy.moveHorizontal(-12);
        assert segmentCopy.getPoLeft().toString().equals("(11.0,2.0)");
        assert segmentCopy.getPoRight().toString().equals("(15.0,2.0)");


        segmentCopy.moveVertical(2);
        assert segmentCopy.getPoLeft().toString().equals("(11.0,4.0)");
        assert segmentCopy.getPoRight().toString().equals("(15.0,4.0)");

        segmentCopy.moveHorizontal(-12);
        assert segmentCopy.getPoLeft().toString().equals("(11.0,4.0)");
        assert segmentCopy.getPoRight().toString().equals("(15.0,4.0)");

        segmentCopy.changeSize(2);
        assert segmentCopy.getPoLeft().toString().equals("(11.0,4.0)");
        assert segmentCopy.getPoRight().toString().equals("(17.0,4.0)");

        segmentCopy.changeSize(-10);
        assert segmentCopy.getPoLeft().toString().equals("(11.0,4.0)");
        System.out.println(segmentCopy.getPoRight());
        assert segmentCopy.getPoRight().toString().equals("(17.0,4.0)");


        assert !segmentCopy.pointOnSegment(new Point(12, 7));
        assert segmentCopy.pointOnSegment(new Point(12, 4));

        assert segmentCopy.pointOnSegment(segmentCopy.getPoLeft());
        assert segmentCopy.pointOnSegment(segmentCopy.getPoRight());

        assert segmentCopy.isBigger(segment);
        assert !segmentCopy.isBigger(segmentCopy);


        System.out.println("segment: " + segment + "  segmentCopy: " + segmentCopy + " anotherSegment: " + anotherSegment + " overlapSegment: " + overlapSegment);
        assert Math.abs(segment.overlap(overlapSegment) - 2) < 0.001;

        assert Math.abs(segment.trapezePerimeter(overlapSegment) - (7 + Math.sqrt(26) + Math.sqrt(29))) < 0.0001;
        System.out.println("============ Testing class Point Done =============");
    }

    private static double getRoundedNumber(double x){
        return Math.round(x * 10000) / 10000.0;
    }
}
