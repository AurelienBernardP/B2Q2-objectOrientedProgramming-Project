

public class Stock{
   private Node stockUsed;
   private Node stockUnused;
   private Node stockUnusedPenta;
   private Node currentHexagone;
   private Node currentPentagone;

   public void printMatrix(int[] matrix){
      for (int i = 0; i < matrix.length; i++) {
             System.out.print(matrix[i] + " ");
     }
     System.out.println();
   }

   private void printList(Node list, int num){

      
      if(list == null){
         System.out.print("Empty\n \n");
         return;
      }
      System.out.print(num + ": Element: " + list.getShape().getType()+ " | ");
      printMatrix(list.getShape().getConcavity());
      printList(list.getNext(), num+1);
   }

   public Stock(int[][] elements, int[] nbElements){
      Shape shape;
      Node nextNode;
      Node currentNode = null;
      int length = nbElements.length;

      if(length < 1){
         System.out.print("Error\n");
         return;
      }

      for (int i = 0; i < length; i++){
         //The starting point for all the pentagones in the stock
         if(stockUnusedPenta == null && elements[i].length == 5)
            stockUnusedPenta = currentNode;

         //Insert an element in stock based on the quantity of it available
         for (int j = 0; j < nbElements[i]; j++){
            shape = new Shape(elements[i], i+1);

            //If head of the list or not
            if(stockUnused == null){
               stockUnused = new Node(shape);
               currentNode = stockUnused;
            }else{
               nextNode = new Node(shape);
               currentNode.setNext(nextNode);
               nextNode.setPrevious(currentNode);
               currentNode = currentNode.getNext();
            }
         }
      }

      currentNode = stockUnusedPenta;
      stockUnusedPenta = stockUnusedPenta.getNext();
      currentNode.setNext(null);
      stockUnusedPenta.setPrevious(null);
      currentHexagone = stockUnused;
      currentPentagone = stockUnusedPenta;
   }

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
      else{
         if(isHexagone)
            stockUnused = tmpNext;
         else
            stockUnusedPenta = tmpNext;
      }

      if(tmpNext != null)
         tmpNext.setPrevious(tmpPrev);

      //Insert at the beginning of a single-way linked list
      currentElement.setNext(stockUsed);
      stockUsed = currentElement;

         currentHexagone = stockUnused;
         currentPentagone = stockUnusedPenta;
   
         /*System.out.print("\n After \n Used stock \n");
         printList(stockUsed, 1);
         System.out.print("\n Unused stock \n");
         printList(stockUnused, 1);
         printList(stockUnusedPenta, 1);*/
      
   }


   public void getNextElement(boolean isHexagone){
      Node currentElement;
      if(isHexagone)
         currentElement = currentHexagone;
      else
         currentElement = currentPentagone;

      int currentType = currentElement.getShape().getType();

      currentElement = currentElement.getNext();

      while(currentElement != null && currentType == currentElement.getShape().getType())
         currentElement = currentElement.getNext();

      if(isHexagone)
         currentHexagone = currentElement;
      else
         currentPentagone = currentElement;

   }

   public void undoUsedElement(boolean isHexagone){
      System.out.print("\nFROM USED TO UNUSED Before \n Used stock \n");
      printList(stockUsed, 1);
      System.out.print("\n Unused stock \n");
      printList(stockUnused, 1);
      printList(stockUnusedPenta, 1);
      if(stockUsed == null){
         System.out.print("Nope \n");
         return;
      }

      
      //Taking off the used element
      Node usedElement = stockUsed;
      stockUsed = stockUsed.getNext();

      //Putting it back to the unused element

      Node tmp;
      if(isHexagone)
         tmp = stockUnused;
      else
         tmp = stockUnusedPenta;
      
      Node tmpPrev = null;

      int usedElementType = usedElement.getShape().getType();
      System.out.print("Taking off element: " + usedElementType + "\n");
      while(tmp != null && tmp.getShape().getType() < usedElementType){
         tmpPrev = tmp;
         tmp = tmp.getNext();
      }
      usedElement.setNext(tmp);
      usedElement.setPrevious(tmpPrev);
      if(tmp != null)
         tmp.setPrevious(usedElement);

      if(tmpPrev != null)
         tmpPrev.setNext(usedElement);
      else{
         if(isHexagone)
            stockUnused = usedElement;
         else
            stockUnusedPenta = usedElement;
      }

      //Rotation of the element
      if(isHexagone){
         currentHexagone = usedElement;
      }
      else{
         currentPentagone = usedElement;
      }
      System.out.print("\nAfter \n Used stock \n");
      printList(stockUsed, 1);
      System.out.print("\n Unused stock \n");
      printList(stockUnused, 1);
      printList(stockUnusedPenta, 1);
      if(usedElement.getShape().rotate() == false){
         usedElement.getShape().resetRotation();
         getNextElement(isHexagone);
         return;
      }



      //System.out.print("Unused: \n");
      //   printList(stockUnused, 1);
      //System.out.print("\n Used: \n");

      //printList(stockUsed, 1);

   }

}