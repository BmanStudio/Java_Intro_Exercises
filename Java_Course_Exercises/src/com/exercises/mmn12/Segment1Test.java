package com.exercises.mmn12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ori Ben Nun
 */
class Segment1Test {

    @Test
    void getPoLeft() {
        Segment1 segment1 = new Segment1(new Point(2,4), new Point(6,0));
        assertEquals(4,segment1.getPoLeft().getY(), 0.01);
        assertEquals(2,segment1.getPoLeft().getX(), 0.01);
    }

    @Test
    void getPoRight() {
        Segment1 segment1 = new Segment1(new Point(2,4), new Point(6,0));
        Segment1 segment2 = new Segment1(2,4, 6,0);
        assertEquals(2, segment2.getPoLeft().getX(), 0.01);
        assertEquals(4, segment2.getPoLeft().getY(), 0.01);
        assertEquals(6, segment2.getPoRight().getX(), 0.01);
        assertEquals(4, segment2.getPoRight().getY(), 0.01);
        assertEquals(true, segment1.equals(segment2));
    }

    @Test
    void getLength() {
        Segment1 segment1 = new Segment1(new Point(2,4), new Point(6,4));
        assertEquals(4,segment1.getLength(), 0.01);

        Segment1 segment2 = new Segment1(new Point(2,4), new Point(2,6));
        assertEquals(0,segment2.getLength(), 0.01);
    }

    @Test
    void testToString() {
        Segment1 segment1 = new Segment1(new Point(3,4), new Point(5,4));
        assertEquals("(3.0,4.0)---(5.0,4.0)",segment1.toString());
    }

    @Test
    void testEquals() {
        Segment1 segment1 = new Segment1(new Point(3,4), new Point(5,4));
        Segment1 segment2 = new Segment1(new Point(3,4), new Point(5,4));
        Segment1 segment3 = new Segment1(new Point(3,5), new Point(5,4));
        assertEquals(true, segment1.equals(segment2));
        assertEquals(false, segment1.equals(segment3));
    }

    @Test
    void isAbove() {
        Segment1 segment1 = new Segment1(new Point(5,4), new Point(5,4));
        Segment1 segment2 = new Segment1(new Point(5,4), new Point(5,4));
        Segment1 segment3 = new Segment1(new Point(3,2), new Point(5,4));
        assertEquals(false, segment1.isAbove(segment2));
        assertEquals(true, segment1.isAbove(segment3));
    }

    @Test
    void isUnder() {
        Segment1 segment1 = new Segment1(new Point(5,4), new Point(5,4));
        Segment1 segment2 = new Segment1(new Point(5,4), new Point(5,4));
        Segment1 segment3 = new Segment1(new Point(3,2), new Point(5,4));
        assertEquals(false, segment1.isUnder(segment2));
        assertEquals(false, segment1.isUnder(segment3));
        assertEquals(true, segment3.isUnder(segment1));
    }

    @Test
    void isLeft() {
        Segment1 segment1 = new Segment1(new Point(6,15), new Point(8,1024));
        Segment1 segment2 = new Segment1(new Point(4,15), new Point(6,4));
        Segment1 segment3 = new Segment1(new Point(2,108), new Point(4,4));
        assertEquals(false, segment1.isLeft(segment2));
        assertEquals(false, segment1.isLeft(segment3));
        assertEquals(false, segment1.isLeft(segment1));
        assertEquals(false, segment2.isLeft(segment3));
        assertEquals(false, segment2.isLeft(segment1));
        assertEquals(true, segment3.isLeft(segment1));
        assertEquals(false, segment3.isLeft(segment3));
    }

    @Test
    void isRight() {
        Segment1 segment1 = new Segment1(new Point(6,15), new Point(8,1024));
        Segment1 segment2 = new Segment1(new Point(4,15), new Point(6,4));
        Segment1 segment3 = new Segment1(new Point(2,108), new Point(4,4));
        Segment1 segment4 = new Segment1(segment1);
        assertEquals(false, segment1.isRight(segment2));
        assertEquals(true, segment1.isRight(segment3));
        assertEquals(true, segment4.isRight(segment3));
        assertEquals(false, segment4.isRight(segment1));
        assertEquals(false, segment1.isRight(segment1));
        assertEquals(false, segment2.isRight(segment3));
        assertEquals(false, segment2.isRight(segment1));
        assertEquals(false, segment3.isRight(segment1));
        assertEquals(false, segment3.isRight(segment3));
    }

