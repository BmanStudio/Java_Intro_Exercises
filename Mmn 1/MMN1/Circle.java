/**
 * This class's single method (main) takes an input from the user to represent a rectangle
 * and calculating the radius, areas and the perimeters of the excircle and the incircle
 * of the given rectangle
 * @author (Ori Ben Nun)
 * last edited: 30/10/2020 by Ori
 */
import java.util.Scanner;

public class Circle
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println ("This program calculates the areas " +
                "and the perimeters of the excircle and the incircle " +
                "of a given rectangle");
                
        System.out.println ("Please enter the two coordinates of the " +
                "left-upper point of the rectangle");
        int leftUpX = scan.nextInt();
        int leftUpY = scan.nextInt();

        System.out.println ("Please enter the two coordinates of the " +
                "right-lower point of the rectangle");
        int rightDownX = scan.nextInt();
        int rightDownY = scan.nextInt();

        // The Incircle variables:
        // Computing the radius of the inner circle
        // which will be half of the rectangle height, so:
        // R = height / 2
        double inRadius = (leftUpY - rightDownY) / 2.0;

        // A = PI * R^^2
        double inArea = Math.PI * Math.pow(inRadius, 2);

        // P = 2 * PI * R
        double inPerimeter = 2 * Math.PI * inRadius;
        
        System.out.println("Incircle: radius = " + inRadius + ", area = " + inArea +", perimeter = " + inPerimeter);

        // The Excircle variables:
        // Computing the radius of the external circle by using triangles formula:
        // formula -> 2R = AB/sin y
        // AB = distance between left-up and right-down corners
        // sin y = sin (90) = 1
        // so R == AB / 2
        double diagonalLength = Math.sqrt(
                Math.pow(leftUpX - rightDownX, 2) + Math.pow(leftUpY - rightDownY, 2)
        );

        double exRadius = diagonalLength / 2;

        // Computing the area by formula:
        // A = PI * R^^2
        double exArea = Math.PI * Math.pow(exRadius, 2);

        // Computing the perimeter similarly:
        // P = 2 * PI * R
        double exPerimeter = 2 * Math.PI * exRadius;

        System.out.println("Excircle: radius = " + exRadius + ", area = " + exArea +", perimeter = " + exPerimeter);
    } // end of method main
} //end of class Circle