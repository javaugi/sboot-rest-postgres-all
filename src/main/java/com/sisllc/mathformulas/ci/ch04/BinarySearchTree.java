package com.sisllc.mathformulas.ci.ch04;

public class BinarySearchTree {

    /* Class containing left and right child of current node and key value*/
    public class Node {

        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }

        @Override
        public String toString() {
            return "" + key;
        }

    }

    // Root of BST
    public Node root;
    public int count = 0;

    public BinarySearchTree() {
        root = null;
    }

    // This method mainly calls insertRec()
    public void insert(int key) {
        count++;
        root = insertRec(root, key);
    }

    /* A recursive function to insert a new key in BST */
    public Node insertRec(Node root, int key) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }

        /* Otherwise, recursively down the tree */
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        /* return the (unchanged) node pointer */
        return root;
    }

    // This method mainly calls InorderRec()
    public void inorder() {
        inorderRec(root);
    }

    // A utility function to do inorder traversal of BST
    public void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }

    // Driver Program to test above functions
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // print inorder traversal of the BST
        tree.inorder();

        System.out.println("search: " + tree.search(tree.root, 70));
    }

    // A utility function to search a given key in BST
    public Node search(Node root, int key) {
        // Base Cases: root is null or key is present at root
        System.out.println("search key=" + key + "-root=" + root);
        if (root == null || root.key == key) {
            return root;
        }

        // val is greater than root's key
        if (root.key > key) {
            return search(root.left, key);
        }

        // val is less than root's key
        return search(root.right, key);
    }

}
