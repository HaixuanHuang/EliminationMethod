package eliminationmethod;
import java.util.Scanner;

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
