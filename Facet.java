
public class Facet{
   private final int[] connectionsFacets;
   private static int[] concavityFacets = new int [90];

   public Facet(int[] connections){
      connectionsFacets = connections;
   }


   public void printMatrix(int[] matrix){
      for (int i = 0; i < matrix.length; i++) {
             System.out.print(matrix[i] + " ");
             if(i == 19 || i == 39 || i == 59 || i == 79)
              System.out.println();
     }
     System.out.println();
   }

   //Ca change un truc que ce soit indépendant de Shape ou je peuxw
   //utiliser une variable shape en entrée?
   public boolean isCompatible(int[] concavityNewShape){
      int length = connectionsFacets.length;
      int indexFacet;

      for (int i = 0; i < length; i++){
         indexFacet = connectionsFacets[i] - 1;
         //System.out.print("Facet soccer:"+concavityFacets[indexFacet]+ " and shape: " + concavityNewShape[i] + "\n");
         if(concavityFacets[indexFacet] == 0 ||
            ((concavityFacets[indexFacet] + concavityNewShape[i]) == 0 ))
            concavityFacets[indexFacet] += concavityNewShape[i];
         else{
            //System.out.print("Error facet for: "+indexFacet+ "+ 1 \n");
            for (int j = 0; j < i; j++){
               indexFacet = connectionsFacets[j] - 1;
               concavityFacets[indexFacet] -= concavityNewShape[j];
            }
            return false;
         }
      }
      printMatrix(concavityNewShape);
      printMatrix(concavityFacets);
      return true;
   }

   public void clearConnections(int[] concavityShape){
      int length = connectionsFacets.length;

      for (int i = 0; i < length; i++)
         concavityFacets[connectionsFacets[i]-1] -= concavityShape[i];
   }

}
