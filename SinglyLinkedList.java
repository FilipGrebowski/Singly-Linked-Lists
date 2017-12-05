import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * The SinglyLinkedList class provides several methods for creating an empty list or 
 * a list from a collection. It adds functionality for adding a node, returning the 
 * current length of the LinkedList and returning the Iterator.
 *
 * @author Filip Grebowski
 * @version 25/11/2017
 */
public class SinglyLinkedList<E> extends AbstractSequentialList<E>
{
    // The first and last reference to a Node.
    Node<E> head, tail;
    
    // The total number of nodes.
    int countNodes;
    
    // Creates an empty list.
    public SinglyLinkedList() {
	head = null;
	tail = null;
	countNodes = 0;
    }
    
    // Creates a list from the collection.
    public SinglyLinkedList(Collection<E> data) {
   	head = null;
   	tail = null;
   	countNodes = 0;
   	
   	Iterator<E> iterator = data.iterator();
   	while(iterator.hasNext()) {
   	    add(iterator.next());
   	}
    }
    
    // Returns the iterator.
    public ListIterator<E> listIterator(int index) {
	return new SinglyListIterator<>(this, index);
    }
       
    // Adds a node.
    public boolean add(E e) {
	if(tail == null) { // If the list is empty.
	    // Creates a new node and sets the head and tail.
	    Node<E> toAdd = new Node<>(e, null);
	    head = toAdd;
	    tail = toAdd;
	} else {
	    // Add a node after the tail and at the tail.
	    Node<E> toAdd = new Node<>(e, null);
	    tail.right = toAdd;
	    tail = toAdd;
	}
	
	countNodes++;
	
	return true;
    }

    // Returns the total number of nodes.
    public int size() {
	return countNodes;
    }
}