    @Test
    void moveHorizontal() {
        Segment1 segment1 = new Segment1(new Point(6,15), new Point(8,1024));
        Segment1 segment2 = new Segment1(new Point(4,15), new Point(6,4));
        Segment1 segment3 = new Segment1(new Point(2,108), new Point(4,4));
        Segment1 segment4 = new Segment1(segment1);
        assertEquals(false, segment1.isRight(segment2));
        assertEquals(true, segment1.isRight(segment3));
        assertEquals(true, segment4.isRight(segment3));
        assertEquals(false, segment4.isRight(segment1));
        assertEquals(false, segment1.isRight(segment1));
        assertEquals(false, segment2.isRight(segment3));
        assertEquals(false, segment2.isRight(segment1));
        assertEquals(false, segment3.isRight(segment1));
        assertEquals(false, segment3.isRight(segment3));
        segment4.moveHorizontal(10);
        assertEquals(true, segment4.isRight(segment3));
        assertEquals(true, segment4.isRight(segment1));
        assertEquals(false, segment4.isRight(segment4));
        segment1.moveHorizontal(10);
        assertEquals(false, segment4.isRight(segment1));
        assertEquals(false, segment4.isLeft(segment1));
        segment1.moveHorizontal(-10);
        segment4.moveHorizontal(-10);
        assertEquals(false, segment4.isRight(segment1));
        assertEquals(false, segment4.isRight(segment2));
        assertEquals(false, segment4.isLeft(segment1));

        Segment1 segment5 = new Segment1(new Point(4,15), new Point(6,4));
        Segment1 segment6 = new Segment1(segment5);
        segment5.moveHorizontal(-4.5);
        assertEquals(true, segment5.equals(segment6));
        segment5.moveHorizontal(-3.5);
        assertEquals(false, segment5.equals(segment6));
        assertEquals(true, segment5.isLeft(segment6));
        Segment1 segment7 = new Segment1(new Point(0,0), new Point(0,4));
        assertEquals(true, segment5.isRight(segment7));
        assertEquals(true, segment7.isLeft(segment5));

    }

    @Test
    void moveVertical() {
        Segment1 segment1 = new Segment1(new Point(6,15), new Point(8,1024));
        Segment1 segment2 = new Segment1(segment1);
        segment1.moveVertical(-100);
        segment1.moveHorizontal(-100);
        segment2.moveHorizontal(-100);
        assertEquals(true, segment1.equals(segment2));
        assertEquals(false, segment2.isAbove(segment1));
        segment1.moveVertical(-10);
        assertEquals(false, segment1.equals(segment2));
        assertEquals(true, segment1.isUnder(segment2));
        segment2.moveVertical(1500);
        segment1.moveVertical(150);
        assertEquals(false, segment1.equals(segment2));
        assertEquals(false, segment1.isAbove(segment2));
        assertEquals(true, segment1.isUnder(segment2));
        assertEquals(true, segment2.isAbove(segment1));
    }

    @Test
    void changeSize() {
        Segment1 segment1 = new Segment1(new Point(3,10), new Point(6,10));
        segment1.changeSize(-2);
        assertEquals(1, segment1.getLength(), 0.01);
        assertEquals(4, segment1.getPoRight().getX(), 0.01);
        segment1.changeSize(-2);
        assertEquals(1, segment1.getLength(), 0.01);
        assertEquals(4, segment1.getPoRight().getX(), 0.01);
        segment1.changeSize(10);
        assertEquals(11, segment1.getLength(), 0.01);
        assertEquals(14, segment1.getPoRight().getX(), 0.01);
        assertEquals(3, segment1.getPoLeft().getX(), 0.01);
        segment1.changeSize(-11);
        assertEquals(0, segment1.getLength());
        assertEquals(3, segment1.getPoRight().getX(), 0.01);
        assertEquals(3, segment1.getPoLeft().getX(), 0.01);
        segment1.changeSize(-0.1);
        assertEquals(0, segment1.getLength());
        assertEquals(3, segment1.getPoRight().getX(), 0.01);
        assertEquals(3, segment1.getPoLeft().getX(), 0.01);
        segment1.changeSize(0.1);
        assertEquals(0.1, segment1.getLength(), 0.01);
        assertEquals(3.1, segment1.getPoRight().getX(), 0.01);
        assertEquals(3, segment1.getPoLeft().getX(), 0.01);
    }

    @Test
    void pointOnSegment() {
        Segment1 segment1 = new Segment1(new Point(3,10), new Point(6,10));
        Point point1 = new Point(2,7);
        assertEquals(false, segment1.pointOnSegment(point1));
        point1.move(1,3);
        assertEquals(true, segment1.pointOnSegment(point1));
        point1.move(2,0);
        assertEquals(true, segment1.pointOnSegment(point1));
        point1.move(2,0);
        assertEquals(false, segment1.pointOnSegment(point1));
        point1.move(-1,1);
        assertEquals(false, segment1.pointOnSegment(point1));
        point1.move(0,-1);
        assertEquals(true, segment1.pointOnSegment(point1));
        assertEquals(true, segment1.pointOnSegment(segment1.getPoLeft()));
        assertEquals(true, segment1.pointOnSegment(segment1.getPoRight()));
        segment1.moveVertical(10);
        assertEquals(false, segment1.pointOnSegment(point1));
        assertEquals(true, segment1.pointOnSegment(segment1.getPoLeft()));
        assertEquals(true, segment1.pointOnSegment(segment1.getPoRight()));
    }

