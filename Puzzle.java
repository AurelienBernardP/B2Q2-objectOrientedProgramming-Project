
public class Puzzle{
   private final int position;
   Shape shape;
   Facet facets;
   Puzzle neighbor;//nécessaire ?

   public boolean threatens(Shape newShape){
      //à voir pour l'input de isCompatible
      if(facets.isCompatible(newShape.concavity) == true){
         newShape.isUsed = true;
         this.shape = newShape;
         return true;
      }
      if(newShape.rotate())
         return threatens(newShape);//newShape a été rotationné

      return false;
   }

   //newShape
}
