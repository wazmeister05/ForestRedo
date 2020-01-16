import java.util.ArrayList;
import java.util.List;

//video 1.14
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {          //abstract because we never implement it
                                                                                                        //classes extend classes and implement interfaces.
    //Because it extends AbstractTree, it has access to all of it's methods.
    //Because it implements Binary Tree, it needs to implement some of the methods of BinaryTree

    public Position<E> sibling(Position<E> p){      //look at the sibling node (left - right or right - left)
        Position<E> parent = parent(p);             //get parent node of current node so we can look at the one on the other side.
        if(parent == null){                         //ie parent is root
            return null;
        }
        if(p == left(parent)){                      //if node p is the left node of the parent, return the right node.
            return right(parent);
        }
        return left(parent);                        //otherwise node p must be the right node of the parent, so return the left node.
    }

    public int numChildren(Position<E> p){          //the number of direct children of a given p. (ie. no grandchildren)
        int count = 0;
        if(left(p) != null){                        //if the given node has a child on the left, increment count
            count++;
        }
        if(right(p) != null){                       //if the given node has a child on the right, increment count
            count++;
        }
        return count;                               //return count
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p){                    //Returns iterable object of positions of children
        List<Position<E>> temp = new ArrayList<>(2);            //Binary tree so never more than 2 children
        if(left(p) != null){
            temp.add(left(p));                      //if the given node has a child on the left, add the child to temp
        }
        if(right(p) != null){
            temp.add(right(p));                     //if the given node has a child on the right, add the child to temp
        }
        return temp;                                //return temp
    }
}
