/*
 * Shape is the representation of a shape.
 * The type integer is the number of the element that this shape represents.
 * The rotation integer indicates if the number of rotations done to the shape.
 * The concavity array is an array of -1, 0, or 1 representing the concavity of 
 * of the shape.
 */
public class Shape
{
	private final int type;
	private int rotation;
	private int[] concavity;

	public Shape(int[] concavity, int type){
		this.concavity = concavity.clone();
		this.type = type;
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

	/* boolean rotate() returns true if the shape can be rotated
	* and false if the rotation will transform the shape into its
	* initial state.
	*/
	public boolean rotate(){
		int nbEdges = concavity.length;

		//if the rotation will transform the shape into its
		// initial state.
		if(rotation == nbEdges-1)
			return false;

		//Anticlockwise rotation of the shape
		int tmp = concavity[nbEdges-1];
		for (int i = nbEdges-1; i > 0; i--)
			concavity[i] = concavity[i-1];

		concavity[0] = tmp;
		rotation += 1;
		
		return true;
	}

	/* void resetRotation() resets the shape to its initial states
	*/
	public void resetRotation(){
		int nbEdges = concavity.length;
		rotation = (rotation % nbEdges);
		if(rotation != (nbEdges-1)){
			rotation += 1;
			resetRotation();
		}

		//Anticlockwise rotation of the shape.
		int tmp = concavity[nbEdges-1];
		for (int i = nbEdges-1; i > 0; i--)
			concavity[i] = concavity[i-1];

		concavity[0] = tmp;
		rotation = 0;

	}

}
