package com.exercises.mmn12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    public void MoveTest()
    {
        Point point2 = new Point(2, 3);
        point2.move(4, 5);
        assertEquals(6, point2.getX(), 0.01);
        assertEquals(8, point2.getY(), 0.01);
        point2.move(-2, -3);
        assertEquals(4, point2.getX(), 0.01);
        assertEquals(5, point2.getY(), 0.01);
    }

    @Test
    public void CopyConstructorTest()
    {
        Point point3 = new Point(4, 5);
        Point point4 = new Point(point3);
        assertEquals(4, point4.getX(), 0.01);
        assertEquals(5, point4.getY(), 0.01);
        point3.move(2,3);
        assertEquals(4, point4.getX(), 0.01);
        assertEquals(5, point4.getY(), 0.01);
    }

    @Test
    public void WrongCreateParametersTest()
    {
        Point point1 = new Point(-1, 2);
        assertEquals(0, point1.getX(), 0.01);
        assertEquals(2, point1.getY(), 0.01);
        Point point2 = new Point(-1, -2);
        assertEquals(0, point2.getX(), 0.01);
        assertEquals(0, point2.getY(), 0.01);
        Point point3 = new Point(2, -2);
        assertEquals(2, point3.getX(), 0.01);
        assertEquals(0, point3.getY(), 0.01);

    }

    @Test
    public void MoveWrongDirectionTest()
    {
        Point point1 = new Point(2, 3);
        point1.move(4, -5);
        assertEquals(2, point1.getX(), 0.01);
        assertEquals(3, point1.getY(), 0.01);
        Point point2 = new Point(2, 3);
        point2.move(4, -2);
        assertEquals(6, point2.getX(), 0.01);
        assertEquals(1, point2.getY(), 0.01);

    }

    @Test
    public void setTest()
    {
        Point point1 = new Point(2, 3);
        point1.setX(3);
        assertEquals(3, point1.getX(), 0.01);
        assertEquals(3, point1.getY(), 0.01);
        point1.setY(4);
        assertEquals(3, point1.getX(), 0.01);
        assertEquals(4, point1.getY(), 0.01);
    }

    @Test
    public void toStringTest()
    {
        Point point1 = new Point(3.0, 4.0);
        assertEquals("(3.0,4.0)", point1.toString());
    }

    @Test
    public void equalTest()
    {
        Point point1 = new Point(2, 3);
        Point point2 = new Point(2, 3);
        assertEquals(true, point1.equals(point2));
        Point point3 = new Point(point2);
        assertEquals(true, point3.equals(point1));
    }

    @Test
    public void isAboveTest()
    {
        Point point1 = new Point(3, 4);
        Point point2 = new Point(2, 3);
        assertEquals(true, point1.isAbove(point2));
        Point point3 = new Point(6, 4);
        assertEquals(false, point1.isAbove(point3));
        Point point4 = new Point(2, 10);
        assertEquals(false, point1.isAbove(point4));
    }

    @Test
    public void isUnderTest()
    {
        Point point1 = new Point(3, 4);
        Point point2 = new Point(2, 3);
        assertEquals(false, point1.isUnder(point2));
        Point point3 = new Point(6, 4);
        assertEquals(false, point1.isUnder(point3));
        Point point4 = new Point(2, 10);
        assertEquals(true, point1.isUnder(point4));
    }

    @Test
    public void isLeftTest(){
        Point point1 = new Point(10, 50);
        Point point2 = new Point(20, 6);
        Point point3 = new Point(10, 6);
        assertEquals(true, point1.isLeft(point2));
        assertEquals(false, point1.isLeft(point1));
        assertEquals(false, point1.isRight(point3));
        assertEquals(false, point1.isLeft(point3));
        assertEquals(false, point3.isLeft(point3));
        assertEquals(false, point3.isLeft(point1));
        assertEquals(true, point2.isRight(point1));
        assertEquals(false, point2.isLeft(point2));
        assertEquals(false, point2.isLeft(point1));
        assertEquals(true, point2.isRight(point1));
        assertEquals(true, point2.isRight(point3));
        assertEquals(false, point2.isRight(point2));
    }

    @Test
    public void distanceTest(){
        Point point1 = new Point(2, 5);
        Point point2 = new Point(2, 5);
        assertEquals(0, point1.distance(point2));
        point2.move(1, 1);
        assertEquals(Math.sqrt(2), point1.distance(point2), 0.01);
    }
}