/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

/**
 *
 * @author javaugi
 */
public class Code3HardGraphTreeTraversal {

    /*
Problem 3: Hard – Graph/Tree Traversal
Task: Given a binary tree, find the maximum path sum, where a path is any sequence of nodes connected by edges.

Example:
Input:

Copy
    1  
   / \  
  2   3  
Output: 6 (Path: 2 → 1 → 3)

Solution (Post-Order DFS):

Key Points:

Recursive DFS with O(N) time.

Tracks local (branch) and global (path) maxima.    
    
In the context of "binary tree Post-Order DFS", DFS stands for Depth-First Search, a method for traversing a tree 
    or graph that explores as far as possible along each branch before backtracking. 
Here's a more detailed explanation:
Depth-First Search (DFS):
    Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm 
    starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and explores as 
    far as possible along each branch before backtracking.
    DFS is an algorithm used to systematically explore a tree or graph by going as deep as possible
    down one path before exploring other paths. 
Binary Tree:
    A binary tree is a data structure where each node has at most two children, referred to as the left and right child. 
Post-Order:
    In the context of DFS, "Post-Order" refers to a specific order in which nodes are visited during the traversal. 
    In post-order traversal, the left and right subtrees are visited before the root node. 
How it works:
    Start at the root node. 
    Explore the left subtree recursively (go as deep as possible). 
    Explore the right subtree recursively (go as deep as possible). 
    Visit (process) the current node (root). 
Example:
    Imagine a simple binary tree: A (root) has children B (left) and C (right). B has a child D (left).
    In post-order DFS, you would visit: D, then B, then C, then A. 
Other DFS Traversal Types:
    There are other DFS traversal methods, including Pre-Order and In-Order, each with a different order of visiting nodes. 
    Pre-Order: Visit the root, then the left subtree, then the right subtree. 
    In-Order: Visit the left subtree, then the root, then the right subtree
    
    
                A      
            /       \    
           B         C    
         /   \       / \   
        D     E     F   G    
    ASummary of Traversal Orders
    Pre-order: A, B, D, E, C, F, G
    In-order: D, B, E, A, F, C, G
    Post-order: D, E, B, F, G, C, A

    Pre-order Traversal (Root, Left, Right) 
    In pre-order traversal, you visit the root node first, then the left subtree, followed by the right subtree.
    Traversal Order: A, B, D, E, C, F, G
    
    In-order Traversal (Left, Root, Right)
    In in-order traversal, you visit the left subtree first, then the root node, and finally the right subtree.
    Traversal Order: D, B, E, A, F, C, G    
        
    Post-order Traversal (Left, Right, Root)
    In post-order traversal, you visit the left subtree first, then the right subtree, and finally the root node.
    Traversal Order: D, E, B, F, G, C,     
    
    Different tree traversal methods provide various benefits and are suited for specific tasks in computer science. 
    Here are some key benefits of using pre-order, in-order, and post-order traversals:1. 
    
    Pre-order Traversal
    Structure Preservation: Pre-order traversal is useful for creating a copy of a tree or generating a prefix expression for expression trees.
    It preserves the structure of the tree.
    Node Processing: It allows processing the root node before its children, which is beneficial for tasks like serialization of trees.
    Hierarchical Data Representation: Pre-order traversal is often used in scenarios where you need to represent hierarchical data, 
    such as file systems or organizational charts.

    2. In-order Traversal
    Sorted Output for Binary Search Trees (BST): In-order traversal of a BST retrieves the nodes in sorted order. This is particularly 
    useful for applications requiring sorted data without additional sorting algorithms.
    Natural Ordering: It allows for the natural ordering of elements, making it useful for operations that depend on the order 
    of elements, like range queries.
    Evaluation of Expressions: In expression trees, in-order traversal can be used to produce infix expressions, which are easier to read and understand.

    3. Post-order Traversal
    Deletion and Cleanup: Post-order traversal is ideal for deleting nodes in a tree since it processes child nodes before the parent, 
    ensuring that all dependencies are handled before the parent is deleted.
    Evaluation of Expression Trees: In expression trees, post-order traversal allows for evaluating expressions where operands are 
    processed before operators, which is crucial for correct computation.
    Memory Management: It is useful in algorithms that require processing child nodes before their parent, such as garbage collection or memory deallocation.

    General Benefits of Tree Traversal Methods

    Flexibility: Different traversal methods provide flexibility in how tree data is processed, allowing developers to choose the most appropriate 
    method based on the task.
    Efficiency: Depending on the operation being performed, certain traversal methods may be more efficient, reducing the time complexity 
    associated with tree operations.
    Algorithm Design: Understanding traversal methods is essential for designing algorithms related to trees, such as searching, insertion, and deletion.

    In summary, the choice of tree traversal method can significantly impact the efficiency and clarity of various algorithms and operations 
    in computer science. Each method has its specific use cases and advantages, making them valuable tools in a programmer's toolkit.    
    
     */
    int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Code3HardGraphTreeTraversal main = new Code3HardGraphTreeTraversal();
        main.run();        
    }
    
    private void run() {
        run1();
        run2();
        run3();
    }

    private void run3() {
        int[] values = {5, 3, 7, 1, 2, 4, 6, 8}; // Example values to insert.
        maxSum = Integer.MIN_VALUE;
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }
        System.out.println("\nThe final tree:\n" + root);
        System.out.println("\nInorder traversal of the BST:");
        inorderTraversal(root);        
        System.out.println("\n Test 2: The Maxsum of the binaryTree created: " + maxPathSum(root));
    }

    private void run2() {
        /*
        int values[] = {1, 2, 3, 4, 5};
        maxSum = Integer.MIN_VALUE;
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }
        // */
        TreeNode root = new TreeNode(1);

        // Create the left child node (2).
        TreeNode leftChild = new TreeNode(2);
        // Create the right child node (3).
        TreeNode rightChild = new TreeNode(3);
        // Connect the nodes to form the tree.
        root.left = leftChild;
        root.right = rightChild;

        // Create the left child node (2).
        TreeNode leftChild4 = new TreeNode(4);
        // Create the right child node (3).
        TreeNode rightChild5 = new TreeNode(5);
        leftChild.left = leftChild4;
        leftChild.right = rightChild5;
        
        /*
                1
               / \
              2   3
             / \
            4   5                
        Pre-order Traversal:
        1 2 4 5 3
        In-order Traversal:
        4 2 5 1 3
        Post-order Traversal:
        4 5 2 3 1        
        */
        System.out.println("\n This is the preOrder: " + maxPathSum(root));
        preOrder(root);
        System.out.println("\n This is the inOrder: " + maxPathSum(root));
        inOrder(root);
        System.out.println("\n This is the postOrder: " + maxPathSum(root));
        postOrder(root);
        System.out.println("\n All Done ");
    }

    private TreeNode run1() {
        TreeNode root = createBinaryTree();
        System.out.println("\n Test 1: The Maxsum of the binaryTree created: " + maxPathSum(root));
        return root;
    }

    private TreeNode createBinaryTree() {
        // Create the root node (1).
        TreeNode root = new TreeNode(1);

        // Create the left child node (2).
        TreeNode leftChild = new TreeNode(2);

        // Create the right child node (3).
        TreeNode rightChild = new TreeNode(3);

        // Connect the nodes to form the tree.
        root.left = leftChild;
        root.right = rightChild;

        // Optionally, print the tree using a traversal method (e.g., inorder).
        System.out.println("\n Inorder traversal:");
        inorderTraversal(root);
        return root;
    }
    
    public TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        System.out.print("\n  1. insert TreeNode val=" + val + "-root.vl=" + root.val + "\n     -root.left=" + root.left + "\n      -root.right=" + root.right);
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }
        System.out.print("\n  2. return root=" + root);

        return root; // Return the (possibly modified) root.
    }    
    

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));
        maxSum = Math.max(maxSum, left + right + node.val);
        int rtnValue = Math.max(left, right) + node.val;
        System.out.print("\nPost-Order Depth-First Search left=" + left + "-right=" + right + "-node.val=" + node.val + "-rtnValue: " + rtnValue
         + "\n (Math.max(maxSum, left + right + node.val)) -maxSum=" + maxSum);
        return rtnValue;
    }
    
    public static void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }    
    
    // Pre-order Traversal: (Root, Left, Right) 
    // Visit the current node first, then the left subtree, then the right subtree.
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");    // Process the current node
        preOrder(root.left);             // Traverse the left subtree
        preOrder(root.right);            // Traverse the right subtree
    }

    // In-order Traversal: (Left, Root, Right)
    // Visit the left subtree first, then the current node, then the right subtree.
    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);              // Traverse the left subtree
        System.out.print(root.val + " ");    // Process the current node
        inOrder(root.right);             // Traverse the right subtree
    }

    // Post-order Traversal: (Left, Right, Root)
    // Visit the left subtree first, then the right subtree, then the current node.
    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);             // Traverse the left subtree
        postOrder(root.right);            // Traverse the right subtree
        System.out.print(root.val + " ");     // Process the current node
    }    

    public class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int x) {
            this.val = x;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "{" + "val=" + val + ", left=" + left + ", right=" + right + '}';
        }
        
    }
}
