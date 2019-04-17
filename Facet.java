
public class Facet{
   private final int[] connectionsFacets;
   private static int[90] concavityFacets;

   public Facet(int[] connections){
      connectionsFacets = connections;
   }

   //Ca change un truc que ce soit indépendant de Shape ou je peuxw
   //utiliser une variable shape en entrée?
   public boolean isCompatible(int[] concavityNewShape){
      int length = length(connectionsFacets);

      for (int i = 0; i < length; i++){
         if(concavityFacets[connectionsFacets[i]] == 0 ||
            ((concavityFacets[connectionsFacets[i]] + concavityNewShape[i]) == 0 )
            concavityFacets[connectionsFacets[i]] += concavityNewShape[i];
         else{
            for (int j = 0; j < i; j++)
               concavityFacets[connectionsFacets[j]] -= concavityNewShape[j];
            return false;
         }
      }
      return true;
   }

   public void clearConnections(int[] concavityShape){
      int length = length(concavityShape);

      for (int i = 0; i < length; i++)
         concavityFacets[connectionsFacets[i]] -= concavityNewShape[i];

   }

}
