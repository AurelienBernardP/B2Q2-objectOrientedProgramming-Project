
public class SoccerBall{

   private Node setStock(int[][] elements, int[] nbElements){
      Shape shape = null;
      Node list = null;
      int length = length(elements);
      
      for (int i = 0; i < (length-1); i++){
         for (int j = 0; j < nbElements[i]; j++) {
            shape = new Shape(elements[i]);
            list = new Node(shape);
            list = list.getNext();
         }
      }
      return list;
   }



}
