/*
 * Node is an auxiliary class modeling an element of a linked list.
 */

public class Node{
    private Shape element;
    private Node previous;
    private Node next;
    
    public Node(Shape s){
        element = s;
        previous = null;
        next = null;
    }
    
    public Node getPrevious(){
        return previous;
    }
    
    public void setPrevious(Node n){
        previous = n;
    }

    public Node getNext(){
        return next;
    }
    
    public void setNext(Node n){
        next = n;
    }
    
    public Shape getShape(){
        return element;
    }
}
