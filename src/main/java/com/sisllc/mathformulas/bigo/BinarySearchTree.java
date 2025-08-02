package com.sisllc.mathformulas.bigo;

public class BinarySearchTree {

    class Node {

        int key = 0;
        Node left, right;

        public Node(int key) {
            this.key = key;
            left = right = null;
        }

        @Override
        public String toString() {
            return "Node [key=" + key + "]";
        }

    }

    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int key) {
        root = insertNode(root, key);
    }

    public Node insertNode(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key) {
            root.left = insertNode(root.left, key);
        } else if (key > root.key) {
            root.right = insertNode(root.right, key);
        }

        return root;
    }

    public void order() {
        System.out.println("roder root=" + root);
        order(root);
    }

    public void order(Node root) {
        if (root != null) {
            order(root.left);
            System.out.println("root key =" + root.key);
            order(root.right);
        }
    }

    public static void main(String[] args) {
        //*
        Integer[] values = {2, 5, 20, 15, 25, 35, 38, 49, 65};
        System.out.println("" + values);

        BinarySearchTree bst = new BinarySearchTree();
        for (Integer val : values) {
            bst.insert(val);
        }

        bst.order();
        System.out.println("search " + search(bst.root, 35));
        // */

        System.out.println("\n\n");
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // print inorder traversal of the BST
        tree.order();

        System.out.println("search " + search(tree.root, 40));
    }

    public static Node search(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (root.key > key) {
            return search(root.left, key);
        }

        return search(root.right, key);
    }

}
