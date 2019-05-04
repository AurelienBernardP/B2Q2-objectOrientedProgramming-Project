/*
 * SoccerBall solves the soccer ball puzzle based on given data.
 * The nbNetPieces integer represents the number of pieces of the net. 
 * stock contains all the unused and used elements for this puzzle.
 * data contains all the elements necessary to solve the puzzle.
 * puzzle[] is an array which represents each piece of the net.
 */
public class SoccerBall{
   private static int nbNetPieces;
   private static Stock stock;
   private static Data data;
   private static Net[] puzzle;

   /*
   *  boolean findSolution(int position) is the main algorithm
   *  which solves the puzzle based on given data.
   *  position represents the unique number of the current piece
   *  of the net which will be filled by an element.
   */
   private static boolean findSolution(int position){
      //If all positions of the net can not be filled
      if(position < 0){
         System.out.print("No solution is possible.\n");
         return false;
      }

      //If the last position of the net is reached
      if(position == nbNetPieces)
         return true;


      //The type of the current piece of the net.
      boolean isHexagone = puzzle[position].isHexagone();

      //Get an unused element from the stock.
      Shape element = stock.getUnused(isHexagone);

      //If any unused element can not fit in the current 
      //position of the net then the previous position is cleared.
      if(element == null){
         puzzle[position-1].clearPosition();
         stock.undoUsedElement(puzzle[position - 1].isHexagone());
         return findSolution(position - 1);
      }

      //If the element can fit in the current position of the net
      //then it is set in.
      if(puzzle[position].threatens(element) == true){
         stock.usedCurrentElement(isHexagone);
         return findSolution(position + 1);
      }
      //Else the next type of element is tried to fit in that position.
      else{
         stock.getNextElement(isHexagone);
         return findSolution(position);
      }
   }

   public static void main(String[] args){
      //Fetch the given data for this puzzle.
      data = new Data();
      nbNetPieces = data.CONNECTIONS.length;
      if(data.ELEMENTS_SIDES.length != data.NB_ELEMENTS.length){
         System.out.print("Error in the given data.\n");
         return;
      }

      //Initialise stocks which contain the unused pieces
      stock = new Stock(data.ELEMENTS_SIDES, data.NB_ELEMENTS);

      //Initialise the map of the soccer ball puzzle
      puzzle = new Net[nbNetPieces];
      for (int i = 0; i < nbNetPieces; i++)
         puzzle[i] =  new Net(i+1, data.CONNECTIONS[i]);

      //Finds solutions for the puzzle based on the given data
      if(findSolution(0) == false)
         return;

      //Displays the solution
      for (int i = 0; i < nbNetPieces; i++)
         puzzle[i].displayNet();

      return;
   }

}
