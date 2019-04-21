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
   private static Net[] puzzle = new Net[32];

   /*
   *  boolean verificationData(Data data) does some verifications
   * on the given data in order to ensure a possible solution for 
   * the puzzle.
   */
   private static boolean verificationData(Data data){

      //The number of positions of the puzzle.
      nbNetPieces = data.CONNECTIONS.length;
      if(nbNetPieces != 32){
         System.out.print("Not expected number of connections!\n");
         return false;
      }

      //The length of the arrays representing the available pieces.
      if(data.ELEMENTS_SIDES.length != data.NB_ELEMENTS.length){
         System.out.print("Error size of ELEMENTS_SIDES and NB_ELEMENTS \n");
         return false;
      }

      //The sum of the array NB_ELEMENTS must have the same number
      //of positions in the puzzle.
      int nbPieces = 0;
      for (int i = 0; i < data.NB_ELEMENTS.length; i++)
         nbPieces += data.NB_ELEMENTS[i];
      if(nbPieces != nbNetPieces){
         System.out.print("Not enough elements to solve the puzzle!\n");
         return false;
      }

      //Verifies the number of given hexagones
      int nbHexagones = 0;
      for (int i = 0; i < 10; i++)
         nbHexagones += data.NB_ELEMENTS[i];
      if(nbHexagones != 20){
         System.out.print("Not enough hexagones to solve the puzzle!\n");
         return false;
      }

      //Verifies the number of given pentagones
      int nbPentagones = 0;
      for (int i = 10; i < 14; i++)
         nbPentagones += data.NB_ELEMENTS[i];
      if(nbPentagones != 12){
         System.out.print("Not enough pentagones to solve the puzzle!\n");
         return false;
      }

      return true;
   }

   /*
   *  boolean findSolution(int position) is the main algorithm
   *  which solves the puzzle basaed on given data.
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
      //Verification of the data
      data = new Data();
      if(verificationData(data) == false)
         return;

      //Initialise stocks which contain the unused pieces
      stock = new Stock(data.ELEMENTS_SIDES, data.NB_ELEMENTS);

      //Initialise the map of the soccer ball puzzle
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
