import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The iterator class provide us with the objects we need to produce and iterate through 
 * a singly linked lists. It allows for checking individual positions and values at specific 
 * indexes,as well as jumping to a particular index and then inserting a node inbetween 
 * two other nodes.
 *
 * @author Filip Grebowski  
 * @version 25/11/2017
 */
public class SinglyListIterator<E> implements ListIterator<E>
{
    private SinglyLinkedList<E> sll; // Initialises list for the iterator.
    private int currentIndex; // Stores current index.
    private Node<E> currentNode; // Stores current node.
    private Node<E> previousNode; // Stores previous node.

    public SinglyListIterator(SinglyLinkedList<E> sll, int index) {
        this.sll = sll;
        currentIndex = 0; // At start.
        currentNode = sll.head; // First node.
        previousNode = null; // No previous node yet.
    
        // Moving the iterator to the desired index.
        for (int i = 0; i < index; i++) {
            next();
        }
    }
   
    // Returns boolean if it has next.
    public boolean hasNext() {
        return currentIndex < sll.size();
    }
    
    // Returns boolean if it has previous.
    public boolean hasPrevious() {
        return previousNode != null;
    }
    
    // Moves to the next node.
    public E next() {
        if (!hasNext()) // If at the end of the list:
            throw new NoSuchElementException("You are already at the end of the list.");
        else {
            // Move to the next node.
            E next = currentNode.left;
            previousNode = currentNode;
            currentNode = currentNode.right;
            currentIndex++;
            return next;
        }
    }
    
    // Returns the next index.
    public int nextIndex() {
        return currentIndex + 1;
    }
    
    // Returns the previous index.
    public int previousIndex() {
        return currentIndex - 1;
    }
    
    // Adds a new node.
    public void add(E e) {
        // Creates a new node.
        Node<E> newNode = new Node<>(e, currentNode);
        if(sll.head == currentNode) { // Inserts the node at the start.
            sll.head = newNode;
            
            if(sll.countNodes == 0)
            sll.tail = newNode;
          
            
        } else {// Inserts a new node inbetween.
            previousNode.right = newNode;
            
        }
        
        // Updates the current node count.
        sll.countNodes++;
        
        // Updates the previous node.
        previousNode = newNode;
        currentIndex++;

    }
    
    // Optional methods that are not implemented. [ remove() and set() ].
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void set(E e) {
        throw new UnsupportedOperationException();
    }
   
    // Returns the previous node. Required by the interface.
    public E previous() {
        if (!hasPrevious())
            throw new NoSuchElementException("You are already at the start of the list.");
        else {
            // Move to the previous node.
            E prev = previousNode.left;
            currentNode = previousNode;
            currentIndex--;
            
            // Update the previous node.
            if(currentIndex == 0) {
                previousNode = null;
            } 
            else {
                previousNode = sll.head;
                while(previousNode.right != currentNode) {
                    previousNode = previousNode.right;
                }
            }
            return prev;
        }
    }
}
