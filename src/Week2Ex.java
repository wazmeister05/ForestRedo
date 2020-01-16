public class Week2Ex {

    private static LinkedBinaryTree<Character> tree = new LinkedBinaryTree<>();

    public static void main(String[] args) {
        Position<Character> root = tree.addRoot('A');
        Position<Character> B = tree.addLeft(tree.root(), 'B');
        Position<Character> D = tree.addLeft(B, 'D');
        Position<Character> F = tree.addLeft(D, 'F');
        Position<Character> G = tree.addRight(D, 'G');
        Position<Character> E = tree.addRight(B, 'E');
        Position<Character> L = tree.addLeft(E, 'L');
        Position<Character> C = tree.addRight(tree.root(), 'C');
        Position<Character> R = tree.addLeft(C, 'R');
        Position<Character> S = tree.addRight(C, 'S');

        //these are to test depth (that Damien provided) and height that I created
        System.out.println(tree.depth(G));
        System.out.println(tree.height(G));

        System.out.println("\n\n2. Preorder method");
        tree.preorder();
        System.out.println("\n\n3. Inorder method");
       // tree.inorder();
        System.out.println("\n\n4. Postorder method");
        tree.postorder();

    }

}
