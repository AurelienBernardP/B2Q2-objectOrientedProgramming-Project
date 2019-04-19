
public class Shape
{
	private int[] concavity;
	private final int type;
	private int rotation;

	public Shape(int[] concavity, int type){
		this.concavity = concavity.clone();
		this.type = type;
		rotation = 0;//nécessaire ? quand on init, on met à 0
	}

	public int getType(){
		return type;
	}
	
	public int[] getConcavity(){
		return concavity;
	}

	public int getRotation(){
		return rotation;
	}

   public void printMatrix(int[] matrix){
      for (int i = 0; i < matrix.length; i++) {
             System.out.print(matrix[i] + " ");
     }
     System.out.println();
   }

	//Problemes avec rotation:
	//Ne pas oublier de reset rotation à 0
	//quand on cherche s'il rentre
	//S'il rentre pas, rotation à l'état init
	public boolean rotate(){
		//System.out.print("Before rotation : ");
		//printMatrix(concavity);
		int nbEdges = concavity.length;
		if(rotation == nbEdges-1)
			return false;

		int tmp = concavity[nbEdges-1];
		for (int i = nbEdges-1; i > 0; i--)
			concavity[i] = concavity[i-1];

		concavity[0] = tmp;
		rotation += 1;
		
		//System.out.print("After rotation : ");
		//printMatrix(concavity);
		return true;
	}

	public void resetRotation(){
		int nbEdges = concavity.length;
		rotation = 0;
		int tmp = concavity[nbEdges-1];
		for (int i = nbEdges-1; i > 0; i--)
			concavity[i] = concavity[i-1];

		concavity[0] = tmp;
	}

}
