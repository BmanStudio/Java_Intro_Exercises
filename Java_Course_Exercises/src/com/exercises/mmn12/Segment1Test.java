package com.exercises.mmn12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}