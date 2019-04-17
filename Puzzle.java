
public class Puzzle{
   private final int position;
   private final boolean isHexagone;
   Shape shape;
   Facet facets;

   public Puzzle(int i, int[] connections){
      position = i;
      facets = Facet(connections);
      shape = null;
      if(length(connections) == 6)
         isHexagone = true;
      else
         isHexagone = false;
   }

   public boolean threatens(Shape newShape){
      //à voir pour l'input de isCompatible
      if(facets.isCompatible(newShape.concavity) == true){
         this.shape = newShape;
         return true;
      }
      if(newShape.rotate() == true)
         return threatens(newShape);//newShape a été rotationné

      return false;
   }

   public void clearPosition(){
      facets.clearConnection(shape.concavity);
      shape = null;
   }

   //newShape
}
