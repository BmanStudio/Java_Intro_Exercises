package com.exercises.mmn12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment2Test {

    @Test
    void getPoLeft() {
        Point pointLeft = new Point(1, 1);
        Point pointRight = new Point(4, 5);
        Segment2 segment1 = new Segment2(pointLeft, pointRight);
        Segment2 segment2 = new Segment2(1, 1, 4, 5);
        Segment2 segment3 = new Segment2(segment1);
        Point seg4Center = new Point(2.5, 1);
        Segment2 segment4 = new Segment2(seg4Center, 3);
        Segment2 segment5 = new Segment2(seg4Center, 4);
        assertEquals(true, segment1.getPoLeft().equals(pointLeft));
        assertEquals(true, segment2.getPoLeft().equals(segment1.getPoLeft()));
        assertEquals(true, segment2.getPoLeft().equals(segment3.getPoLeft()));
        assertEquals(true, segment4.getPoLeft().equals(segment3.getPoLeft()));
        assertEquals(true, segment4.getPoLeft().equals(segment2.getPoLeft()));
        assertEquals(true, segment4.getPoLeft().equals(segment1.getPoLeft()));
        assertEquals(false, segment4.getPoLeft().equals(segment5.getPoLeft()));
    }

    @Test
    void getPoRight() {
        Point pointLeft = new Point(1, 1);
        Point pointRight = new Point(4, 1);
        Segment2 segment1 = new Segment2(pointLeft, pointRight);
        Segment2 segment2 = new Segment2(1, 1, 4, 5);
        Segment2 segment3 = new Segment2(segment1);
        Point seg4Center = new Point(2.5, 1);
        Segment2 segment4 = new Segment2(seg4Center, 3);
        Segment2 segment5 = new Segment2(seg4Center, 4);
        assertEquals(true, segment1.getPoRight().equals(pointRight));
        assertEquals(true, segment2.getPoRight().equals(segment1.getPoRight()));
        assertEquals(true, segment2.getPoRight().equals(segment3.getPoRight()));
        assertEquals(true, segment4.getPoRight().equals(segment3.getPoRight()));
        assertEquals(true, segment4.getPoRight().equals(segment2.getPoRight()));
        assertEquals(true, segment4.getPoRight().equals(segment1.getPoRight()));
        assertEquals(false, segment4.getPoRight().equals(segment5.getPoRight()));
    }
}