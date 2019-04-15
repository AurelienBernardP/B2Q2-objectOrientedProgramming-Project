/*
 * INFO0062 - Object-oriented programming
 * Exercise session #2
 *
 * Auxiliary class modeling an element of a linked list. Note that this class is designed to store 
 * a String (for the example), but you can of course modify it to store any other kind of object.
 *
 * @author: J.-F. Grailet
 */

public class Node
{
    private Shape shape;
    private Node next;
    
    public Node(Shape s)
    {
        shape = s;
        next = null;
    }
    
    public Node getNext()
    {
        return next;
    }
    
    public void setNext(Node n)
    {
        next = n;
    }
    
    public Shape getShape()
    {
        return shape;
    }
}
