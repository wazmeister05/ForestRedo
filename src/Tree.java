//video 1.1
import java.util.Iterator;

public interface Tree<E> extends Iterable<E> {             //define all the things a tree is expected to do. Iterable so we can iterate through nodes. Generic so we can decide type at runtime

    Position<E> root();     //tell us root node
    Position<E> parent(Position<E> p) throws IllegalArgumentException;                  //tell us parent of given position
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;      //return children of given node
    int numChildren(Position<E> p) throws IllegalArgumentException;                     //return number of children given node has
    boolean isInternal(Position<E> p) throws IllegalArgumentException;                  //returns if node is Internal (has children)
    boolean isExternal(Position<E> p) throws IllegalArgumentException;                  //returns if node is External (has no children)
    boolean isRoot(Position<E> p) throws IllegalArgumentException;                      //returns if node is root node
    int size();                                                                         //size of tree
    boolean isEmpty();                                                                  //is the tree empty
    Iterator<E> iterator();                                                             //return iterator object to help us go through nodes.
    Iterable<Position<E>> positions();                                                  //return all the positions of the tree
}
