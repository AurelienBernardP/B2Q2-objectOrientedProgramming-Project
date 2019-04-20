
public class Puzzle{
   private final int position;
   private final boolean isHexagone;
   private Shape shape;
   private Facet facets;

   public void printMatrix(int[] matrix){
      for (int i = 0; i < matrix.length; i++) {
             System.out.print(matrix[i] + " ");
     }
   }

   public boolean isHexagone(){
      return isHexagone;
   }


   public void displayPuzzle(){
      System.out.print("Position "+ position+ " - ");
      System.out.print("Element "+ shape.getType()+ " - ");
      System.out.print("Orientation "+ shape.getRotation()+ "\n");
   }

   public Puzzle(int i, int[] connections){
      position = i;
      facets = new Facet(connections);
      shape = null;
      if(connections.length == 6)
         isHexagone = true;
      else
         isHexagone = false;
   }

   public boolean threatens(Shape newShape){
      //à voir pour l'input de isCompatible
      if(facets.isCompatible(newShape.getConcavity()) == true){
         this.shape = newShape;
         return true;
      }
      if(newShape.rotate() == true)
         return threatens(newShape);//newShape a été rotationné

      newShape.resetRotation();
      return false;
   }

   public void clearPosition(){
      facets.clearConnections(shape.getConcavity());
      shape = null;
   }


}
