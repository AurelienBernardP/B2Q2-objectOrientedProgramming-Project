
/*
 * Stock is the representation of stocks which contains all the elements necessary
 * to solve the puzzle.
 * stockUnusedPenta is a doubly linked list which contains all given pentagones that have 
 * not been used yet (sorted based on the type of the elements).
 * stockUnusedHexa is a doubly linked list which contains all given hexagones that have 
 * not been used yet (sorted based on the type of the elements).
 * stockUsed is a singly linked list which contains all elements which has been fitted
 * in the net.
 * currentHexagone assists on reaching any element of the stockUnusedHexa list. (it is
 * called "assistant")
 * currentPentagone assists on reaching any element of the stockUnusedPenta list. (it is
 * called "assistant")
 * 
 */
public class Stock{
   private Node stockUsed;
   private Node stockUnusedHexa;
   private Node stockUnusedPenta;
   private Node currentHexagone;
   private Node currentPentagone;

   /*
   * Stock(int[][] elements, int[] nbElements) initialises the stocks based on the given
   * elements.
   */
   public Stock(int[][] elements, int[] nbElements){
      Shape shape;
      Node nextNode;
      Node currentNode = null;
      int length = elements.length;

      //Adding the elements to the stocks
      for (int i = 0; i < length; i++){

         //The starting point for all the pentagones in the stock
         if(stockUnusedPenta == null && elements[i].length == 5)
            stockUnusedPenta = currentNode;

         //Insert the element in stock based on the quantity of it available
         for (int j = 0; j < nbElements[i]; j++){

            //A shape of that element is created
            shape = new Shape(elements[i], i+1);

            //If the first element is inserted then the head of the list is created.
            if(stockUnusedHexa == null){
               stockUnusedHexa = new Node(shape);
               currentNode = stockUnusedHexa;
            }

            //Else the element is inserted in the doubly linked list
            else{
               nextNode = new Node(shape);
               currentNode.setNext(nextNode);
               nextNode.setPrevious(currentNode);
               currentNode = currentNode.getNext();
            }
         }//end for-j
      }//end for-i

      //Dividing the doubly linked list into 2 others for the hexagones and pentagones
      currentNode = stockUnusedPenta;
      stockUnusedPenta = stockUnusedPenta.getNext();
      currentNode.setNext(null);
      stockUnusedPenta.setPrevious(null);

      //Setting the assistants at the beginning of the lists
      currentHexagone = stockUnusedHexa;
      currentPentagone = stockUnusedPenta;
   }

   /*
   * Shape getUnused(boolean isHexagone) returns the shape of an unused element
   */
   public Shape getUnused(boolean isHexagone){
      if(isHexagone){
         if(currentHexagone == null)
            return null;
         return currentHexagone.getShape();
      }else{
         if(currentPentagone == null)
            return null;
         return currentPentagone.getShape();
      }
   }

   /*
   * Node getNode(boolean isHexagone) is an accessor for the assistants
   */
   private Node getNode(boolean isHexagone){
      if(isHexagone)
         return currentHexagone;
      else
         return currentPentagone;
   }

   /*
   * void usedCurrentElement(boolean isHexagone) switches the element from the stock
   * containing all unused elements to the other stock.
   */
   public void usedCurrentElement(boolean isHexagone){

      //Get the current element
      Node currentElement = getNode(isHexagone);
      Node tmpPrev = currentElement.getPrevious();
      Node tmpNext = currentElement.getNext();

      //Taking the current element off the list
      if(tmpNext != null)
         tmpNext.setPrevious(tmpPrev);
      //If the current element is the head of the list
      if(tmpPrev == null){
         if(isHexagone)
            stockUnusedHexa = tmpNext;
         else
            stockUnusedPenta = tmpNext;
      }
      else{
         tmpPrev.setNext(tmpNext);
      }

      //Insert the current element at the beginning of the single-way linked list
      currentElement.setNext(stockUsed);
      stockUsed = currentElement;

      //Setting the assistants back to the head of the lists
      currentHexagone = stockUnusedHexa;
      currentPentagone = stockUnusedPenta;
   }

   /*
   * void getNextElement(boolean isHexagone) shifts one of the assistants to the following
   * type of element.
   */
   public void getNextElement(boolean isHexagone){
      Node currentElement = getNode(isHexagone);
      if(currentElement == null)
         return;

      int currentType = currentElement.getShape().getType();

      //Shifts to the next element which type is different from the initial element.
      while(currentElement != null && currentType == currentElement.getShape().getType())
         currentElement = currentElement.getNext();

      //Shifting the assistant to the next type of element.
      if(isHexagone)
         currentHexagone = currentElement;
      else
         currentPentagone = currentElement;

   }

   /*
   * void undoUsedElement(boolean isHexagone) switches the element from the stock
   * containing all used elements to the other stock.
   */
   public void undoUsedElement(boolean isHexagone){
      if(stockUsed == null){
         System.out.print("Used stock is empty\n");
         return;
      }

      //Taking off the element from its stock
      Node usedElement = stockUsed;
      stockUsed = stockUsed.getNext();

      //Selecting one of the 2 stocks of unusued elements
      Node tmp, tmpPrev = null;
      if(isHexagone)
         tmp = stockUnusedHexa;
      else
         tmp = stockUnusedPenta;

      //Searching for the position in the list to which the element must be added
      int usedElementType = usedElement.getShape().getType();
      while(tmp != null && tmp.getShape().getType() < usedElementType){
         tmpPrev = tmp;
         tmp = tmp.getNext();
      }

      //Putting the element in the correct position of the list to keep the latter sorted.
      usedElement.setNext(tmp);
      usedElement.setPrevious(tmpPrev);
      if(tmp != null)
         tmp.setPrevious(usedElement);
      //If the element must be added at the head of the list
      if(tmpPrev == null){
         if(isHexagone)
            stockUnusedHexa = usedElement;
         else
            stockUnusedPenta = usedElement;
      }else{
         tmpPrev.setNext(usedElement);
      }

      //Shifting the assistants to that displaced element
      if(isHexagone){
         currentHexagone = usedElement;
      }
      else{
         currentPentagone = usedElement;
      }

      //Since the element has been moved from stockUsed, it can fit in
      //the net without causing problem to solved the whole puzzle but
      //not in its current rotation.
      if(usedElement.getShape().rotate() == false){
         usedElement.getShape().resetRotation();
         getNextElement(isHexagone);
         return;
      }

   }

}