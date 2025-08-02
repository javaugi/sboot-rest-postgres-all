/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.ci.ch04;

import com.spring5.ProjectTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class BinarySearchTreeTest extends ProjectTest {

    private static final Logger log = LoggerFactory.getLogger(BinarySearchTreeTest.class);

    /*
Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
     */
    BinarySearchTree tree = null;

    @Before
    public void setup() {
        tree = new BinarySearchTree();
    }

    @Test
    public void when1ThenCount1() {
        tree.insert(50);
        Assert.assertEquals(1, tree.count);
        Assert.assertTrue(tree.root != null);
        Assert.assertTrue(tree.root.key == 50);
    }

    @Test
    public void when2ThenCount2() {
        tree.insert(50);
        tree.insert(30);
        Assert.assertEquals(2, tree.count);
        Assert.assertTrue(tree.root != null);
        Assert.assertTrue(tree.root.key == 50);
    }

    @Test
    public void when3ThenCount3() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        Assert.assertEquals(3, tree.count);
        Assert.assertTrue(tree.root != null);
        System.out.println("root=" + tree.root);
        Assert.assertTrue(tree.root.key == 50);
    }

    @Test
    public void when4ThenCount4() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(70);
        Assert.assertEquals(4, tree.count);
        Assert.assertTrue(tree.root != null);
        System.out.println("root=" + tree.root);
        Assert.assertTrue(tree.root.key == 50);
        tree.inorder();
    }

    @Test
    public void when7ThenCount7() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        Assert.assertEquals(7, tree.count);
        Assert.assertTrue(tree.root != null);
        System.out.println("root=" + tree.root);
        Assert.assertTrue(tree.root.key == 50);
        tree.inorder();
        tree.search(tree.root, 70);
    }
}
