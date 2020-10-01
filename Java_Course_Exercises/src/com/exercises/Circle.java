package com.exercises;

import java.util.Scanner;
public class Circle
{
    public static void main (String [] args)
    {
        Do();

    } // end of method main

    public static void Do() {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculates the areas " +
                "and the perimeters of the excircle and the incircle " +
                "of a given rectangle \n");
        System.out.print ("Please enter the two coordinates of the " +
                "left-upper point of the rectangle\n");
        int leftUpX = scan.nextInt();
        int leftUpY = scan.nextInt();

        System.out.print ("Please enter the two coordinates of the " +
                "right-lower point of the rectangle");
        int rightDownX = scan.nextInt();
        int rightDownY = scan.nextInt();

        // Computing the other corners of the rectangle
        int leftDownX = leftUpX;
        int leftDownY = rightDownY;

        int rightUpX = rightDownX;
        int rightUpY = leftUpY;

        // The Incircle variables
        // Computing the radius of the inner circle by counting on the fact
        // that the width of the rect is greater than the height, so we know
        // for sure that the radius will be half of the height, so:
        // R = height / 2
        // height = leftupY - leftdownY
        final float inRadius = (leftUpY - leftDownY) / 2;
        // Computing the area by formula:
        // A = PI * R^^2
        final double inArea = Math.PI * Math.pow(inRadius, 2);
        // Computing the perimeter similarly:
        // P = 2 * PI * R
        final double inPerimeter = 2 * Math.PI * inRadius;

        System.out.println("Incircle: radius = " + inRadius + ", area = " + inArea +", perimeter = " + inPerimeter);

        // The Excircle variables:
        //
        //
        // Computing the radius of the external circle by using triangles formula:
        // formula -> 2R = AB/sin y
        // AB = distance between left-up and right-down corners
        // sin y = sin (90) = 1
        // so R == AB / 2
        double diagonalLength = Math.sqrt(Math.pow(leftUpX - rightDownX, 2) +
                Math.pow(leftUpY - rightDownY, 2));
        final double exRadius = diagonalLength / 2;
        // Computing the area by formula:
        // A = PI * R^^2
        final double exArea = Math.PI * Math.pow(exRadius, 2);
        // Computing the perimeter similarly:
        // P = 2 * PI * R
        final double exPerimeter = 2 * Math.PI * exRadius;

        System.out.println("Excircle: radius = " + exRadius + ", area = " + exArea +", perimeter = " + exPerimeter);
    }
} //end of class Circle
