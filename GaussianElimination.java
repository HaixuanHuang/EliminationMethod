package eliminationmethod;

/**
 * GaussianElimination
 *
 * This class can store an AugmentedMatrix variable,
 * perform Gaussian Elimination on that,
 * and print the result.
 * 
 * @author, Haixuan Huang, huan1291@purdue.edu
 *
 * @version, 08/23/2018, West Lafayette, IN, Purdue University
 *
*/

public class GaussianElimination
{
    private AugmentedMatrix mat;

    //	constructor
    public GaussianElimination(AugmentedMatrix aMat)
    {
    	mat = aMat;
    }
    
    //	excecute the Gaussian Elimination
    public void GaussianEliminate()
    {
        double[][] aMat = mat.getAugmentedMatrix();
    	for(int col=0; col<aMat[0].length-1; col++)
	{
            if(!mat.isSingular())
		eliminate(mat,col);
            else
		break;
	}
	if(!mat.isSingular())
            mat.setUpperTriangular();
	else
	{
            System.out.println("The coefficient matrix is singular.");
            System.out.println("The linear equation system has no solution or has infinitely many solutions.");
	}
	if(mat.isUpperTriangular())
	{
            double[] solutions = backSubstitution(mat);
            for(int i=0; i<solutions.length; i++)
            {
                System.out.println("x" + (i+1) +" = " + solutions[i]);
            }
	}
    }
	
    // return true if all components are zero in the first column of the left matrix
    private boolean failToFindPivot(AugmentedMatrix aMat, int numColToEliminate)
    {
	double[][] mat = aMat.getAugmentedMatrix();
	for(int row = numColToEliminate; row<mat.length; row++)
	{
            if(mat[row][numColToEliminate] != 0)
		return false;
	}
	return true;
    }
	
    // exchange the row with largest-absolute-value pivot to the row numColToEliminate
    // intending to avoid overflow
    private int rowToBeExchanged(AugmentedMatrix aMat, int numColToEliminate)
    {
	double[][] mat = aMat.getAugmentedMatrix();
	int maxIndex = numColToEliminate;
	double maxEntry = mat[numColToEliminate][numColToEliminate];
	for(int row = numColToEliminate+1; row < mat.length; row++)
	{
            if(Math.abs(mat[row][numColToEliminate])>Math.abs(maxEntry))
            {
		maxIndex = row;
		maxEntry = mat[row][numColToEliminate];
            }
	}
	return maxIndex;
    }
	
    // get pivot after row exchange
    private double getPivot(AugmentedMatrix aMat, int numColToEliminate)
    {
	double[][] mat = aMat.getAugmentedMatrix();
	return mat[numColToEliminate][numColToEliminate];
    }
	
    //	return multiplier on row numColToEliminate
    // multiplier = first component of the row to be eliminated / pivot
    private double getMultiplier(AugmentedMatrix aMat, int numColToEliminate, int numRowToBeEliminated)
    {
	double[][] mat = aMat.getAugmentedMatrix();
	return mat[numRowToBeEliminated][numColToEliminate] / getPivot(aMat, numColToEliminate);		
    }
	
    // eliminate the numColToEliminate-th unknown of aMat
    // if we failed to find a pivot for this unknown then set aMat as singular
    private void eliminate(AugmentedMatrix aMat, int numColToEliminate)
    {
	if(!failToFindPivot(aMat, numColToEliminate)) // if we found a pivot
	{
            // put the row with the pivot with the largest absolute value to the row numColToEliminate
            aMat.exchangeRow(numColToEliminate, rowToBeExchanged(aMat, numColToEliminate)); 
            for(int row=numColToEliminate+1; row<aMat.getAugmentedMatrix().length; row++)
            {
            	aMat.rowSubstraction(getMultiplier(aMat, numColToEliminate, row), numColToEliminate, row);
            }
	}
	else
            aMat.setSingular();
    }
	
    // precondition: the upperTrigMat is upperTriangular
    //		upperTrigMat.isUpperTriangular() == true;
    // calculate the solution vector
    // return the solution array
    private double[] backSubstitution(AugmentedMatrix upperTrigMat)
    {
    	double[] solution = new double[upperTrigMat.getAugmentedMatrix().length];
	double[][] upperTriangleMatrix = upperTrigMat.getAugmentedMatrix();
	
	double denominator = 0;
	double subtrahend = 0;
	double minuend = 0;
	double numerator =  0;
	
	for(int index = solution.length-1; index>=0; index--)
	{
            denominator = upperTriangleMatrix[index][index]; // calculate the denominator for the formula for solution
            subtrahend = 0;
            // calculate the subtrahend of the numerator for the formula for solution
            for(int j = index+1; j<upperTriangleMatrix[index].length-1; j++)
            {
		subtrahend += upperTriangleMatrix[index][j] * solution[j];
            }
            minuend = upperTriangleMatrix[index][upperTriangleMatrix[index].length-1];
            numerator =  minuend - subtrahend;
            // calculate the solution
            solution[index] = numerator / denominator;				
	}
            return solution;
    }

}