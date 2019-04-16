
public class SoccerBall{
   Data data;
   int[][] elementHexagone;
   int[] nbElementsHexagone;
   int[][] elementPentagone;
   int[] nbElementsPentagone;
   private Node listHexagone;
   private Node listPentagone;
   private Puzzle[] puzzle;
   Shape shape;

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

   private void findSolution(int position){
      if(position == 32)
         return;
      Shape newShape = findShape(puzzle.isHexagone);
      if(newShape == null){
         puzzle.shape.isUsed = false;
         puzzle.shape.rotate();
         puzzle.facets.clearConnection(puzzle.shape.concavity);
         puzzle.shape = null;
         findSolution( position-1);
      }
      if(puzzle.threatens() == true)
         findSolution( position+1);
      else

   }

   public void main(){

      for (int i = 0; i < 14; i++) {
         if(i < 10){
         elementHexagone[i] = data.ELEMENTS_SIDES[i];
         nbElementsHexagone[i] = data.NB_ELEMENTS[i];
         }
         else{
            elementPentagone[i] = data.ELEMENTS_SIDES[i];
            nbElementsPentagone[i] = data.NB_ELEMENTS[i];
         }
      }

      listHexagone = setStock(elementHexagone,nbElementsHexagone);
      listPentagone = setStock(elementPentagone,nbElementsPentagone);

      for (int i = 1; i <= 32; i++) {
         puzzle[i] = new Puzzle(i, data.CONNECTIONS(i));
      }

      
   }

}
