/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

import com.interview.capitalonesignalmock.Code3HardGraphTreeTraversal.TreeNode;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author javaugi
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class Code3HardMaxPathSumTest {
    Code3HardGraphTreeTraversal main = new Code3HardGraphTreeTraversal();

    @Test
    public void testMaxPathSum_PositiveTree() {
        TreeNode root = main.new TreeNode(1);
        root.left = main.new  TreeNode(2);
        root.right = main.new  TreeNode(3);
        System.out.println("testMaxPathSum_PositiveTree ...");
        assertEquals(6, main.maxPathSum(root));
    }

    @Test
    public void testMaxPathSum_NegativeTree() {
        TreeNode root = main.new  TreeNode(-10);
        root.left = main.new  TreeNode(9);
        root.right = main.new  TreeNode(20);
        root.right.left = main.new  TreeNode(15);
        root.right.right = main.new  TreeNode(7);
        System.out.println("testMaxPathSum_NegativeTree ...");
        assertEquals(42, main.maxPathSum(root)); // Path: 15 → 20 → 7
    }

    @Test
    public void testMaxPathSum_SingleNode() {
        TreeNode root = main.new  TreeNode(1);
        System.out.println("testMaxPathSum_SingleNode ...");
        assertEquals(1, main.maxPathSum(root));
    }    
}
