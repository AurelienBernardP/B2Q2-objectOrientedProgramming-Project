
public class SoccerBall{
   private static int nbPosition;
   private static Stock stock;
   private static Puzzle[] puzzle = new Puzzle[32];


   private static void findSolution(int position){
      if(position < 0){
         System.out.print("No solution possible.\n");
         return;
      }

      if(position == nbPosition)
         return;


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

   public static void main(String[] args){
      Data data = new Data();
      stock = new Stock(data.ELEMENTS_SIDES, data.NB_ELEMENTS);
      nbPosition = data.CONNECTIONS.length;

      for (int i = 0; i < nbPosition; i++)
         puzzle[i] =  new Puzzle(i+1, data.CONNECTIONS[i]);

      findSolution(0);

      for (int i = 0; i < nbPosition; i++)
         puzzle[i].displayPuzzle();

      return;
   }

}
