
public class Shape
{
	private final int nbEdges;
	private final int[] concavity;
	private final int type;
	private int rotation;

	public Shape(int[] concavity, int type){
		nbEdges = length(concavity);
		this.concavity = concavity;
		this.type = type;
		rotation = 0;//nécessaire ? quand on init, on met à 0
	}

	public int getType(){
		return type;
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
