package eliminationmethod;

public class AugmentedMatrix
{
    private double[][] augMatrix; 
    private final double[][] originMatrix; // reserve the origin augmented matrix
    private boolean singular;	//initialValue=false;
    private boolean upperTriangle; //initialValue=false;
	
    // constructor
    public AugmentedMatrix(double[][] aMat)
    {
    	augMatrix = aMat;
    	originMatrix = aMat;
    	singular = false;
    	upperTriangle = false;
    }

    //	exchange rowA and rowB
    // edit augMatrix
    public void exchangeRow(int rowA, int rowB)
    {
    	double[][] newMat = new double[augMatrix.length][augMatrix[0].length];
    	for(int row=0; row<newMat.length; row++)
    	{
            for(int col=0; col<newMat[row].length; col++)
            {
		if(row == rowA)
                    newMat[row][col]=augMatrix[rowB][col];
		else if(row==rowB)
                    newMat[row][col]=augMatrix[rowA][col];
		else
                    newMat[row][col]=augMatrix[row][col];
            }
	}
	augMatrix = newMat;
    }
	
    //	singular = true
    public void setSingular()
    {
	singular = true;
    }

    // return singular
    public boolean isSingular()
    {
	return singular;
    }
	
    //set upperTriangle = true
    public void setUpperTriangular()
    {
    	upperTriangle = true;
    }
	
    // return upperTriangle
    public boolean isUpperTriangular()
    {
    	return upperTriangle;
    }
    
    //	multiplier times rowA is substracted from rowB
    //	edit augMatrix
    public void rowSubstraction(double multiplier, int rowA, int rowB)
    {
    	for(int col = 0; col<augMatrix[0].length; col++)
	{
            augMatrix[rowB][col] -= multiplier*augMatrix[rowA][col];
	}
    }
	
    //	return augMatrix
    public double[][] getAugmentedMatrix()
    {
    	return augMatrix;
    }
    
}
