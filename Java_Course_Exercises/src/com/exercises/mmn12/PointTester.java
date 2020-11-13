package com.exercises.mmn12;

/**
 * Compilation (only !) tester for Maman 12, 20441, 2021a
 * 
 * @author Course staff 
 * @version 2_1
 */
public class PointTester
{
    public static void main (String [] args)
    {
        System.out.println("============ Testing class Point =============");
        Point p1 = new Point(3.0, 4.0);
        Point p2 = new Point(p1);
        double d1 = p1.getX();
        d1 = p1.getY();
        if (!p1.equals(p2))
            System.out.println("Check your equals method in class Point");
        p1.setX(0.0);
        p1.setY(0.0);
        boolean b1 = p1.isAbove(p2);
        b1 = p1.isUnder(p2);
        b1 = p1.isLeft(p2);
        b1 = p1.isRight(p2);
        
        d1 = p1.distance(p2);
        p1.move(1.0, 2.0);
        
        if (p1.toString().indexOf('@') != -1)
            System.out.println("Check your toString method in class Point");
        System.out.println("============ Testing class Point Done =============");        
    }
}

