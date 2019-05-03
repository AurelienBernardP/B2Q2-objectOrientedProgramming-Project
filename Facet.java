/*
 * Facet is the representation of the connections between pieces of the net.
 * The connections array is an array of integers denoting adjacency with other facets
 * between pieces of the net.
 * The concavityFacets array is an array of -1, 0, or 1 representing the concavity of 
 * all the facets of the puzzle.
 *
 */
public class Facet{
   private static int nbEdges;
   private static int sizeConcavityFacets = 100;
   private final int multiplicativeFactor = 5;
   private final int[] connections;
   private static int[] concavityFacets = new int [sizeConcavityFacets];

   /**
   * Facet(int[] connections) is going to initialise the "connections" array.
   */
   public Facet(int[] connections){
      this.connections = connections;
      this.nbEdges += connections.length;

      //If the number of edges are greater than expected, reallocation is done
      if(nbEdges > sizeConcavityFacets){
         sizeConcavityFacets *= multiplicativeFactor;
         concavityFacets = new int [sizeConcavityFacets];
      }
   }

   /*
   * boolean isCompatible(int[] concavityShape) verifies if the given concavity
   * of a shape "concacivtyShape" is compatible with the puzzle.
   */
   public boolean isCompatible(int[] concavityShape){
      int nbEdges;
      int indexSide;

      nbEdges = connections.length;
      if(nbEdges != concavityShape.length){
         System.out.print("Error isCompatible: Size of shape not correct.\n");
         return false;
      }

      //Verifies if the shape can fit in the puzzle
      for (int i = 0; i < nbEdges; i++){
         indexSide = connections[i] - 1;

         //If the facet is empty or if the concavity of the shape matches with
         //the puzzle.
         if(concavityFacets[indexSide] == 0 ||
            ((concavityFacets[indexSide] + concavityShape[i]) == 0 ))
            concavityFacets[indexSide] += concavityShape[i];
         else{

            //Deleting all effects done to the puzzle
            for (int j = 0; j < i; j++)
               concavityFacets[connections[j]-1] -= concavityShape[j];
            return false;
         }
      }
      return true;
   }

   /*
   * void clearConnections(int[] concavityShape) deletes all effects done to the puzzle
   * from the concavity of the shape "concavityShape".
   */
   public void clearConnections(int[] concavityShape){
      int nbEdges = connections.length;

      for (int i = 0; i < nbEdges; i++)
         concavityFacets[connections[i]-1] -= concavityShape[i];
   }

}
