import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    private Node<E> root;
    private int size;

    protected static class Node<E> implements Position<E>{          //create an implementation of Position as an inner class

        private E element;
        private Node<E> parent;                                     //every node except root will have a parent
        private Node<E> left;                                       //every internal node will have a left child
        private Node<E> right;                                      //every internal node will have a right child

        public Node(E element, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {         //called by LinkedBinaryTree.createNode
            this.element = element;
            this.parent = parent;
            right = rightChild;
            left = leftChild;
        }

        public Node<E> getParent() {                                //get parent node
            return parent;
        }

        public void setParent(Node<E> parent) {                     //set parent node
            this.parent = parent;
        }

        public Node<E> getLeft() {                                 //get left child value
            return left;
        }

        public void setLeft(Node<E> left) {                        //set left child value
            this.left = left;
        }

        public Node<E> getRight() {                                //get right child value
            return right;
        }

        public void setRight(Node<E> right) {                       //set right child value
            this.right = right;
        }

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E element){                          //override currently stored element
            this.element = element;
        }
    }

    protected Node<E> validate(Position<E> p) throws IllegalArgumentException{              //after confirming position is valid, it automatically casts it to a node for us.
        if(!(p instanceof Node)){                                                           //ie if p is not a node
            throw new IllegalArgumentException("Not a position type");
        }
        Node<E> node = (Node<E>) p;                                                         //this will cast the position p to node
        if(node.getParent() == node){                                                       //indicates node disconnected from tree
            throw new IllegalArgumentException("p is no longer in the tree");
        }
        return node;                                                                        //at this point, certain it's a node and it's connected to the tree
    }

    protected Node<E> createNode(E element, Node<E> parent, Node<E> leftChild, Node<E> rightChild){     //creates new node.
        return new Node(element, parent, leftChild, rightChild);                                        //calls Node constructor
    }

    public LinkedBinaryTree(){      //constructor for this class
        root = null;    //root hasn't been added yet
        size = 0;       //empty tree
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
            return validate(p).getLeft();                                                     //use node method getLeft() to get left node
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return validate(p).getRight();                                                      //use node method getRight() to get right node
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return validate(p).getParent();                                                     //use node method getParent() to get parent
    }

    @Override
    public int size() {
        return size;
    }

    public Position<E> addLeft(Position<E> p, E element) throws IllegalArgumentException{       //add node and return it so we can use it to add children to the new child node.
        Node<E> parent = validate(p);                                                           //create node of the position in question and refer to it as the parent (since we're adding children to this node)
        if(parent.getLeft() != null){
            throw new IllegalArgumentException("Left child already exists");                    //if p already has left child
        }
        Node<E> child = createNode(element, parent, null, null);             //nulls because they don't have children yet
        parent.setLeft(child);                                                                  //add new child to the left of the parent
        size++;                                                                                 //added a new node
        return child;                                                                           //return the child we just created
    }

    public Position<E> addRight(Position<E> p, E element) throws IllegalArgumentException{       //add node and return it so we can use it to add children to the new child node.
        Node<E> parent = validate(p);                                                           //create node of the position in question and refer to it as the parent (since we're adding children to this node)
        if(parent.getRight() != null){
            throw new IllegalArgumentException("Right child already exists");                    //if p already has right child
        }
        Node<E> child = createNode(element, parent, null, null);             //nulls because they don't have children yet
        parent.setRight(child);                                                                  //add new child to the right of the parent
        size++;                                                                                 //added a new node
        return child;                                                                           //return the child we just created
    }

    public Position<E> addRoot(E element) throws IllegalStateException{                         //create root of tree
        if(!isEmpty()){
            throw new IllegalStateException("Tree not empty");
        }
        root = createNode(element, null, null, null);
        size = 1;
        return root;
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException{                           //p is node we want to overwrite, e is new data
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;                                                                            //return what has been overwritten
    }

    public void attach(Position<E> p, LinkedBinaryTree<E> tree1, LinkedBinaryTree<E> tree2) throws IllegalArgumentException{    //trees already existing (1 & 2) to be added to the given position
        Node<E> node = validate(p);                                                                                             //make sure it is allowed
        if(isInternal(p)){
            throw new IllegalArgumentException("p must be external");                                                           //and not an internal node. You can't add a tree to a node that is already used
        }
        size += tree1.size() + tree2.size();                                                                                    //increment sizes of tree 1 & 2 to the current trees size
        if(!tree1.isEmpty()){                                                                                                   //if tree1 is empty, nothing to add
            tree1.root.setParent(node);                                                                                         //set parent of tree1 root as node p
            node.setLeft(tree1.root);                                                                                           //just add root of tree1 to current tree and everything below will be attached
            tree1.root = null;                                                                                                  //tree1 doesn't have a root anymore
            tree1.size = 0;                                                                                                     //or a size
        }
        if(!tree2.isEmpty()){                                                                                                   //if tree2 is empty, nothing to add
            tree2.root.setParent(node);                                                                                         //set parent of tree2 root as node p
            node.setRight(tree2.root);                                                                                           //just add root of tree2 to current tree and everything below will be attached
            tree2.root = null;                                                                                                  //tree2 doesn't have a root anymore
            tree2.size = 0;                                                                                                     //or a size
        }
    }


    public E remove(Position<E> p) throws IllegalArgumentException{                                                             //removes node from tree and replaces it with a child node
        Node<E> node = validate(p);
        if(numChildren(p) == 2){                                                                                                //if there are 2 children, throws IAE as we don't know which to assign as new parent
            throw new IllegalArgumentException("p has 2 children");
        }
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());                                            //if there is a node in the left, get that, otherwise get right child.
        if (child != null){
            child.setParent(node.getParent());                                                                                  //take parent of node being removed and make the child of node being removed the child of the parent node being removed
        }
        if(node == root){                                                                                                       //if the node we're removing is the root, make the child the new root
            root = child;
        }
        else{                                                                                                                   //update the child reference of the new parent
            Node<E> parent = node.getParent();                                                                                  //if the newly promoted node if the left hand child of the old nodes parent, make it so
            if (node == parent.getLeft()){
                parent.setLeft(child);
            }
            else{
                parent.setRight(child);
            }
        }
        size--;
        E temp = node.getElement();     //save element before garbage collection
        node.setElement(null);          //garbage collection
        node.setLeft(null);             //garbage collection
        node.setRight(null);            //garbage collection
        node.setParent(null);           //garbage collection
        return temp;                    //return saved element
    }




    //these don't seem to have a purpose as of week 2...

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterable<Position<E>> positions() {
        return null;
    }
}
