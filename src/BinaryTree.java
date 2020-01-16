//video 1.14
public interface BinaryTree<E> extends Tree<E> {                        //serves as ADT for BinaryTree. Generic. Extends Tree so has same basic functions but does some stuff slightly differently.
                                                                        //note, interfaces extend other interfaces

    Position<E> left(Position<E> p) throws IllegalArgumentException;            //in binary tree, each node has 2 children. This method returns the left child
    Position<E> right(Position<E> p) throws IllegalArgumentException;           //same as above
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;         //returns sibling of given node (ie. if given left, return right and vice versa)
}
