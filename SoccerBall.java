
public class SoccerBall{
   private Data data;
   Stock stock;
   private Puzzle[] puzzle;

   private void findSolution(int position){
      if(position < 0){
         //PRINT ERROR
         return;
      }

      if(position == 32)
         return;

      
      Shape shape = stock.getUnused(puzzle[position].isHexagone);
      if(shape == null){
         puzzle.shape.rotate();//à faire dans stock peut etre
         puzzle[position].clearPosition();
         findSolution(position-1);
      }

      if(puzzle.threatens(shape) == true){
         stock.usedCurrentElement(puzzle[position].isHexagone);
         findSolution(position + 1);
      }
      else{
         stock.getNextElement();
         findSolution(position);
      }
   }

   public void main(){
      stock = new Stock(data.ELEMENTS_SIDES, data.NB_ELEMENTS)

      for (int i = 1; i <= 32; i++) {
         puzzle[i-1] = new Puzzle(i, data.CONNECTIONS[i]);
      }

      findSolution(0);
   }

}
