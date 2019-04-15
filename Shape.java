
public class Shape
{
	private final int nbEdges;
	private final int[] concavity;
	private int rotation;
	private boolean isUsed;

	public Shape(int[] concavity){
		nbEdges = length(concavity);
		this.concavity = concavity;
		rotation = 0;//nécessaire ? quand on init, on met à 0
		isUsed = false;
	}
	
	public boolean isUsed(){
		return isUsed;
	}

	public boolean rotate(){
		if(rotation == nbEdges)
			return false;

		int tmp = concavity[nbEdges];
		for (int i = nbEdges-1; i < 0; i++)
			concavity[i] = concavity[i-1];

		concavity[0] = tmp;
		rotation += 1;
		return true;
	}

}
