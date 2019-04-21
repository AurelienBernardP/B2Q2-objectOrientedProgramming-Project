/*
 * Net is the representation of a piece of the net.
 * The position is a unique number representing a piece of the net.
 * The isHexagone boolean indicates if a piece of the net expects a 
 * hexagone or a pentagone.
 * shape corresponds to the shape that a piece of the net contains.
 * facets corresponds to the sides of a piece of the net.
 */
public class Net{
   private final int position;
   private final boolean isHexagone;
   private Shape shape;
   private Facet facets;

   /*
   * Net(int i, int[] connections) initialise a piece of the net
   * based on the input.
   */
   public Net(int position, int[] connections){

      //The unique number representing the position of the piece.
      this.position = position;

      //The connections of the piece in the net.
      facets = new Facet(connections);

      if(connections.length == 6)
         isHexagone = true;
      else
         isHexagone = false;
   }


   /*
   * boolean threatens(Shape newShape) returns true if the shape
   * "newShape" can fit in the net. Otherwise, it returns false.
   */
   public boolean threatens(Shape newShape){

      //If the element is compatible with the current piece of the net
      if(facets.isCompatible(newShape.getConcavity()) == true){
         this.shape = newShape;
         return true;
      }

      //If the element can be rotated
      if(newShape.rotate() == true)
         return threatens(newShape);

      newShape.resetRotation();
      return false;
   }

   public boolean isHexagone(){
      return isHexagone;
   }

   /*
   * void clearPosition() takes the element off the net.
   */
   public void clearPosition(){
      facets.clearConnections(shape.getConcavity());
      shape = null;
   }

   public void displayNet(){
      System.out.print("Position "+ position+ " - ");
      System.out.print("Element "+ shape.getType()+ " - ");
      System.out.print("Orientation "+ shape.getRotation()+ "\n");
   }

}
