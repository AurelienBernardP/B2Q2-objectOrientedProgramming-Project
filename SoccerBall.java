
public class SoccerBall{
   private static Puzzle[] puzzle = new Puzzle[32];
   private static Stock stock;
   private static int tmp = 32;

   private static void findSolution(int position){
      if(position < 0){
         System.out.print("No solution possible.\n");
         return;
      }

      if(position == tmp){
         return;
      }

      System.out.print("For position: "+ position+ "\n");
      boolean isHexagone = puzzle[position].isHexagone();
      Shape shape = stock.getUnused(isHexagone);
      if(shape == null){
         puzzle[position-1].clearPosition();
         stock.undoUsedElement(puzzle[position-1].isHexagone());
         findSolution(position-1);
         return;
      }

      if(puzzle[position].threatens(shape) == true){
         
         stock.usedCurrentElement(isHexagone);
         findSolution(position + 1);
      }
      else{
         stock.getNextElement(isHexagone);
         findSolution(position);
      }
   }

public static void printMatrix(int[] matrix){
   for (int i = 0; i < matrix.length; i++) {
          System.out.print(matrix[i] + " ");
      }
      System.out.println();
}

   public static void main(String[] args){
      Data data = new Data();
      Shape shape;
      stock = new Stock(data.ELEMENTS_SIDES, data.NB_ELEMENTS);

      /*for (int i = 0; i < 5; i++) {
         shape = stock.getUnused(true);
         stock.usedCurrentElement(true);
         stock.undoUsedElement(true);
      }*/
      for (int i = 0; i < 32; i++){
         puzzle[i] =  new Puzzle(i+1, data.CONNECTIONS[i]);
      }
      findSolution(0);
      System.out.print("Yas queen slaaaay\n");

      for (int i = 0; i < tmp; i++)
         puzzle[i].displayPuzzle();

      return;
   }

}
