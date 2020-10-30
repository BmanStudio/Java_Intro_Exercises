/**
 * Class Temperature converts degrees between the measurement unit Celsius, Fahrenheit and Kelvin (as C / F / K respectively)
 * by asking the user to input an unit type and degrees of that unit.
 * The class then outputs the converted degress in each of the three types.
 * @author (Ori Ben Nun)
 * Last edited: 30/10/2020 by Ori
 */
import java.util.Scanner;

public class Temperature
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner (System.in);

        System.out.println("This program taking a degrees measurement unit as C / F / K, along with"
                + "amount of degrees of that unit, and converts that to each other unit.");

        // Collecting the user's input for the type
        System.out.println("Please insert a unit type (C / F / K as one char) and number of degrees (float) to be converted");
        String word = scan.next();
        char inputType = word.charAt(0);

        // Collecting the user's input for the amount of degrees
        float inputDegrees = scan.nextFloat();

        // Constants:
        final float F_TO_C_RATIO = 5.0f / 9; // the ratio when converting F to C units.
        final float C_K_TO_F_RATIO = 9.0f / 5; // actually (1 / F_TO_C_RATIO). This ratio is also being used when converting K to F.
        final float F_CONST = 32.0f; // the difference when converting from / to F units.
        final float K_CONST = 273.15f; // the difference when converting from / to K units.

        // Declaring the variables which being overwritten inside the switch statement
        float inputInCel = 0, inputInFar = 0, inputInKel = 0;

        // Below are the logic and the arithmetics, being done in order to complete the conversion.
        // The conversion equations was taken from the Maman11 table, and was completed using basic manipulations
        // to represent the ratios using the same constants
        switch (inputType){
            case 'C':
                inputInCel = inputDegrees;
                inputInFar = (C_K_TO_F_RATIO * inputDegrees) + F_CONST;
                inputInKel = inputDegrees + K_CONST;
                break;
            case 'F':
                inputInCel = F_TO_C_RATIO * (inputDegrees - F_CONST);
                inputInFar = inputDegrees;
                inputInKel = (F_TO_C_RATIO * inputDegrees) + K_CONST - (F_TO_C_RATIO * F_CONST);
                break;
            case 'K':
                inputInCel = inputDegrees - K_CONST;
                inputInFar = C_K_TO_F_RATIO * (inputDegrees - K_CONST) + F_CONST;
                inputInKel = inputDegrees;
        }
        
        // Outputs the results:
        System.out.println(inputInCel + " " + 'C');
        System.out.println(inputInFar + " " + 'F');
        System.out.println(inputInKel + " " + 'K');
    } // end of method main
} // end of class Temperature
