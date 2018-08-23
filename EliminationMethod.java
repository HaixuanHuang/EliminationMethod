package eliminationmethod;
import java.util.Scanner;

/**
 * EliminationMethod
 *
 * This program can read a n*n linear equation system and print its solutions.
 * If the equation system has no solution or has infinitely many solutions,
 * the program will print that information.
 * 
 * @author, Haixuan Huang, huan1291@purdue.edu
 *
 * @version, 08/23/2018, West Lafayette, IN, Purdue University
 *
*/

public class EliminationMethod 
{
    public static void main(String[] args) 
    {
        int numOfUnknowns = 0;
        Scanner scan = new Scanner(System.in);
        
        // read the number of unknowns
        System.out.print("Please enter the number of unknowns: ");
        numOfUnknowns = scan.nextInt();
        double[][] equationSystem = new double[numOfUnknowns][numOfUnknowns+1];
        
        // read the equations
        System.out.println("Please enter the augmented-matrix representation for your equation system: ");
        for(int row = 0; row<equationSystem.length; row++)
        {
            for(int col = 0; col<equationSystem[row].length; col++)
            {
                equationSystem[row][col] = scan.nextDouble();
            }
        }
        
        AugmentedMatrix augMatrix = new AugmentedMatrix(equationSystem);
        GaussianElimination elimination = new GaussianElimination(augMatrix);
        
        //print solutions
        System.out.println("Here are solutions: ");
        elimination.GaussianEliminate();
    }
}
