

public class Stock{
   Node stockUsed;
   Node stockUnused;
   Node stockUnusedPenta;
   Node currentHexagone;
   Node currentPentagone;

   public Stock(int[][] elements, int[] nbElements){
      stockUsed = null;
      stockUsedPentagone = null;
      stockUnused = null;//inutile de = null ????
      Shape shape;
      Node nextNode;
      Node currentNode = stockUnused;
      int length = length(elements);

      if(length < 1)
         return;//PRINT ERROR

      //Setting the head of the linked list
      shape = new Shape(elements[0], 1);
      currentNode = new Node(shape);
      currentNode = currentNode.getNext();

      for (int i = 1; i < (length-1); i++){

         //The starting point for all the pentagones in the stock
         if(stockUnusedPenta == null && length(elements[i]) == 5)
            stockUnusedPenta = currentNode;

         //Insert an element in stock based on the quantity of it available
         for (int j = 0; j < nbElements[i]; j++) {
            shape = new Shape(elements[i], i+1);
            nextNode = new Node(shape);
            currentNode.setNext(nextNode);
            nextNode.setPrevious(currentNode);
            currentNode = currentNode.getNext();
         }
      }

      currentHexagone = stockUnused;
      currentPentagone = stockUnusedPenta;
   }

   public Shape getUnused(boolean isHexagone){
      if(currentElement == null)
         return null;

      if(isHexagone)
         return currentHexagone.getShape();
      else
         return currentPentagone.getShape();
   }

   public void usedCurrentElement(boolean isHexagone){
      Node currentElement;
      if(isHexagone)
         currentElement = currentHexagone;
      else
         currentElement = currentPentagone;
   
      //switch shape de unused à used tq shape est inséré en premier
      Node tmpPrev = currentElement.getPrevious();
      Node tmpNext = currentElement.getNext();

      if(tmpPrev != null)
         tmpPrev.setNext(tmpNext);

      if(tmpNext != null)
         tmpNext.setPrevious(currentElement.getPrevious());

      //Insert at the beginning of a single-way linked list
      currentElement.setNext(stockUsed);
      stockUsed = currentElement;

      if(isHexagone)
         currentHexagone = stockUnused;
      else
         currentPentagone = stockUnusedPenta;
   }

   public void getNextElement(){
      Node currentElement;
      if(isHexagone)
         currentElement = currentHexagone;
      else
         currentElement = currentPentagone;

      int currentType = getShape(currentElement).getType();

      currentElement = currentElement.getNext();
      if(currentElement == null)
         return;

      while(currentElement != null && currentType == getShape(currentElement).getType())
         currentElement = currentElement.getNext();

      if(isHexagone)
         currentHexagone = currentElement;
      else
         currentPentagone = currentElement;

   }
}