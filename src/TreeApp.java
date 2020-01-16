public class TreeApp {

    private static LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();

    public static void main(String[] args) {
        tree.addRoot(1);
        System.out.println(tree.depth(tree.root()));
        Position<Integer> child = tree.addLeft(tree.root(), 2);
        child = tree.addLeft(child, 3);
        System.out.println(tree.depth(child));
    }

}


