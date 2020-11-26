package com.exercises.mmn12;


/**
 * Test Segment1 class methods
 *
 * @author Ariel Szabo
 */
public class ArielSeg1Tester
{
    public static void main()
    {
        System.out.println("============ Testing class Point =============");
        Point pointLeft = new Point(1, 2);
        Point pointRight = new Point(5, 3);
        Segment1 segment = new Segment1(pointLeft, pointRight);

        Segment1 anotherSegment = new Segment1(10, 4, 13, 4);

        Segment1 segmentCopy = new Segment1(segment);

        assert segment.getPoLeft().equals(pointLeft);
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

        Segment1 overlapSegment = new Segment1(0, 7, 3, 7);
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
        assert segmentCopy.getPoRight().toString().equals("(17.0,4.0)"): segmentCopy.getPoRight();

        segmentCopy.changeSize(-10);
        assert segmentCopy.getPoLeft().toString().equals("(11.0,4.0)");
        assert segmentCopy.getPoRight().toString().equals("(17.0,4.0)");


        assert !segmentCopy.pointOnSegment(new Point(12, 7));
        assert segmentCopy.pointOnSegment(new Point(12, 4));

        assert segmentCopy.pointOnSegment(segmentCopy.getPoLeft());
        assert segmentCopy.pointOnSegment(segmentCopy.getPoRight());

        assert segmentCopy.isBigger(segment);
        assert !segmentCopy.isBigger(segmentCopy);


        System.out.println("segment: " + segment + "  segmentCopy: " + segmentCopy + " anotherSegment: " + anotherSegment + " overlapSegment: " + overlapSegment);
        assert segment.overlap(overlapSegment) == 2: segment.overlap(overlapSegment);

        assert segment.trapezePerimeter(overlapSegment) == 7 + Math.sqrt(26) + Math.sqrt(29);
        System.out.println("============ Testing class Point Done =============");
    }

    private static double getRoundedNumber(double x){
        return Math.round(x * 10000) / 10000.0;
    }
}
