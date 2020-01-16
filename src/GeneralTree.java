/*
Submission for Week 1
Will Stolton QHB18155

2.    Implement a general tree by extending the AbstractTree class which makes use of the Node class from the first question.

You may not have access to the Node’s methods in order to use them in certain places. In this case, you want to cast a Position to a Node.
Is this the best approach? Is it safe to cast any Position to the type Node? You may want to think about validating that it is a Node before casting.
 isn’t necessary for this exercise, but think it through!
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeneralTree<E> extends AbstractTree<E>{

    private GeneralNode<E> root;
    private int size;

    public GeneralTree(){
        root = null;
        size = 0;
    }

    public Position<E> addRoot(E e) throws IllegalStateException{
        if(!isEmpty()){
            throw new IllegalStateException("Not empty");
        }
        root = new GeneralNode<E>(e, null);
        size = 1;
        return root;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public int size() {
        return size;
    }

    public GeneralNode<E> validate(Position<E> p) throws IllegalArgumentException {
        if(!(p instanceof GeneralNode)){
            throw new IllegalArgumentException("Not a valid type of position");
        }
        GeneralNode<E> node = (GeneralNode<E>) p;    //casting a position as a node. Damien does this in week 2 and it seemed useful so borrowed it
        return node;
    }


    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return validate(p).getParent();
    }


    /*
    Disclaimer: I couldn't get children() to work since it was overridden and my node class was returning an ArrayList
    So I made another method called getChildren. How the hell do we work with Iterables!
     */

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<GeneralNode<E>> children = validate(p).getChildren();
        return (Iterable<Position<E>>) children.iterator();
    }

    public List<GeneralNode<E>> getChildren(Position<E> p) throws IllegalArgumentException{
        return validate(p).getChildren();
    }

    public void setChildren(Position<E> p, ArrayList<GeneralNode<E>> children) throws IllegalArgumentException{
        validate(p).setChildren(children);
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        return validate(p).getChildren().size();
    }




    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterable<Position<E>> positions() {
       return null;
    }






}