    @Test
    void isBigger() {
        Segment1 segment1 = new Segment1(new Point(3,10), new Point(6,10));
        Segment1 segment2 = new Segment1(new Point(3,10), new Point(6,10));
        assertEquals(false, segment1.isBigger(segment1));
        assertEquals(false, segment1.isBigger(segment2));
        segment2.changeSize(0.5);
        assertEquals(true, segment2.isBigger(segment1));
        segment1.moveHorizontal(500);
        assertEquals(true, segment2.isBigger(segment1));
        segment1.changeSize(0.5);
        assertEquals(false, segment2.isBigger(segment1));
        assertEquals(false, segment1.isBigger(segment2));
        segment1.changeSize(0.5);
        assertEquals(true, segment1.isBigger(segment2));
    }

    @Test
    void overlap() {
        Segment1 segment1 = new Segment1(new Point(3,10), new Point(6,10));
        Segment1 segment2 = new Segment1(new Point(3,80), new Point(6,50));
        assertEquals(3, segment1.overlap(segment2), 0.01);
        assertEquals(3, segment1.overlap(segment1), 0.01);
        segment2.moveHorizontal(1);
        assertEquals(2, segment1.overlap(segment2), 0.01);
        segment2.changeSize(-2);
        assertEquals(1, segment1.overlap(segment2), 0.01);
        segment1.changeSize(-1);
        assertEquals(1, segment1.overlap(segment2), 0.01);
        segment1.changeSize(-1);
        assertEquals(0, segment1.overlap(segment2), 0.01);

        Segment1 segment3 = new Segment1(segment1);
        segment3.moveVertical(1024);
        assertEquals(segment1.getLength(), segment1.overlap(segment3), 0.01);
        segment3.changeSize(-1);
        assertEquals(segment1.getLength() -1, segment1.overlap(segment3), 0.01);
        segment3.moveHorizontal(1);
        assertEquals(segment1.getLength() -1, segment1.overlap(segment3), 0.01);

        Segment1 segment4 = new Segment1(new Point(3,4), new Point(5,4));
        Segment1 segment5 = new Segment1(new Point(0,2), new Point(2,4));
        Segment1 segment6 = new Segment1(new Point(1,1), new Point(4,1));
        Segment1 segment7 = new Segment1(new Point(1,10), new Point(4,10));

        assertEquals(1, segment6.overlap(segment4), 0.01);
        assertEquals(1, segment6.overlap(segment5), 0.01);
        assertEquals(segment7.getLength(), segment7.overlap(segment6), 0.01);
        segment7.changeSize(10);
        assertEquals(segment6.getLength(), segment7.overlap(segment6), 0.01);
        segment7.moveHorizontal(100);
        assertEquals(0, segment7.overlap(segment6), 0.01);
    }

    @Test
    void trapezePerimeter() {
        Segment1 segment1 = new Segment1(new Point(1,1), new Point(4,1));
        Segment1 segment2 = new Segment1(new Point(1,2), new Point(4,2));
        assertEquals(3 + 3 + 1 + 1, segment1.trapezePerimeter(segment2));
        segment2.moveVertical(10);
        assertEquals(3 + 3 + 1 + 1 + 20, segment1.trapezePerimeter(segment2), 0.01);
        segment1.changeSize(2);
        assertEquals(3 + 3 + 1 + 1 + 20 + 2, segment1.trapezePerimeter(segment2), .5);
        segment1.changeSize(2);
        assertEquals(3 + 3 + 1 + 1 + 20 + 2 + 2, segment1.trapezePerimeter(segment2), 1);
        segment1.moveHorizontal(1);
        assertEquals(3 + 3 + 1 + 1 + 20 + 2 + 2 + 1, segment1.trapezePerimeter(segment2), .5);
        segment2.moveVertical(100);
        assertEquals(3 + 3 + 1 + 1 + 20 + 2 + 2 + 1 + 200, segment1.trapezePerimeter(segment2), 1);
    }

    @Test
    void arielTest(){
        ArielSeg1Tester.main();
    }

    @Test
    void teachersTester() {
        System.out.println("============ Testing class Segment1 =============");

        Segment1 s11 = new Segment1(1.0 , 2.0, 3.0, 4.0);
        Segment1 s12 = new Segment1(1.0 , 2.0, 3.0, 4.0);
        if (!s11.equals(s12))
            System.out.println("Check your equals method in class Segment1");

        Point p3 = new Point(5.0, 6.0);
        Point p4 = new Point(7.0, 8.0);
        Segment1 s13 = new Segment1(p3, p4);
        Segment1 s14 = new Segment1(s12);

        Point p5 = s11.getPoLeft();
        p5 = s11.getPoRight();

        double d1 = s11.getLength();

        if (s11.toString().indexOf('@') != -1)
            System.out.println("Check your toString method in class Segment1");

        boolean b1 = s11.isAbove(s12);
        b1 = s11.isUnder(s12);
        b1 = s11.isLeft(s12);
        b1 = s11.isRight(s12);

        s11.moveHorizontal(1.0);
        s11.moveVertical(1.0);
        s11.changeSize(1.0);

        b1 = s11.pointOnSegment(p3);
        b1 = s11.isBigger(s12);
        d1 = s11.overlap(s12);
        d1 = s11.trapezePerimeter(s12);

        System.out.println("============ Testing class Segment1 Done =============");
    }
}